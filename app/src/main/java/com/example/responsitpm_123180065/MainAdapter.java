package com.example.responsitpm_123180065;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.responsitpm_123180065.data.kumulatif.ContentItem;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<ContentItem> kumulatif = new ArrayList<>();

    public void setKumulatif(ArrayList<ContentItem> kumulatif) {
            this.kumulatif.clear();
            this.kumulatif.addAll(kumulatif);
        // update tampilan jika terdapat perubahan data
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kasus, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(kumulatif.get(position).getTanggal());
        holder.JumKonfimasi.setText(String.valueOf(kumulatif.get(position).getCONFIRMATION()));
        holder.JumSembuh.setText(String.valueOf(kumulatif.get(position).getConfirmationSelesai()));
        holder.JumMeninggal.setText(String.valueOf(kumulatif.get(position).getConfirmationMeninggal()));
    }


    @Override
    public int getItemCount() {
        return kumulatif.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date,JumSembuh,JumMeninggal,JumKonfimasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            JumSembuh = itemView.findViewById(R.id.JumSembuh);
            JumKonfimasi = itemView.findViewById(R.id.JumTerKonfimasi);
            JumMeninggal = itemView.findViewById(R.id.JumMeninggal);
        }
    }
}
