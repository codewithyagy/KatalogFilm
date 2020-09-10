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
import com.adeeva.movieworld.ViewModel.MovieMainViewModel;
import com.adeeva.movieworld.activity.MovieDetailActivity;
import com.adeeva.movieworld.adapter.MovieAdapter;
import com.adeeva.movieworld.entity.MovieItems;
import com.adeeva.movieworld.listener.ItemClickSupport;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private MovieAdapter adapter;
    private MovieMainViewModel movieMainViewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieMainViewModel = ViewModelProviders.of(this).get(MovieMainViewModel.class);
        movieMainViewModel.LANGUAGE = getString(R.string.language_code);
        movieMainViewModel.getMovies().observe(this, getMovies);

        recyclerView = view.findViewById(R.id.rv_movie);

        showRecycler();

        progressBar = view.findViewById(R.id.progressBar);

        movieMainViewModel.setMovie();
        showLoading(true);

    }

    private void showRecycler() {
        adapter = new MovieAdapter(getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(getContext(), getString(R.string.you_choose) + Objects.requireNonNull(movieMainViewModel.getMovies().getValue()).get(position).getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movieMainViewModel.getMovies().getValue().get(position));
                startActivity(intent);

            }
        });
    }

    private Observer<ArrayList<MovieItems>> getMovies = new Observer<ArrayList<MovieItems>>() {
        @Override
        public void onChanged(@Nullable ArrayList<MovieItems> movieItems) {
            if (movieItems != null) {
                adapter.setData(movieItems);
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
