package hcmute.nhom7.foody.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.model.Quan;
import hcmute.nhom7.foody.utils.ImageUtils;
import hcmute.nhom7.foody.view.DetailActivity;
import hcmute.nhom7.foody.view.home.fragment.RecentFragment;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder> {
    private List<Quan> quanList;
    private Context context;

    public CustomRecyclerViewAdapter(Context context, List<Quan> quans){
        this.context = context;
        this.quanList = quans;
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
        Quan quan = this.quanList.get(position);

        int imageResId = this.getDrawableResIdByName(quan.getImage());
        Bitmap imgResBitMap = ImageUtils.decodeImg(quan.getImage());

//        holder.imgQuan.setImageResource(imageResId);
        holder.imgQuan.setImageBitmap(imgResBitMap);
        holder.txtTenQuan.setText(quan.getName());
        holder.txtComment.setText(quan.getComment());
        holder.txtType.setText(quan.getType());
        if(holder.txtType.getText().equals(context.getString(R.string.review))){
            holder.txtType.setBackgroundColor(context.getColor(R.color.blue));
        }else
            holder.txtType.setBackgroundColor(context.getColor(R.color.red_light));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("quanan", quan);
                context.startActivity(intent);
            }
        });

        if(quan.getType().equals(context.getString(R.string.delivery))){

        }
    }

    public int getDrawableResIdByName(String resName) {
        String packageName = context.getPackageName();

        int resId = context.getResources().getIdentifier(resName, "drawable", packageName);
        Log.i(RecentFragment.LOG_TAG, "Res Name: "+resName+"==> Res ID = "+resId);
        return resId;
    }

    @Override
    public int getItemCount() {
        return quanList.size();
    }

    private void handleRecycleItemClick(RecyclerView recyclerView, View itemView){
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Quan quan = this.quanList.get(itemPosition);

        Toast.makeText(this.context, quan.getName(), Toast.LENGTH_SHORT).show();
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
