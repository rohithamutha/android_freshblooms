package com.example.freshblooms

import CartItem
import CartResponse
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freshblooms.api.ApiClient
import com.example.freshblooms.api.ApiService

import retrofit2.*

class CartFragment : Fragment() {

    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var checkoutButton: Button
    private lateinit var cartAdapter: CartAdapter
    private val cartList = mutableListOf<CartItem>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)


        cartRecyclerView = view.findViewById(R.id.cartRecyclerView)
        checkoutButton = view.findViewById(R.id.btnCheckout)

        cartAdapter = CartAdapter(cartList)
        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartRecyclerView.adapter = cartAdapter


        val userId = PreferenceHelper(requireContext()).getUserId()

        if (userId != "") {
            fetchCartItems(userId)
        }

        checkoutButton.setOnClickListener {
            startActivity(Intent(context, BillingActivity::class.java))
        }

        return view
    }

    private fun fetchCartItems(userId: String) {

        val call = ApiClient.instance.create(ApiService::class.java).getCartItems(userId)

        call.enqueue(object : Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if (response.isSuccessful && response.body()?.status == "success") {
                    cartList.clear()
                    cartList.addAll(response.body()?.cart ?: emptyList ())
                    cartAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(requireContext(), "Failed to load cart", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
