package com.avikal.ecommerce.communicator;

import com.avikal.ecommerce.model.ProductResponse;
import com.avikal.ecommerce.utils.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface DependService {

    @GET(Constants.REQUEST_PRODUCT)
    Observable<ProductResponse> getProduct();

}
