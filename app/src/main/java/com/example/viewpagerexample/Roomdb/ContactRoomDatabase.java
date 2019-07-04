package com.example.viewpagerexample.Roomdb;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.viewpagerexample.dao.ContactsDao;
import com.example.viewpagerexample.model.Contacts;


@Database(entities = {Contacts.class}, version = 1)
public abstract class ContactRoomDatabase extends RoomDatabase {

    public abstract ContactsDao contactsDao();

    private static volatile ContactRoomDatabase INSTANCE;
    public static ContactRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class, "contact_table")
                                              .allowMainThreadQueries()
                                              .build();
                }
            }
        }
        return INSTANCE;
    }

}




