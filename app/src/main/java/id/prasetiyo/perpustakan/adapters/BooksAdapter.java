package id.prasetiyo.perpustakan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import id.prasetiyo.perpustakan.R;
import id.prasetiyo.perpustakan.models.Books;

/**
 * Created by aoktox on 30/04/16.
 */
public class BooksAdapter extends ArrayAdapter<Books> {

    public BooksAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public BooksAdapter(Context context, int resource, List<Books> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_view_buku, null);
        }

        Books p = getItem(position);

        if (p != null) {
            TextView judul = (TextView) v.findViewById(R.id.judul_buku);
            TextView pengarang = (TextView) v.findViewById(R.id.pengarang);
            ImageView thumnail = (ImageView) v.findViewById(R.id.thumbnail);

            if (judul != null) {
                judul.setText(p.getJudul());
            }

            if (pengarang != null) {
                pengarang.setText(p.getPengarang());
            }

            setImage(thumnail,p.getId_buku());
        }

        return v;
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
