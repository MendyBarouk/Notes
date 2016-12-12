package com.memory.mendybarouk.notes;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    public static final String NOTE = "com.memory.mendybarouk.notes.NOTE";
    public static final String I = "com.memory.mendybarouk.notes.I";
    public static final String ADD = "com.memory.mendybarouk.notes.ADD";
    public static final int BACK_FROM_DATA_ACTIVITY = 0;
    ListView mListView;
    List<Note> notes = new ArrayList<Note>();
    NoteAdapter noteAdapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteAdapter = new NoteAdapter(this,notes);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);

        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(this);

        for (int i = 0; i < 100; i++) {
            notes.add(new Note("title " + i, "data " + i));
        }

        displayListeNotes();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, EditorActivity.class);
        Note note = (Note) adapterView.getItemAtPosition(i);

        intent.putExtra(ADD,false);
        intent.putExtra(NOTE,note);
        intent.putExtra(I,i);

        startActivityForResult(intent,BACK_FROM_DATA_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BACK_FROM_DATA_ACTIVITY){
            if (resultCode == RESULT_OK){
                if (data.getBooleanExtra(EditorActivity.DELETE, false)){
                    notes.remove(data.getIntExtra(I, -1));
                    mListView.setAdapter(noteAdapter);
                    return;
                }
                Note note = (Note) data.getSerializableExtra(NOTE);
                if (!data.getBooleanExtra(ADD, false)) {
                    int i = data.getIntExtra(I, -1);
                    if (i != -1) {
                        notes.remove(i);
                        notes.add(0, note);
                        noteAdapter.notifyDataSetChanged();
                    }
                }else {
                    notes.add(0, note);
                    mListView.setAdapter(noteAdapter);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, EditorActivity.class);
        intent.putExtra(ADD, true);
        startActivityForResult(intent, BACK_FROM_DATA_ACTIVITY);
    }

    private void displayListeNotes(){
        NoteAdapter adapter = new NoteAdapter(MainActivity.this, notes);
        mListView.setAdapter(adapter);
    }
}
