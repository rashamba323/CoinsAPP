package com.coin.coinsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.coin.adapter.CoinAdapter;
import com.coin.api.Api;
import com.coin.model.Coin;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_item)
    RecyclerView listItem;

    CoinAdapter coinAdapter;

    List<Coin> coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        coinAdapter = new CoinAdapter();
        loadData();
        listItem.setLayoutManager(new LinearLayoutManager(this));
        listItem.setAdapter(coinAdapter);
    }

    private void loadData() {
        Api api = ((App) getApplication()).getApi();

        api.ticker().enqueue(new Callback<List<Coin>>() {
            @Override
            public void onResponse(Call<List<Coin>> call, Response<List<Coin>> response) {
                coins = response.body();

                Collections.sort(coins, new Comparator<Coin>() {
                    @Override
                    public int compare(Coin o1, Coin o2) {
                        return Double.compare(o2.percentChange, o2.percentChange);
                    }
                });
                coinAdapter.setCoins(coins);
            }

            @Override
            public void onFailure(Call<List<Coin>> call, Throwable t) {
                Log.d("JSON", "Error: " + t.toString());
            }
        });
    }

//    @OnClick({R.id.sort_price, R.id.sort_precent})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.sort_price:
//                Collections.sort(coins, new Comparator<Coin>() {
//                    @Override
//                    public int compare(Coin o1, Coin o2) {
//                        return Double.compare(o2.priceUsd, o2.priceUsd);
//                    }
//                });
//                break;
//            case R.id.sort_precent:
//
//                break;
//        }
//    }
}
