package com.example.serveyappfor.rooms;

import com.example.serveyappfor.models.SaveServey;
import com.example.serveyappfor.pojo.Servey;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public interface ServeyDao {

    @Insert
    long insert(SaveServey saveServey);


    @Query("SELECT * FROM SaveServey")
    Flowable<List<SaveServey>> getData();
}
