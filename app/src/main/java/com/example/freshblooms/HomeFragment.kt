package com.example.freshblooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.freshblooms.api.FlowerResponse
import com.example.freshblooms.api.ApiClient
import com.example.freshblooms.api.ApiService
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var imageSlider: ViewPager2
    private lateinit var flowerRecyclerView: RecyclerView
    private lateinit var designRecyclerView: RecyclerView
    private lateinit var seasonChipGroup: ChipGroup

    private lateinit var flowerAdapter: FlowerAdapter
    private lateinit var designAdapter: DesignAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // View binding
        imageSlider = view.findViewById(R.id.imageSlider)
        flowerRecyclerView = view.findViewById(R.id.flowerRecyclerView)
        designRecyclerView = view.findViewById(R.id.designRecyclerView)
        seasonChipGroup = view.findViewById(R.id.seasonChipGroup)

        // Slider
        val sliderImages = listOf(R.drawable.chrysanthemums, R.drawable.coneflower, R.drawable.aster)
        imageSlider.adapter = SliderAdapter(sliderImages)

        // ✅ Set up FlowerAdapter with onClick → send flower.id
        flowerAdapter = FlowerAdapter(
            emptyList(),
            onFlowerClick = { flower ->
                val flowerDetailsFragment = FragmentFeatured.newInstance(flower.id)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, flowerDetailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        )

        flowerRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        flowerRecyclerView.adapter = flowerAdapter

        // Design Adapter
        designAdapter = DesignAdapter(emptyList())
        designRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        designRecyclerView.adapter = designAdapter

        // Season filter listener
        seasonChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedSeasons = mutableListOf<String>()
            for (id in checkedIds) {
                val chip = group.findViewById<Chip>(id)
                selectedSeasons.add(chip.text.toString())
            }
            filterFlowers(selectedSeasons)
            filterDesigns(selectedSeasons)
        }

        getFlowersFromApi("all season")
        getDesignsFromApi("wedding")

        return view
    }

    private fun getFlowersFromApi(season: String) {
        val requestBody = mapOf("season" to season)

        ApiClient.instance.create(ApiService::class.java).getFlowersBySeason(requestBody)
            .enqueue(object : Callback<FlowerResponse> {
                override fun onResponse(call: Call<FlowerResponse>, response: Response<FlowerResponse>) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        val flowerItems = response.body()?.data ?: emptyList()

                        flowerAdapter.updateList(flowerItems)
                    } else {
                        Toast.makeText(requireContext(), "No flowers found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<FlowerResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), "Failed: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun getDesignsFromApi(season: String) {
        val requestBody = mapOf("season" to season)

        ApiClient.instance.create(ApiService::class.java).getDesignsBySeason(requestBody)
            .enqueue(object : Callback<DesignResponse> {
                override fun onResponse(call: Call<DesignResponse>, response: Response<DesignResponse>) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        val designItems = response.body()?.data ?: emptyList()
                        val designs = designItems.map {
                            FlowerDesign(
                                it.flowername,
                                it.image,
                                "₹${it.price}"
                            )
                        }
                        designAdapter.updateList(designs)
                    } else {
                        Toast.makeText(requireContext(), "No designs found", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<DesignResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), "Failed: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun filterFlowers(selectedSeasons: List<String>) {
        if (selectedSeasons.isEmpty() || selectedSeasons.contains("All Seasons")) {
            getFlowersFromApi("all season")
        } else {
            getFlowersFromApi(selectedSeasons[0].lowercase())
        }
    }

    private fun filterDesigns(selectedSeasons: List<String>) {
        if (selectedSeasons.isEmpty()) {
            getDesignsFromApi("wedding")
        } else {
            getDesignsFromApi(selectedSeasons[0].lowercase())
        }
    }
}
