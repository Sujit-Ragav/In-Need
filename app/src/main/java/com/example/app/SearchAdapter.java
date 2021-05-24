package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> hnamelist;


    class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView hospitalname;
        LinearLayout searchitems;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalname = itemView.findViewById(R.id.hospitalname);
            searchitems = itemView.findViewById(R.id.searchitems);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> hnamelist) {
        this.context = context;
        this.hnamelist = hnamelist;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.searchitems,parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.hospitalname.setText(hnamelist.get(position));

        holder.searchitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,phospitaldetails.class);
                intent.putExtra("userid",hnamelist.get(position));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return hnamelist.size();
    }
}
