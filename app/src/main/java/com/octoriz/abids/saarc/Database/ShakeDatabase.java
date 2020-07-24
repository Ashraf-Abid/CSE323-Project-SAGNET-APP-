package com.octoriz.abids.saarc.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.octoriz.abids.saarc.Dao.ShakeDao;
import com.octoriz.abids.saarc.Entity.Country;
import com.octoriz.abids.saarc.Entity.Name;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Country.class, Name.class}, version = 1, exportSchema = false)
public abstract class ShakeDatabase extends RoomDatabase {

    //singletoon class, can't create multiple instance of it
    private static ShakeDatabase instance;

    //is used to access our dao, room will handle everything
    public abstract ShakeDao shakeDao();

    //singleton database
    //use only one thread at a time

    public static synchronized ShakeDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ShakeDatabase.class,"shake_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void,Void>{

        private ShakeDao shakeDao;

        private PopulateDbAsyncTask(ShakeDatabase db){
            shakeDao = db.shakeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            shakeDao.insertCountry(new Country("Afganistan"));
            shakeDao.insertCountry(new Country("Bangladesh"));
            shakeDao.insertCountry(new Country("Bhutan"));
            shakeDao.insertCountry(new Country("India"));
            shakeDao.insertCountry(new Country("Maldives"));
            shakeDao.insertCountry(new Country("Nepal"));
            shakeDao.insertCountry(new Country("Pakistan"));
            shakeDao.insertCountry(new Country("Srilanka"));
            shakeDao.insertCountry(new Country("Country 1"));
            shakeDao.insertCountry(new Country("Country 2"));
            shakeDao.insertCountry(new Country("Country 3"));
            shakeDao.insertCountry(new Country("Country 4"));
            shakeDao.insertCountry(new Country("Country 5"));
            shakeDao.insertCountry(new Country("Country 6"));

            return null;
        }
    }

}
