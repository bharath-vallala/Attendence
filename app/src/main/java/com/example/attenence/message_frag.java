package com.example.attenence;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;
import com.example.attenence.models.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class message_frag extends Fragment {


    public message_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_message_frag, container, false);

        TextView name=v.findViewById(R.id.name);
        TextView email=v.findViewById(R.id.email);
        TextView phn=v.findViewById(R.id.phn);
        TextView dept=v.findViewById(R.id.dept);

        name.setText(shared_pref.getName(getActivity().getApplicationContext()));
        email.setText(shared_pref.getEmail(getActivity().getApplicationContext()));
        phn.setText(String.valueOf(shared_pref.getPhn(getActivity().getApplicationContext())));
        dept.setText(shared_pref.getDept(getActivity().getApplicationContext()));

        Button button=v.findViewById(R.id.logout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.apply();


                startActivity(new Intent(getActivity().getApplicationContext(), Login_Act.class));

            }
        });


        // Inflate the layout for this fragment
            return v;
    }

}
