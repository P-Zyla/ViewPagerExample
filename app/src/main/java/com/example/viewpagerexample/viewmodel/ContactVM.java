package com.example.viewpagerexample.viewmodel;

import android.database.Cursor;
import android.provider.ContactsContract;
import androidx.databinding.BaseObservable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.viewpagerexample.Roomdb.ContactRoomDatabase;
import com.example.viewpagerexample.adaper.ContactAdapter;
import com.example.viewpagerexample.dao.ContactsDao;
import com.example.viewpagerexample.databinding.FragmentContactListBinding;
import com.example.viewpagerexample.fragment.ContactListFragment;
import com.example.viewpagerexample.model.Contacts;
import java.util.ArrayList;
import java.util.List;


public class ContactVM extends BaseObservable {

    private static ContactAdapter contactAdapter;
    private static List<ContactRecyclerVM> contactList;
    private ContactsDao contactsDao;
    private Cursor cur;
    ContactListFragment contactListFragment;
    private FragmentContactListBinding binding;

    public ContactVM(ContactListFragment contactListFragment, FragmentContactListBinding binding) {
        this.binding = binding;
        contactList = new ArrayList<>();
        this.contactListFragment = contactListFragment;
        setData();
    }

    private void setData() {

        ContactRoomDatabase contactRoomDatabase = ContactRoomDatabase.getDatabase(binding.getRoot().getContext());
        contactsDao = contactRoomDatabase.contactsDao();
        contactList=contactsDao.getAllContacts();

       if(contactList.size()==0){
           cur = contactListFragment.getActivity().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
           if ((cur != null ? cur.getCount() : 0) > 0) {
               while (cur != null && cur.moveToNext()) {
                   String id = cur.getString(
                           cur.getColumnIndex(ContactsContract.Contacts._ID));
                   String name = cur.getString(cur.getColumnIndex(
                           ContactsContract.Contacts.DISPLAY_NAME));

                   if (cur.getInt(cur.getColumnIndex(
                           ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                       Cursor pCur = contactListFragment.getActivity().getContentResolver().query(
                               ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                               null,
                               ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                               new String[]{id}, null);
                       while (pCur.moveToNext()) {
                           String phoneNo = pCur.getString(pCur.getColumnIndex(
                                   ContactsContract.CommonDataKinds.Phone.NUMBER));

                           Contacts myContacts = new Contacts(name, phoneNo);
                           contactsDao.insert(myContacts);
                       }
                       pCur.close();
                   }
               }
           }
           if (cur != null) {
               cur.close();
           }
           contactList=contactsDao.getAllContacts();
       }

        RecyclerView recyclerView = binding.recyclerViewContactList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        contactAdapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(contactAdapter);

    }
}

