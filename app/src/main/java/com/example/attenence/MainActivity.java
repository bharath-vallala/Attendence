package com.example.attenence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.attenence.models.*;
import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!shared_pref.getLoggedStatus(getApplicationContext())){

            Intent intent=new Intent(this,Login_Act.class);
            startActivity(intent);
        }


        EventBus.getDefault().post(new EventBus_model("1") );
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_view);
        NavController navController= Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        Log.d("name_Context", "onCreate: "+shared_pref.getName(getApplicationContext()));
    }


}
