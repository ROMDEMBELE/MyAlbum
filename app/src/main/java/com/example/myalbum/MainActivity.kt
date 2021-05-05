package com.example.myalbum

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.album_list)
        runBlocking {
            launch {
                try {
                    val res1 = API.apiService.getAlbums()
                    if (res1.isSuccessful) {
                        res1.body()?.let { albums ->
                            val res2 = API.apiService.getUsers()
                            if (res2.isSuccessful) {
                                res2.body()?.let { users ->
                                    recyclerView.adapter = AlbumAdapter(albums, users)
                                }
                            } else {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Error Occurred: ${res2.message()}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Error Occurred: ${res1.message()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error Occurred: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
    }
}