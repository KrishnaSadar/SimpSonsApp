package com.example.mlsc_task.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mlsc_task.R
import com.example.mlsc_task.models.chareCter
import com.google.android.material.bottomsheet.BottomSheetDialog
class CharacterAdapter(
    val context: Context,
    var charactersList: List<chareCter>
): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val cvCharacter = item.findViewById(R.id.characterCard) as CardView
        val ivCharacter = item.findViewById(R.id.ivCharacter) as ImageView
        val tvCharacterName = item.findViewById(R.id.tvCharacterName) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_charecter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = charactersList[position]

        Glide.with(context)
            .load(character.image)
            .centerInside()
            .into(holder.ivCharacter)

        holder.tvCharacterName.text = character.name

        holder.cvCharacter.setOnClickListener {
            showQuote(character.quote)
        }
    }

    override fun getItemCount(): Int = charactersList.size

    private fun showQuote(quote: String) {
        val bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_frase)

        val tvQuote = bottomSheetDialog.findViewById<TextView>(R.id.tvQuote)

        tvQuote?.text = quote

        bottomSheetDialog.show()
    }
}
