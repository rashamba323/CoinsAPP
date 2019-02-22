package com.coin.api;

import com.coin.model.Coin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String baseUrl = "https://api.coinmarketcap.com/v1/";

    @GET("ticker")
    Call<List<Coin>> ticker();


}
