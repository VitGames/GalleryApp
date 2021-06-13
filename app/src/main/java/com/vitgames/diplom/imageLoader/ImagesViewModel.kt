package com.vitgames.diplom.imageLoader

import android.os.Build
import androidx.annotation.RequiresApi
import com.vitgames.diplom.CoroutineModel

class ImagesViewModel() : CoroutineModel() {
    private val imagesModelLoader = ImagesModelLoader()
    var listOfImages: ArrayList<String?> = imagesModelLoader.images

    @RequiresApi(Build.VERSION_CODES.Q)
    fun getImagesFromStorage(): ArrayList<String?> {
        listOfImages = imagesModelLoader.images
        return listOfImages
    }
}