package com.example.mycontacts.model;


import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mycontacts.model.db.ContactDao;
import com.example.mycontacts.model.db.ContactDatabase;
import com.example.mycontacts.util.Utility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactRepository {

    private Context context;
    private static ContactDao contactDao;

    public ContactRepository(Context context) {
        this.context = context;
        ContactDatabase contactDatabase = ContactDatabase.getDataBase(context);
        contactDao = contactDatabase.contactDao();

    }

    public List<Contact> fetchContacts() {
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String phoneNo = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String contactID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String photoUri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

                Log.e("contactList", "getAllContacts: " + name + " " + phoneNo + " " + photoUri);
                contact.setName(name);
                contact.setPhoneNumber(phoneNo);
                contact.setPhotoUri(photoUri);
                contact.setIsFav(Utility.NOT_SELECTED_CONTACT);
                contact.setIsDeleted(Utility.NOT_SELECTED_CONTACT);
                addContact(contact);
                contacts.add(contact);

            }while (cursor.moveToNext());
            cursor.close();
        }
        return contacts;
    }

    public LiveData<List<Contact>> getAllContactList(){
        return contactDao.getAllContacts();
    }
    //method to add contact
    private void addContact(Contact contact) {
        new AddContact().execute(contact);
    }

    //Async task to delete contact
    public static class AddContact extends AsyncTask<Contact, Void, Void> {
        @Override
        protected Void doInBackground(Contact... notes) {
            contactDao.insertContact(notes[0]);
            return null;
        }
    }



    public void makeFavContact(String mobileNumber){
        new updateFavContact().execute(mobileNumber);
    }
    //Async task to add note
    public static class updateFavContact extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... mobileNumber) {
            contactDao.favContact(mobileNumber[0],Utility.FAV_CONTACT);
            return null;
        }
    }

    public void removeFromFav(String mobileNumber){
        new removeFromFavContact().execute(mobileNumber);
    }
    //Async task to add note
    public static class removeFromFavContact extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... mobileNumber) {
            contactDao.removeFavContact(mobileNumber[0],Utility.NOT_SELECTED_CONTACT);
            return null;
        }
    }
    //method to delete contact
    public void deleteContact(String mobileNumber){
        new updateDeletedContact().execute(mobileNumber);
    }


    public static class updateDeletedContact extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... mobileNumber) {
            contactDao.deleteContact(mobileNumber[0],Utility.DELETE_CONTACT);
            return null;
        }
    }

    public void restoreData(){
        new restoreContacts().execute("");
    }

    public static class restoreContacts extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... mobileNumber) {
            contactDao.restoreDeletedData(Utility.NOT_SELECTED_CONTACT);
            return null;
        }
    }


    public LiveData<List<Contact>> getAllFavContacts(){
        return contactDao.getAllFavContacts();
    }
    public LiveData<List<Contact>> getAllDeletedContacts(){
        return contactDao.getAllDeletedContacts();
    }



}
