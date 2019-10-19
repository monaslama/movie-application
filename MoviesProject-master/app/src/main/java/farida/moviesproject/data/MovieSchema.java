package farida.moviesproject.data;

/**
 * Created by Farida Alaaeldin on 24-Jul-17.
 */
public class MovieSchema {


        public class FavoriteMovies
        {
            public static final String tableName="favoriteMovies";
            public static final String tableColumnTitle="Title";
            public static final String tableColumnOverview="Overview";
            public static final String tableColumnImageLink="ImageLink";
            public static final String tableColumnFavorite="Favorite";
        }

        public class AllMovies
        {
            public static final String tableName="allMovies";
            public static final String tableColumnTitle="Title";
            public static final String tableColumnOverview="Overview";
            public static final String tableColumnImageLink="ImageLink";
            public static final String tableColumnFavorite="Favorite";
        }


}
