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
import com.adeeva.movieworld.entity.MovieItems;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<MovieItems> mData = new ArrayList<>();
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MovieItems> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(final MovieItems item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvRelease, tvDescription;
        ImageView imgPoster;
        Button btnFavorite, btnShare;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_title);
            tvRelease = itemView.findViewById(R.id.item_release);
            tvDescription = itemView.findViewById(R.id.item_description);
            imgPoster = itemView.findViewById(R.id.item_img);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
            btnShare = itemView.findViewById(R.id.btn_set_share);
        }

        void bind(final MovieItems movieItems) {
            tvTitle.setText(movieItems.getTitle());
            tvRelease.setText(movieItems.getRelease());
            tvDescription.setText(movieItems.getOverview());

            Glide.with(context)
                    .load(movieItems.getPosterPath())
                    .apply(new RequestOptions().override(350, 550))
                    .into(imgPoster);

            btnFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, context.getString(R.string.favorite) + movieItems.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, context.getString(R.string.share) + movieItems.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
