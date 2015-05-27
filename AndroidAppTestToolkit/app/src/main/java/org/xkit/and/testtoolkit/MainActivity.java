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

                SystemContactsUtil.addPhoneContact(MainActivity.this, "", "", null); // add some empty data
                SystemContactsUtil.addPhoneContact(MainActivity.this, "corner case", "", null);
                SystemContactsUtil.addPhoneContact(MainActivity.this, "corner case", "", null);
                SystemContactsUtil.addPhoneContact(MainActivity.this, "corner case", "+1324442", null);
                SystemContactsUtil.addPhoneContact(MainActivity.this, "corner case", "+13244424", null);
                SystemContactsUtil.addPhoneContact(MainActivity.this, "Hai Guo", "+86 136 8192 1024", null);
                SystemContactsUtil.addPhoneContact(MainActivity.this, "I'm owner", "+8613681921024", null);
                SystemContactsUtil.addPhoneContact(MainActivity.this, "I'm owner", "+8613127560350", null);
                SystemContactsUtil.addPhoneContact(MainActivity.this, "I'm owner", "+14086010240", null);
                SystemContactsUtil.addPhoneContact(MainActivity.this, "XX__XA", "+14088883239", null);
                SystemContactsUtil.addPhoneContact(MainActivity.this, "Beckon Care", "+14088883239", null);
                SystemContactsUtil.addPhoneContact(MainActivity.this, "Beckon Care", "+1 408 888 3239", null);
                SystemContactsUtil.addPhoneContact(MainActivity.this, "Beckon Care", "+1(408) 888-3239", null);
                SystemContactsUtil.addPhoneContact(MainActivity.this, "Beckon Care", "+1 (408) 888-3239", null);

                for (int i = 0; i < COUNT; i++) {
                    boolean zhOren = (Math.random() * 2) < 1 ? true : false;

                    String fullName;
                    if (zhOren) {
                        int first = (int) (Math.random() * lengthZh);
                        int last = (int) (Math.random() * lengthZh);
                        fullName = UserData.NAME_ZH.substring(first, first + 1) + UserData.NAME_ZH.substring(last, last + 1);
                    } else {
                        fullName = enTokens[(int) (Math.random() * lengthEn)] + " " + enTokens[(int) (Math.random() * lengthEn)];
                    }

                    if (fullName.equals("")) { // do not need to exclude string with blank chars
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

    private static final String[] BK_USERS = {"+8610020003330", "+8610020003331", "+8610020003332", "+8610020003333", "+8610020003334", "+8610020003335", "+8610020003336", "+8610020003337", "+8610020003338", "+8610020003339",
            "+8610020003340", "+8610020003341", "+8610020003342", "+8610020003343", "+8610020003344", "+8610020003345", "+8610020003346", "+8610020003347", "+8610020003348", "+8610020003349",
            "+8610020003350", "+8610020003351", "+8610020003352", "+8610020003353", "+8610020003354", "+8610020003355", "+8610020003356", "+8610020003357", "+8610020003358", "+8610020003359",
            "+8610020003360", "+8610020003361", "+8610020003362", "+8610020003363", "+8610020003364", "+8610020003365", "+8610020003366", "+8610020003367", "+8610020003368", "+8610020003369",
            "+8610020003370", "+8610020003371", "+8610020003372", "+8610020003373", "+8610020003374", "+8610020003375", "+8610020003376", "+8610020003377", "+8610020003378", "+8610020003379",
            "+8610020003380", "+8610020003381", "+8610020003382", "+8610020003383", "+8610020003384", "+8610020003385", "+8610020003386", "+8610020003387", "+8610020003388", "+8610020003389",
            "+8610020003390", "+8610020003391", "+8610020003392", "+8610020003393", "+8610020003394", "+8610020003395", "+8610020003396", "+8610020003397", "+8610020003398", "+8610020003399",
            "+8610020003400", "+8610020003401", "+8610020003402", "+8610020003403", "+8610020003404", "+8610020003405", "+8610020003406", "+8610020003407", "+8610020003408", "+8610020003409",
            "+8610020003410", "+8610020003411", "+8610020003412", "+8610020003413", "+8610020003414", "+8610020003415", "+8610020003416", "+8610020003417", "+8610020003418", "+8610020003419",
            "+8610020003420", "+8610020003421", "+8610020003422", "+8610020003423", "+8610020003424", "+8610020003425", "+8610020003426", "+8610020003427", "+8610020003428", "+8610020003429",
            "+8610020003430", "+8610020003431", "+8610020003432", "+8610020003433", "+8610020003434", "+8610020003435", "+8610020003436", "+8610020003437", "+8610020003438", "+8610020003439",
            "+8610020003440", "+8610020003441", "+8610020003442", "+8610020003443", "+8610020003444", "+8610020003445", "+8610020003446", "+8610020003447", "+8610020003448", "+8610020003449",
            "+8610020003450", "+8610020003451", "+8610020003452", "+8610020003453", "+8610020003454", "+8610020003455", "+8610020003456", "+8610020003457", "+8610020003458", "+8610020003459"};

    public void onFillBKContacts(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < BK_USERS.length; i++) {
                    String fullName = "NPC";
                    if (i < BK_USERS.length / 2) {
                        fullName = "测试人员";
                    }
                    fullName = fullName + "-" + i;
                    SystemContactsUtil.addPhoneContact(MainActivity.this, fullName, BK_USERS[i], null);
                }

                showToast("Add 140 Beckon contacts done");
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
