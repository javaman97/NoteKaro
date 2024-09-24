package com.notekaro.notes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.notekaro.notes.R
import com.notekaro.notes.model.Note

class NotesRVAdapter(private val context: Context, private val listener: INotesRVAdapter): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    private val allNotes=ArrayList<Note>()

    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val txtView: TextView =itemView.findViewById(R.id.txttitle)
        val deleteButton: ImageView =itemView.findViewById(R.id.img_delete)
        val editButton:ImageView = itemView.findViewById(R.id.img_edit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
     val viewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        viewHolder.editButton.setOnClickListener {
            listener.onItemUpdated(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val currentNote =allNotes[position]
        holder.txtView.text=currentNote.text

    }

    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
}
interface  INotesRVAdapter{
    fun onItemClicked(note: Note)
    fun onItemUpdated(note: Note)
}