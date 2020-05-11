package com.byted.camp.todolist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.byted.camp.todolist.beans.Note;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.operation.db.AppDatabase;

import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private Button addBtn, proBtn;
    Handler mHandler = new Handler();
    TodoListApplication application = null;
    public AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);
        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }
        application = (TodoListApplication)this.getApplication();
        db = application.getDb();
        addBtn = findViewById(R.id.btn_add);
        proBtn = findViewById(R.id.btn_pro);
        final int[] priority = {0};
        priority[0] = 0;
        Toast.makeText(NoteActivity.this,
                "Please input the priority of note first, 1-4", Toast.LENGTH_SHORT).show();
        proBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(NoteActivity.this,
//                        "please input priority, 1-4", Toast.LENGTH_SHORT).show();
                CharSequence content = editText.getText();
                int ans = judge(content);
                if(ans == 0) {
                    Toast.makeText(NoteActivity.this,
                            "Not valid priority", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Please input note", Toast.LENGTH_SHORT).show();
                    priority[0] = ans;
                }
    //            finish();
            }
        });

//        Toast.makeText(NoteActivity.this,
//                    "Please input note", Toast.LENGTH_SHORT).show();
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(priority[0] == 0) {
                        Toast.makeText(NoteActivity.this,
                                "please input priority first", Toast.LENGTH_SHORT).show();
                        return;

                    }
                    CharSequence content = editText.getText();
                    if (TextUtils.isEmpty(content)) {
                        Toast.makeText(NoteActivity.this,
                                "No content to add", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    boolean succeed = saveNote2Database(content.toString().trim(), priority[0]);
                    priority[0] = 0;
                    if (succeed) {
                        Toast.makeText(NoteActivity.this,
                                "Note added", Toast.LENGTH_SHORT).show();
                        setResult(Activity.RESULT_OK);
                    } else {
                        Toast.makeText(NoteActivity.this,
                                "Error", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
            });



    }

    private int judge(CharSequence context) {
        String tmp = context.toString();
        switch (tmp) {
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            default:
                return 0;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean saveNote2Database(String content, int priority) {
        long cnt = System.currentTimeMillis();
        Note note = new Note(cnt);
        note.setContent(content);
        Date date = new Date(System.currentTimeMillis());
        note.setDate(date);
        note.setPriority(priority);
        note.setState(State.TODO);
        db.noteDao().insertAll(note);
        return true;
//        // TODO 插入一条新数据，返回是否插入成功
    }
}
