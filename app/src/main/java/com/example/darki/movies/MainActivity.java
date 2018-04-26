package com.example.darki.movies;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.darki.movies.adapter.MoviesAdapter;
import com.example.darki.movies.api.Client;
import com.example.darki.movies.api.Service;
import com.example.darki.movies.model.Movie;
import com.example.darki.movies.model.MoviesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private List<Movie> movieList;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeRefreshLayout;
    public static final String LOG_TAG = MoviesAdapter.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.main_content);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViews();
                Toast.makeText(MainActivity.this,"Movies Refreshed" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Activity getActivity(){
        Context context=this;
        while (context instanceof ContextWrapper){
            if(context instanceof Activity){
                return (Activity) context;
            }
            context =((ContextWrapper) context).getBaseContext();
        }
    return null;
    }
    private void initViews(){
        pd=new ProgressDialog(this);
        pd.setMessage("fetching movies ....");
        pd.setCancelable(false);
        pd.show();

        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);

        movieList =new ArrayList<>();
        adapter=new MoviesAdapter(this,movieList);

        if(getActivity().getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        loadJson();
    }

    private void loadJson(){
        try{
            if(BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please pbtain Api Key", Toast.LENGTH_SHORT).show();
                pd.dismiss();
                return;
            }
            Client Client=new Client();
            Service apiService=
                    Client.getClient().create(Service.class);
            Call<MoviesResponse> call=apiService.getPoplarMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    List<Movie> movies=response.body().getResults();
                    recyclerView.setAdapter(new MoviesAdapter(getApplicationContext(),movies));
                    recyclerView.smoothScrollToPosition(0);
                    if(swipeRefreshLayout.isRefreshing()){
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    Log.d("Error",t.getMessage());
                    Toast.makeText(MainActivity.this,"Error Fetcing Data" ,Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this , e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.menu_main,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case R.id.menu_settings
//            return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
