package com.example.alberto.malcompanion.ui.adapters

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.alberto.malcompanion.R
import com.example.alberto.malcompanion.data.extensions.ctx
import com.example.alberto.malcompanion.model.AnimeItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.anime_item.view.*
import timber.log.Timber


/**
 * Adapter to display anime list elements
 */
class AnimeListAdapter(val animeItems: List<AnimeItem>, val listener: (AnimeItem, ImageView) -> Unit) :
   RecyclerView.Adapter<AnimeListAdapter.ViewHolder>() {

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = LayoutInflater.from(parent.ctx).inflate(R.layout.anime_item, parent, false)
      return ViewHolder(view, listener)
   }

   override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
      viewHolder.bind(animeItems.get(position))
   }

   override fun getItemCount() = animeItems.size

   class ViewHolder(view: View, val listener: (AnimeItem, ImageView) -> Unit) : RecyclerView.ViewHolder(view) {

      fun bind(animeItem: AnimeItem) {
         with(animeItem) {
            Picasso.with(itemView.ctx).load(image).into(itemView.animeImage)
            ViewCompat.setTransitionName(itemView.animeImage, title)
            itemView.animeProgressBar.progress = getProgress()
            itemView.setOnClickListener {
               Timber.d("Adapter transition name: ${ViewCompat.getTransitionName(itemView.animeImage)}")
               listener(this, itemView.animeImage) }
         }
      }

   }
}