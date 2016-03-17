package com.cloudsimple.demo_android;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tomek on 3/17/16.
 */
public class DemoPlayService {
    private static final String SVCURL = "http://10.0.2.2:9000";
    private Retrofit retrofit;
    private DemoPlay demoPlay;

    public DemoPlayService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SVCURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        demoPlay = retrofit.create(DemoPlay.class);
    }

    public List<Product> getProducts() {
        List<Product> products = Lists.newArrayList();
        products.add(new Product(1, "Fork", "Table"));
        products.add(new Product(2, "Spoon", "Table"));
//        try {
//            products = demoPlay.productsList().execute().body();
//        } catch (IOException e) {
//            products = Lists.newArrayList();
//        }
        return products;
    }

    public Product getProduct(int id) {
        try {
            return demoPlay.productDetails(id).execute().body();
        } catch (IOException e) {
            return new Product(-1, "", "");
        }
    }

}
