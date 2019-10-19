package farida.moviesproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import farida.moviesproject.adapter.Adapter;
import farida.moviesproject.data.MovieHelper;
import farida.moviesproject.model.Movie;
import farida.moviesproject.adapter.RecyclerViewClickListener;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener{

    boolean first=true;
    int globalPosition;
    Adapter adapter,fav;
    String sector="http://image.tmdb.org/t/p/w342/";
    List<Movie> myMovies;
    RecyclerView display;
    RequestQueue requestQueue;
    String url;
    MovieHelper movieHelper;
    Cursor row;
    List<Movie> favoriteList;
    int bool=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Movies");
        setContentView(R.layout.activity_main);


        globalPosition=0;
        display=(RecyclerView)findViewById(R.id.display);
        display.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        movieHelper=new MovieHelper(this);

        myMovies=new ArrayList<>();

       /*

favoriteList=new ArrayList<>();
        row=movieHelper.select();
        row.moveToFirst();
        for(int i=0;i<row.getCount();i++)
        {

            Movie temp=new Movie();
            temp.setName(row.getString(0));
            temp.setOverView(row.getString(1));
            temp.setPosterPath(row.getString(2));
            if (Integer.parseInt(row.getString(3))==1&&myMovies.get(i).getName()==temp.getName()) {
                temp.setFavorite();
                myMovies.get(i).setFavorite();
            }
            else
                temp.unFavorite();
            favoriteList.add(temp);


            row.moveToNext();
        }*/

        adapter=new Adapter(getApplicationContext(),myMovies,this);
        display.setAdapter(adapter);

        requestQueue= Volley.newRequestQueue(getApplicationContext());

       // display.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));

        url="https://api.themoviedb.org/3/discover/movie?api_key=69f8d44407d7b73a4103add4c76fccb6";
        getMovieData(url);





    }






    void getMovieData(String url)
    {
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray fileinput=response.getJSONArray("results");
                    for(int i=0;i<fileinput.length();i++){
                        JSONObject temp=fileinput.getJSONObject(i);
                        Movie movie=new Movie();
                        movie.setName(temp.getString("title"));
                        movie.setPosterPath(sector+temp.getString("poster_path"));
                        movie.setOverView(temp.getString("overview"));
                        myMovies.add(movie);
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        requestQueue.add(request);

    }
    @Override
    public void RecyclerViewListClicked(int position) {

        Intent myintent=new Intent(MainActivity.this,Main2Activity.class);

        globalPosition=position;

        if(bool==1)
        {
            myintent.putExtra("title",myMovies.get(position).getName());
            myintent.putExtra("overview",myMovies.get(position).getOverView());
            myintent.putExtra("posterpath",myMovies.get(position).getPosterPath());
            myintent.putExtra("favorite",myMovies.get(position).getFavorite());
        }
        else
        {

                myintent.putExtra("title",favoriteList.get(position).getName());
                myintent.putExtra("overview",favoriteList.get(position).getOverView());
                myintent.putExtra("posterpath",favoriteList.get(position).getPosterPath());
                myintent.putExtra("favorite",favoriteList.get(position).getFavorite());
        }

        startActivity(myintent);
    }


    @Override
    protected void onStart() {
        super.onStart();
if(first==true)
{
    first=false;
    return;
}

        row=movieHelper.select();
        row.moveToFirst();
        for(int i=0;i<row.getCount();i++) {


           if (myMovies.get(i).getName().equals(row.getString(0)))
           { if (Integer.parseInt(row.getString(3)) == 1)
                myMovies.get(i).setFavorite();
            else
                myMovies.get(i).unFavorite();}


            row.moveToNext();
        }
      /*  row=movieHelper.select();
        row.moveToFirst();
        for(int i=0;i<globalPosition;i++)
        {
           if( Integer.parseInt(row.getString(3))==1)
            {
                 myMovies.get(globalPosition).setFavorite();

            }
            row.moveToNext();
        }
*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();


        if(id==R.id.allMovies)
        {

            bool=1;
            setTitle("Movies");
            display.setAdapter(adapter);
        }
        else
        {
            bool=0;
            setTitle("Favorites");
            favoriteList=new ArrayList<>();
            row=movieHelper.select();
            row.moveToFirst();
            for(int i=0;i<row.getCount();i++)
            {

                Movie temp=new Movie();
                temp.setName(row.getString(0));
                temp.setOverView(row.getString(1));
                temp.setPosterPath(row.getString(2));
                if (Integer.parseInt(row.getString(3))==1)
                    temp.setFavorite();
                else
                    temp.unFavorite();
                favoriteList.add(temp);




                row.moveToNext();
            }
          fav=new Adapter(getApplicationContext(),favoriteList,this);
            display.setAdapter(fav);
            adapter.notifyDataSetChanged();

        }

        return super.onOptionsItemSelected(item);
    }
}
