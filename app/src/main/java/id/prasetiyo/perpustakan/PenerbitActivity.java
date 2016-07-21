package id.prasetiyo.perpustakan;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by aoktox on 30/04/16.
 */
public class PenerbitActivity extends AppCompatActivity {

    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        manager = getFragmentManager();

        Fragment newFragment = new ListPenerbit();
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.replace(R.id.fragment,newFragment,"ListPenerbit").commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
