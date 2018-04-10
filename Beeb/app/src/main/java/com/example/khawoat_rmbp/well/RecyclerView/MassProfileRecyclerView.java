package com.example.khawoat_rmbp.well.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khawoat_rmbp.well.RecyclerView.DataRecyclerView.DataProfile;
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
        dataProfile.getPoints();


        String i = dataProfileList.get(position).getPoints();
       double value = Double.parseDouble(i);


        if (value == 0){
          //  viewHolder.star0.setVisibility(View.VISIBLE);
            viewHolder.star0.setVisibility(View.VISIBLE);
        }else if (value>=0.1 && value<=0.5){

            viewHolder.star.setVisibility(View.VISIBLE);
        }else if (value>=0.6 && value<=1){

            viewHolder.star1.setVisibility(View.VISIBLE);
        }else if (value>=1.1 && value<=1.5){

            viewHolder.start1h5.setVisibility(View.VISIBLE);
        }else if (value>=1.6 && value<=2){

            viewHolder.star2.setVisibility(View.VISIBLE);
        }else if (value>=2.1 && value<=2.5){

            viewHolder.start2h5.setVisibility(View.VISIBLE);
        }else if (value>=2.6 && value<=3){

            viewHolder.star3.setVisibility(View.VISIBLE);
        }else if (value>=3.1 && value<=3.5){

            viewHolder.start3h5.setVisibility(View.VISIBLE);
        }else if (value>=3.6 && value<=4){

            viewHolder.star4.setVisibility(View.VISIBLE);
        }else if (value>=4.1 && value<=4.5){

            viewHolder.start4h5.setVisibility(View.VISIBLE);
        }else if (value>=4.6 && value<=5){

            viewHolder.star5.setVisibility(View.VISIBLE);
        }


        viewHolder.tvNameMass.setText(dataProfile.getNameMass());

    }

    @Override
    public int getItemCount() {
        return dataProfileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameMass;
        public ImageView star,star0,star1,star2,star3,star4,star5,start1h5,start2h5,start3h5,start4h5;;
        public ViewHolder(View itemView) {
            super(itemView);
            this.tvNameMass = (TextView) itemView.findViewById(R.id.tvNameMass);

            this.star = (ImageView) itemView.findViewById(R.id.star);
            this.star0 = (ImageView) itemView.findViewById(R.id.star0);
            this.star1 = (ImageView) itemView.findViewById(R.id.star1);
            this.star2 = (ImageView) itemView.findViewById(R.id.star2);
            this.star3 = (ImageView) itemView.findViewById(R.id.star3);
            this.star4 = (ImageView) itemView.findViewById(R.id.star4);
            this.star5 = (ImageView) itemView.findViewById(R.id.star5);

            this.start1h5 = (ImageView) itemView.findViewById(R.id.start1h5);
            this.start2h5 = (ImageView) itemView.findViewById(R.id.start2h5);
            this.start3h5 = (ImageView) itemView.findViewById(R.id.start3h5);
            this.start4h5 = (ImageView) itemView.findViewById(R.id.start4h5);


        }
    }
}
