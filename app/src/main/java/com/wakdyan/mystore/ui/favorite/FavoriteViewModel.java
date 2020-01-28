package com.wakdyan.mystore.ui.favorite;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wakdyan.mystore.data.local.Product;
import com.wakdyan.mystore.data.local.ProductRepository;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {
    ProductRepository mProductRepository;

    public FavoriteViewModel(Application application) {
        super(application);

        mProductRepository = new ProductRepository(application);
    }

    public LiveData<List<Product>> getAllFavorites() {
        return mProductRepository.getAllProducts();
    }

}