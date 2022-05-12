package hcmute.nhom7.foody.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.database.SavedDAO;
import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.Saved;
import hcmute.nhom7.foody.model.User;
import hcmute.nhom7.foody.utils.ResourceUtils;

public class SavedAdapter extends BaseAdapter {
    private List<Saved> savedList;
    private Context context;
    private int layout;
    private SavedDAO savedDAO;
    private User user;
    private ListView listView;

    public SavedAdapter(Context context, int layout, List<Saved> savedList, SavedDAO savedDAO, User user, ListView listView) {
        this.context = context;
        this.savedList = savedList;
        this.layout = layout;
        this.savedDAO = savedDAO;
        this.user = user;
        this.listView = listView;
    }

    @Override
    public int getCount() {
        return savedList.size();
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
        Saved saved = savedList.get(i);
        Food food = savedDAO.getFoodId(saved.getFoodId());

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        ImageView imgRemove = view.findViewById(R.id.imageViewSave);
        imgRemove.setImageResource(ResourceUtils.getDrawableResIdByName(this.context, "remove_icon"));

        TextView textTenMonAn = view.findViewById(R.id.textTenMonAn);
        textTenMonAn.setText(food.getName());

        TextView textMoTa = view.findViewById(R.id.textMoTa);
        textMoTa.setText(food.getDescription());

        TextView textGia = view.findViewById(R.id.textGia);
        textGia.setText(food.getPriceString());

        ImageView imageMonAn = view.findViewById(R.id.imageMonAn);
        imageMonAn.setImageBitmap(food.getBitMapImg());

        ImageView imgRemoveSaved = view.findViewById(R.id.imageViewSave);
        imgRemoveSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (savedDAO.removeSaved(user.getId(), food.getId())) {
                    loadSaved();
                    Toast.makeText(context, "Xóa món ăn đã lưu", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Chưa xóa món ăn đã lưu", Toast.LENGTH_SHORT).show();
                }

            }
        });

        LinearLayout layoutMonAn = view.findViewById(R.id.layoutMonAn);
        layoutMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBookFood(user, food);
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

        Button btnBook = dialog.findViewById(R.id.btnBook);
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
                    if (savedDAO.bookFood(user, food, soLuong)) {
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

    private void loadSaved() {
    }
}
