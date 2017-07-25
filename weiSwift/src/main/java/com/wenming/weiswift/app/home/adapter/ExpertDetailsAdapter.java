package com.wenming.weiswift.app.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.home.activity.ExpertDetailsActivity;

/**
 * Created by qhn on 2017/7/25.
 */

public class ExpertDetailsAdapter extends RecyclerView.Adapter<ExpertDetailsAdapter.ExpertDetailsHolder>{

    private final Context mContext;

    public ExpertDetailsAdapter(Context context){
        mContext = context;
    }
    @Override
    public ExpertDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_expert_details,parent,false);
        return new ExpertDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpertDetailsHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 20;
    }
    class ExpertDetailsHolder extends RecyclerView.ViewHolder {
        public ExpertDetailsHolder(View itemView) {
            super(itemView);

        }
    }
}
