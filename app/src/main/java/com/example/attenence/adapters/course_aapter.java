package com.example.attenence.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attenence.Attendence_frag;
import com.example.attenence.Attenence_act;
import com.example.attenence.R;
import com.example.attenence.models.subjects;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class course_aapter extends RecyclerView.Adapter<course_aapter.ViewHolder> {


    ArrayList<String> arrayList;
    Context context;
    View view;
    int sem;
    FragmentActivity fragmentActivity;

    public course_aapter(ArrayList<String> arrayList, Context context,View view,FragmentActivity fragmentActivity) {
        this.arrayList = arrayList;
        this.context = context;
        this.view=view;
        this.fragmentActivity=fragmentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_lay, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.textView.setText(arrayList.get(position));
holder.materialCardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(context,"future update",Toast.LENGTH_SHORT).show();

                    }
});
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

TextView textView;
CardView materialCardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.course_id);
            materialCardView=itemView.findViewById(R.id.course_card);
        }
    }
}
