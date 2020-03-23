package com.example.mycontacts.model.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mycontacts.model.Contact;

@Database(entities = Contact.class, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {

    private static ContactDatabase mInstance;
    public static ContactDatabase getDataBase(Context context){
        if (mInstance == null)
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDatabase.class, "contact_db")
                    .build();

        return mInstance;
    }
    //method to remove instance
    public static void closeDatabase() {
        mInstance = null;
    }

    //define note dao ( data access object )
    public abstract ContactDao contactDao();
}
