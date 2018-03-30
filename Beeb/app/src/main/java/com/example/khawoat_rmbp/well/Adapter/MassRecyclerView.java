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
 * Created by jah on 3/23/2018.
 */

public class MassRecyclerView extends RecyclerView.Adapter<MassRecyclerView.ViewHolder> {

    private Context context;
    private List<DataHistory> dataHistoryList;
    DataHistory dataHistory;
    public MassRecyclerView(Context context,List<DataHistory> DataHistoryList){
        this.context = context;
        this.dataHistoryList = DataHistoryList;
    }

    @Override
    public MassRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_myservice_masseuse, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataHistory dataHistory = dataHistoryList.get(position);
        dataHistory.getNdCustomer();
//        holder.tvRes.setText(dataHistory.getId());
        holder.tvNameCus.setText(dataHistory.getNdCustomer());
        holder.tvNameType.setText(dataHistory.getNameType());
        holder.tvDate.setText(dataHistory.getDate());
        holder.tvStarTime.setText(dataHistory.getStartTime());
        holder.tvEndTime.setText(dataHistory.getEndTime());
        holder.tvLocation.setText(dataHistory.getLocation());
    }



    @Override
    public int getItemCount() {
        return dataHistoryList.size();
    }
    public static class  ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameCus,tvNameType,tvDate,tvStarTime,tvEndTime,tvLocation,tvRes;

        public ViewHolder(View itemView){
            super(itemView);
            tvNameCus = (TextView) itemView.findViewById(R.id.tvNameCus);
            tvNameType = (TextView) itemView.findViewById(R.id.tvNameType);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvStarTime = (TextView) itemView.findViewById(R.id.tvStarTime);
            tvEndTime = (TextView) itemView.findViewById(R.id.tvEndTime);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
        }
    }
}
