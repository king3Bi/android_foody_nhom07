package hcmute.nhom7.foody.adapter;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.database.HomeDAO;
import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.User;
import hcmute.nhom7.foody.utils.ResourceUtils;

public class ResultSearchFoodAdapter extends BaseAdapter {
    private Context context;
    private List<Food> foodList;
    private int layout;
    private HomeDAO homeDAO;
    private User user;

    public ResultSearchFoodAdapter(Context context, int layout, List<Food> foodList, HomeDAO homeDAO, User user) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
        this.homeDAO = homeDAO;
        this.user = user;
    }
    @Override
    public int getCount() {
        return foodList.size();
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
        Food food = foodList.get(i);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        if (homeDAO.checkSavedFood(user.getId(), food.getId())) {
            ImageView imgRemove = view.findViewById(R.id.imageViewSave);
            imgRemove.setImageResource(ResourceUtils.getDrawableResIdByName(this.context, "check_icon_red"));
        }

//        Bitmap imgBitMap = ImageUtils.decodeImg(monAn.getImage());

        ImageView imageViewFood = (ImageView) view.findViewById(R.id.imageMonAn);
        imageViewFood.setImageBitmap(food.getBitMapImg());

        TextView textTenMonAn = (TextView) view.findViewById(R.id.textTenMonAn);
        textTenMonAn.setText(food.getName());

        TextView textMota = (TextView) view.findViewById(R.id.textMoTa);
        textMota.setText(food.getDescription());

        TextView textGia = (TextView) view.findViewById(R.id.textGia);
        textGia.setText(food.getPriceString());

        LinearLayout layoutMonAn = (LinearLayout) view.findViewById(R.id.layoutMonAn);
        layoutMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBookFood(user, food);

            }
        });

        ImageView imgSave = (ImageView) view.findViewById(R.id.imageViewSave);
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!homeDAO.checkSavedFood(user.getId(), food.getId())) {
                    if (homeDAO.saveFood(user, food)) {
                        ImageView imgRemove = view.findViewById(R.id.imageViewSave);
                        imgRemove.setImageResource(ResourceUtils.getDrawableResIdByName(context, "check_icon_red"));
                        Toast.makeText(context, "Lưu món ăn thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Lưu món ăn không thành công!\n" +
                                "Vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    private void DialogBookFood(User user, Food food) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_book_food);

        ImageView imgFood = dialog.findViewById(R.id.imageMonAnBook);
        imgFood.setImageBitmap(food.getBitMapImg());

        TextView textTenMonAn = dialog.findViewById(R.id.textTenMonAnBook);
        textTenMonAn.setText(food.getName());

        TextView textGiaMonAn = dialog.findViewById(R.id.textGiaBook);
        textGiaMonAn.setText(food.getPriceString());

        TextView textSoluong = dialog.findViewById(R.id.textSoLuong);
        TextView textTongTien = dialog.findViewById(R.id.textTongTien);

        ImageButton btnAddToCart = dialog.findViewById(R.id.btnAddToCart);
        Button btnHuy = dialog.findViewById(R.id.btnCancel);

        Button btnDecrease = (Button) dialog.findViewById(R.id.btnSub);
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(textSoluong.getText().toString());
                if (soLuong == 0) {
                    return;
                }
                textSoluong.setText(Integer.toString(--soLuong));
            }
        });

        Button btnIncrease = (Button) dialog.findViewById(R.id.btnAdd);
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soLuong = Integer.parseInt(textSoluong.getText().toString());
                textSoluong.setText(Integer.toString(++soLuong));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(textSoluong.getText().toString());
                int soLuong = Integer.parseInt(textSoluong.getText().toString());
                if (soLuong == 0) {
                    Toast.makeText(context,
                            "Số lượng phải khác 0",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if(homeDAO.bookFood(user, food, soLuong)) {
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
