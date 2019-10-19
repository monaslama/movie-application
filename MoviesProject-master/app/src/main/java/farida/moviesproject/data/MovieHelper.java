package farida.moviesproject.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Farida Alaaeldin on 24-Jul-17.
 */
public class MovieHelper extends SQLiteOpenHelper {

    public static final int Version = 3;
    public static final String Database_Name="Movies.db";

    public MovieHelper(Context context)
    {
        super(context,Database_Name,null,Version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     sqLiteDatabase.execSQL("CREATE TABLE "+MovieSchema.FavoriteMovies.tableName+" ( " +
             MovieSchema.FavoriteMovies.tableColumnTitle+" Text , " +
             MovieSchema.FavoriteMovies.tableColumnOverview+" Text , " +
             MovieSchema.FavoriteMovies.tableColumnImageLink+" Text , " +
             MovieSchema.FavoriteMovies.tableColumnFavorite+" Text " +
             ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieSchema.FavoriteMovies.tableName);
        onCreate(sqLiteDatabase);
    }

    public void insert(String title,String overview,String imageLink,String favorite)
    {
        SQLiteDatabase DB=getWritableDatabase();
        ContentValues row=new ContentValues();
        row.put(MovieSchema.FavoriteMovies.tableColumnTitle,title);
        row.put(MovieSchema.FavoriteMovies.tableColumnOverview,overview);
        row.put(MovieSchema.FavoriteMovies.tableColumnImageLink,imageLink);
        row.put(MovieSchema.FavoriteMovies.tableColumnFavorite,favorite);

        DB.insert(MovieSchema.FavoriteMovies.tableName,null,row);
    }

    public void delete(String title)
    {
        SQLiteDatabase db=getReadableDatabase();
        String selection=MovieSchema.FavoriteMovies.tableColumnTitle+" LIKE ?";
        String selectionArguments[]={title};
        db.delete(MovieSchema.FavoriteMovies.tableName,selection,selectionArguments);
    }

    public Cursor select()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c;
        c= db.query(MovieSchema.FavoriteMovies.tableName,new String[]{MovieSchema.FavoriteMovies.tableColumnTitle,MovieSchema.FavoriteMovies.tableColumnOverview,MovieSchema.FavoriteMovies.tableColumnImageLink,MovieSchema.FavoriteMovies.tableColumnFavorite},null,null,null,null,null);
        return c;
    }

}
