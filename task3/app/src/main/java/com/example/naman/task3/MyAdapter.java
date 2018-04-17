package com.example.naman.task3;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Item> repositoryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, forks, stars, githublink;
        public Button githubButton;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.title);
            forks = (TextView) view.findViewById(R.id.genre);
            stars = (TextView) view.findViewById(R.id.year);
            githublink = (TextView) view.findViewById(R.id.githubLink);
            githubButton = (Button) view.findViewById(R.id.githubLinkButton);
            githubButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = githublink.getText().toString();
                    Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }


    public MyAdapter(List<Item> repositoryList) {
        this.repositoryList = repositoryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item repository = repositoryList.get(position);
        holder.name.setText(repository.getTitle());
        holder.forks.setText(repository.getGenre());
        holder.stars.setText(repository.getYear());
        holder.githublink.setText(repository.getLink());
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

}