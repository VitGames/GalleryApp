package com.vitgames.diplom

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vitgames.diplom.fragments.RecyclerFragment
import com.vitgames.diplom.fragments.SettingsFragment
import com.vitgames.diplom.imageLoader.ImagesModelLoader
import com.vitgames.diplom.imageLoader.ImagesViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val settingsFragment = SettingsFragment()
    private val recyclerFragment = RecyclerFragment()
    private val imagesModelLoader = ImagesModelLoader()
    private val viewModel: ImagesViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )
        if (runStoragePermissionDialog()) {
            setCurrentFragment(recyclerFragment)
            imagesModelLoader.getAllShownImagesPath(this)
            recyclerFragment.images = imagesModelLoader.images
            recyclerFragment.showImages()

        } else {
            showPermissionDialog()
        }
        val buttonSettings: ImageView = findViewById(R.id.imageView_settings)
        buttonSettings.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, settingsFragment)
                commit()
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }

    private fun runStoragePermissionDialog(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            ActivityCompat.requestPermissions(this, permissions, 0)
            setCurrentFragment(recyclerFragment)
            return false
        }
        return true
    }

    private fun showPermissionDialog() {
        MaterialAlertDialogBuilder(applicationContext)
            .setTitle("Ошибка доступа")
            .setMessage("Для работы приложения необхомимо Ваше разрешение на доступ к файлам")
            .setNegativeButton("Понял!") { dialog, _ ->
                runStoragePermissionDialog()
            }.show()
    }
}