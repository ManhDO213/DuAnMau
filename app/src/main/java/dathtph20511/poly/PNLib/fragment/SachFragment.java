package dathtph20511.poly.PNLib.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import dathtph20511.poly.PNLib.R;
import dathtph20511.poly.PNLib.adapter.LoaiSachSpinnerAdapter;
import dathtph20511.poly.PNLib.adapter.SachAdapter;
import dathtph20511.poly.PNLib.dao.LoaiSachDAO;
import dathtph20511.poly.PNLib.dao.SachDAO;
import dathtph20511.poly.PNLib.model.LoaiSach;
import dathtph20511.poly.PNLib.model.Sach;


public class SachFragment extends Fragment {
    LayoutInflater inflater;
    View viewDialogAddSach;
    FloatingActionButton floatingActionButton;
    Context mContext;

    ArrayList<Sach> arrSach = new ArrayList<>();
    ArrayList<LoaiSach> arrLoaiSach = new ArrayList<>();
    RecyclerView rcySach;
    SachDAO sachDAO;
    LoaiSachDAO loaiSachDAO;
    SachAdapter sachAdapter;
    Spinner spinner_loai_sach;
    LoaiSachSpinnerAdapter loaiSachAdapter;
    int maLoaiSach;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        inflater = getLayoutInflater();
        floatingActionButton = view.findViewById(R.id.float_btn_add_sach);
        viewDialogAddSach = inflater.inflate(R.layout.dialog_add_sach, null);

        rcySach= view.findViewById(R.id.rcv_sach);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        rcySach.setLayoutManager(layoutManager);

        loaiSachDAO = new LoaiSachDAO(mContext);
        sachDAO = new SachDAO(mContext);
        arrSach = (ArrayList<Sach>) sachDAO.getAllSach();
        sachAdapter = new SachAdapter(mContext, arrSach);
        rcySach.setAdapter(sachAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewDialogAddSach.getParent() != null) {
                    ((ViewGroup)viewDialogAddSach.getParent()).removeAllViews();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setView(viewDialogAddSach);
                EditText ed_name_sach, ed_gia_thue;
                Button btn_add_sach, btn_cancel_add_sach;
                TextInputLayout input_name_sach, input_price_sach;
                input_name_sach = viewDialogAddSach.findViewById(R.id.input_name_sach);
                input_price_sach = viewDialogAddSach.findViewById(R.id.input_price_sach);
                spinner_loai_sach = viewDialogAddSach.findViewById(R.id.spn_sach);
                ed_name_sach = viewDialogAddSach.findViewById(R.id.ed_name_sach);
                ed_gia_thue = viewDialogAddSach.findViewById(R.id.ed_gia_thue_sach);
                btn_add_sach = viewDialogAddSach.findViewById(R.id.btn_dialog_add_sach);
                btn_cancel_add_sach = viewDialogAddSach.findViewById(R.id.btn_dialog_cancle_add_sach);

                ed_gia_thue.setText("");
                ed_name_sach.setText("");
                input_name_sach.setError("");
                input_price_sach.setError("");

                arrLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAllLoaiSach();
                int check = 1;
                if(arrLoaiSach.size() == 0) {
                    check = -1;
                    Snackbar snackbar = Snackbar
                            .make(view, "Chưa có loại sách!", Snackbar.LENGTH_LONG)
                            .setAction("Đóng", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                    snackbar.show();
                }

                if (check> 0) {
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    arrLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAllLoaiSach();
                    loaiSachAdapter = new LoaiSachSpinnerAdapter(mContext, arrLoaiSach);
                    spinner_loai_sach.setAdapter(loaiSachAdapter);
                    spinner_loai_sach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            maLoaiSach = arrLoaiSach.get(i).maloai;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                    btn_cancel_add_sach.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                    btn_add_sach.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int check = 1;
                            if (ed_name_sach.getText().toString().isEmpty()) {
                                input_name_sach.setError("Tên sách đang trống!");
                                check = -1;
                            }else if(ed_name_sach.getText().length()<3) {
                                input_name_sach.setError("Tên sách không được nhỏ hơn 5 kí tự!");
                                check = -1;
                            }else if(ed_name_sach.getText().length()> 16) {
                                input_name_sach.setError("Tên sách không được dài hơn 20 kí tự");
                                check = -1;
                            }else {
                                input_name_sach.setError("");
                            }
                            if (ed_gia_thue.getText().toString().isEmpty()) {
                                input_price_sach.setError("Giá sách đang trống!");
                                check = -1;
                            }else if(ed_gia_thue.getText().length()<4 ||ed_gia_thue.getText().length()> 6) {
                                input_price_sach.setError("Giá sách không hợp lệ!");
                                check = -1;
                            }else {
                                input_price_sach.setError("");
                            }
                            if(check > 0) {
                                Sach sach = new Sach();
                                sach.tenSach = ed_name_sach.getText().toString();
                                sach.giaThue = Integer.parseInt(ed_gia_thue.getText().toString());
                                sach.maLoai = maLoaiSach;
                                sachDAO.insertSach(sach);
                                arrSach = new ArrayList<>();
                                arrSach = (ArrayList<Sach>) sachDAO.getAllSach();
                                sachAdapter = new SachAdapter(mContext, arrSach);
                                rcySach.setAdapter(sachAdapter);
                                alertDialog.dismiss();
                                Snackbar.make(view, "Thêm sách thành công!", Snackbar.LENGTH_LONG)
                                        .setBackgroundTint(ContextCompat.getColor(getActivity(), R.color.primary_color))
                                        .show();
                                ed_gia_thue.setText("");
                                ed_name_sach.setText("");
                                input_name_sach.setError("");
                                input_price_sach.setError("");
                            }
                        }
                    });
                }
            }
        });
    }

}