package com.memory.mendybarouk.notes;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mendybarouk on 08/12/2016.
 */

public class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(Context context, List<Note> notes) {
        super(context, 0, notes);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_note,parent, false);
        }
        NoteViewHolder viewHolder = (NoteViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new NoteViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.data = (TextView) convertView.findViewById(R.id.data);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Notes> notes
        Note note = getItem(position);
        viewHolder.title.setText(note.getTitle());
        String data = note.getData();
        if (data.length() > 10){
            data = data.substring(0, 10) + "...";
        }
        viewHolder.data.setText(data);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm");
        viewHolder.time.setText(dateFormat.format(note.getTime().getTime()));

        //nous renvoyons notre vue à l'adapter, afin qu'il l'affiche
        //et qu'il puisse la mettre à recycler lorsqu'elle sera sortie de l'écran
        return convertView;
    }

    private class NoteViewHolder{
        public TextView title;
        public TextView data;
        public TextView time;
    }
}
