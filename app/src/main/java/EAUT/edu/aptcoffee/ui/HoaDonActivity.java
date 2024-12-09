package fpt.edu.Sarangcoffee.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import fpt.edu.Sarangcoffee.R;
import fpt.edu.Sarangcoffee.adapter.HoaDonAdapter;
import fpt.edu.Sarangcoffee.dao.HoaDonDAO;
import fpt.edu.Sarangcoffee.interfaces.ItemHoaDonOnClick;
import fpt.edu.Sarangcoffee.model.HoaDon;

public class HoaDonActivity extends AppCompatActivity {
    public static final String MA_HOA_DON = "maHoaDon";
    Toolbar toolbar;
    RecyclerView recyclerViewHoaDon;
    HoaDonDAO hoaDonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        initToolBar();

        initView();
        hoaDonDAO = new HoaDonDAO(this);
        loadData();

    }

    private void loadData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewHoaDon.setLayoutManager(linearLayoutManager);

        HoaDonAdapter adapter = new HoaDonAdapter(HoaDonActivity.this, hoaDonDAO.getByTrangThai(HoaDon.DA_THANH_TOAN), new ItemHoaDonOnClick() {
            @Override
            public void itemOclick(View view, HoaDon hoaDon) {
                Intent intent = new Intent(HoaDonActivity.this, ChiTietHoaDonActivity.class);
                intent.putExtra(MA_HOA_DON, String.valueOf(hoaDon.getMaHoaDon()));
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left);
            }
        });
        recyclerViewHoaDon.setAdapter(adapter);
    }

    private void initView() {
        recyclerViewHoaDon = findViewById(R.id.recyclerViewHoaDon);
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbarHoaDon);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_in_left, R.anim.anim_out_right);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}