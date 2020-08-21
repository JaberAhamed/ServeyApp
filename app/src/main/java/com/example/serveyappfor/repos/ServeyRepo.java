package com.example.serveyappfor.repos;

import android.app.Application;

import com.example.serveyappfor.networks.APIServices;
import com.example.serveyappfor.networks.RetrofitInstance;
import com.example.serveyappfor.pojo.Servey;
import com.example.serveyappfor.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public  class ServeyRepo {
    private MutableLiveData<List<Servey>> liveData;
    private Application application;
    private APIServices services;

    public ServeyRepo(Application application) {
        this.application = application;
        services = RetrofitInstance.getRetrofitInstance().create(APIServices.class);
    }

    public MutableLiveData<List<Servey>> getServey(){

        liveData = new MutableLiveData<>();
        final List<Servey> serveys = new ArrayList<>();

        Call<List<Servey>> service_response = services.getServeyQuestion(Constant.TIME);

        service_response.enqueue(new Callback<List<Servey>>() {
            @Override
            public void onResponse(Call<List<Servey>> call, Response<List<Servey>> response) {


                List<Servey> classDetailsList = new ArrayList<>();







                    if (response.code() == 200) {
                        if (response.body()!=null) {
                            classDetailsList.addAll(response.body());
                            liveData.postValue(classDetailsList);
                        } else {
                            liveData.postValue(classDetailsList);
                        }
                    }

                else {
                    liveData.postValue(classDetailsList);
                }

            }

            @Override
            public void onFailure(Call<List<Servey>> call, Throwable t) {

                liveData.postValue(serveys);


            }
        });

        return liveData;

    }


}
