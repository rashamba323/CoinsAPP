package com.coin.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.annotations.SerializedName;

public class Coin {

    public String id;
    public String name;
    public String symbol;

    @SerializedName("price_usd")
    public double priceUsd;

    @SerializedName("percent_change_24h")
    public double percentChange;


/*
id	1
name	"Bitcoin"
symbol	"BTC"
website_slug	"bitcoin"
rank	1
circulating_supply	17211425
total_supply	17211425
max_supply	21000000
last_updated	1534357057*/
}
