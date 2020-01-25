package com.example.attenence;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.attenence.adapters.course_aapter;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.attenence.models.*;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class Attendence_frag extends Fragment {


    public Attendence_frag() {
        // Required empty public constructor
    }

//Button sem1,sem2,sem3,sem4;
    int sem;
    String subject;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_attendence_frag, container, false);
        MaterialCardView materialCardView=view.findViewById(R.id.msc_card1);
        RecyclerView recyclerView=view.findViewById(R.id.rec_courses);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new course_aapter(new subjects().getArray(),getContext(),view,getActivity()));

        materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                ViewGroup viewGroup = view.findViewById(android.R.id.content);
                final View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.alert_dialog, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();

                final subjects subjects=new subjects();


                final AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                ViewGroup viewGroup1 = view.findViewById(android.R.id.content);
                final View dialogView1 = LayoutInflater.from(v.getContext()).inflate(R.layout.subject, viewGroup1, false);
                builder1.setView(dialogView1);
                final AlertDialog alertDialog1 = builder1.create();

                final ListView listView=dialogView1.findViewById(R.id.list_sub);



//                sem1=dialogView.findViewById(R.id.sem1);
//                sem2=dialogView.findViewById(R.id.sem2);
//                sem3=dialogView.findViewById(R.id.sem3);
//                sem4=dialogView.findViewById(R.id.sem4);


                ListView listView1=dialogView.findViewById(R.id.list_sem);
                ArrayAdapter arrayAdapter=new ArrayAdapter(view.getContext(),R.layout.list_text,R.id.text1,subjects.getSems());
                 listView1.setAdapter(arrayAdapter);

                 listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                     @Override
                     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                         switch (position){
                             case 0:
                                 sem=1;
                                 listView.setAdapter(new ArrayAdapter(view.getContext(),R.layout.list_text,R.id.text1,subjects.getSem1()));

                                 break;

                             case 1:
                                 sem=2;
                                 listView.setAdapter(new ArrayAdapter(view.getContext(),R.layout.list_text,R.id.text1,subjects.getSem2()));

                                 break;

                             case 2:
                                 sem=3;
                                 listView.setAdapter(new ArrayAdapter(view.getContext(),R.layout.list_text,R.id.text1,subjects.getSem3()));

                                 break;
                             case 3:
                                 sem=4;
                                 listView.setAdapter(new ArrayAdapter(view.getContext(),R.layout.list_text,R.id.text1,subjects.getSem4()));

                                 break;





                         }
                         alertDialog1.show();
                         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                             @Override
                             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                 Intent intent=new Intent(getActivity(),Attenence_act.class);
                                 Log.d("selected", "onItemSelected: "+"one selected");
                                 intent.putExtra("sem",String.valueOf(sem));
                                 intent.putExtra("subject", (String) listView.getItemAtPosition(position));
                                 startActivity(intent);

//                                 switch (position){
//                                     case 0:
//                                         Log.d("selected", "onItemSelected: "+"one selected");
//                                         intent.putExtra("sem",String.valueOf(sem));
//                                         intent.putExtra("subject",subjects.getSem1()[position]);
//                                         startActivity(intent);
//                                         break;
//
//                                     case 1:
//                                         Log.d("selected", "onItemSelected: "+"one selected");
//                                         intent.putExtra("sem",String.valueOf(sem));
//                                         intent.putExtra("subject",subjects.getSem2()[position]);
//                                         startActivity(intent);
//                                         break;
//
//                                     case 2:
//                                         Log.d("selected", "onItemSelected: "+"one selected");
//                                         intent.putExtra("sem",String.valueOf(sem));
//                                         intent.putExtra("subject",subjects.getSem3()[position]);
//                                         startActivity(intent);
//                                         break;
//                                     case 3:
//                                         Log.d("selected", "onItemSelected: "+"one selected");
//                                         intent.putExtra("sem",String.valueOf(sem));
//                                         intent.putExtra("subject",subjects.getSem4()[position]);
//                                         startActivity(intent);
//                                         break;
//
//
//
//
//
//                                 }





                             }
                         });
                     }
                 });

//                sem1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                                sem=1;
//
//                        ArrayAdapter aa = new ArrayAdapter(view.getContext(),android.R.layout.simple_spinner_item,subjects.getSem1());
//                        listView.setAdapter(aa);
//                       alertDialog1.show();
//listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Log.d("selected", "onItemSelected: "+"one selected");
//        Intent intent=new Intent(getActivity(),Attenence_act.class);
//        intent.putExtra("sem","1");
//        intent.putExtra("subject",subjects.getSem1()[position]);
//        startActivity(intent);
//    }
//});
//
//
//
//
//
//
//
//
//
//
//                    }
//                });
//                sem2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        sem=2;
//                        ArrayAdapter aa = new ArrayAdapter(view.getContext(),android.R.layout.simple_spinner_item,subjects.getSem2());
//                        listView.setAdapter(aa);
//                       alertDialog1.show();
//
//
//
//                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                Log.d("selected", "onItemSelected: "+"one selected");
//                                Intent intent=new Intent(getActivity(),Attenence_act.class);
//                                intent.putExtra("sem","2");
//                                intent.putExtra("subject",subjects.getSem2()[position]);
//                                startActivity(intent);
//                            }
//                        });
//
//
//                    }
//                });
//                sem3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        sem=3;
//                        ArrayAdapter aa = new ArrayAdapter(view.getContext(),android.R.layout.simple_spinner_item,subjects.getSem3());
//                        listView.setAdapter(aa);
//                        alertDialog1.show();
//
//
//
//                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                Log.d("selected", "onItemSelected: "+"one selected");
//                                Intent intent=new Intent(getActivity(),Attenence_act.class);
//                                intent.putExtra("sem","3");
//                                intent.putExtra("subject",subjects.getSem3()[position]);
//                                startActivity(intent);
//                            }
//                        });
//
//
//                    }
//                });
//                sem4.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        sem=4;
//                        ArrayAdapter aa = new ArrayAdapter(view.getContext(),android.R.layout.simple_spinner_item,subjects.getSem4());
//                        listView.setAdapter(aa);
//                        alertDialog1.show();
//
//
//
//                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                Log.d("selected", "onItemSelected: "+"one selected");
//                                Intent intent=new Intent(getActivity(),Attenence_act.class);
//                                intent.putExtra("sem","4");
//                                intent.putExtra("subject",subjects.getSem4()[position]);
//                                startActivity(intent);
//                            }
//                        });
//
//
//                    }
//                });

                alertDialog.show();
            }
        });

        return view ;



    }



    public  void getData(String sem){
        final Call<List<dataModel>> dataModelCall=apiconn.getService().getData(sem);

        dataModelCall.enqueue(new Callback<List<dataModel>>() {
            @Override
            public void onResponse(Call<List<dataModel>> call, Response<List<dataModel>> response) {


                for (int i = 0; i <response.body().size() ; i++) {
                    dataModel dataModel=response.body().get(i);
                    Log.d("message", "onResponse: "+dataModel.getStudentName());
                }


            }

            @Override
            public void onFailure(Call<List<dataModel>> call, Throwable t) {
                Log.e("error", "onFailure: ",t );
            }
        });


    }

}
