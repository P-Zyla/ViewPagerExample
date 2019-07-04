package com.example.viewpagerexample.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerexample.databinding.ContactBinding;
import com.example.viewpagerexample.viewmodel.ContactRecyclerVM;

public class ContactViewHolder extends RecyclerView.ViewHolder {
private ContactBinding contactBinding;

public ContactViewHolder(ContactBinding contactBinding) {
        super(contactBinding.getRoot());
        this.contactBinding = contactBinding;
        }


public void  bind(ContactRecyclerVM chatModel){
        this.contactBinding.setContactView(chatModel);

        }

public ContactBinding getContactBinding(){
        return contactBinding;
        }
}
