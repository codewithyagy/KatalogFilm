package com.adeeva.movieworld.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adeeva.movieworld.R;
import com.adeeva.movieworld.entity.MovieItems;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    TextView tvDetailTitleMovie, tvDetailReleaseMovie, tvDetailDescriptionMovie;
    ImageView imgDetailMovie;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private String textTitle, textRelease, textDescription, imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        tvDetailTitleMovie = findViewById(R.id.detail_tv_title_movie);
        tvDetailReleaseMovie = findViewById(R.id.detail_tv_release_movie);
        tvDetailDescriptionMovie = findViewById(R.id.detail_tv_description_movie);
        imgDetailMovie = findViewById(R.id.detail_img_movie);

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        //progressBar = findViewById(R.id.progressBar);

        //MovieItems movieItems = getIntent().getParcelableExtra(EXTRA_MOVIE);
        //String textTitle = movieItems.getTitle();
        //String textRelease = movieItems.getRelease();
        //String textDescription = movieItems.getOverview();
        //String imgPoster = movieItems.getPosterPath();

        //tvDetailTitleMovie.setText(textTitle);
        //tvDetailReleaseMovie.setText(textRelease);
        //tvDetailDescriptionMovie.setText(textDescription);
        //Glide.with(MovieDetailActivity.this)
        //        .load(imgPoster)
        //        .apply(new RequestOptions().override(350, 550))
        //        .into(imgDetailMovie);

        //showLoading(true);

        //if (imgDetailMovie != null) {
        //    showLoading(false);
        //}

        progressStatus = 0;

        progressBar.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;

                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);

                            if (progressStatus == 100) {
                                progressBar.setVisibility(View.GONE);

                                MovieItems movieItems = getIntent().getParcelableExtra(EXTRA_MOVIE);
                                textTitle = movieItems.getTitle();
                                textRelease = movieItems.getRelease();
                                textDescription = movieItems.getOverview();
                                imgPoster = movieItems.getPosterPath();

                                tvDetailTitleMovie.setText(textTitle);
                                tvDetailReleaseMovie.setText(textRelease);
                                tvDetailDescriptionMovie.setText(textDescription);
                                Glide.with(MovieDetailActivity.this)
                                        .load(imgPoster)
                                        .apply(new RequestOptions().override(350, 550).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background))
                                        .into(imgDetailMovie);

                            }
                        }
                    });
                }
            }
        }).start();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    //private void showLoading(Boolean state) {
    //    if (state) {
    //        progressBar.setVisibility(View.VISIBLE);
    //    } else {
    //        progressBar.setVisibility(View.GONE);
    //    }
    //}
}
