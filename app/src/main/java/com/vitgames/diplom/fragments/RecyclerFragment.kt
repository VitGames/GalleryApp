package com.vitgames.diplom.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.vitgames.diplom.R
import com.vitgames.diplom.imageLoader.GalleryAdapter
import com.vitgames.diplom.imageLoader.ImagesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class RecyclerFragment : Fragment(R.layout.recycler_fragment) {

     var images: ArrayList<String?>? = null
    private val viewModel: ImagesViewModel by viewModel()
    private var recyclerView: RecyclerView? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recycler_fragment, container, false)

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview_gallery_images)
        showImages()
    }


    @RequiresApi(Build.VERSION_CODES.Q)
     fun showImages() {
        //images = viewModel.listOfImages
        recyclerView?.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView?.adapter = GalleryAdapter(requireContext(), images!!)
        recyclerView?.setHasFixedSize(true)
    }
}