package com.octoriz.abids.saarc.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.octoriz.abids.saarc.Entity.Name;
import com.octoriz.abids.saarc.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameHodler> {

    private List<Name> nameList = new ArrayList<>();

    @NonNull
    @Override
    public NameHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_list,parent,false);

        return new NameHodler(item);
    }

    @Override
    public void onBindViewHolder(@NonNull NameHodler holder, int position) {
        Name name = nameList.get(position);
        int num = position+1;

        holder.txtSerial.setText(num+" .");
        holder.txtName.setText(name.getName());
    }

    public void setNameList(List<Name> nameList){
        this.nameList = nameList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    class NameHodler extends RecyclerView.ViewHolder{

        private AppCompatTextView txtSerial, txtName;

        public NameHodler(@NonNull View itemView) {
            super(itemView);
            txtSerial = itemView.findViewById(R.id.txtSerial);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
