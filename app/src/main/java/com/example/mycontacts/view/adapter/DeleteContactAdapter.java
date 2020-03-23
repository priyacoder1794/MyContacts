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

import java.util.ArrayList;

public class DeleteContactAdapter extends RecyclerView.Adapter<DeleteContactAdapter.DelContactView> {
    private Context context;
    private ArrayList<Contact> allContact = new ArrayList<>();
    public DeleteContactAdapter(Context context, ArrayList<Contact> allContact) {
        this.context = context;
        this.allContact = allContact;
    }

    @NonNull
    @Override
    public DelContactView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_ietm_contacts,parent,false);
        return new DelContactView(view);    }

    @Override
    public void onBindViewHolder(@NonNull DelContactView holder, int position) {
        final Contact contact = allContact.get(position);
        if(contact.getPhotoUri()!=null){
            holder.txtIconName.setVisibility(View.GONE);
            holder.imgProfile.setVisibility(View.VISIBLE);
            Glide.with(context).load(contact.getPhotoUri()).into(holder.imgProfile);
        }else {
            holder.txtIconName.setVisibility(View.VISIBLE);
            holder.imgProfile.setVisibility(View.INVISIBLE);
            holder.txtIconName.setText(contact.getName().substring(0,1));
        }
        Glide.with(context).load(contact.getPhotoUri()).into(holder.imgProfile);
        holder.txtNumber.setText(contact.getPhoneNumber());
        holder.txtName.setText(contact.getName());
        holder.imgDelete.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return allContact.size();
    }

    public class DelContactView extends RecyclerView.ViewHolder {
        private ImageView imgProfile,imgFav,imgDelete;
        private TextView txtName,txtNumber,txtIconName;
        public DelContactView(@NonNull View itemView) {
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
