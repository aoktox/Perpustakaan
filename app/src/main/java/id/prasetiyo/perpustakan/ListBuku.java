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

import java.util.ArrayList;
import java.util.Map;

import id.prasetiyo.perpustakan.adapters.BooksAdapter;
import id.prasetiyo.perpustakan.helpers.DatabaseAccess;
import id.prasetiyo.perpustakan.models.Books;

public class ListBuku extends Fragment {

    ListView listView;
    FragmentManager manager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_buku, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.setTitle("Daftar buku");
        //activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        manager = getFragmentManager();
        listView = (ListView) view.findViewById(R.id.list_buku);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(activity.getApplicationContext());
        databaseAccess.open();
        ArrayList<Books> tmp;
        Bundle bundle = this.getArguments();
        //Cek apakah activity sebelumnya adalah penerbit
        if (bundle != null) {
            Map<String,String> data = (Map<String, String>)bundle.getSerializable("penerbit");
            tmp = databaseAccess.getBooksByPenerbit(data.get("nama"));
            activity.setTitle("Daftar buku | "+data.get("nama"));
        }
        else {
            tmp = databaseAccess.getBooks();
        }

        final ArrayList<Books> books = tmp;
        listView.setAdapter(new BooksAdapter(activity.getApplicationContext(),R.layout.list_view_buku,books));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment newFragment = new DetailBuku();
                Bundle bundle = new Bundle();
                bundle.putSerializable("buku", books.get(position));
                newFragment.setArguments(bundle);
                FragmentTransaction transaction =manager.beginTransaction();
                transaction.setCustomAnimations(R.animator.slide_up,
                        R.animator.slide_down,
                        R.animator.slide_up,
                        R.animator.slide_down);
                transaction.replace(R.id.fragment,newFragment,"DetailBuku").addToBackStack(null).commit();
            }
        });
        return view;
    }
}