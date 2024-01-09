package com.kaushlendraprajapati.demo2app.Adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.kaushlendraprajapati.demo2app.HomeActivity;
import com.kaushlendraprajapati.demo2app.Modal.HomeModalClass;
import com.kaushlendraprajapati.demo2app.R;
import com.kaushlendraprajapati.demo2app.databinding.RawlayoutHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<HomeModalClass> homeModalClasses;

    public HomeAdapter(List<HomeModalClass> homeModalClasses) {
        this.homeModalClasses = homeModalClasses;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rawlayout_home,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        holder.name.setText(homeModalClasses.get(position).getName());
        holder.email.setText(homeModalClasses.get(position).getEmail());
        holder.mobile.setText(homeModalClasses.get(position).getMobile());
        holder.password.setText(homeModalClasses.get(position).getPassword());

    }

    @Override
    public int getItemCount() {
        return homeModalClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,email,mobile,password;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvName);
            email=itemView.findViewById(R.id.tvEmail);
            mobile=itemView.findViewById(R.id.tvMobile);
            password=itemView.findViewById(R.id.tvPassword);
        }
    }
}
