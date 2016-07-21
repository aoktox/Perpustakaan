package id.prasetiyo.perpustakan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences prefs;
    private static final String PREF_NAME = "PERPUSPref";
    private static final String CEK_LOGIN = "is_login";
    private static final String ID_ANGGOTA = "id_anggota";

    private ImageButton daftar,berita,penerbit,pinjam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        boolean is_login = prefs.getBoolean(CEK_LOGIN, false);
        if (!is_login){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        daftar = (ImageButton) findViewById(R.id.list_books);
        berita = (ImageButton) findViewById(R.id.list_news);
        penerbit = (ImageButton) findViewById(R.id.list_penerbit);
        pinjam  = (ImageButton) findViewById(R.id.list_pinjam);

        pinjam.setOnClickListener(this);
        daftar.setOnClickListener(this);
        berita.setOnClickListener(this);
        penerbit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent i;
        switch (id){
            case R.id.list_books:
                i = new Intent(this, BookActivity.class);
                startActivity(i);
                break;

            case R.id.list_pinjam:

                break;

            case R.id.list_news:
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                break;

            case R.id.list_penerbit:
                i = new Intent(this, PenerbitActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.act_logout) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        if (id == R.id.act_to_profile) {
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
