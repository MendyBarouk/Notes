package com.memory.mendybarouk.notes;

import android.content.Intent;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class DataActivity extends AppCompatActivity {

    public static final String DELETE = "com.memory.mendybarouk.notes.DELETE";
    int i;
    boolean fAdd;
    EditText editTextTitle, editTextData;
    Note note;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);



        intent = getIntent();
        fAdd = intent.getBooleanExtra(MainActivity.ADD, false);


        editTextTitle = (EditText) findViewById(R.id.activity_data_title);
        editTextData = (EditText) findViewById(R.id.activity_data_data);

        if (!fAdd){
            modifyNote();
        }

    }

    private void modifyNote() {

        note = (Note) intent.getSerializableExtra(MainActivity.NOTE);
        i = intent.getIntExtra(MainActivity.I, -1);

        if (note != null) {
            editTextTitle.setText(note.getTitle());
            editTextData.setText(note.getData());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void save() {
        String title = editTextTitle.getText().toString().trim();
        String data = editTextData.getText().toString().trim();

        if (!title.isEmpty() && !data.isEmpty()) {
            Intent intent = new Intent();
            if (!fAdd) {
                saveModification(title, data, intent);

            } else if (fAdd){
                addNote(title, data, intent);
            }
            setResult(RESULT_OK, intent);
        }
        finish();
    }

    private void delete() {
        Intent intent = new Intent();
        intent.putExtra(DELETE, true);
        intent.putExtra(MainActivity.I, i);
        setResult(RESULT_OK, intent);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addNote(String title, String data, Intent intent) {
        note = new Note(title, data);
        intent.putExtra(MainActivity.ADD, true);
        intent.putExtra(MainActivity.NOTE, note);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveModification(String title, String data, Intent intent) {
        note = new Note(title, data);
        intent.putExtra(MainActivity.ADD, false);
        intent.putExtra(MainActivity.NOTE, note);
        intent.putExtra(MainActivity.I, i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!fAdd) {
            getMenuInflater().inflate(R.menu.activity_data_save_delete, menu);
            return true;
        }
        getMenuInflater().inflate(R.menu.activity_data_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                delete();
                return  true;
            case R.id.action_save:
                save();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
