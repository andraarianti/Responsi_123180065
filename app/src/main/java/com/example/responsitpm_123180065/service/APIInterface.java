package com.example.responsitpm_123180065.service;

import com.example.responsitpm_123180065.data.faskes.FaskesResponse;
import com.example.responsitpm_123180065.data.kumulatif.KumulatifResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    String URL_BASE = "https://covid19-public.digitalservice.id/api/v1/";

    @GET("rekapitulasi_v2/jabar/harian")
    Call<KumulatifResponse> getKumulatif();

    @GET("sebaran_v2/jabar/faskes")
    Call<FaskesResponse> getFaskes();
}
