package com.uc.movieappw4.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uc.movieappw4.R
import com.uc.movieappw4.helper.Const
import com.uc.movieappw4.model.Result
import com.uc.movieappw4.view.MovieDetail

class NowPlayingAdapter(private val dataSet: ArrayList<Result>) :
    RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvtitle: TextView
        val tvreleased: TextView
        val cvNowPlaying: CardView
        val thumb: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            tvreleased = view.findViewById(R.id.tv_released_poster)
            tvtitle = view.findViewById(R.id.tv_title_poster)
            cvNowPlaying = view.findViewById(R.id.cv_now_playing)
            thumb = view.findViewById(R.id.img_poster)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_nowplaying, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvtitle.text = dataSet[position].title
        viewHolder.tvreleased.text = dataSet[position].release_date
        viewHolder.cvNowPlaying.setOnClickListener{
            val intent = Intent(it.context, MovieDetail::class.java)
            intent.putExtra("movie_id", dataSet[position].id)
            it.context.startActivity(intent)
            Thread.sleep(500)
        }
        Glide.with(viewHolder.itemView).load(Const.IMG_URL + dataSet[position].poster_path).into(viewHolder.thumb)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}