package farida.moviesproject.model;

import java.io.Serializable;

/**
 * Created by Farida Alaaeldin on 19-Jul-17.
 */
public class Movie
{
        private String title;
        private String posterPath;
        private String overview;
        private int favorite;


        public Movie()
        {
            favorite =0;
        }

        public void setFavorite(){favorite=1;}

        public int getFavorite(){return favorite;}

        public void unFavorite(){favorite=0;}

        public void setName(String name)
        {
            this.title=name;
        }

        public void setPosterPath(String rate)
        {
            this.posterPath=rate;
        }

        public void setOverView(String desc)
        {
            this.overview=desc;
        }

        public String getName()
        {
            return title;
        }

        public String getPosterPath()
        {
            return posterPath;
        }

        public String getOverView()
        {
            return  overview;
        }

}


