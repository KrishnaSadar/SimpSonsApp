package com.example.mlsc_task.views

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mlsc_task.databinding.ActivityMainBinding
import com.example.mlsc_task.viewmodels.MainViewModel
import com.example.mlsc_task.views.adapters.CharacterAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setupRecyclerView()

        viewModel.getCharacters()

        viewModel.charactersList.observe(this) { characters ->
            adapter.charactersList = characters
            adapter.notifyDataSetChanged()
        }

        binding.tilSearch.setEndIconOnClickListener {
            hideKeyboard()
            if (binding.tietSearch.text.toString().isEmpty()) {
                viewModel.getCharacters()
            } else {
                viewModel.getCharacter(binding.tietSearch.text.toString().trim())
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvCharacters.layoutManager = GridLayoutManager(this, 2)
        adapter = CharacterAdapter(this, arrayListOf())
        binding.rvCharacters.adapter = adapter
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
