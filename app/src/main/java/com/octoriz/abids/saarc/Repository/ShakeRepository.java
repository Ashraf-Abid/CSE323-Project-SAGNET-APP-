package com.octoriz.abids.saarc.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.octoriz.abids.saarc.Dao.ShakeDao;
import com.octoriz.abids.saarc.Database.ShakeDatabase;
import com.octoriz.abids.saarc.Entity.Country;
import com.octoriz.abids.saarc.Entity.Name;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ShakeRepository {

    private ShakeDao shakeDao;

    private LiveData<List<Name>> allNamesById;
    private LiveData<List<Name>> allNames;
    private LiveData<List<Country>> allCountries;

    public ShakeRepository(Application application){
        ShakeDatabase database = ShakeDatabase.getInstance(application);
        shakeDao = database.shakeDao();
        allCountries = shakeDao.getAllCountry();
        allNames = shakeDao.getAllNames();
    }

    //livedata by default runs in background so no async is needed
    public LiveData<List<Country>> getAllCountriesLive(){return allCountries;}
    public LiveData<List<Name>> getAllNames(){return allNames;}

    public LiveData<List<Name>> getAllNamesById(int id){
        allNamesById = shakeDao.getAllNamesById(id);
        return allNamesById;
    }

    public void insertName(Name name){
        new InserNameAsync(shakeDao).execute(name);
    }

    public void deleteAllNames(){new DeleteAllNamesAsync(shakeDao).execute();}


    //static so doesn't have a ref to repository class, so no memery leak
    //asynctask takes 3 peram, 1=param passed to asynch, 2=progress, 3=return result type we get back
    private static class InserNameAsync extends AsyncTask<Name, Void, Void>{

        private ShakeDao shakeDao;

        private InserNameAsync(ShakeDao shakeDao){
            this.shakeDao = shakeDao;
        }

        @Override
        protected Void doInBackground(Name... names) {
            shakeDao.insertName(names[0]);
            return null;
        }
    }

    //delete all names
    private static class DeleteAllNamesAsync extends AsyncTask<Void, Void, Void>{

        private ShakeDao shakeDao;

        private DeleteAllNamesAsync(ShakeDao shakeDao){
            this.shakeDao = shakeDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            shakeDao.deleteAllNames();
            return null;
        }
    }
}
