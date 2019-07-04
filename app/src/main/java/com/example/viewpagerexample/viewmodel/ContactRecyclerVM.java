package com.example.viewpagerexample.viewmodel;

import androidx.databinding.BaseObservable;
import com.example.viewpagerexample.model.Contacts;

public class ContactRecyclerVM extends BaseObservable {

    public int Id;
    public String Name,Number;

    public ContactRecyclerVM(Contacts contacts) {
        this.Name=contacts.getName();
        this.Number= contacts.getNumber();
        this.Id=contacts.getId();
    }
    public ContactRecyclerVM() {

    }

}
