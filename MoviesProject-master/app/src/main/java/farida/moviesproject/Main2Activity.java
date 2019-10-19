package farida.moviesproject;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import farida.moviesproject.data.MovieHelper;

public class Main2Activity extends AppCompatActivity {

    String title,overview,imageURL;
    TextView outTitle,outOverview;
    ImageView outImage;
    Button favorite;
    int clicked=0;
    int favoriteStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Details");
        setContentView(R.layout.activity_main2);

        Bundle data=getIntent().getExtras();
        title=data.getString("title");
        overview=data.getString("overview");
        imageURL=data.getString("posterpath");
        favoriteStatus=data.getInt("favorite");

        outTitle=(TextView)findViewById(R.id.movieTitleOutput);
        outOverview=(TextView)findViewById(R.id.overviewOutput);
        outImage=(ImageView)findViewById(R.id.displayImage);
        favorite=(Button)findViewById(R.id.heartButton);

        outTitle.setText(title);
        outOverview.setText(overview);
        Picasso.with(getApplicationContext()).load(imageURL).into(outImage);

        if(favoriteStatus==1)
            favorite.setTextColor(Color.RED);
        else
            favorite.setTextColor(Color.BLACK);


        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MovieHelper movieHelper=new MovieHelper(Main2Activity.this);
                if (clicked==0)
                {
                    clicked=1;
                    favorite.setTextColor(Color.RED);
                    movieHelper.insert(title,overview,imageURL,"1");
                }
                else
                {
                    clicked=0;
                    favorite.setTextColor(Color.BLACK);
                    movieHelper.delete(title);
                }



            }
        });



    }
}
