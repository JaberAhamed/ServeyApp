package com.example.serveyappfor.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.serveyappfor.R;
import com.example.serveyappfor.databinding.ActivityServeyQuestionBinding;
import com.example.serveyappfor.pojo.Servey;
import com.example.serveyappfor.viewmodels.ServeyViewModel;
import com.example.serveyappfor.views.fragments.QuestionFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ServeyQuestionActivity extends AppCompatActivity {

    ActivityServeyQuestionBinding binding;
    ServeyViewModel serveyViewModel;
    List<Servey> serveys;
    List<Servey>partone = new ArrayList<>();
    List<Servey>parttwo=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_servey_question);
        serveyViewModel = new ViewModelProvider(this).get(ServeyViewModel.class);

        serveyViewModel.getServey().observe(this, new Observer<List<Servey>>() {
            @Override
            public void onChanged(List<Servey> serveyss) {
                binding.progressCircular.setVisibility(View.VISIBLE);


                for(int i=0;i<serveyss.size();i++){
                    if(serveyss.get(i).getType().equals("Checkbox")||serveyss.get(i).getType().equals("text")){
                        partone.add(serveyss.get(i));
                       /// Toast.makeText(ServeyQuestionActivity.this, String.valueOf(serveyss.size()+" "+partone.size()), Toast.LENGTH_SHORT).show();
                    }else {
                        parttwo.add(serveyss.get(i));
                    }

                }
                binding.progressCircular.setVisibility(View.GONE);
                QuestionFragment fragment = new QuestionFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("partone", (Serializable) partone);
                bundle.putSerializable("partwo", (Serializable) parttwo);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


                transaction.add(R.id.fgmt_question, fragment);
                transaction.commit();




            }
        });







    }
}