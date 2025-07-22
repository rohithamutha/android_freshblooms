package com.example.freshblooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freshblooms.api.FlowerItem

class FeaturedFragment : Fragment() {

    private lateinit var flowerAdapter: FlowerAdapter
    private lateinit var recyclerView: RecyclerView
    private var flowerList: List<FlowerItem> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_featured, container, false)
        recyclerView = view.findViewById(R.id.featuredRecyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flowerList = listOf<FlowerItem>()

        flowerAdapter = FlowerAdapter(flowerList) { flower ->
            openFlowerDetails(flower)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = flowerAdapter
    }

    private fun openFlowerDetails(flower: FlowerItem) {
        val bundle = Bundle().apply {
            putInt("flower_id", flower.id)
        }

//        val detailsFragment = FlowerDetailsFragment()
//        detailsFragment.arguments = bundle
//
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.fragment_container, detailsFragment)
//            .addToBackStack(null)
//            .commit()
    }
}
