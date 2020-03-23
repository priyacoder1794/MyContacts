package com.example.mycontacts.view.interfaces;

public interface ISelectContacts  {
    void makeFavContact(int position,String mobileNumber);
    void deleteContact(int position,String mobileNumber);
    void removeFromFav(String mobileNumber);
}
