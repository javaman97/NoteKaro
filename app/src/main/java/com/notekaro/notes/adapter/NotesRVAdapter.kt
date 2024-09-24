package com.notekaro.notes.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
        init {
            // Delete button click
            deleteButton.setOnClickListener {
                listener.onItemClicked(allNotes[adapterPosition])
            }

            // Edit button click
            editButton.setOnClickListener {
                val currentNote = allNotes[adapterPosition]

                val builder = AlertDialog.Builder(context)
                val input = EditText(context)
                input.setText(currentNote.text) // Pre-fill with current text
                builder.setView(input)

                builder.setTitle("Edit Note")
                    .setPositiveButton("OK") { dialog, which ->
                        val newText = input.text.toString()
                        val updatedNote = currentNote.copy(text = newText)
                        listener.onItemUpdated(updatedNote)
                    }
                    .setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }

                builder.show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
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