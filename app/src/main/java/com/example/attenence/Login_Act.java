package com.example.attenence;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attenence.models.LoginData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.attenence.models.*;
import com.victor.loading.newton.NewtonCradleLoading;

public class Login_Act extends AppCompatActivity {
    NewtonCradleLoading newtonCradleLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        newtonCradleLoading=findViewById(R.id.loader_id);

        if (shared_pref.getLoggedStatus(getApplicationContext())){

            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }


        Button b=findViewById(R.id.Login_button);
        final EditText email=findViewById(R.id.email);
        final EditText pass=findViewById(R.id.password);
        Log.d("email data", "onCreate: "+email.getText().toString()+" "+pass.getText().toString());

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newtonCradleLoading.setVisibility(View.VISIBLE);
                newtonCradleLoading.start();
                //newtonCradleLoading.setLoadingColor(R.color.white);

                Call<List<LoginData>> listCall=apiconn.getLogin().Login(email.getText().toString(),pass.getText().toString());

                listCall.enqueue(new Callback<List<LoginData>>() {
                    @Override
                    public void onResponse(Call<List<LoginData>> call, Response<List<LoginData>> response) {
                        if (response.isSuccessful()){

                            shared_pref.setName(getApplicationContext(),response.body().get(0).getTName().toString());
                            shared_pref.setEmail(getApplicationContext(),response.body().get(0).getEmail().toString());
                            shared_pref.setPhoneNumber(getApplicationContext(),response.body().get(0).getPhoneNumber());
                            shared_pref.setDept(getApplicationContext(),response.body().get(0).getDept().toString());
                            shared_pref.setLoggedIn(getApplicationContext(),true);

                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);


                            Log.d("logindata", "onResponse: "+response.body().get(0).getTName());
                        }else {
                            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                            newtonCradleLoading.stop();
                            newtonCradleLoading.setVisibility(View.GONE);
                            Log.d("login failed", "onResponse: ");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<LoginData>> call, Throwable t) {
                        Log.e("login error", "onFailure: ",t );
                    }
                });

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        newtonCradleLoading.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newtonCradleLoading.stop();
    }
}
