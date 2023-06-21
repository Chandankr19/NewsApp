package com.tecdroid.newsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {


    String api = "39a865698a40490ea4cf4eef214f34de";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country = "in";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerViewOfHome = v.findViewById(R.id.recyclerViewHome);
       modelClassArrayList = new ArrayList<>();
       recyclerViewOfHome.setLayoutManager(new LinearLayoutManager(getContext()));
       adapter = new Adapter(getContext(), modelClassArrayList);
       recyclerViewOfHome.setAdapter(adapter);

       findNews();
       return v;
    }

    private void findNews() {


        ApiUtilities.getApiInterface().getNews(country, 100,api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(@NonNull Call<MainNews> call, @NonNull Response<MainNews> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MainNews> call, @NonNull Throwable t) {

            }
        });
    }
}