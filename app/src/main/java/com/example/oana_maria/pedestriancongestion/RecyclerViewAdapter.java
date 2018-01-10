package com.example.oana_maria.pedestriancongestion;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oana_maria.pedestriancongestion.models.Location;

import java.util.List;

/**
 * Created by Oana-Maria on 06/01/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Location> locationList;
    public final OnLocationClickedListener onLocationClickedListener;


    public RecyclerViewAdapter(OnLocationClickedListener onLocationClickedListener) {
        this.onLocationClickedListener = onLocationClickedListener;
    }

    public interface OnLocationClickedListener {
        void onLocationClicked(Location location);
    }

    public void updateLocations(List<Location> locations) {
        this.locationList = locations;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext() );
        View view = layoutInflater.inflate(R.layout.location_item, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(locationList.get(position));
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    @Override
    public int getItemViewType(int position){
        return R.layout.location_item;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView latitude, longitude,time_stamp;
        private Location location;

        public ViewHolder(View itemView) {
            super(itemView);
            latitude =itemView.findViewById(R.id.latitude);
            longitude= itemView.findViewById(R.id.longitude);
            time_stamp= itemView.findViewById(R.id.time_stamp);


        }


        private void bind(final Location location) {

            this.location = location;
            latitude.setText(location.getLatitude());
            longitude.setText(location.getLongitude());
            time_stamp.setText(location.getTime_stamp());
            itemView.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLocationClickedListener.onLocationClicked(location);
                }
            }));


        }


    }
}
