package com.wakdyan.mystore.ui.favorite;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wakdyan.mystore.data.local.Product;
import com.wakdyan.mystore.data.local.ProductRepository;

import java.util.List;

public class FavoriteViewModel extends ViewModel {
    ProductRepository mProductRepository;

    public FavoriteViewModel(Application application) {
        mProductRepository = new ProductRepository(application);
    }

    public LiveData<List<Product>> getAllFavorites() {
        return mProductRepository.getAllProducts();
    }

}