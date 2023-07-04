package dathtph20511.poly.PNLib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import dathtph20511.poly.PNLib.dao.ThuThuDAO;
import dathtph20511.poly.PNLib.fragment.AddUserFragment;
import dathtph20511.poly.PNLib.fragment.ChangePassFragment;
import dathtph20511.poly.PNLib.fragment.DoanhThuFragment;
import dathtph20511.poly.PNLib.fragment.LoaiSachFragment;
import dathtph20511.poly.PNLib.fragment.PhieuMuonFragment;
import dathtph20511.poly.PNLib.fragment.SachFragment;
import dathtph20511.poly.PNLib.fragment.ThanhVienFragment;
import dathtph20511.poly.PNLib.fragment.TopFragment;
import dathtph20511.poly.PNLib.model.ThuThu;

public class MainActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    FragmentManager fragmentManager;
    AddUserFragment addUserFragment;
    ChangePassFragment changePassFragment;
    DoanhThuFragment doanhThuFragment;
    LoaiSachFragment loaiSachFragment;
    PhieuMuonFragment phieuMuonFragment;
    SachFragment sachFragment;
    ThanhVienFragment thanhVienFragment;
    TopFragment topFragment;

    View dialogLogOut;
    LayoutInflater layoutInflater;

    View mHeaderView;
    TextView tvUsername;
    ThuThuDAO thuThuDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



            Context context = this;
            layoutInflater = getLayoutInflater();//anh xa dialog logout
            dialogLogOut = layoutInflater.inflate(R.layout.dialog_log_out, null);


            drawerLayout = findViewById(R.id.drawer_app);//drawer app
            navigationView = findViewById(R.id.menu_drawer);
            ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
            toggle.syncState();



            toolbar = findViewById(R.id.toolbar_app);//custom tool bar
            setSupportActionBar(toolbar);
            toolbar.setTitleTextColor(Color.WHITE);

             ActionBar actionBar = getSupportActionBar();
            actionBar.setHomeAsUpIndicator(R.drawable.icon_menu_drawer);
            actionBar.setDisplayHomeAsUpEnabled(true);

            //hien thi username len header
            mHeaderView = navigationView.getHeaderView(0);
            tvUsername = mHeaderView.findViewById(R.id.tv_username_header);
            Intent intent = getIntent();
            String user = intent.getStringExtra("user");
            thuThuDAO = new ThuThuDAO(this);
            ThuThu thuThu = thuThuDAO.getIdTT(user);
            String username = thuThu.hoTen;
            tvUsername.setText("Welcome " + username+ "!");

            if(!user.equalsIgnoreCase("admin")) {
                navigationView.getMenu().findItem(R.id.nav_them_nguoi_dung).setVisible(false);
            }

            addUserFragment = new AddUserFragment();
            changePassFragment= new ChangePassFragment();
            doanhThuFragment = new DoanhThuFragment();
            loaiSachFragment = new LoaiSachFragment();
            phieuMuonFragment = new PhieuMuonFragment();
            sachFragment = new SachFragment();
            thanhVienFragment = new ThanhVienFragment();
            topFragment = new TopFragment();

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, phieuMuonFragment).commit();
            //an menu
//        navigationView.getMenu().findItem(R.id.nav_them_nguoi_dung).setVisible(false);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_phieu_muon:
                            setTitle("Quản lý phiếu mượn");
                            PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, phieuMuonFragment).commit();
                            break;
                        case R.id.nav_loai_sach:
                            setTitle("Quản lý loại sách");
                            LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, loaiSachFragment).commit();
                            break;
                        case R.id.nav_sach:
                            setTitle("Quản lý sách");
                            SachFragment sachFragment = new SachFragment();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, sachFragment).commit();
                            break;
                        case R.id.nav_thanh_vien:
                            setTitle("Quản lý thành viên");
                            ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, thanhVienFragment).commit();
                            break;
                        case R.id.nav_top:
                            setTitle("10 sách mượn nhiều nhất");
                            TopFragment topFragment = new TopFragment();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, topFragment).commit();
                            break;
                        case R.id.nav_doanh_thu:
                            setTitle("Doanh thu");
                            DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, doanhThuFragment).commit();
                            break;
                        case R.id.nav_them_nguoi_dung:
                            setTitle("Thêm người dùng");
                            AddUserFragment addUserFragment = new AddUserFragment();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, addUserFragment).commit();
                            break;
                        case R.id.nav_doi_mat_khau:
                            setTitle("Đổi mật khẩu");
                            ChangePassFragment changePassFragment = new ChangePassFragment();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, changePassFragment).commit();
                            break;
                        case R.id.nav_dang_xuat:
                            if(dialogLogOut.getParent() != null) {
                                ((ViewGroup)dialogLogOut.getParent()).removeAllViews();
                            }
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setView(dialogLogOut);

                            Button btn_logout, btn_cancel_logout;
                            btn_logout = dialogLogOut.findViewById(R.id.btn_dialog_log_out);
                            btn_cancel_logout = dialogLogOut.findViewById(R.id.btn_dialog_cancel_log_out);

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                            btn_logout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                    MainActivity.this.finish();
                                }
                            });
                            btn_cancel_logout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alertDialog.dismiss();
                                }
                            });
                            break;
                    }
                    drawerLayout.closeDrawer(navigationView);
                    return false;
                }
            });
        }
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if(item.getItemId() == android.R.id.home) {
                drawerLayout.openDrawer(navigationView);
            }
            return  super.onOptionsItemSelected(item);
        }
    }

