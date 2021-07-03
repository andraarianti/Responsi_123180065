package com.example.responsitpm_123180065.service;

import androidx.lifecycle.MutableLiveData;

import com.example.responsitpm_123180065.data.faskes.DataItem;
import com.example.responsitpm_123180065.data.faskes.FaskesResponse;
import com.example.responsitpm_123180065.data.kumulatif.ContentItem;
import com.example.responsitpm_123180065.data.kumulatif.KumulatifResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    private Retrofit retrofit = null;

    // fungsi untuk membuat retrofit dan mengkoneksikan dengan request yang ada di ExpedisiAPI
    public APIInterface getAPI(){
        if(retrofit == null){
            // membangun atau instansiasi data retrofit
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(APIInterface.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(APIInterface.class);
    }

    public void getKumulatif(final APIListener<ArrayList<ContentItem>> listener){
        //pemanggilan request
        getAPI().getKumulatif().enqueue(new Callback<KumulatifResponse>() {
            @Override
            public void onResponse(Call<KumulatifResponse> call, Response<KumulatifResponse> response) {
                // menyimpan hasil pengambilan data
                KumulatifResponse data = response.body();
                if (data!=null && data.getData()!=null){
                    listener.onSuccess((ArrayList<ContentItem>) data.getData().getContent());
                }
            }
            @Override
            public void onFailure(Call<KumulatifResponse> call, Throwable t) {
                // mengirimkan pesan error melalui Callback / jembatan listener
                listener.onFailed(t.getMessage());
            }
        });
    }

    public void getFaskes(final APIListener<ArrayList<DataItem>> listener){
        //pemanggilan request
        getAPI().getFaskes().enqueue(new Callback<FaskesResponse>() {
            @Override
            public void onResponse(Call<FaskesResponse> call, Response<FaskesResponse> response) {
                // menyimpan hasil pengambilan data
                FaskesResponse data = response.body();
                if (data!=null && data.getData()!=null){
                    listener.onSuccess((ArrayList<DataItem>) data.getData());
                }
            }
            @Override
            public void onFailure(Call<FaskesResponse> call, Throwable t) {
                // mengirimkan pesan error melalui Callback / jembatan listener
                listener.onFailed(t.getMessage());
            }
        });
    }
}
