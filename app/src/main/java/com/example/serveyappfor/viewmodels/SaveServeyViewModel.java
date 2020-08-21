package com.example.serveyappfor.viewmodels;

import android.app.Application;

import com.example.serveyappfor.models.SaveServey;
import com.example.serveyappfor.pojo.Servey;
import com.example.serveyappfor.rooms.Appdatabase;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;

public class SaveServeyViewModel extends AndroidViewModel {

    Appdatabase appDatabase;



    public SaveServeyViewModel(@androidx.annotation.NonNull Application application) {
        super(application);
        appDatabase= Appdatabase.getDatabase(application);
    }

    public long insert  (SaveServey saveServey) {
        return appDatabase.serveyDao().insert(saveServey);
    }

    public Flowable<List<SaveServey>> getAlldata(){
        return  appDatabase.serveyDao().getData();
    }
}
