package id.prasetiyo.perpustakan;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import id.prasetiyo.perpustakan.helpers.DatabaseAccess;

/**
 * Created by aoktox on 30/04/16.
 */
public class ListPenerbit extends Fragment {

    ListView listView;
    FragmentManager manager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_buku, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.setTitle("Daftar penerbit");
        //activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        manager = getFragmentManager();
        listView = (ListView) view.findViewById(R.id.list_buku);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(activity.getApplicationContext());
        databaseAccess.open();
        final ArrayList<Map<String, String>> penerbit = databaseAccess.getPenerbit();

        SimpleAdapter adapter = new SimpleAdapter(
                activity.getApplicationContext(),
                penerbit,
                R.layout.list_view_penerbit,
                new String[] { "nama", "jumlahBuku", },
                new int[] { R.id.nama_penerbit, R.id.jumlah_buku}
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment newFragment = new ListBuku();
                Bundle bundle = new Bundle();
                bundle.putSerializable("penerbit", (Serializable) penerbit.get(position));
                newFragment.setArguments(bundle);
                FragmentTransaction transaction =manager.beginTransaction();
                transaction.setCustomAnimations(R.animator.slide_up,
                        R.animator.slide_down,
                        R.animator.slide_up,
                        R.animator.slide_down);
                transaction.replace(R.id.fragment,newFragment,"DetailPenerbit").addToBackStack(null).commit();
            }
        });

        /*ArrayList<Pengguna> users = databaseAccess.getUsers();
        for (Pengguna user : users) {
            Log.d("User", "Nama: "+user.getNama());
        }*/

//        ArrayList<Peminjaman> pinjaman = databaseAccess.getPeminjaman();
//        ArrayList<Peminjaman> pinjamanAgus = databaseAccess.getPeminjamanUser(1);
//        Toast.makeText(getActivity().getApplicationContext(),"Jumlah :"+pinjaman.size()+" "+pinjamanAgus.size(),Toast.LENGTH_SHORT).show();

        return view;
    }
}