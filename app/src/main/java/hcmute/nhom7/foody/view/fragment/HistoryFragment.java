package hcmute.nhom7.foody.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.adapter.CartAdapter;
import hcmute.nhom7.foody.adapter.HistoryAdapter;
import hcmute.nhom7.foody.database.HistoryDAO;
import hcmute.nhom7.foody.model.Bill;
import hcmute.nhom7.foody.model.User;

public class HistoryFragment extends Fragment {

    private ListView listViewHistory;
    private List<Bill> billList;
    private HistoryDAO historyDAO;
    private User user;
    private HistoryAdapter historyAdapter;

    public HistoryFragment(HistoryDAO historyDAO, User user) {
        this.historyDAO = historyDAO;
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        listViewHistory = view.findViewById(R.id.listViewHistory);
        billList = historyDAO.getAllBillOfUser(user);
        historyAdapter = new HistoryAdapter(getContext(), R.layout.custom_layout_bill, billList, historyDAO, user);
        listViewHistory.setAdapter(historyAdapter);

        return view;
    }
}