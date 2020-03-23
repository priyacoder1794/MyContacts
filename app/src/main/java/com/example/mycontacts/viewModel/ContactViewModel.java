package com.example.mycontacts.viewModel;

import android.content.Context;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.LiveData;

import com.example.mycontacts.model.Contact;
import com.example.mycontacts.model.ContactRepository;

import java.util.List;

public class ContactViewModel {

    private ObservableArrayList<Contact> contacts;
    private ContactRepository repository;
    private LiveData<List<Contact>> contactsOfDb;

    public ContactViewModel(Context context) {
        contacts = new ObservableArrayList<>();
        repository = new ContactRepository(context);
    }

    public List<Contact> getContacts() {
        contacts.addAll(repository.fetchContacts());
        return contacts;
    }

    public LiveData<List<Contact>> getAllContacts() {
        contactsOfDb = repository.getAllContactList();
        return contactsOfDb;
    }

    public void makeFavContact(String mobileNumber){
        repository.makeFavContact(mobileNumber);
    }
    public void deleteContact(String mobileNumber){
        repository.deleteContact(mobileNumber);
    }

    public LiveData<List<Contact>> getAllFavContacts() {
        LiveData<List<Contact>> contactsOfDb = repository.getAllFavContacts();
        return contactsOfDb;
    }
    public LiveData<List<Contact>> getAllDeletedContacts() {
        LiveData<List<Contact>> contactsOfDb = repository.getAllDeletedContacts();
        return contactsOfDb;
    }


}
