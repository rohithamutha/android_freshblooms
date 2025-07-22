package com.example.freshblooms

import BasicResponse
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.freshblooms.api.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentFeatured : Fragment() {

    private var flowerId: Int = -1
    private lateinit var featuredRecyclerView: RecyclerView
    private lateinit var featuredAdapter: FlowerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flowerId = arguments?.getInt("flowerId") ?: -1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_featured, container, false)

        featuredRecyclerView = view.findViewById(R.id.featuredRecyclerView)
        featuredRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        featuredAdapter = FlowerAdapter(emptyList()) { flower ->
            val fragment = newInstance(flower.id)
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .addToBackStack(null)
                .commit()
        }
        featuredRecyclerView.adapter = featuredAdapter

        if (flowerId != -1) {
            loadFlowerDetails(view)
        } else {
            Toast.makeText(requireContext(), "Invalid flower ID", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun loadFlowerDetails(view: View) {
        val api = ApiClient.instance.create(ApiService::class.java)
        val body = mapOf("id" to flowerId)

        api.getFlowerById(body).enqueue(object : Callback<SingleFlowerResponse> {
            override fun onResponse(call: Call<SingleFlowerResponse>, response: Response<SingleFlowerResponse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    val flower = response.body()!!.data

                    val imageView = view.findViewById<ImageView>(R.id.imgLarge)
                    val nameView = view.findViewById<TextView>(R.id.largeTitle)
                    val priceView = view.findViewById<TextView>(R.id.largePrice)
                    val seasonView = view.findViewById<TextView>(R.id.largeSeason)
                    val descriptionView = view.findViewById<TextView>(R.id.largeDescription)

                    val quantityText = view.findViewById<TextView>(R.id.tvQuantity)
                    val btnIncrease = view.findViewById<ImageView>(R.id.btnIncrease)
                    val btnDecrease = view.findViewById<ImageView>(R.id.btnDecrease)
                    val btnAddToCart = view.findViewById<Button>(R.id.btnAddToCart)

                    Glide.with(requireContext()).load(ApiClient.IMAGE_URL + flower.image).into(imageView)

                    nameView.text = flower.flowername
                    priceView.text = "₹${flower.price}"
                    seasonView.text = flower.seasonal_flowers
                    descriptionView.text = flower.description

                    var quantity = 1
                    quantityText.text = quantity.toString()

                    btnIncrease.setOnClickListener {
                        quantity++
                        quantityText.text = quantity.toString()
                    }

                    btnDecrease.setOnClickListener {
                        if (quantity > 1) {
                            quantity--
                            quantityText.text = quantity.toString()
                        }
                    }

                    btnAddToCart.setOnClickListener {
                        sendToCartAPI(flower.id, quantity)
                    }

                    loadFeaturedFlowers(flower.seasonal_flowers.trim(), flower.id)
                } else {
                    Toast.makeText(requireContext(), "Flower not found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SingleFlowerResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun sendToCartAPI(productId: Int, quantity: Int) {
        val api = ApiClient.instance.create(ApiService::class.java)

        val userId = PreferenceHelper(requireContext()).getUserId()
        val requestBody = mapOf(
            "user_id" to userId.toString(),
            "product_id" to productId.toString(),
            "quantity" to quantity.toString(),
            "status" to "active"
        )

        api.addToCart(requestBody).enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful && response.body()?.status == "success") {
                    Toast.makeText(requireContext(), "Added to cart successfully", Toast.LENGTH_SHORT).show()
//
//                    parentFragmentManager.beginTransaction()
//                        .replace(R.id.nav_host_fragment, CartFragment())
//                        .addToBackStack(null)
//                        .commit()
                } else {
                    Toast.makeText(requireContext(), "Failed to add to cart", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Network Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadFeaturedFlowers(season: String, excludeId: Int) {
        val api = ApiClient.instance.create(ApiService::class.java)
        val requestBody = mapOf("season" to season.lowercase().trim())

        api.getFlowersBySeason(requestBody).enqueue(object : Callback<FlowerResponse> {
            override fun onResponse(call: Call<FlowerResponse>, response: Response<FlowerResponse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    val allFlowers = response.body()!!.data
                    val filtered = allFlowers.filter { it.id != excludeId }
                    featuredAdapter.updateList(filtered)
                } else {
                    Toast.makeText(requireContext(), "No featured flowers found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<FlowerResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        fun newInstance(flowerId: Int): FragmentFeatured {
            return FragmentFeatured().apply {
                arguments = Bundle().apply {
                    putInt("flowerId", flowerId)
                }
            }
        }
    }
}
