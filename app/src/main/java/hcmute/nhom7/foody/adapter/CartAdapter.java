package hcmute.nhom7.foody.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Hashtable;
import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.database.CartDAO;
import hcmute.nhom7.foody.database.SavedDAO;
import hcmute.nhom7.foody.model.Booking;
import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.User;
import hcmute.nhom7.foody.utils.ResourceUtils;

public class CartAdapter extends BaseAdapter {
    private List<Booking> bookingList;
    private Context context;
    private int layout;
    private CartDAO cartDAO;
    private User user;
    private Hashtable<Integer, Boolean> mapCheckBox;

    public CartAdapter(Context context, int layout, List<Booking> bookingList, CartDAO cartDAO, User user, Hashtable<Integer, Boolean> mapCheckBox) {
        this.context = context;
        this.bookingList = bookingList;
        this.layout = layout;
        this.cartDAO = cartDAO;
        this.user = user;
        this.mapCheckBox = mapCheckBox;
    }

    @Override
    public int getCount() {
        return bookingList.size();
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
        Booking booking = bookingList.get(i);
        Food food = cartDAO.getFoodId(booking.getFoodId());

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView textTenMonAn = view.findViewById(R.id.textTenMonAnBooking);
        textTenMonAn.setText(food.getName());

        TextView textSoLuong = view.findViewById(R.id.textSoLuongBooking);
        textSoLuong.setText(Integer.toString(booking.getQuantity()));

        TextView textGia = view.findViewById(R.id.textGiaBooking);
        textGia.setText(food.getPriceString());

        CheckBox checkBox = view.findViewById(R.id.checkboxBooking);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mapCheckBox.replace(i, b);
            }
        });

        return view;
    }

}
