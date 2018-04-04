package com.example.khawoat_rmbp.well.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khawoat_rmbp.well.R;

import java.util.List;

/**
 * Created by jah on 3/17/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<DataHistory> dataHistoryList;
    DataHistory dataHistory;
    public RecyclerViewAdapter(Context context, List<DataHistory> DataHistoryList){
        this.context = context;
        this.dataHistoryList = DataHistoryList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_new_service_masseuse, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DataHistory dataHistory = dataHistoryList.get(position);
        dataHistory.getNdCustomer();
     //   holder.tvRes.setText(dataHistory.getId());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvNameCus,tvNameType,tvDate,tvStarTime,tvEndTime,tvLocation,tvRes;


        public ViewHolder(View itemView) {
            super(itemView);
            //tvRes = (TextView) itemView.findViewById(R.id.tvRes);
           tvNameCus = (TextView) itemView.findViewById(R.id.tvNameCus);
           tvNameType = (TextView) itemView.findViewById(R.id.tvNameType);
           tvDate = (TextView) itemView.findViewById(R.id.tvDate);
           tvStarTime = (TextView) itemView.findViewById(R.id.tvStarTime);
           tvEndTime = (TextView) itemView.findViewById(R.id.tvEndTime);
           tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);



        }
    }
}
