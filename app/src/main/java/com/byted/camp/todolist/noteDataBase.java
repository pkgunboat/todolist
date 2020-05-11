package com.byted.camp.todolist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.byted.camp.todolist.beans.Note;
import com.byted.camp.todolist.operation.db.dao.NoteDao;

@Database(entities = {Note.class}, version = 1, exportSchema  = false)
@TypeConverters({Note.Converters.class})
public abstract  class noteDataBase extends RoomDatabase {
    public abstract NoteDao noteDao();

}
