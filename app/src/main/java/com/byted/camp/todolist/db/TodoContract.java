package com.byted.camp.todolist.db;

import android.provider.BaseColumns;
import android.provider.ContactsContract;

import com.byted.camp.todolist.beans.Note;
import com.byted.camp.todolist.operation.db.FeedReaderContract;

import java.util.Date;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // TODO 定义表结构和 SQL 语句常量
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NoteEntry.TABLE_NAME + " (" +
                    NoteEntry._ID + " INTEGER PRIMARY KEY," +
                    NoteEntry.Content + " TEXT," +
                    NoteEntry.date + " TEXT"+
                    NoteEntry.state + "TEXT)";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + NoteEntry.TABLE_NAME;

    private TodoContract() {

    }

    public static class NoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "NoteTable";
        public static final String Content = "Content";
        public static final String date = "date";
        public static final String state = "state";

    }
}
