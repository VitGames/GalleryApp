package com.vitgames.diplom.imageLoader

import android.annotation.SuppressLint
import android.app.Activity
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.MediaStore.MediaColumns
import androidx.annotation.RequiresApi


class ImagesModelLoader() {
    var images: ArrayList<String?> = ArrayList()
    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("Recycle")
     fun getAllShownImagesPath(activity: Activity): ArrayList<String?>{
        val cursor: Cursor?
        val column_index: Int
        val listOfAllImages = ArrayList<String?>()
        var absolutePathOfImage: String? = null
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaColumns.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )
        cursor = activity.contentResolver.query(
            uri, projection, null,
            null, null
        )
        val column_index_data: Int = cursor!!.getColumnIndexOrThrow(MediaColumns.DATA)
        column_index = cursor
            .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data)
            listOfAllImages.add(absolutePathOfImage)
            images = listOfAllImages
        }
        return listOfAllImages
    }
}