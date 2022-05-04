package hcmute.nhom7.foody.adapter;

import android.content.Context;
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
import hcmute.nhom7.foody.model.Comment;
import hcmute.nhom7.foody.view.DetailActivity;

public class ItemCommentAdapter extends RecyclerView.Adapter<ItemCommentAdapter.ViewHolder> {
    private List<Comment> commentList;
    private Context context;


    public ItemCommentAdapter(Context context,List<Comment> comments) {
        this.context = context;
        this.commentList = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_comment,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = this.commentList.get(position);

        int imageResId = this.getDrawableResIdByName(comment.getAvatar());

        holder.imgAvatar.setImageResource(imageResId);
        holder.txtUsername.setText(comment.getUsername());
        holder.txtTitle.setText(comment.getTitle());
        holder.txtContent.setText(comment.getContent());
    }

    @Override
    public int getItemCount() {
        int soComment = commentList.size();
        if(soComment > 3){
            return 3;
        }else {
            return commentList.size();
        }
    }

    private void handleRecycleItemClick(RecyclerView recyclerView, View itemView){
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Comment comment = this.commentList.get(itemPosition);

        Toast.makeText(this.context, comment.getUsername(), Toast.LENGTH_SHORT).show();
    }

    private int getDrawableResIdByName(String resName) {
        String packageName = context.getPackageName();

        int resId = context.getResources().getIdentifier(resName, "drawable", packageName);
        Log.i(DetailActivity.LOG_TAG, "Res Name: "+resName+"==> Res ID = "+resId);
        return resId;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView txtUsername;
        TextView txtTitle;
        TextView txtContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAvatar = itemView.findViewById(R.id.imageviewAvatar);
            txtUsername = itemView.findViewById(R.id.textUsername);
            txtTitle = itemView.findViewById(R.id.textTitle);
            txtContent =itemView.findViewById(R.id.textContent);
        }
    }
}
