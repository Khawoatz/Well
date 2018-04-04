package com.example.khawoat_rmbp.well.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khawoat_rmbp.well.R;

import java.util.List;

/**
 * Created by jah on 3/28/2018.
 */

public class MassNotiRecyclerView extends RecyclerView.Adapter<MassNotiRecyclerView.ViewHolder> {
    private Context context;
    private List<DataNoti> dataNotiList;
    DataNoti dataNoti;

    public MassNotiRecyclerView(Context context, List<DataNoti> DataNotiList) {
        this.context = context;
        this.dataNotiList = DataNotiList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_notificaion_mass, parent, false);

       ViewHolder viewHolder = new ViewHolder(view);
       return viewHolder;


    }

    @Override
    public void onBindViewHolder(MassNotiRecyclerView.ViewHolder viewHolder, int position) {
        DataNoti dataNoti = dataNotiList.get(position);
        dataNoti.getNdCustomer();

        if (dataNotiList.get(position).getPoints().equals("0")){
            viewHolder.star0.setVisibility(View.VISIBLE);
//            viewHolder.star2.setVisibility(View.VISIBLE);
//            viewHolder.star3.setVisibility(View.VISIBLE);
//            viewHolder.star4.setVisibility(View.VISIBLE);
//            viewHolder.star5.setVisibility(View.VISIBLE);

        }else if (dataNotiList.get(position).getPoints().equals("1")){

            viewHolder.star1.setVisibility(View.VISIBLE);
//            viewHolder.star3.setVisibility(View.VISIBLE);
//            viewHolder.star4.setVisibility(View.VISIBLE);
//            viewHolder.star5.setVisibility(View.VISIBLE);

        }else if (dataNotiList.get(position).getPoints().equals("2")){

            viewHolder.star2.setVisibility(View.VISIBLE);
//            viewHolder.star4.setVisibility(View.VISIBLE);
//            viewHolder.star5.setVisibility(View.VISIBLE);
        }else if (dataNotiList.get(position).getPoints().equals("3")){
            viewHolder.star3.setVisibility(View.VISIBLE);
//            viewHolder.star1.setVisibility(View.VISIBLE);
//            viewHolder.star2.setVisibility(View.VISIBLE);
//            viewHolder.star4.setVisibility(View.VISIBLE);
//            viewHolder.star5.setVisibility(View.VISIBLE);
        }else if (dataNotiList.get(position).getPoints().equals("4")){

            viewHolder.star4.setVisibility(View.VISIBLE);
        }else if (dataNotiList.get(position).getPoints().equals("5")){
            viewHolder.star5.setVisibility(View.VISIBLE);
        }


        viewHolder.tvNameCuss.setText(dataNoti.getNdCustomer());
        viewHolder.tvDescription.setText(dataNoti.getNameDescription());





    }

    @Override
    public int getItemCount() {
        return dataNotiList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameCuss,tvDescription;
      public ImageView star0,star1,star2,star3,star4,star5;

        private ViewHolder(View itemView) {
            super(itemView);
           this.tvNameCuss = (TextView) itemView.findViewById(R.id.tvNameCuss);
            this.tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);

            this.star0 = (ImageView) itemView.findViewById(R.id.star0);
            this.star1 = (ImageView) itemView.findViewById(R.id.star1);
            this.star2 = (ImageView) itemView.findViewById(R.id.star2);
            this.star3 = (ImageView) itemView.findViewById(R.id.star3);
            this.star4 = (ImageView) itemView.findViewById(R.id.star4);
            this.star5 = (ImageView) itemView.findViewById(R.id.star5);
        }
    }
}
