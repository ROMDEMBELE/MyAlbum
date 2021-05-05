package com.example.myalbum

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GalleryActivity : AppCompatActivity() {

    companion object {
        val ALBUM_ID_EXTRA = "ALBUM_ID_EXTRA"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val albumId = intent.getIntExtra(ALBUM_ID_EXTRA,-1)
        val images = arrayListOf<Int>(R.id.image1,R.id.image2,R.id.image3, R.id.image4,R.id.image5, R.id.image6, R.id.image7, R.id.image8, R.id.image9, R.id.image10, R.id.image11)
        if(albumId >= 0) {
            runBlocking {
                launch {
                    try {
                        val res1 = API.apiService.getPhotos(albumId)
                        if (res1.isSuccessful) {
                            res1.body()?.let { photos ->
                                photos.forEachIndexed { index, photo ->
                                    if(index < images.size) {
                                        val imageView = findViewById<ImageView>(images[index])
                                        imageView.setImageURI(Uri.parse(photo.url))
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(
                                this@GalleryActivity,
                                "Error Occurred: ${res1.message()}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@GalleryActivity,
                            "Error Occurred: ${e.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

    }
}