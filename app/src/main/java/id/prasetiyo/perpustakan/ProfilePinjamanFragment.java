package id.prasetiyo.perpustakan;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.prasetiyo.perpustakan.helpers.DatabaseAccess;
import id.prasetiyo.perpustakan.models.Peminjaman;
import id.prasetiyo.perpustakan.models.Pengguna;

/**
 * Created by aoktox on 01/05/16.
 */
public class ProfilePinjamanFragment extends Fragment {
    private SharedPreferences prefs;
    private static final String PREF_NAME = "PERPUSPref";
    private static final String ID_ANGGOTA = "id_anggota";
    private Pengguna user;

    public ProfilePinjamanFragment() {
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
        View view = inflater.inflate(R.layout.fragment_pinjaman_profile, container, false);
        Activity activity = getActivity();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(activity.getApplicationContext());
        databaseAccess.open();
        prefs = activity.getSharedPreferences(PREF_NAME,activity.MODE_PRIVATE);
        user = databaseAccess.getUsers(prefs.getInt(ID_ANGGOTA,0));
        ArrayList<Peminjaman> pinjaman = databaseAccess.getPeminjamanUser(user.getId_anggota());

        ((TextView) view.findViewById(R.id.detail_jumlah_pinjam)).setText("Total pinjaman : \n"+pinjaman.size());

        return view;
    }
}