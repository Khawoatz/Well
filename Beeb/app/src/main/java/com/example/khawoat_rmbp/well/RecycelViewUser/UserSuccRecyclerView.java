package com.example.khawoat_rmbp.well.RecycelViewUser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khawoat_rmbp.well.RecycelViewUser.DataRecycelViewUser.DataHistoryUser;
import com.example.khawoat_rmbp.well.R;

import java.util.List;

/**
 * Created by jah on 4/5/2018.
 */

public class UserSuccRecyclerView extends RecyclerView.Adapter<UserSuccRecyclerView.ViewHolder> {
    private Context context;
    private List<DataHistoryUser> dataHistoryUserList;
    DataHistoryUser dataHistoryUser;

    public UserSuccRecyclerView(Context context,List<DataHistoryUser> DataHistoryUserList) {
        this.context = context;
        this.dataHistoryUserList = DataHistoryUserList;
    }

    @Override
    public UserSuccRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_succes_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DataHistoryUser dataHistoryUser = dataHistoryUserList.get(position);
        dataHistoryUser.getNameMass();

        holder.tvNameMass.setText(dataHistoryUser.getNameMass());
        holder.tv_Massage.setText(dataHistoryUser.getNameType());
        holder.tvDate.setText(dataHistoryUser.getDate());
        holder.tvStarTime.setText(dataHistoryUser.getStartTime());
        holder.tvEndTime.setText(dataHistoryUser.getEndTime());
        holder.tvLocation.setText(dataHistoryUser.getLocation());
    }

    @Override
    public int getItemCount() {
        return dataHistoryUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameMass, tv_Massage, tvDate, tvStarTime, tvEndTime, tvLocation, tvRes;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNameMass = (TextView) itemView.findViewById(R.id.tvNameMass);
            tv_Massage = (TextView) itemView.findViewById(R.id.tv_Massage);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvStarTime = (TextView) itemView.findViewById(R.id.tvStarTime);
            tvEndTime = (TextView) itemView.findViewById(R.id.tvEndTime);
            tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
        }
    }
}
