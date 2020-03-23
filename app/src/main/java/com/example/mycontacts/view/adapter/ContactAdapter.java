package com.example.mycontacts.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mycontacts.R;
import com.example.mycontacts.model.Contact;
import com.example.mycontacts.util.Utility;
import com.example.mycontacts.view.interfaces.ISelectContacts;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactView> {

    private Context context;
    private ArrayList<Contact> allContact = new ArrayList<>();
    private ISelectContacts iSelectContacts;

    public ContactAdapter(Context context, ArrayList<Contact> allContact,ISelectContacts iSelectContacts) {
        this.context = context;
        this.allContact = allContact;
        this.iSelectContacts = iSelectContacts;
    }

    @NonNull
    @Override
    public ContactView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_ietm_contacts,parent,false);
        return new ContactView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactView holder,final int position) {
        final Contact contact = allContact.get(position);
        if ((contact.getIsFav()).equals(Utility.FAV_CONTACT)){
            Glide.with(context).load(R.drawable.ic_fav).into(holder.imgFav);

        }
        if(contact.getPhotoUri()!=null){
            holder.txtIconName.setVisibility(View.GONE);
            holder.imgProfile.setVisibility(View.VISIBLE);
            Glide.with(context).load(contact.getPhotoUri()).into(holder.imgProfile);
        }else {
            holder.txtIconName.setVisibility(View.VISIBLE);
            holder.imgProfile.setVisibility(View.INVISIBLE);
            holder.txtIconName.setText(contact.getName().substring(0,1));
        }
        holder.txtNumber.setText(contact.getPhoneNumber());
        holder.txtName.setText(contact.getName());
        holder.imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSelectContacts.makeFavContact(position,contact.getPhoneNumber());
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSelectContacts.deleteContact(position,contact.getPhoneNumber());
            }
        });
    }

    @Override
    public int getItemCount() {
        return allContact.size();
    }

    public class ContactView extends RecyclerView.ViewHolder{
         private ImageView imgProfile,imgFav,imgDelete;
         private TextView txtName,txtNumber,txtIconName;

        public ContactView(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.icon);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgFav = itemView.findViewById(R.id.imgFav);
            txtName = itemView.findViewById(R.id.name);
            txtNumber = itemView.findViewById(R.id.number);
            txtIconName = itemView.findViewById(R.id.iconName);


        }
    }



}
