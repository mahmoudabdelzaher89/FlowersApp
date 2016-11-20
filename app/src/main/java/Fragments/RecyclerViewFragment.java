package Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahmoud.flowersws.Flower;
import com.mahmoud.flowersws.FlowersAdapter;
import com.mahmoud.flowersws.FlowersRequestInterface;
import com.mahmoud.flowersws.MainActivity;
import com.mahmoud.flowersws.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by HP Pro 3500 on 20/11/2016.
 */

public class RecyclerViewFragment extends Fragment {
    RecyclerView recyclerView;
    List<Flower> flowersList =  new ArrayList<>();

    //Change Here :D by Eslam El-hoseiny
    FlowersAdapter adatpter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        loadFlowersData();
        return v;
    }

    private void loadFlowersData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://services.hanselandpetal.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FlowersRequestInterface request = retrofit.create(FlowersRequestInterface.class);
        Call<ArrayList<Flower>> call = request.getJson();
        call.enqueue(new Callback<ArrayList<Flower>>() {
            @Override
            public void onResponse(Call<ArrayList<Flower>> call, Response<ArrayList<Flower>> response) {
                flowersList =  response.body();
                adatpter = new FlowersAdapter(flowersList,getActivity());
                recyclerView.setAdapter(adatpter);
            }

            @Override
            public void onFailure(Call<ArrayList<Flower>> call, Throwable t) {
                Log.d("Error ",t.toString());
            }
        });
    }
}

