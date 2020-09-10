package com.adeeva.movieworld.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adeeva.movieworld.R;
import com.adeeva.movieworld.entity.TvShowItems;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class TvShowDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TV = "extra_tv";
    TextView tvDetailTitleTvShow, tvDetailReleaseTvShow, tvDetailDescriptionTvShow;
    ImageView imgDetailTvShow;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private String textTitle, textRelease, textDescription, imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        tvDetailTitleTvShow = findViewById(R.id.detail_tv_title_tv);
        tvDetailReleaseTvShow = findViewById(R.id.detail_tv_release_tv);
        tvDetailDescriptionTvShow = findViewById(R.id.detail_tv_description_movie);
        imgDetailTvShow = findViewById(R.id.detail_img_tv);

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        //showLoading(true);

        //TvShowItems tvShowItems = getIntent().getParcelableExtra(EXTRA_TV);
        //textTitle = tvShowItems.getTitle();
        //textRelease = tvShowItems.getRelease();
        //textDescription = tvShowItems.getOverview();
        //imgPoster = tvShowItems.getPosterPath();

        //tvDetailTitleTvShow.setText(textTitle);
        //tvDetailReleaseTvShow.setText(textRelease);
        //tvDetailDescriptionTvShow.setText(textDescription);
        //Glide.with(TvShowDetailActivity.this)
        //        .load(imgPoster)
        //        .apply(new RequestOptions().override(350, 550))
        //        .into(imgDetailTvShow);

        //if (imgDetailTvShow != null) {
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
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);

                            if (progressStatus == 100) {
                                progressBar.setVisibility(View.GONE);
                                TvShowItems tvShowItems = getIntent().getParcelableExtra(EXTRA_TV);
                                textTitle = tvShowItems.getTitle();
                                textRelease = tvShowItems.getRelease();
                                textDescription = tvShowItems.getOverview();
                                imgPoster = tvShowItems.getPosterPath();

                                tvDetailTitleTvShow.setText(textTitle);
                                tvDetailReleaseTvShow.setText(textRelease);
                                tvDetailDescriptionTvShow.setText(textDescription);
                                Glide.with(TvShowDetailActivity.this)
                                        .load(imgPoster)
                                        .apply(new RequestOptions().override(350, 550))
                                        .into(imgDetailTvShow);
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
    //   }
    //}
}
