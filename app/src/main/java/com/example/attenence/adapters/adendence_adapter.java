package com.example.attenence.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.attenence.Attenence_act;
import com.example.attenence.R;
import  com.example.attenence.models.*;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import  com.example.attenence.*;

public class adendence_adapter extends RecyclerView.Adapter<adendence_adapter.ViewHolder>  {


    List<dataModel> dataModels;
    Context context;
    String sub;
RecyclerView recyclerView;
    public adendence_adapter(List<dataModel> dataModels,Context context,String sub) {
        this.context=context;
        this.dataModels = dataModels;
        this.sub=sub;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rec_layout_att, parent, false);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.student_name.setText(dataModels.get(position).getStudentName());
        holder.Roll_Num.setText(String.valueOf(dataModels.get(position).getStudentRoll()));
        holder.Father_Name.setText(dataModels.get(position).getFatherName());

        holder.present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("presentData", "onClick: "+sub+dataModels.get(position).getAttendenceId()+1);

                postdata(sub,dataModels.get(position).getAttendenceId(),1);
                Transition transition = new Fade();
                transition.setDuration(1000);
                transition.addTarget(R.id.send_message);
                transition.addTarget(R.id.button_absent);
                transition.addTarget(R.id.button_present);

                TransitionManager.beginDelayedTransition(recyclerView, transition);
                holder.send.setVisibility( View.GONE);
                holder.absent.setVisibility(View.GONE);
                holder.present.setVisibility(View.GONE);



            }
        });
        holder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AbsentData", "onClick: "+sub+dataModels.get(position).getAttendenceId()+1);

                postdata(sub,dataModels.get(position).getAttendenceId(),0);

                               Transition transition = new Fade();
                transition.setDuration(1000);
                transition.addTarget(R.id.send_message);
                transition.addTarget(R.id.button_absent);
                transition.addTarget(R.id.button_present);

                TransitionManager.beginDelayedTransition(recyclerView, transition);
                holder.send.setVisibility( View.VISIBLE);
                holder.absent.setVisibility(View.GONE);
                holder.present.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        this.recyclerView=recyclerView;
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
TextView student_name;
TextView Roll_Num;
TextView Father_Name;
Button present;
Button absent;
Button send;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            student_name=itemView.findViewById(R.id.stuent_name);
            Roll_Num=itemView.findViewById(R.id.roll_num);
            Father_Name=itemView.findViewById(R.id.father_name);
            present=itemView.findViewById(R.id.button_present);
            absent=itemView.findViewById(R.id.button_absent);
            send=itemView.findViewById(R.id.send_message);
        }
    }

    public  void postdata(String sub,int id,int status){



//        final String  url="https://guarded-harbor-72259.herokuapp.com/msc/";
//        Attenence_act.list list=null;
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//
////         OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//
//
//
//        Retrofit.Builder builder =
//                new Retrofit.Builder()
//                        .baseUrl(url)
//                        .addConverterFactory(
//                                GsonConverterFactory.create()
//                        );
//        Retrofit retrofit =
//                builder
//                        .client(
//                                client
//                        )
//                        .build();
//
//        list =  retrofit.create(Attenence_act.list.class);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate= formatter.format(date);
        strDate="'"+strDate+"'";

        Log.d("strdate", "postdata: "+strDate);


        Call<PostAttenence> postAttenenceCall=apiconn.getService().postData(new PostAttenence(id,status,strDate),sub);
        postAttenenceCall.enqueue(new Callback<PostAttenence>() {
            @Override
            public void onResponse(Call<PostAttenence> call, Response<PostAttenence> response) {
if (response.isSuccessful()) {
    Log.d("resp", "onResponse: " + response.body().getSubject());
}else {

    Log.d("posterror", "onResponse: "+response.errorBody().toString());
}

            }

            @Override
            public void onFailure(Call<PostAttenence> call, Throwable t) {
                Log.e("ourerror", "onFailure: ",t );
            }
        });



    }

}
