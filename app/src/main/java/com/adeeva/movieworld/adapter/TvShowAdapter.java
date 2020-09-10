package com.adeeva.movieworld.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adeeva.movieworld.R;
import com.adeeva.movieworld.entity.TvShowItems;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {
    private ArrayList<TvShowItems> mData = new ArrayList<>();
    private Context context;

    public TvShowAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<TvShowItems> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(final TvShowItems item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview, viewGroup, false);
        return new TvShowViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder tvShowViewHolder, int position) {
        tvShowViewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvRelease, tvOverview;
        ImageView imgPoster;
        Button btnFavorite, btnShare;

        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_title);
            tvRelease = itemView.findViewById(R.id.item_release);
            tvOverview = itemView.findViewById(R.id.item_description);
            imgPoster = itemView.findViewById(R.id.item_img);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
            btnShare = itemView.findViewById(R.id.btn_set_share);
        }

        void bind(final TvShowItems tvShowItems) {
            tvTitle.setText(tvShowItems.getTitle());
            tvRelease.setText(tvShowItems.getRelease());
            tvOverview.setText(tvShowItems.getOverview());

            Glide.with(context)
                    .load(tvShowItems.getPosterPath())
                    .apply(new RequestOptions().override(350, 550))
                    .into(imgPoster);

            btnFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, context.getString(R.string.favorite) + tvShowItems.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, context.getString(R.string.share) + tvShowItems.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
