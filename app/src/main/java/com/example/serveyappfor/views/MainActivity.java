package com.example.serveyappfor.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.serveyappfor.R;
import com.example.serveyappfor.adapters.ServeyAdapter;
import com.example.serveyappfor.databinding.ActivityMainBinding;
import com.example.serveyappfor.models.SaveServey;
import com.example.serveyappfor.pojo.Servey;
import com.example.serveyappfor.viewmodels.SaveServeyViewModel;
import com.example.serveyappfor.viewmodels.ServeyViewModel;
import com.example.serveyappfor.views.fragments.QuestionFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    SaveServeyViewModel saveServeyViewModel;
    ServeyAdapter serveyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

       binding.btnAddserveyID.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(),ServeyQuestionActivity.class));
           }
       });
        saveServeyViewModel = new ViewModelProvider(this).get(SaveServeyViewModel.class);

        saveServeyViewModel.getAlldata().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<SaveServey>>() {
                    @Override
                    public void accept(List<SaveServey> serveys) throws Exception {

                        binding.rclServeylistID.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                        serveyAdapter=new ServeyAdapter(getApplicationContext(),serveys);
                        binding.rclServeylistID.setAdapter(serveyAdapter);
                    }
                });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}