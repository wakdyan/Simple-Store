package com.wakdyan.mystore.ui.detail;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wakdyan.mystore.data.local.Cart;
import com.wakdyan.mystore.data.local.Product;
import com.wakdyan.mystore.data.local.ProductRepository;

public class DetailViewModel extends ViewModel {
    private int mId;
    private boolean state = false;
    private MutableLiveData<Boolean> newState = new MutableLiveData<>();
    private ProductRepository mProductRepository;

    public DetailViewModel(Application application) {
        mProductRepository = new ProductRepository(application);
    }


    public void setId(int id) {
        mId = id;
    }

    private int getId() {
        return mId;
    }

//    public void setFavorite() {
//        ProductRepository productRepository = new ProductRepository();
//        newState = !state;

//        newState.postValue(!state);
//        state = !state;
//
//        productRepository.setFavorite(state);
//    }

    public void insertProduct(Product product) {
        mProductRepository.insertProduct(product);
    }

    public void deleteProduct(Product product) {
        mProductRepository.deleteProduct(product);
    }

    public LiveData<Product> getProduct() {
        return mProductRepository.getProduct(mId);
    }

    public void insertCart(Cart cart) {
        mProductRepository.insertCart(cart);
    }


//    public ArrayList<Product> getDetailProduct() {
//        return DataDummy.generateDataDummy();
//    }
}
