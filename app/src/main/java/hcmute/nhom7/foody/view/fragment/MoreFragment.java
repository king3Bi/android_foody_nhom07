package hcmute.nhom7.foody.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import hcmute.nhom7.foody.R;
import hcmute.nhom7.foody.database.MoreDAO;
import hcmute.nhom7.foody.model.User;
import hcmute.nhom7.foody.view.LoginActivity;
import hcmute.nhom7.foody.view.profile.ProfileActivity;

public class MoreFragment extends Fragment {

    LinearLayout layout;
    View mView;
    TextView textViewUsername;
    Button btnLogOut;
    private MoreDAO moreDAO;
    private User user;

    public MoreFragment(MoreDAO moreDAO, User user) {
        this.moreDAO = moreDAO;
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_more, container, false);
        layout = mView.findViewById(R.id.linearLayoutMoreProfile);
        textViewUsername = mView.findViewById(R.id.textUsername);
        btnLogOut = mView.findViewById(R.id.buttonLogout);

        textViewUsername.setText(this.user.getHoTen());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                SharedPreferences sp= context.getSharedPreferences("Login", context.MODE_PRIVATE);
                SharedPreferences.Editor Ed=sp.edit();
                Ed.putString("email", null);
                Ed.putString("password", null);
                Ed.commit();

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return mView;
    }
}
