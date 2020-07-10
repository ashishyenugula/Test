package com.ashish.test.service;
import com.ashish.test.model.PixabayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.ashish.test.util.Constatns.Pixabay.PIXABAY_API_PATH;



public interface PixabayService {
    @GET(PIXABAY_API_PATH)
    Call<PixabayResponse> imageSearch(@Query("q") String keywords);

    @GET(PIXABAY_API_PATH + "&editors_choice=true")
    Call<PixabayResponse> editorsChoice();
}
