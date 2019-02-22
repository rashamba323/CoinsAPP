package com.coin.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coin.coinsapp.R;
import com.coin.model.Coin;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder> {




    private List<Coin> coins = Collections.emptyList();

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
        notifyDataSetChanged();
    }

    @Override
    public CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CoinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoinViewHolder holder, int position) {

        holder.bind(coins.get(position));
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    static class CoinViewHolder extends RecyclerView.ViewHolder {
        TextView itemSymbol;
        TextView itemName;
        TextView itemPrise;
        TextView itemPercentChange;
        Random random = new Random();

        private Context context;

        public CoinViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();

            itemSymbol = itemView.findViewById(R.id.item_symbol);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrise = itemView.findViewById(R.id.item_prise);
            itemPercentChange = itemView.findViewById(R.id.item_percent_change);
        }

        private static int[] colors = {
                0xffffa000,
                0xff0288d1,
                0xffd32f2f,
                0xffc2185b,
                0xff7b1fa2,
                0xffe65100
        };

        public void bind(Coin coin) {
            itemSymbol.setText(coin.symbol);
            Drawable background = itemSymbol.getBackground();
            Drawable wrap = DrawableCompat.wrap(background);
            DrawableCompat.setTint(wrap, colors[random.nextInt(colors.length)]);
            itemName.setText(coin.name);
            itemPrise.setText(context.getString(R.string.item_prise, coin.priceUsd));

            if(coin.percentChange >= 0)
                itemPercentChange.setTextColor(context.getResources().getColor(R.color.percent_change_positive));
            else
                itemPercentChange.setTextColor(context.getResources().getColor(R.color.percent_change_negative));

            itemPercentChange.setText(context.getString(R.string.item_percent_change, coin.percentChange));


        }


    }
}
