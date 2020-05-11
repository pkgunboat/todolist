package com.byted.camp.todolist.beans;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * Created on 2019/1/23.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
@Entity(tableName = "NoteTable")
public class Note implements Comparable<Note>{
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "date")
    private Date date;
    @ColumnInfo(name = "state")
    private State state;
    @ColumnInfo(name = "Content")
    private String content;
    @ColumnInfo(name = "priority")
    private  int priority;

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Note(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static class Converters {
        @TypeConverter
        public static Date fromTimestamp(Long value) {
            return value == null ? null : new Date(value);
        }

        @TypeConverter
        public static Long dateToTimestamp(Date date) {
            return date == null ? null : date.getTime();
        }

        @TypeConverter
        public static State toState(int value) {
           if(value == 1) {
               return State.DONE;
           } else {
               return State.TODO;
           }
        }

        @TypeConverter
        public static int toInt(State state) {
            return state.intValue;
        }
    }

    @Override
    public int compareTo(Note note) {
        if(this.getState() == note.getState())
            return note.getPriority() - this.getPriority();
        else {
            if(this.getState() == this.getState().TODO) return -1;
            else return 1;
        }
    }
}

