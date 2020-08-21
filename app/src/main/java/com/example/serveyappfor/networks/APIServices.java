package com.example.serveyappfor.networks;


import com.example.serveyappfor.pojo.Servey;
import com.example.serveyappfor.viewmodels.ServeyViewModel;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIServices {




    @GET("getSurvey")
    Call<List<Servey>> getServeyQuestion(@Header("time") String string);


}
