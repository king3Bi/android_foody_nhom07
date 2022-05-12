package hcmute.nhom7.foody.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.database.HistoryDAO;
import hcmute.nhom7.foody.model.Bill;
import hcmute.nhom7.foody.model.Booking;
import hcmute.nhom7.foody.model.Food;
import hcmute.nhom7.foody.model.User;

public class HistoryAdapter extends BaseAdapter {
    private List<Bill> billList;
    private Context context;
    private int layout;
    private User user;
    private HistoryDAO historyDAO;

    public HistoryAdapter(Context context, int layout, List<Bill> billList, HistoryDAO historyDAO, User user) {
        this.context = context;
        this.layout = layout;
        this.billList = billList;
        this.user = user;
        this.historyDAO = historyDAO;
    }

    @Override
    public int getCount() {
        return this.billList.size();
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
        Bill bill = billList.get(i);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView textBillId = view.findViewById(R.id.textBillId);
        textBillId.setText("Mã hóa đơn: " + bill.getId());

        TextView textAddress = view.findViewById(R.id.textAddressBill);
        textAddress.setText("Địa chỉ: " + bill.getAddress());

        TextView textPhoneNumber = view.findViewById(R.id.textPhoneNumberBill);
        textPhoneNumber.setText("SĐT: " + bill.getPhoneNumber());

        TextView textPrice = view.findViewById(R.id.textTotalPriceBill);
        textPrice.setText("Tổng tiền: " + Double.toString(bill.getTotalPrice()) + " VND");

        return view;
    }
}
