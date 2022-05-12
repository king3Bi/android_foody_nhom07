package hcmute.nhom7.foody.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.utils.ImageUtils;
import hcmute.nhom7.foody.view.DetailActivity;

public class ItemMenuAdapter extends RecyclerView.Adapter<ItemMenuAdapter.ViewHolder> {
    private List<Food> foodList;
    private Context context;


    public ItemMenuAdapter(Context context,List<Food> foods) {
        this.context = context;
        this.foodList = foods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_menu,parent,false);
        ItemMenuAdapter.ViewHolder viewHolder = new ItemMenuAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = this.foodList.get(position);

        int imageResId = this.getDrawableResIdByName(food.getImage());
//        Bitmap imgBitmap = ImageUtils.decodeImg(food.getImage());

        holder.imgMonAn.setImageResource(imageResId);
//        holder.imgMonAn.setImageBitmap(imgBitmap);
        System.out.println("Ten mon an: " + food.getName());
        holder.txtTenMonAn.setText(food.getName());
        holder.txtMoTa.setText(food.getDescription());
        holder.txtGia.setText(food.getPriceString() +" VNĐ");

    }


    @Override
    public int getItemCount() {
        int soComment = foodList.size();
        if(soComment > 3){
            return 3;
        }else {
            return foodList.size();
        }
    }

    private void handleRecycleItemClick(RecyclerView recyclerView, View itemView){
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Food food = this.foodList.get(itemPosition);

        Toast.makeText(this.context, food.getName(), Toast.LENGTH_SHORT).show();
    }

    private int getDrawableResIdByName(String resName) {
        String packageName = context.getPackageName();

        int resId = context.getResources().getIdentifier(resName, "drawable", packageName);
        Log.i(DetailActivity.LOG_TAG, "Res Name: "+resName+"==> Res ID = "+resId);
        return resId;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMonAn;
        TextView txtTenMonAn, txtMoTa, txtGia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMonAn = itemView.findViewById(R.id.imageMonAn);
            txtTenMonAn = itemView.findViewById(R.id.textTenMonAn);
            txtMoTa = itemView.findViewById(R.id.textMoTa);
            txtGia =itemView.findViewById(R.id.textGia);
        }
    }
}
