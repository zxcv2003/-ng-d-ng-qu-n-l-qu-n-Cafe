package fpt.edu.Sarangcoffee.model;

import androidx.annotation.NonNull;

public class Ban {
    private int maBan;
    private int trangThai;
    public static final int CO_KHACH = 1;
    public static final int CON_TRONG = 0;


    public Ban() {
    }

    public Ban(int trangThai) {
        this.trangThai = trangThai;
    }

    public Ban(int maBan, int trangThai) {
        this.maBan = maBan;
        this.trangThai = trangThai;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @NonNull
    @Override
    public String toString() {
        return "Ban{" +
                "maBan=" + maBan +
                ", trangThai=" + trangThai +
                '}';
    }
}
