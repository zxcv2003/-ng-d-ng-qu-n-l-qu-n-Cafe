package fpt.edu.Sarangcoffee.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.edu.Sarangcoffee.R;
import fpt.edu.Sarangcoffee.adapter.ChiTietHoaDonAdapter;
import fpt.edu.Sarangcoffee.dao.HangHoaDAO;
import fpt.edu.Sarangcoffee.dao.HoaDonChiTietDAO;
import fpt.edu.Sarangcoffee.dao.HoaDonDAO;
import fpt.edu.Sarangcoffee.model.HangHoa;
import fpt.edu.Sarangcoffee.model.HoaDon;
import fpt.edu.Sarangcoffee.model.HoaDonChiTiet;
import fpt.edu.Sarangcoffee.utils.MyToast;
import fpt.edu.Sarangcoffee.utils.XDate;

public class ChiTietHoaDonActivity extends AppCompatActivity {
    RecyclerView recyclerViewThucUong;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    HangHoaDAO hangHoaDAO;
    HoaDonDAO hoaDonDAO;
    TextView tvMaBan, tvGioVao, tvGioRa;
    ImageView ivBack;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);
        initView();
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        hangHoaDAO = new HangHoaDAO(this);
        hoaDonDAO = new HoaDonDAO(this);
        loadData();
        fillActivity();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showComfirmDeleteHDCT();
            }
        });

    }

    private void showComfirmDeleteHDCT() {
        // Xoá hoá đơn chi tiết và hoá đơn (Theo mã hoá đơn)
        AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietHoaDonActivity.this, R.style.AlertDialogTheme)
                .setMessage("Bạn có muốn xoá hoá đơn HD0775098507" + getMaHoaDon() + "?")
                .setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (hoaDonDAO.deleteHoaDon(getMaHoaDon()) && hoaDonChiTietDAO.deleteHoaDonChiTietByMaHoaDon(getMaHoaDon())) {
                            MyToast.successful(ChiTietHoaDonActivity.this, "Xoá thành công");
                            onBackPressed();
                        } else {
                            MyToast.error(ChiTietHoaDonActivity.this, "Xoá không thành côn");
                        }
                    }
                })
                .setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    private void fillActivity() {
        HoaDon hoaDon = hoaDonDAO.getByMaHoaDon(getMaHoaDon());
        tvMaBan.setText("B0" + hoaDon.getMaBan());
        tvGioVao.setText(XDate.toStringDateTime(hoaDon.getGioVao()));
        tvGioRa.setText(XDate.toStringDateTime(hoaDon.getGioRa()));
    }

    private String getMaHoaDon() {
        Intent intent = getIntent();
        return intent.getStringExtra(HoaDonActivity.MA_HOA_DON);
    }

    private void loadData() {
        String maHoaDon = getMaHoaDon();
        ArrayList<HoaDonChiTiet> listHDCT = hoaDonChiTietDAO.getByMaHoaDon(maHoaDon);
        ArrayList<HangHoa> list = new ArrayList<>();
        for (int i = 0; i < listHDCT.size(); i++) {
            list.add(hangHoaDAO.getByMaHangHoa(String.valueOf(listHDCT.get(i).getMaHangHoa())));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewThucUong.setLayoutManager(linearLayoutManager);
        ChiTietHoaDonAdapter adapter = new ChiTietHoaDonAdapter(this, list, listHDCT);
        recyclerViewThucUong.setAdapter(adapter);
    }

    private void initView() {
        recyclerViewThucUong = findViewById(R.id.recyclerViewThucUong);
        tvMaBan = findViewById(R.id.tvMaBan);
        tvGioVao = findViewById(R.id.tvGioVao);
        tvGioRa = findViewById(R.id.tvGioRa);
        ivBack = findViewById(R.id.ivBack);
        btnDelete = findViewById(R.id.btnDelete);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_in_left, R.anim.anim_out_right);
    }
}