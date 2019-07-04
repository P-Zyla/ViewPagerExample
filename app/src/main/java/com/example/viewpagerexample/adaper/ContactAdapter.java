package com.example.viewpagerexample.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.viewpagerexample.databinding.ContactBinding;
import com.example.viewpagerexample.holder.ContactViewHolder;
import com.example.viewpagerexample.viewmodel.ContactRecyclerVM;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    List<ContactRecyclerVM> contactList;


    public ContactAdapter(List<ContactRecyclerVM> contactList) {
        this.contactList = contactList;
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ContactBinding contactBinding = ContactBinding.inflate(layoutInflater, parent, false);
        return new ContactViewHolder(contactBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactRecyclerVM contactModel = contactList.get(position);
        holder.bind(contactModel);
        holder.getContactBinding().setContactView(contactModel);


    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

}
