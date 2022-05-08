package hcmute.nhom7.foody.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;
import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.adapter.CustomRecyclerViewAdapter;
import hcmute.nhom7.foody.adapter.ItemCommentAdapter;
import hcmute.nhom7.foody.adapter.ItemMenuAdapter;
import hcmute.nhom7.foody.database.Database;
import hcmute.nhom7.foody.model.Comment;
import hcmute.nhom7.foody.model.MonAn;
import hcmute.nhom7.foody.model.Quan;
import hcmute.nhom7.foody.utils.ImageUtils;
import hcmute.nhom7.foody.view.home.fragment.RecentFragment;

public class DetailActivity extends AppCompatActivity {
    public static final String LOG_TAG = "AndroidExample";
    private RecyclerView recyclerView, recyclerviewProfile;
    private Database db;
    List<Comment> itemList;
    TextView txtTenQuan, txtTitleToolbar;
    ImageView imgQuan;
    Toolbar toolbar;
    LinearLayout layoutMenu;
    Quan quan;
    CustomRecyclerViewAdapter customRecyclerViewAdapter;
    ItemMenuAdapter itemMenuAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = new Database(this);
        List<MonAn> foods = db.getAllFood();

        quan = (Quan) getIntent().getParcelableExtra("quanan");

        txtTenQuan = (TextView) findViewById(R.id.textNameQuan);
        imgQuan = (ImageView) findViewById(R.id.imageQuan);
        txtTitleToolbar = (TextView) findViewById(R.id.textTitleToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        layoutMenu = (LinearLayout) findViewById(R.id.layoutMenu);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        recyclerView = findViewById(R.id.recyclerviewComment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemCommentAdapter adapter =  new ItemCommentAdapter(this, getListComment());
        recyclerView.setAdapter(adapter);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerviewProfile = findViewById(R.id.recyclerviewMenu);
        recyclerviewProfile.setHasFixedSize(true);
        recyclerviewProfile.setLayoutManager(new LinearLayoutManager(this));
        ItemMenuAdapter menuAdapter =  new ItemMenuAdapter(this, foods);
        recyclerviewProfile.setAdapter(menuAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        Bitmap imgBitMap = ImageUtils.decodeImg(quan.getImage());

        txtTenQuan.setText(quan.getName());
        txtTitleToolbar.setText(quan.getName());
//        imgQuan.setImageResource(getDrawableResIdByName(quan.getImage()));
        imgQuan.setImageBitmap(imgBitMap);
        if(quan.getType().equals(getString(R.string.Delivery))){
            layoutMenu.setVisibility(View.VISIBLE);
        }
    }

    private List<Comment> getListComment() {
        List<Comment> list = new ArrayList<Comment>();
        Comment comment1 = new Comment("snowee","User", "Title", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
        Comment comment2 = new Comment("snowee","User", "Title", "comment comment commet");
        Comment comment3 = new Comment("snowee","User", "Title", "comment comment commet");

        list.add(comment1);
        list.add(comment2);
        list.add(comment3);
        return list;
    }

    private List<MonAn> getListMonAn(){
        List<MonAn> list = new ArrayList<MonAn>();
        MonAn monAn1 = new MonAn("snowee", "Tên món ăn", "Mô tả", "Giá");
        MonAn monAn2 = new MonAn("snowee", "Tên món ăn", "Mô tả", "Giá");
        MonAn monAn3 = new MonAn("snowee", "Tên món ăn", "Mô tả", "Giá");

        list.add(monAn1);
        list.add(monAn2);
        list.add(monAn3);

        return list;
    }

    private int getDrawableResIdByName(String resName) {
        String packageName = getPackageName();

        int resId = getResources().getIdentifier(resName, "drawable", packageName);
        Log.i(RecentFragment.LOG_TAG, "Res Name: "+resName+"==> Res ID = "+resId);
        return resId;
    }
}
