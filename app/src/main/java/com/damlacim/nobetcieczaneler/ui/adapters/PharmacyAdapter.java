package com.damlacim.nobetcieczaneler.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.damlacim.nobetcieczaneler.R;
import com.damlacim.nobetcieczaneler.databinding.PharmacyListItemBinding;
import com.damlacim.nobetcieczaneler.model.PharmacyModel;

import java.util.List;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.ViewHolder> {

    private final Context context;
    public List<PharmacyModel> pharmacyModelList;
    public PharmacyClickListener pharmacyClickListener;

    public interface PharmacyClickListener{
        void onClick(int position,PharmacyModel pharmacy);
    }

    public PharmacyAdapter(List<PharmacyModel> pharmacyModelList, Context context) {
        this.pharmacyModelList = pharmacyModelList;
        this.context = context;
    }

    public void setOnClickListener(PharmacyClickListener listener){
        this.pharmacyClickListener = listener;
    }

    @NonNull
    @Override
    public PharmacyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(PharmacyListItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyAdapter.ViewHolder holder, final int position) {
        PharmacyModel pharmacyObj = pharmacyModelList.get(position);
        if (!pharmacyObj.getEczaneAdi().contains("ECZANESİ"))
            holder.listItemBinding.tvPharmacyName.setText(pharmacyObj.getEczaneAdi() + " " +context.getString(R.string.pharmacy));
        else
            holder.listItemBinding.tvPharmacyName.setText(pharmacyObj.getEczaneAdi());

        holder.listItemBinding.tvPharmacyAddress.setText(pharmacyObj.getEczaneAdres());
        holder.itemView.setOnClickListener(v -> pharmacyClickListener.onClick(holder.getAdapterPosition(),pharmacyObj));
    }

    @Override
    public int getItemCount() {
        return pharmacyModelList == null ? 0 :
                pharmacyModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final PharmacyListItemBinding listItemBinding;

        public ViewHolder(PharmacyListItemBinding listItemBinding) {
            super(listItemBinding.getRoot());
            this.listItemBinding = listItemBinding;
        }
    }
}

