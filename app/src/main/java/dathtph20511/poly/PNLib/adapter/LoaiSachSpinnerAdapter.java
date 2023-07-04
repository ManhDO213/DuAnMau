package dathtph20511.poly.PNLib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import dathtph20511.poly.PNLib.R;
import dathtph20511.poly.PNLib.model.LoaiSach;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private ArrayList<LoaiSach> arrLoaiSach;
    TextView tv_name_loai_sach;

    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> arrLoaiSach) {
        super(context, 0, arrLoaiSach);
        this.arrLoaiSach = arrLoaiSach;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.loai_sach_item_spinner, null);
        }
        final  LoaiSach loaiSach = arrLoaiSach.get(position);
        if(loaiSach != null) {
            tv_name_loai_sach = view.findViewById(R.id.tv_name_loai_sach_spinner_frag_sach);
            tv_name_loai_sach.setText(loaiSach.tenloai);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.loai_sach_item_spinner_drop, null);
        }
        final  LoaiSach loaiSach = arrLoaiSach.get(position);
        if(loaiSach != null) {
            tv_name_loai_sach = view.findViewById(R.id.tv_name_loai_sach_spinner_frag_sach_drop);
            tv_name_loai_sach.setText(loaiSach.tenloai);
        }
        return view;
    }
}
