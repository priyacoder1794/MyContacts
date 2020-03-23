package com.example.mycontacts.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mycontacts.R;
import com.example.mycontacts.model.Contact;
import com.example.mycontacts.viewModel.ContactViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ContactViewModel contactViewModel;
    public final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactViewModel = new ContactViewModel(this);
        findView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPhoneContactsPermission()){
                requestStoragePermission();
            }
        }
    }


    private void findView(){
        Button btnContacts,btnFavourites,btnDeleted;
        btnContacts = findViewById(R.id.btnContacts);
        btnFavourites = findViewById(R.id.btnFavourites);
        btnDeleted = findViewById(R.id.btnDeleted);

        btnContacts.setOnClickListener(this);
        btnFavourites.setOnClickListener(this);
        btnDeleted.setOnClickListener(this);
    }

    //We are calling this method to check the permission status
    private boolean hasPhoneContactsPermission() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE:
               getContactList();
        }
    }


    private void getContactList(){
        contactViewModel.getContacts();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnContacts:
                startActivity(new Intent(MainActivity.this,ContactsActivity.class));
                break;
            case R.id.btnFavourites:
                startActivity(new Intent(MainActivity.this,FavouritesActivity.class));
                break;
            case R.id.btnDeleted:
                startActivity(new Intent(MainActivity.this,DeletedActivity.class));
                break;
        }
    }
}
