package org.xkit.and.testtoolkit;

import android.content.*;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class SystemContactsUtil {
    public static void addPhoneContact(Context context, String contactName, String phoneNumber, byte[] contactPhoto) {
        ContentValues values = new ContentValues();
        Uri rawContactUri = context.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);

        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contactName);
        context.getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);

        values.clear();
        values.put(android.provider.ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber);
        values.put(ContactsContract.Data.DATA2, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE); // default is mobile number

        context.getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);

        if (contactPhoto != null) {
            values.clear();
            values.put(android.provider.ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Photo.PHOTO, contactPhoto);
            context.getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
        }
    }

    /**
     * This will delete all the contacts from database, BE REALLY SURE what you are doing.
     */
    public static void deleteContacts(Context ctx) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ops.add(ContentProviderOperation.newDelete(
                ContactsContract.RawContacts.CONTENT_URI.buildUpon()
                        .appendQueryParameter(
                                ContactsContract.CALLER_IS_SYNCADAPTER, "true")
                        .build()
        ).build());
        try {
            ctx.getContentResolver()
                    .applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }
}
