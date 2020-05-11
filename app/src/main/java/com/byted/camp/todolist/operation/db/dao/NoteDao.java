package com.byted.camp.todolist.operation.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.byted.camp.todolist.beans.Note;

import java.util.List;

/**
 * @author zhongshan
 * 2020-04-19.
 */
@Dao
public interface NoteDao {

    @Query("SELECT * FROM NoteTable")
    List<Note> getAll();

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Insert
    void insertAll(Note... notes);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);
}
