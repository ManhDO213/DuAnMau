package dathtph20511.poly.PNLib.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dathtph20511.poly.PNLib.R;

public class LoaiSachViewHolder extends RecyclerView.ViewHolder {
    TextView tv_name_loai_sach;
    ImageView img_delete_loai_sach;
    View item_Loai_sach;
    public LoaiSachViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_name_loai_sach = itemView.findViewById(R.id.tv_name_loai_sach_frag_loai_sach);
        img_delete_loai_sach = itemView.findViewById(R.id.img_delete_loai_sach);
        item_Loai_sach = itemView.findViewById(R.id.item_loai_sach);
    }
}
