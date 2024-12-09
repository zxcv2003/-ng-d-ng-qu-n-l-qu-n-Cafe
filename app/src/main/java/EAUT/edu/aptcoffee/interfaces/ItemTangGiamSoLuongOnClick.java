package fpt.edu.Sarangcoffee.interfaces;

import android.view.View;

import fpt.edu.Sarangcoffee.model.HangHoa;
import fpt.edu.Sarangcoffee.model.HoaDonChiTiet;

public interface ItemTangGiamSoLuongOnClick {
    void itemOclick(View view, int indext, HoaDonChiTiet hoaDonChiTiet, HangHoa hangHoa);
    void itemOclickDeleteHDCT(View view, HoaDonChiTiet hoaDonChiTiet);
}
