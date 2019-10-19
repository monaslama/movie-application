package farida.moviesproject.adapter;

import android.content.Context;
import android.graphics.Movie;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import farida.moviesproject.R;


/**
 * Created by Farida Alaaeldin on 19-Jul-17.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.Holder>{
    Context context;
    List<farida.moviesproject.model.Movie> movie;
    RecyclerViewClickListener itemListener;


    public Adapter(Context c , List<farida.moviesproject.model.Movie> m,RecyclerViewClickListener r)
    {
        this.context=c;
        this.movie=m;
        this.itemListener=r;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.item ,parent ,false);
        Holder myHolder=new Holder(row);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String s=movie.get(position).getName();
        String imageURL=movie.get(position).getPosterPath();
        Picasso.with(context).load(imageURL).into(holder.beauty);
    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
      ImageView beauty;


        public Holder(View itemView) {
            super(itemView);
            beauty=(ImageView)itemView.findViewById(R.id.photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
           itemListener.RecyclerViewListClicked(this.getLayoutPosition());
        }

        public void bind (farida.moviesproject.model.Movie movie)
        {
            Picasso.with(context).load(Uri.parse(movie.getPosterPath())).into(beauty);
        }
    }

}

