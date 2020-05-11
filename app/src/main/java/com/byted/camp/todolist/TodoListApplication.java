package com.byted.camp.todolist;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.byted.camp.todolist.operation.db.AppDatabase;
import com.facebook.stetho.Stetho;

/**
 * @author wangzhongshan
 * @date 2020-04-19.
 */
public class TodoListApplication extends Application {
    AppDatabase db;
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        db = Room.databaseBuilder(this, AppDatabase.class,"todo") //new a database
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .build();
        db.close();
    }

    static final Migration MIGRATION_1_2 =new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE NoteTable"+" ADD COLUMN priority INTEGER NOT NULL DEFAULT 1");
        }
    };


    public AppDatabase getDb() {
        return db;
    }
}
