package com.example.serveyappfor.views.fragments;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.serveyappfor.R;
import com.example.serveyappfor.databinding.LayoutFragmentpartoneBinding;
import com.example.serveyappfor.models.Serveyinfo;
import com.example.serveyappfor.pojo.Servey;
import com.example.serveyappfor.utils.ServeyShareprefarence;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class QuestionFragment extends Fragment {

    LayoutFragmentpartoneBinding binding;
    ServeyShareprefarence serveyShareprefarence;
 

    String check="";


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        binding = DataBindingUtil.inflate(inflater, R.layout.layout_fragmentpartone, container, false);
        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
       // userInforSharePreference = new UserInforSharePreference(sharedPreferences
        //serveys = (List<Servey>) getActivity().getIntent().getSerializableExtra("partone");


        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        serveyShareprefarence =new ServeyShareprefarence(sharedPreferences);
        binding.prgbarId.setVisibility(View.VISIBLE);
        String pre = getArguments().getString("pre");
        if(pre!=null){
            if(pre.equals("previous")){
                if(ServeyShareprefarence.getCheck().equals("Yes")){
                    binding.cboxyseID.setChecked(true);
                }else {
                    binding.cbxnoId.setChecked(false);
                }
                binding.etAddressId.setText(ServeyShareprefarence.getText());
            }
        }

        final List<Servey> serveys = (List<Servey>) getArguments().getSerializable("partone");
        final List<Servey> parttwo  = (List<Servey>) getArguments().getSerializable("partwo");



        List<Servey> serveys1 = sortlist(serveys);

            binding.tvCheckproductID.setText("1. "+serveys1.get(0).getQuestion());
            String[]str =  serveys1.get(0).getOptions().split(",");
            binding.cboxyseID.setText(str[0]);
            binding.cbxnoId.setText(str[1]);

            binding.cboxyseID.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(binding.cboxyseID.isChecked()){
                        binding.cboxyseID.setChecked(true);
                        check="Yes";
                        binding.cbxnoId.setChecked(false);
                    }else {
                        binding.cboxyseID.setChecked(false);
                    }


                }
            });

        binding.cbxnoId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(binding.cbxnoId.isChecked()){
                    binding.cbxnoId.setChecked(true);
                    check="No";

                    binding.cboxyseID.setChecked(false);
                }else {

                    binding.cbxnoId.setChecked(false);
                }


            }
        });



        binding.tvAddressID.setText("2. "+serveys1.get(1).getQuestion());
        binding.prgbarId.setVisibility(View.GONE);



        binding.btnNextID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getContext(), String.valueOf(parttwo.size()), Toast.LENGTH_SHORT).show();

                if(check.equals("")||binding.etAddressId.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "You have to fill-up full form", Toast.LENGTH_SHORT).show();
                }else {
                    ServeyShareprefarence.saveserveyInfo(new Serveyinfo(check,binding.etAddressId.getText().toString()));
                    QuestionFragmentpartTwo fragment = new QuestionFragmentpartTwo();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("partwo", (Serializable) parttwo);
                    bundle.putSerializable("partone", (Serializable) serveys);
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.fgmt_question, fragment);
                    transaction.commit();
                }

            }
        });



        return binding.getRoot();

    }



    private List<Servey> sortlist(List<Servey> serveys) {
        List<Servey> servey = new ArrayList<>();
        if(serveys.get(0).getType().equals("Checkbox")){
            servey.add(serveys.get(0));
            servey.add(serveys.get(1));

        }else {
            servey.add(serveys.get(1));
            servey.add(serveys.get(0));

        }
        return servey;
    }
}
