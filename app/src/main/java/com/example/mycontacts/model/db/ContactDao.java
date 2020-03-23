package com.example.mycontacts.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.mycontacts.model.Contact;
import java.util.List;
@Dao
public interface ContactDao {

    // Dao method to get all notes
    @Query("SELECT * FROM CONTACT WHERE isDeleted==1")
    LiveData<List<Contact>> getAllContacts();

    // Dao method to insert note
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertContact(Contact note);

    // Dao method to delete note
    @Query("UPDATE CONTACT SET isDeleted=:value WHERE CONTACT.phoneNumber=:mob_number")
    void deleteContact(String mob_number,String value);

    // Dao method to delete note
    @Query("UPDATE CONTACT SET isFav=:value WHERE CONTACT.phoneNumber=:mob_number")
    void favContact(String mob_number,String value);

    @Query("SELECT * FROM Contact WHERE isFav==2")
    LiveData<List<Contact>> getAllFavContacts();

    @Query("SELECT * FROM Contact WHERE isDeleted==3")
    LiveData<List<Contact>> getAllDeletedContacts();

    @Query("DELETE FROM CONTACT")
    public void clearTable();

}
