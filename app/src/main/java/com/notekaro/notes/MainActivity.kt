package com.notekaro.notes

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.notekaro.notes.adapter.INotesRVAdapter
import com.notekaro.notes.adapter.NotesRVAdapter
import com.notekaro.notes.model.Note
import com.notekaro.notes.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    private lateinit var viewModel: NoteViewModel
    private lateinit var recyclerView:RecyclerView
    private lateinit var input:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        input=findViewById(R.id.edit_text)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this,this)
        recyclerView.adapter=adapter
        viewModel= ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]

        viewModel.allNotes.observe(this, Observer { list->
            list?.let{

                adapter.updateList(it)
            }
        })

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this ,"${note.text} Deleted",Toast.LENGTH_SHORT).show()
        }

    override fun onItemUpdated(note: Note) {
        viewModel.updateNote(note)
        Toast.makeText(this ,"${note.text} Updated",Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this ,"$noteText Added",Toast.LENGTH_SHORT).show()
        }
    }
}