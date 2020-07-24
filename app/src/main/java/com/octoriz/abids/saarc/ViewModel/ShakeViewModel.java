package com.octoriz.abids.saarc.ViewModel;

import android.app.Application;

import com.octoriz.abids.saarc.Entity.Country;
import com.octoriz.abids.saarc.Entity.Name;
import com.octoriz.abids.saarc.Repository.ShakeRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ShakeViewModel extends AndroidViewModel {

    private ShakeRepository shakeRepository;
    private LiveData<List<Name>> nameList;
    private LiveData<List<Name>> nameListAll;
    private LiveData<List<Country>> countryList;

    public ShakeViewModel(@NonNull Application application) {
        super(application);
        shakeRepository = new ShakeRepository(application);
        countryList = shakeRepository.getAllCountriesLive();
        nameListAll = shakeRepository.getAllNames();
    }

    //wrapper methods
    //as our activity can't directly access repository
    //it can only be accessed by ViewModel heheheheh....

    public void insert(Name name){
        shakeRepository.insertName(name);
    }

    public void deleteAllNames(){
        shakeRepository.deleteAllNames();
    }

    public LiveData<List<Country>> getCountryList(){
        return countryList;
    }
    public LiveData<List<Name>> getAllNames(){return nameListAll;}

    public LiveData<List<Name>> getNameList(int id){
        nameList = shakeRepository.getAllNamesById(id);
        return nameList;
    }

}
