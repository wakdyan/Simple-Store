package com.wakdyan.mystore.ui.detail;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wakdyan.mystore.data.local.Cart;
import com.wakdyan.mystore.data.local.Product;
import com.wakdyan.mystore.data.local.ProductRepository;

public class DetailViewModel extends AndroidViewModel {
    private int mId;
    private ProductRepository mProductRepository;

    public DetailViewModel(Application application) {
        super(application);

        mProductRepository = new ProductRepository(application);
    }

    public void setId(int id) {
        mId = id;
    }

    private int getId() {
        return mId;
    }

    public void insertProduct(Product product) {
        mProductRepository.insertProduct(product);
    }

    public void deleteProduct(Product product) {
        mProductRepository.deleteProduct(product);
    }

    public LiveData<Product> getProduct() {
        return mProductRepository.getProduct(getId());
    }

    public void insertCart(Cart cart) {
        mProductRepository.insertCart(cart);
    }

}
