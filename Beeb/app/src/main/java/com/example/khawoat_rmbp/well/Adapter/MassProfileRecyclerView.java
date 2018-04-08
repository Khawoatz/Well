package com.example.khawoat_rmbp.well.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khawoat_rmbp.well.R;

import java.util.List;

/**
 * Created by jah on 4/6/2018.
 */

public class MassProfileRecyclerView extends RecyclerView.Adapter<MassProfileRecyclerView.ViewHolder> {
    private Context context;
    private List<DataProfile> dataProfileList;
    DataProfile dataProfile;
    public MassProfileRecyclerView(Context context, List<DataProfile> DataProfileList) {
        this.context = context;
        this.dataProfileList = DataProfileList;
    }
    @Override
    public MassProfileRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_profilemass_user, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        DataProfile dataProfile = dataProfileList.get(position);
       dataProfile.getNameMass();


        viewHolder.tvNameMass.setText(dataProfile.getNameMass());

    }

    @Override
    public int getItemCount() {
        return dataProfileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameMass;
        public ViewHolder(View itemView) {
            super(itemView);
            this.tvNameMass = (TextView) itemView.findViewById(R.id.tvNameMass);

        }
    }
}
