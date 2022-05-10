package hcmute.nhom7.foody.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.model.MonAn;
import hcmute.nhom7.foody.utils.ImageUtils;

public class ResultSearchFoodAdapter extends BaseAdapter {
    private Context context;
    private List<MonAn> monAnList;
    private int layout;

    public ResultSearchFoodAdapter(Context context, int layout, List<MonAn> monAnList) {
        this.context = context;
        this.layout = layout;
        this.monAnList = monAnList;
    }
    @Override
    public int getCount() {
        return monAnList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MonAn monAn =monAnList.get(i);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        Bitmap imgBitMap = ImageUtils.decodeImg(monAn.getImage());

        ImageView imageViewFood = (ImageView) view.findViewById(R.id.imageMonAn);
        imageViewFood.setImageBitmap(imgBitMap);

        TextView textTenMonAn = (TextView) view.findViewById(R.id.textTenMonAn);
        textTenMonAn.setText(monAn.getTenMonAn());

        TextView textMota = (TextView) view.findViewById(R.id.textMoTa);
        textMota.setText(monAn.getMoTa());

        TextView textGia = (TextView) view.findViewById(R.id.textGia);
        textGia.setText(monAn.getGia());

        return view;
    }
}
