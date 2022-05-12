package hcmute.nhom7.foody.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.database.HomeDAO;
import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.User;
import hcmute.nhom7.foody.utils.ImageUtils;
import hcmute.nhom7.foody.view.home.fragment.RecentFragment;

public class CustomRecyclerFoodViewAdapter extends RecyclerView.Adapter<CustomRecyclerFoodViewAdapter.ViewHolder> {
    private List<Food> foodList;
    private Context context;
    private HomeDAO homeDAO;
    private User user;

    public CustomRecyclerFoodViewAdapter(Context context, List<Food> foods, User user, HomeDAO homeDAO){
        this.context = context;
        this.foodList = foods;
        this.user = user;
        this.homeDAO = homeDAO;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_recycleview_recent,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = this.foodList.get(position);

        int imageResId = this.getDrawableResIdByName(food.getImage());
//        Bitmap imgResBitMap = ImageUtils.decodeImg(quan.getImage());

        holder.imgQuan.setImageResource(imageResId);
//        holder.imgQuan.setImageBitmap(food.getBitMapImg());
        holder.txtTenQuan.setText(food.getName());
        holder.txtComment.setText(food.getPriceString());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddFoodToCart(user, food);
            }
        });

    }
    private void DialogAddFoodToCart(User user, Food food) {
        System.out.println("Dialog food: " + food.getName());
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_food_to_cart);

        int idres = ImageUtils.getIdImgFromDrawable(context, food.getImage());
        ImageView imgFood = dialog.findViewById(R.id.imageMonAnBook);
//        imgFood.setImageBitmap(food.getBitMapImg());
        imgFood.setImageResource(idres);

        TextView textTenMonAn = dialog.findViewById(R.id.textTenMonAnBook);
        textTenMonAn.setText(food.getName());

        TextView textGiaMonAn = dialog.findViewById(R.id.textGiaBook);
        textGiaMonAn.setText(food.getPriceString());

        TextView textSoluong = dialog.findViewById(R.id.textSoLuong);
//        TextView textTongTien = dialog.findViewById(R.id.textTongTien);

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
                System.out.println("Add food: " + food.getName());
                System.out.println("So luong: " + textSoluong.getText().toString());
                int soLuong = Integer.parseInt(textSoluong.getText().toString());
                if (soLuong == 0) {
                    Toast.makeText(context,
                            "Số lượng phải khác 0",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if(homeDAO.addFoodToCart(user, food, soLuong)) {
                        Toast.makeText(context,
                                "Đã thêm vào giỏ hàng.",
                                Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context,
                                "Đã có lỗi xảy ra, vui lòng thử lại",
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

    public int getDrawableResIdByName(String resName) {
        String packageName = context.getPackageName();

        int resId = context.getResources().getIdentifier(resName, "drawable", packageName);
        Log.i(RecentFragment.LOG_TAG, "Res Name: "+resName+"==> Res ID = "+resId);
        return resId;
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    private void handleRecycleItemClick(RecyclerView recyclerView, View itemView){
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Food food = this.foodList.get(itemPosition);

        Toast.makeText(this.context, food.getName(), Toast.LENGTH_SHORT).show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgQuan;
        TextView txtTenQuan;
        TextView txtComment;
        TextView txtType;
        TextView txtTitleToolbar;
        CardView cardView;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgQuan = itemView.findViewById(R.id.imageviewQuan);
            txtTenQuan = itemView.findViewById(R.id.textTenQuan);
            txtComment = itemView.findViewById(R.id.textComment);
            txtType = itemView.findViewById(R.id.textType);
            cardView = itemView.findViewById(R.id.cardview);
            txtTitleToolbar = itemView.findViewById(R.id.textTitleToolbar);
            linearLayout=itemView.findViewById(R.id.linearLayout);
        }
    }
}
