package com.avikal.ecommerce.dagger;



import com.avikal.ecommerce.communicator.ServiceCall;
import com.avikal.ecommerce.ui.activity.ProductCategoryActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by avikaljain on 21/2/18.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(ProductCategoryActivity productCategoryActivity);

    void inject(ServiceCall serviceCall);


}
