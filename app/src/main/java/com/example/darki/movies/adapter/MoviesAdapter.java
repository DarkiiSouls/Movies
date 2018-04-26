package com.example.darki.movies.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.darki.movies.DetailActivity;
import com.example.darki.movies.R;
import com.example.darki.movies.model.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private Context mcontext;
    private List<Movie> movieList;

    public MoviesAdapter(Context mcontext ,List<Movie> movieList){
        this.mcontext=mcontext;
        this.movieList=movieList;


    }
    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup,int i){
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_card, viewGroup ,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder,int i){
        viewHolder.title.setText(movieList.get(i).getOriginaltitle());
        String vote=Double.toString(movieList.get(i).getVoteaverage());
        viewHolder.userrating.setText(vote);

        Glide.with(mcontext)
                .load(movieList.get(i).getPosterpath())
                .load(R.drawable.load)
                .into(viewHolder.thumbnail);

    }

    @Override
    public int getItemCount(){
        return movieList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title ,userrating;
        public ImageView thumbnail;

        public  MyViewHolder(View view){
            super(view);
            title=(TextView) view.findViewById((R.id.title));
            userrating=(TextView) view.findViewById(R.id.userrating);
            thumbnail=(ImageView) view.findViewById(R.id.thumbnail);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   int pos=getAdapterPosition();
                   if(pos !=RecyclerView.NO_POSITION){
                       Movie clickedDataItem = movieList.get(pos);
                       Intent intent = new Intent(mcontext, DetailActivity.class);
                       intent.putExtra("movies", (Parcelable) clickedDataItem);
                       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       mcontext.startActivity(intent);
                       Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getOriginaltitle(), Toast.LENGTH_SHORT).show();
                   }
                }
            });
        }
    }
}

