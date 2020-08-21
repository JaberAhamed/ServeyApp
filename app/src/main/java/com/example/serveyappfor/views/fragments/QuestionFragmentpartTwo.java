package com.example.serveyappfor.views.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.serveyappfor.R;
import com.example.serveyappfor.databinding.LayoutFragmentpartoneBinding;
import com.example.serveyappfor.databinding.LayoutQuestionParttwoBinding;
import com.example.serveyappfor.models.SaveServey;
import com.example.serveyappfor.models.Serveyinfo;
import com.example.serveyappfor.pojo.Servey;
import com.example.serveyappfor.utils.ServeyShareprefarence;
import com.example.serveyappfor.viewmodels.SaveServeyViewModel;
import com.example.serveyappfor.views.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class QuestionFragmentpartTwo extends Fragment {
    LayoutQuestionParttwoBinding binding;
    ServeyShareprefarence serveyShareprefarence;

    String q3="";
    String q4="";

    SaveServeyViewModel saveServeyViewModel;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        binding = DataBindingUtil.inflate(inflater, R.layout.layout_question_parttwo, container, false);
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        saveServeyViewModel = new ViewModelProvider(getActivity()).get(SaveServeyViewModel.class);

        final List<Servey>serveys = (List<Servey>) getArguments().getSerializable("partwo");
        final List<Servey> partone = (List<Servey>) getArguments().getSerializable("partone");



        Collections.sort(serveys, new Comparator<Servey>() {
            @Override
            public int compare(Servey u1, Servey u2) {
                return u1.getType().compareTo(u2.getType());
            }
        });

        binding.tvDropID.setText("3. "+serveys.get(0).getQuestion());
        final String[]option = serveys.get(0).getOptions().split(",");
        ArrayAdapter aa = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,option);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnMultichoise.setAdapter(aa);
        
        binding.spnMultichoise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                q3=option[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        
        

        binding.tvMultichoiseID.setText("4. "+serveys.get(1).getQuestion());
        String[]option1 = serveys.get(1).getOptions().split(",");

        binding.rd1.setText(option1[0]);
        binding.rd2.setText(option1[1]);
        binding.rd3.setText(option1[2]);
        binding.rd4.setText(option1[3]);
        binding.rd5.setText(option1[4]);

        binding.rdgroupID.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
               if(i == R.id.rd_1){
                   q4 = binding.rd1.getText().toString();

               }else if (i==R.id.rd_2){
                   q4 = binding.rd2.getText().toString();
               }else if(i==R.id.rd_3){
                   q4 = binding.rd3.getText().toString();
               }else if(i==R.id.rd_4){
                   q4 = binding.rd4.getText().toString();
               }else if(i==R.id.rd_5){
                   q4 = binding.rd5.getText().toString();
               }
            }
        });

        binding.tvNumber.setText("5. "+serveys.get(2).getQuestion());

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        serveyShareprefarence =new ServeyShareprefarence(sharedPreferences);






        binding.btnDoneID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(q3.equals("")||q4.equals("")){
                    Toast.makeText(getContext(), "You have to ans no 3 and 4", Toast.LENGTH_SHORT).show();
                }else {

                    Completable.fromAction(new Action() {
                        @Override
                        public void run() throws Exception {



                            saveServeyViewModel.insert(new SaveServey(ServeyShareprefarence.getCheck(),ServeyShareprefarence.getText(),q3,q4,binding.etNmberId.getText().toString()));


                        }
                    }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {

                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();


                        }

                        @Override
                        public void onComplete() {


                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });


                }

            }
        });

        binding.btnPreviousID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionFragment fragment = new QuestionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("pre","previous");
                bundle.putSerializable("partone", (Serializable) partone);
                bundle.putSerializable("partwo", (Serializable) serveys);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();


                transaction.add(R.id.fgmt_question, fragment);
                transaction.commit();
            }
        });








        return binding.getRoot();

    }




    private List<Servey> sortlist(List<Servey> serveys) {
        List<Servey> servey = new ArrayList<>();
        for(int i=0;i<serveys.size();i++){
            if(serveys.get(i).getType().equals("dropdown")){
                servey.set(0,serveys.get(i));

            }
        }
        if(serveys.get(0).getType().equals("dropdown")){
            servey.add(serveys.get(0));
            servey.add(serveys.get(1));

        }else {
            servey.add(serveys.get(1));
            servey.add(serveys.get(0));

    }



        return servey;
    }
}
