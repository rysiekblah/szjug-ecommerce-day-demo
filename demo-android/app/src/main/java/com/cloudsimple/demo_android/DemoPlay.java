package com.cloudsimple.demo_android;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by tomek on 3/17/16.
 */
public interface DemoPlay {

    @GET("products")
    Call<List<Product>> productsList();

    @GET("/product/{id}")
    Call<Product> productDetails(@Path("id") int productId);
}
