package com.wakdyan.mystore.ui.cart;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wakdyan.mystore.data.local.Cart;
import com.wakdyan.mystore.data.local.Checkout;
import com.wakdyan.mystore.data.local.ProductRepository;

import java.util.List;

public class CartViewModel extends ViewModel {
    private ProductRepository mProductRepository;

    public CartViewModel(Application application) {
        mProductRepository = new ProductRepository(application);
    }

    public LiveData<List<Cart>> getAllCarts() {
        return mProductRepository.getAllCharts();
    }

    public void insert(Cart cart) {
        mProductRepository.insertCart(cart);
    }

    public void delete(int id) {
        mProductRepository.deleteCart(id);
    }

    public void insertCheckout(Checkout checkout) {
        mProductRepository.insertCheckout(checkout);
    }
}
