package fpt.edu.Sarangcoffee.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpt.edu.Sarangcoffee.R;
import fpt.edu.Sarangcoffee.dao.HoaDonChiTietDAO;
import fpt.edu.Sarangcoffee.interfaces.ItemHoaDonOnClick;
import fpt.edu.Sarangcoffee.model.HoaDon;
import fpt.edu.Sarangcoffee.utils.XDate;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.HoaDonViewHolder>{
    Context context;
    ArrayList<HoaDon> list;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    ItemHoaDonOnClick itemHoaDonOnClick;

    public HoaDonAdapter(Context context, ArrayList<HoaDon> list, ItemHoaDonOnClick itemHoaDonOnClick) {
        this.context = context;
        this.list = list;
        this.hoaDonChiTietDAO = new HoaDonChiTietDAO(context);
        this.itemHoaDonOnClick = itemHoaDonOnClick;
    }

    @NonNull
    @Override
    public HoaDonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_hoa_don, parent, false);
        return new HoaDonViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HoaDonViewHolder holder, int position) {
        HoaDon hoaDon = list.get(position);
        if(hoaDon == null){
            return;
        }
        holder.tvMaHoaDon.setText("HD0775098507"+hoaDon.getMaHoaDon());
        holder.tvtitlGioVao.setText(XDate.toStringDateTime(hoaDon.getGioVao()));
        holder.tvGioVao.setText(XDate.toStringDateTime(hoaDon.getGioVao()));
        holder.tvGioRa.setText(XDate.toStringDateTime(hoaDon.getGioRa()));
        holder.tvGiaTien.setText(hoaDonChiTietDAO.getGiaTien(hoaDon.getMaHoaDon())+"VND");
        holder.tvChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemHoaDonOnClick.itemOclick(view, hoaDon);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list == null){
            return 0;
        }
        return list.size();
    }

    public static class HoaDonViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaHoaDon, tvtitlGioVao, tvGioVao, tvGioRa, tvGiaTien, tvChiTiet;
        public HoaDonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaHoaDon = itemView.findViewById(R.id.tvMaHoaDon);
            tvtitlGioVao = itemView.findViewById(R.id.titleGioVao);
            tvGioVao = itemView.findViewById(R.id.tvGioVao);
            tvGioRa = itemView.findViewById(R.id.tvGioRa);
            tvGiaTien = itemView.findViewById(R.id.tvGiaTien);
            tvChiTiet = itemView.findViewById(R.id.tvChiTiet);

        }
    }
}
