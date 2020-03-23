package com.example.mycontacts.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontacts.R;
import com.example.mycontacts.model.Contact;
import com.example.mycontacts.view.adapter.FavAndDelContactAdapter;
import com.example.mycontacts.viewModel.ContactViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {

    private ContactViewModel contactViewModel;
    private ArrayList<Contact> allContact = new ArrayList<>();
    private FavAndDelContactAdapter favAndDelContactAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        contactViewModel = new ContactViewModel(FavouritesActivity.this);
        findView();
    }

    private void findView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerFavourite);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FavouritesActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        favAndDelContactAdapter = new FavAndDelContactAdapter(FavouritesActivity.this, allContact);
        recyclerView.setAdapter(favAndDelContactAdapter);
        getFavContactList();
    }
    private void getFavContactList(){
        contactViewModel.getAllFavContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                allContact.addAll(contacts);
                favAndDelContactAdapter.notifyDataSetChanged();
            }
        });
    }

}
