package com.meddemo3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.meddemo3.R
import com.meddemo3.data.Story
import com.meddemo3.utils.MyLogger


/**
 * Actuals adapter
 * Created by Niaz Sattarov
 */
class ActualsAdapter constructor(
    val context: Context,
    val stories: List<Story>
) : RecyclerView.Adapter<ActualsAdapter.ViewHolder>() {

    init {
        MyLogger.d("ActualsAdapter - init")
    }

    /**
     * View Holder
     */
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var cardView: LinearLayout = v.findViewById<LinearLayout>(R.id.item_actual_card_view)
        var textView: TextView = v.findViewById<View>(R.id.item_actual_text) as TextView
        var imageView: ImageView = v.findViewById<View>(R.id.item_actual_image) as ImageView

        init {
        }

    }

    /**
     * On create holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new View
        val v: View = LayoutInflater
            .from(context)
            .inflate(R.layout.item_actual, parent, false)
        val vh = ViewHolder(v)
        return vh
    }

    /**
     * Bind view holder
     */
    override fun onBindViewHolder(holder: ViewHolder, position1: Int) {
        val position = position1
        val story: Story = stories[position]
        MyLogger.d("ActualsAdapter - position=" + position + " img=" + story.img)

        holder.textView.setText(story.name)
        holder.cardView.setOnClickListener {
            var actualImages = story.storyItem?.images
            MyLogger.v("ActualsAdapter - actualImages.size=" + actualImages?.size)
        }
        MyLogger.v("ActualsAdapter - storiesItemName=" + story.storyItem?.storiesItems?.name)

        holder.imageView.load(story.img)

//        Glide.with(context)
//            .load(story.img)
//            .diskCacheStrategy(DiskCacheStrategy.NONE)
//            .into(holder.imageView);
}

    /**
     * Get count
     */
    override fun getItemCount(): Int {
        return stories.size
    }
}