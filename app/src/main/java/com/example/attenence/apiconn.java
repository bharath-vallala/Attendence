package com.example.attenence;

import com.example.attenence.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class apiconn {

   public static final String  url="https://guarded-harbor-72259.herokuapp.com/msc/";
    public static final String  url1="https://guarded-harbor-72259.herokuapp.com/login/staff/";

    public static  list list=null;
    public static  list list1=null;





    public static list getService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
       // static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

                    if (list==null){
                        Retrofit.Builder builder =
                                new Retrofit.Builder()
                                        .baseUrl(url)
                                        .addConverterFactory(
                                                GsonConverterFactory.create(gson)
                                        );
                        Retrofit retrofit =
                                builder
                                        .client(
                                                client
                                        )
                                        .build();

                         list =  retrofit.create(list.class);
                    }

  return list;
  }
  public static list getLogin(){
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
      Gson gson = new GsonBuilder()
              .setLenient()
              .create();

      if (list1==null){
          Retrofit.Builder builder =
                  new Retrofit.Builder()
                          .baseUrl(url1)
                          .addConverterFactory(
                                  GsonConverterFactory.create(gson)
                          );
          Retrofit retrofit =
                  builder
                          .client(
                                 client
                          )
                          .build();

          list1 =  retrofit.create(list.class);
      }
        return list1;
  }

    public interface list{

         @GET("{sem}")
        Call<List<dataModel>> getData(@Path("sem") String sem);

        @POST("upate/{Subject_1}")
        Call<PostAttenence> postData(@Body PostAttenence postModel, @Path("Subject_1") String Subject);

        @GET("{email}/{pass}")
        Call<List<LoginData>> Login(@Path("email") String email,@Path("pass") String pass);


    }
}
