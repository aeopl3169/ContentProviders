package com.shiva.contentproviders;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

/*
    private static final String TAG = "ContentProviderDemo";
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 20;
    private boolean firstTimeLoaded = false;
    private String mSelectionCluse = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " = ?";
    private String[] mSelectionArguments = new String[]{"Ajay"};
    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
*/

    private TextView textViewQueryResult;
//    private Button buttonLoadData;

    private String[] mColumnProjection = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewQueryResult = findViewById(R.id.textViewQueryResult);
//        buttonLoadData = findViewById(R.id.buttonLoadData);
//        buttonLoadData.setOnClickListener(this);

        // java.lang.RuntimeException: Unable to start activity ComponentInfo{com.shiva.contentproviders/com.shiva.contentproviders.MainActivity}: java.lang.SecurityException: Permission Denial: opening provider com.android.providers.contacts.ContactsProvider2 from ProcessRecord{b30009b 4892:com.shiva.contentproviders/u0a78} (pid=4892, uid=10078) requires android.permission.READ_CONTACTS or android.permission.WRITE_CONTACTS
        // Initializing the content resolver with getContentResolver()
        ContentResolver contentResolver = getContentResolver();
        /*
         * @param 1 - The table to be opened
         * @param 2 - represents the name of the column
         * @param 3 - Selection clause (Ex: WHERE clause)
         * @param 4 - Selection args, if we use ? in third parameter then selection args need to be passed according to the ? specified. (Ex: WHERE value substitution)
         * @param 5 - Sort order, if we are sorting, selection clause and selection args(i.e. 3th and 4th parameters) should be null.
         *
         * */
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                mColumnProjection,
                null,
                null,
                null);

        // Cursor should be greater than 0 and not null
        if (cursor != null && cursor.getCount() > 0) {

            StringBuilder stringBuilderQueryResult = new StringBuilder("");
            // moveToNext() will move to the next row in the table.
            while (cursor.moveToNext()) {
                stringBuilderQueryResult.append(cursor.getString(0) + " , " + cursor.getString(1) + " , " + cursor.getString(2) + "\n");
            }
            textViewQueryResult.setText(stringBuilderQueryResult.toString());
        } else {
            textViewQueryResult.setText("No Contacts in this device");
        }
    }

    @Override
    public void onClick(View v) {

    }
}