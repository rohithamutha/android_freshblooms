package com.example.freshblooms.api


import BasicResponse
import CartResponse
import ClassicResponse
import com.example.freshblooms.DesignResponse
import com.example.freshblooms.OrderResponse
import com.example.freshblooms.PendingOrderResponse
import com.example.freshblooms.SingleFlowerResponse
import com.example.freshblooms.TransactionHistoryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("android_signup.php")
    fun signup(@Body request: SignupRequest): Call<SignupResponse>

    @POST("android_login.php") // Replace with your actual PHP filename
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("get_flowers_by_season.php")
    fun getFlowersBySeason(@Body season: Map<String, String>): Call<FlowerResponse>

    @POST("get_design_by_season.php")
    fun getDesignsBySeason(@Body season: Map<String, String>): Call<DesignResponse>

    @FormUrlEncoded
    @POST("get_cart.php")
    fun getCartItems(@Field("user_id") userId: String): Call<CartResponse>

    @FormUrlEncoded
    @POST("get_orders.php")
    fun getUserOrders(
        @Field("user_id") userId: String
    ): Call<OrderResponse>

    @POST("getFlowerById.php")
    fun getFlowerById(@Body body: Map<String, Int>): Call< SingleFlowerResponse>


    @GET("delete_cart.php")
    fun deleteCartItem(@Query("id") cid: Int): Call<BasicResponse>

    @FormUrlEncoded
    @POST("android_add_to_cart.php")
    fun addToCart(@FieldMap data: Map<String, String>): Call<BasicResponse>


    @POST("get_cart_items.php")
    fun getCartItems(@Body body: Map<String, String>): Call<CartResponse>

    @POST("android_delete_cart.php")
    fun deleteCartItem(@Body body: Map<String, Int>): Call<BasicResponse>

    @POST("place_order.php")
    fun placeOrder(@Body orderData: Map<String, @JvmSuppressWildcards Any>): Call<Map<String, String>>

    @Multipart
    @POST("add_flower.php")
    fun addProduct(
        @PartMap partMap: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part images: List<MultipartBody.Part>
    ): Call<ClassicResponse>

    @GET("get_pending_orders.php")
    fun getPendingOrders(): Call<PendingOrderResponse>

    @GET("admin_get_transaction_history.php")
    fun getTransactionHistory(): Call<TransactionHistoryR    esponse>

    @GET("admin_get_pending_orders.php")  // Update with correct URL/path
    fun getAdminOrders(): Call<OrderResponse>


}