package com.example.mycontacts.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycontacts.R;
import com.example.mycontacts.model.Contact;
import com.example.mycontacts.view.adapter.DeleteContactAdapter;
import com.example.mycontacts.view.adapter.FavContactAdapter;
import com.example.mycontacts.viewModel.ContactViewModel;

import java.util.ArrayList;
import java.util.List;

public class DeletedActivity extends AppCompatActivity {
    private ContactViewModel contactViewModel;
    private ArrayList<Contact> allContact = new ArrayList<>();
    private DeleteContactAdapter deleteContactAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleted);
        contactViewModel = new ContactViewModel(DeletedActivity.this);
        findView();
    }
    private void findView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerDeleted);
        Button btnRestoreContact = findViewById(R.id.btnRestoreContact);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DeletedActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        deleteContactAdapter = new DeleteContactAdapter(DeletedActivity.this, allContact);
        recyclerView.setAdapter(deleteContactAdapter);

        btnRestoreContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactViewModel.restoreContact();
                getFavContactList();
            }
        });

        getFavContactList();
    }
    private void getFavContactList(){
        allContact.clear();
        contactViewModel.getAllDeletedContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                allContact.addAll(contacts);
                deleteContactAdapter.notifyDataSetChanged();
            }
        });
    }
}
