package com.example.mycontacts.view.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontacts.R;
import com.example.mycontacts.model.Contact;
import com.example.mycontacts.view.adapter.ContactAdapter;
import com.example.mycontacts.view.interfaces.ISelectContacts;
import com.example.mycontacts.viewModel.ContactViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity  extends AppCompatActivity implements ISelectContacts {

    private ContactViewModel contactViewModel;
    private ArrayList<Contact> allContact = new ArrayList<>();
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        contactViewModel = new ContactViewModel(ContactsActivity.this);
        findView();
    }

    private void findView(){

        RecyclerView recyclerView = findViewById(R.id.recyclerContacts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ContactsActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        contactAdapter = new ContactAdapter(ContactsActivity.this,allContact,this);
        recyclerView.setAdapter(contactAdapter);
        getContactList();
    }

    private void getContactList(){
        allContact.clear();
        contactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                allContact.addAll(contacts);
                contactAdapter.notifyDataSetChanged();
                Log.e("AllList",""+allContact.toString());
            }
        });

    }

    @Override
    public void makeFavContact(int position, String mobileNumber) {
        contactViewModel.makeFavContact(mobileNumber);
        getContactList();

    }

    @Override
    public void deleteContact(int position, String mobileNumber) {
        contactViewModel.deleteContact(mobileNumber);
        getContactList();
    }
}
