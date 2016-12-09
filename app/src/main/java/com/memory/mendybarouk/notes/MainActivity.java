package com.memory.mendybarouk.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    //List<Note> notes = new ArrayList<Note>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list_view);

        displayListeNotes();

    }

    private List<Note> generateNotes(){
        List<Note> notes = new ArrayList<Note>();
        notes.add(new Note("Florent", "Mon premier tweet !"));
        notes.add(new Note("Kevin", "C'est ici que ça se passe !"));
        notes.add(new Note("Logan", "Que c'est beau..."));
        notes.add(new Note("Mathieu", "Il est quelle heure ??"));
        notes.add(new Note("Willy", "On y est presque"));
        notes.add(new Note("Florent", "Mon premier tweet !"));
        notes.add(new Note("Kevin", "C'est ici que ça se passe !"));
        notes.add(new Note("Logan", "Que c'est beau..."));
        notes.add(new Note("Mathieu", "Il est quelle heure ??"));
        notes.add(new Note("Willy", "On y est presque"));
        notes.add(new Note("Florent", "Mon premier tweet !"));
        notes.add(new Note("Kevin", "C'est ici que ça se passe !"));
        notes.add(new Note("Logan", "Que c'est beau..."));
        notes.add(new Note("Mathieu", "Il est quelle heure ??"));
        notes.add(new Note("Willy", "On y est presque"));
        return notes;
    }

    private void displayListeNotes(){
        List<Note> notes = generateNotes();

        NoteAdapter adapter = new NoteAdapter(MainActivity.this, notes);
        mListView.setAdapter(adapter);
    }
}
