package com.example.youlu.biz;

import android.content.Context;
import android.view.View;

import com.example.youlu.adapter.MyNoteAdapter;
import com.example.youlu.bean.Note;
import com.example.youlu.holder.NoteViewHolder;
import com.example.youlu.listener.OnLoadNotesFinshListener;
import com.example.youlu.util.YouluUtils_Calllog;
import com.example.youlu.util.YouluUtils_Note;

public class NoteBiz {
	Context context;
	public NoteBiz(Context context) {
		this.context = context;
	}
	public void asyncGetAllNotes(OnLoadNotesFinshListener listener) {
		YouluUtils_Note.asyncGetAllNotes(context, listener);
	}

	public void init(NoteViewHolder vh, Note note, View view) {
		YouluUtils_Note.init(context,vh, note, view);
	}
	public void deleteNote(MyNoteAdapter adapter, Note note) {
		YouluUtils_Note.deleteNote(context, adapter, note);
	}
	public void Update(int get_id) {
		YouluUtils_Note.update(context,get_id);
	}

}
