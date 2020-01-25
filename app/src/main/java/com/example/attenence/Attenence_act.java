package com.example.attenence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.attenence.models.EventBus_model;
import com.example.attenence.models.PostAttenence;
import com.example.attenence.models.dataModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.example.attenence.adapters.*;
import com.example.attenence.models.*;
import javax.security.auth.Subject;

public class Attenence_act extends AppCompatActivity {
RecyclerView recyclerView;
List<dataModel> dataModels;
String sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attenence_act);
        recyclerView =findViewById(R.id.recyclerView_Attendence);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Button button=findViewById(R.id.done);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Attenence_act.this,MainActivity.class));
            }
        });
        String s=getIntent().getStringExtra("sem");

        String ss=getIntent().getStringExtra("subject");
        Log.d("justs", "onCreate: "+s+" "+ss);
        Log.d("sssss", "onCreate: "+getsem(s,ss));
sub=getsem(s,ss);
       getData(s);
       //postdata(getsem(s,ss));

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(EventBus_model model){

        Log.d("bharath", "onMessage: ");

    }



    public  void getData(String sem){
        final Call<List<dataModel>> dataModelCall=apiconn.getService().getData(sem);

        dataModelCall.enqueue(new Callback<List<dataModel>>() {
            @Override
            public void onResponse(Call<List<dataModel>> call, Response<List<dataModel>> response) {

                List<dataModel> dataModels=new ArrayList<>();
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {

                        dataModel dataModel = response.body().get(i);
                        Log.d("message1", "onResponse: " + dataModel.getStudentName());
                        Log.d("message1", "onResponse: " + dataModel.getStudentRoll());
                        Log.d("message1", "onResponse: " + dataModel.getFatherName());

                    }
                    recyclerView.setAdapter(new adendence_adapter(response.body(), getApplicationContext(), sub));
                }else{
                    Log.d("geterror", "onResponse: "+response.errorBody() );
                }

            }

            @Override
            public void onFailure(Call<List<dataModel>> call, Throwable t) {
                Log.e("error1", "onFailure: ",t );
            }
        });



    }
    public  void postdata(String sub){



          final String  url="https://guarded-harbor-72259.herokuapp.com/msc/";
          Attenence_act.list list=null;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

//         OkHttpClient.Builder httpClient = new OkHttpClient.Builder();



            Retrofit.Builder builder =
                    new Retrofit.Builder()
                            .baseUrl(url)
                            .addConverterFactory(
                                    GsonConverterFactory.create()
                            );
            Retrofit retrofit =
                    builder
                            .client(
                                    client
                            )
                            .build();

            list =  retrofit.create(Attenence_act.list.class);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate= formatter.format(date);
        strDate="'"+strDate+"'";

        Log.d("strdate", "postdata: "+strDate);


        Call<PostAttenence> postAttenenceCall=list.postData(new PostAttenence(1232,0,strDate),sub);
        postAttenenceCall.enqueue(new Callback<PostAttenence>() {
            @Override
            public void onResponse(Call<PostAttenence> call, Response<PostAttenence> response) {

                                   Log.d("resp", "onResponse: "+response.body().getSubject());

            }

            @Override
            public void onFailure(Call<PostAttenence> call, Throwable t) {
                Log.e("ourerror", "onFailure: ",t );
            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(Attenence_act.this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(Attenence_act.this);
    }


    public interface list{


        @POST("upate/{Subject_1}")
        Call<PostAttenence> postData(@Body PostAttenence postModel, @Path("Subject_1") String Subject);


    }

    public String getsem(String sem, String Sub) {
        subjects subjects=new subjects();
     String   subb="Subject_";
        for (int i = 0; i <subjects.getSem1().length ; i++) {

            if(sem.equals("1")&&Sub.equals(subjects.getSem1()[i])){

                return subb+String.valueOf(i+1);
            }

        }
        for (int i = 0; i <subjects.getSem2().length ; i++) {

            if(sem.equals("2")&&Sub.equals(subjects.getSem2()[i])){

                return subb+String.valueOf(i+1);
            }


        }
        for (int i = 0; i <subjects.getSem3().length ; i++) {

            if(sem.equals("3")&&Sub.equals(subjects.getSem3()[i])){

                return subb+String.valueOf(i+1);
            }

        }
        for (int i = 0; i <subjects.getSem4().length ; i++) {

            if(sem.equals("4")&&Sub.equals(subjects.getSem4()[i])){

                return subb+String.valueOf(i+1);
            }

        }


        return "failed";
    }

}
