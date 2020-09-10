package com.adeeva.movieworld.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adeeva.movieworld.R;
import com.adeeva.movieworld.ViewModel.TvMainViewModel;
import com.adeeva.movieworld.activity.TvShowDetailActivity;
import com.adeeva.movieworld.adapter.TvShowAdapter;
import com.adeeva.movieworld.entity.TvShowItems;
import com.adeeva.movieworld.listener.ItemClickSupport;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private TvShowAdapter adapter;
    private TvMainViewModel tvMainViewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvMainViewModel = ViewModelProviders.of(this).get(TvMainViewModel.class);
        tvMainViewModel.LANGUAGE = Objects.requireNonNull(getContext()).getString(R.string.language_code);
        tvMainViewModel.getTvShows().observe(this, getTvShows);

        recyclerView = view.findViewById(R.id.rv_tv);

        showRecycler();

        progressBar = view.findViewById(R.id.progressBar);

        tvMainViewModel.setTv();
        showLoading(true);
    }

    private void showRecycler() {
        adapter = new TvShowAdapter(getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(getContext(), getString(R.string.you_choose) + Objects.requireNonNull(tvMainViewModel.getTvShows().getValue()).get(position).getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), TvShowDetailActivity.class);
                intent.putExtra(TvShowDetailActivity.EXTRA_TV, tvMainViewModel.getTvShows().getValue().get(position));
                startActivity(intent);
            }
        });
    }

    private Observer<ArrayList<TvShowItems>> getTvShows = new Observer<ArrayList<TvShowItems>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShowItems> tvShowItems) {
            if (tvShowItems != null) {
                adapter.setData(tvShowItems);
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
