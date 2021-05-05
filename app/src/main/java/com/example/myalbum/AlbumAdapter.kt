package com.example.myalbum

import android.content.ClipData.Item
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myalbum.databinding.AlbumViewBinding
import com.example.myalbum.models.Album
import com.example.myalbum.models.User


class AlbumAdapter(private val albums: MutableList<Album>, private val users: MutableList<User>): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>()  {

    init {
        // sort albums
        albums.sortedBy { album -> album.title }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AlbumViewHolder(AlbumViewBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        // bind holder with album and author
       holder.bind(albums[position],users.find { user -> albums[position].userId == user.id })
    }

    override fun getItemCount(): Int {
        return albums.size
    }

    class AlbumViewHolder(private val binding: AlbumViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album, author: User?) {
            binding.title = album.title
            binding.username = author?.username
            binding.root.setOnClickListener {
                it.context.startActivity(Intent(it.context, GalleryActivity::class.java).apply {
                    putExtra(GalleryActivity.ALBUM_ID_EXTRA, album.id)
                })
            }
            binding.executePendingBindings()
        }


    }
}