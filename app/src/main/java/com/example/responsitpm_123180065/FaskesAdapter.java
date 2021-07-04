package com.example.responsitpm_123180065;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.responsitpm_123180065.data.faskes.DataItem;

import java.util.ArrayList;

public class FaskesAdapter extends RecyclerView.Adapter<FaskesAdapter.ViewHolder> {
    private ArrayList<DataItem> faskes = new ArrayList<>();

    public void setFaskes(ArrayList<DataItem> faskes) {
        this.faskes.clear();
        this.faskes.addAll(faskes);
        // update tampilan jika terdapat perubahan data
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FaskesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(faskes.get(position));
    }

    @Override
    public int getItemCount() {
        return faskes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama_rs,alamat;
        private Button maps;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_rs = itemView.findViewById(R.id.nama_rs);
            alamat = itemView.findViewById(R.id.alamat);
            maps = itemView.findViewById(R.id.maps);
        }

        @SuppressLint("SetTextI18n")
        public void bind(DataItem dataItem) {
            nama_rs.setText(dataItem.getNama());
            alamat.setText(dataItem.getAlamat());

            maps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri Intent = Uri.parse("rs: " + dataItem.getLatitude() + "," + dataItem.getLongitude() + "?q=" + Uri.encode(dataItem.getNama()));
                    Intent maps = new Intent(android.content.Intent.ACTION_VIEW, Intent);
                    maps.setPackage("com.google.apps.maps");
                    itemView.getContext().startActivity(maps);
                }
            });
        }
    }
}
