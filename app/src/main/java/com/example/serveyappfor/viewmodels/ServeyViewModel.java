package com.example.serveyappfor.viewmodels;

import android.app.Application;

import com.example.serveyappfor.pojo.Servey;
import com.example.serveyappfor.repos.ServeyRepo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ServeyViewModel extends AndroidViewModel {

    private ServeyRepo serveyrepo;
    public ServeyViewModel(@NonNull Application application) {
        super(application);
        serveyrepo = new ServeyRepo(application);
    }

    public MutableLiveData<List<Servey>> getServey(){

        return serveyrepo.getServey();
    }
}
