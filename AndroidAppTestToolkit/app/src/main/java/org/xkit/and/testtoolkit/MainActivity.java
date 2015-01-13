package org.xkit.and.testtoolkit;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String TAG = "AndroidTestToolkit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onFillupContacts(View view) {
        final int COUNT = 5000;

        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] enTokens = UserData.NAME_EN.split(" ");
                int lengthEn = enTokens.length;

                int lengthZh = UserData.NAME_ZH.length();

                String[] namePrefix = UserData.COUNTRY_PREFIX.split(" ");
                int lengthPrefix = namePrefix.length;

                for (int i = 0; i < COUNT; i++) {
                    boolean zhOren = (Math.random() * 2) < 1 ? true : false;

                    String fullName = null;
                    if (zhOren) {
                        int first = (int) (Math.random() * lengthZh);
                        int last = (int) (Math.random() * lengthZh);
                        fullName = UserData.NAME_ZH.substring(first, first + 1) + UserData.NAME_ZH.substring(last, last + 1);
                    } else {
                        fullName = enTokens[(int) (Math.random() * lengthEn)] + " " + enTokens[(int) (Math.random() * lengthEn)];
                    }

                    if (fullName == null || fullName.equals("")) { // do not need to exclude string with blank chars
                        Log.w(TAG, "onFillupContacts " + fullName);
                        continue;
                    }

                    int prefix = (int) (Math.random() * lengthPrefix);

                    int numberCap = (int) (Math.random() * 11);

                    String national = "";
                    for (int z = 0; z < numberCap; z++) {
                        national = national + (int) (Math.random() * 10);
                    }

                    String fullNumber = namePrefix[prefix] + national;

                    SystemContactsUtil.addPhoneContact(MainActivity.this, fullName, fullNumber, null);
                }

                showToast(COUNT + " contacts has been inserted");
            }
        }).start();
    }

    public void onCleanContacts(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemContactsUtil.deleteContacts(MainActivity.this);
                showToast("Contacts has been cleaned");
            }
        }).start();
    }

    private void showToast(final String message) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
