package hcmute.nhom7.foody.adapter;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.database.HomeDAO;
import hcmute.nhom7.foody.model.MonAn;
import hcmute.nhom7.foody.model.User;
import hcmute.nhom7.foody.utils.ImageUtils;

public class ResultSearchFoodAdapter extends BaseAdapter {
    private Context context;
    private List<MonAn> monAnList;
    private int layout;
    private HomeDAO homeDAO;
    private User user;

    public ResultSearchFoodAdapter(Context context, int layout, List<MonAn> monAnList, HomeDAO homeDAO, User user) {
        this.context = context;
        this.layout = layout;
        this.monAnList = monAnList;
        this.homeDAO = homeDAO;
        this.user = user;
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

        LinearLayout layoutMonAn = (LinearLayout) view.findViewById(R.id.layoutMonAn);
        layoutMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBookFood(monAn);

            }
        });

        ImageView imgSave = (ImageView) view.findViewById(R.id.imageViewSave);
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Lưu món ăn", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void DialogBookFood(MonAn monAn) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_book_food);

        ImageView imgFood = dialog.findViewById(R.id.imageMonAnBook);
        imgFood.setImageBitmap(monAn.getBitMapImg());

        TextView textTenMonAn = dialog.findViewById(R.id.textTenMonAnBook);
        textTenMonAn.setText(monAn.getTenMonAn());

        TextView textGiaMonAn = dialog.findViewById(R.id.textGiaBook);
        textGiaMonAn.setText(monAn.getGia());

        TextView textSoluong = dialog.findViewById(R.id.textSoLuong);

        Button btnBook = dialog.findViewById(R.id.btnBook);
        Button btnHuy = dialog.findViewById(R.id.btnCancel);

        Button btnDecrease = (Button) dialog.findViewById(R.id.btnSub);
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(textSoluong.getText().toString());
                textSoluong.setText(Integer.toString(++soLuong));
            }
        });

        Button btnIncrease = (Button) dialog.findViewById(R.id.btnAdd);
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(textSoluong.getText().toString());
                textSoluong.setText(Integer.toString(--soLuong));
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(textSoluong.getText().toString());
                int soLuong = Integer.parseInt(textSoluong.getText().toString());
                if (soLuong == 0) {
                    Toast.makeText(context,
                            "Số lượng phải khác 0",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if(homeDAO.bookFood(user, monAn, soLuong)) {
                        Toast.makeText(context,
                                "Đã đặt món.",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context,
                                "Đặt món không thành công, vui lòng đặt lại",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
