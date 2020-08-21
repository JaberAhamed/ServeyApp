package com.example.serveyappfor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.serveyappfor.R;
import com.example.serveyappfor.databinding.LayoutAdapterBinding;
import com.example.serveyappfor.models.SaveServey;
import com.example.serveyappfor.pojo.Servey;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;

public  class ServeyAdapter extends RecyclerView.Adapter<ServeyAdapter.ViewHolder> {

    private Context context;
    private List<SaveServey> sarveylist;




    public ServeyAdapter(Context context, List<SaveServey> sarveylist) {
        this.context = context;
        this.sarveylist = sarveylist;
    }

    @NonNull
    @Override
    public ServeyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutAdapterBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_adapter, parent, false);
        return new ServeyAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ServeyAdapter.ViewHolder holder, final int position) {



        holder.binding.tvCheck.setText("Product :"+sarveylist.get(position).getCheck());
        holder.binding.tvText.setText("Address : "+ sarveylist.get(position).getText());
        holder.binding.tvDrop.setText("FrequentProduct : "+ sarveylist.get(position).getDrop());
        holder.binding.tvMult.setText("Health :"+ sarveylist.get(position).getMult());
        holder.binding.tvNum.setText("number :"+ sarveylist.get(position).getNum());



    }

    @Override
    public int getItemCount() {
        return sarveylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LayoutAdapterBinding binding;

        public ViewHolder(@NonNull LayoutAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
