package hcmute.nhom7.foody.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.view.profile.ProfileActivity;

public class MoreFragment extends Fragment {

    LinearLayout layout;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_more, container, false);
        layout = mView.findViewById(R.id.linearLayoutMoreProfile);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        return mView;
    }
}
