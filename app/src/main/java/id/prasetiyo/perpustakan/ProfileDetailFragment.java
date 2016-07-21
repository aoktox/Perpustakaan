package id.prasetiyo.perpustakan;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.prasetiyo.perpustakan.helpers.DatabaseAccess;
import id.prasetiyo.perpustakan.models.Pengguna;

/**
 * Created by aoktox on 01/05/16.
 */
public class ProfileDetailFragment extends Fragment{
    private SharedPreferences prefs;
    private static final String PREF_NAME = "PERPUSPref";
    private static final String ID_ANGGOTA = "id_anggota";
    private Pengguna user;

    public ProfileDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_profile, container, false);
        Activity activity = getActivity();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(activity.getApplicationContext());
        databaseAccess.open();
        prefs = activity.getSharedPreferences(PREF_NAME,activity.MODE_PRIVATE);
        user = databaseAccess.getUsers(prefs.getInt(ID_ANGGOTA,0));

        ((TextView) view.findViewById(R.id.detail_nama_user)).setText(user.getNama());
        ((TextView) view.findViewById(R.id.detail_email_user)).setText(user.getEmail());
        ((TextView) view.findViewById(R.id.detail_hp_user)).setText(user.getNo_telp());
        ((TextView) view.findViewById(R.id.detail_alamat_user)).setText(user.getAlamat());


        return view;
    }
}
