package com.example.viewpagerexample.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.viewpagerexample.model.Contacts;
import com.example.viewpagerexample.viewmodel.ContactRecyclerVM;

import java.util.List;

@Dao
public interface ContactsDao {

    @Query("SELECT * from contact_table")
    List<ContactRecyclerVM> getAllContacts();

   @Query("SELECT * from contact_table where Id = :id")
    Contacts[] getContactById(int id);

    @Query("SELECT * from contact_table where Name= :name")
    Contacts[] getContactByName(String name);

    @Insert
    void insert(Contacts contacts);



}
