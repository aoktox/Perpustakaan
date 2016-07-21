package id.prasetiyo.perpustakan;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import id.prasetiyo.perpustakan.helpers.DatabaseAccess;
import id.prasetiyo.perpustakan.models.Books;

/**
 * Created by aoktox on 28/04/16.
 */
public class DetailBuku extends Fragment {
    Button act_pinjam;
    //private SharedPreferences prefs;
    private static final String PREF_NAME = "PERPUSPref";
    private static final String CEK_LOGIN = "is_login";
    private static final String ID_ANGGOTA = "id_anggota";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail_buku, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ImageView backdrop = (ImageView) view.findViewById(R.id.backdrop);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(activity.getApplicationContext());
        databaseAccess.open();
        final SharedPreferences prefs = activity.getSharedPreferences(PREF_NAME,activity.MODE_PRIVATE);
        //activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = this.getArguments();
        final Books b = (Books) bundle.getSerializable("buku");
        //getActivity().setTitle(bundle.getString("judul","Detail buku"));
        getActivity().setTitle(b.getJudul());

        ((TextView) view.findViewById(R.id.det_judul)).setText(b.getJudul());
        ((TextView) view.findViewById(R.id.det_pengarang)).setText(b.getPengarang());
        ((TextView) view.findViewById(R.id.det_penerbit)).setText(b.getPenerbit());
        ((TextView) view.findViewById(R.id.det_stock)).setText(b.getJumlah());

        setImage(backdrop,b.getId_buku());
        act_pinjam = (Button) view.findViewById(R.id.btn_simoan);

        act_pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseAccess.add_pinjam(prefs.getInt("id_anggota",0),b.getId_buku());
                //Toast.makeText(getActivity().getApplicationContext(), "Pinjam", Toast.LENGTH_SHORT).show();
                Snackbar.make(v, "Pinjam buku selesai", Snackbar.LENGTH_LONG)
                        .setAction("Pinjam",null).show();
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Snackbar.make(view2, "Pinjam buku", Snackbar.LENGTH_LONG)
                        .setAction("Pinjam", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(),"To Do: Pinjam",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });*/

        return view;
    }

    private void setImage(ImageView b,int id){
        switch (id){
            case 1: b.setImageResource(R.drawable.sampul_01);
                break;
            case 2: b.setImageResource(R.drawable.sampul_02);
                break;
            case 3: b.setImageResource(R.drawable.sampul_03);
                break;
            case 4: b.setImageResource(R.drawable.sampul_04);
                break;
            case 5: b.setImageResource(R.drawable.sampul_05);
                break;
            case 6: b.setImageResource(R.drawable.sampul_06);
                break;
        }
    }
}
