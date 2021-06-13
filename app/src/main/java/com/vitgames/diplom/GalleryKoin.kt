package com.vitgames.diplom

import android.app.Application
import com.vitgames.diplom.imageLoader.ImagesModelLoader
import com.vitgames.diplom.imageLoader.ImagesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class GalleryKoin : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GalleryKoin)
            modules(listOf(images))
        }
    }
    private val images = module {
        single { ImagesModelLoader() }
        factory { MainActivity() }
        viewModel {ImagesViewModel()}
    }
}