package com.wakdyan.mystore.data.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductRepository {
    private ProductDao mDao;
    private ExecutorService mExecutorService;

    public ProductRepository(Application application) {
        mExecutorService = Executors.newSingleThreadExecutor();
        ProductDatabase db = ProductDatabase.getInstance(application);
        mDao = db.mProductDao();
    }

    // Product
    public LiveData<List<Product>> getAllProducts() {
        return mDao.getAllProduct();
    }

    public void insertProduct(final Product product) {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mDao.insertProduct(product);
            }
        });
    }

    //    Cart
    public LiveData<List<Cart>> getAllCharts() {
        return mDao.getAllCart();
    }

    public void insertCart(final Cart cart) {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mDao.insertCart(cart);
            }
        });
    }


    public LiveData<Product> getProduct(int id) {
        return mDao.getProduct(id);
    }

    public void deleteProduct(final Product product) {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mDao.deleteProduct(product);
            }
        });
    }

    public LiveData<Cart> getCart(int id) {
        return mDao.getCart(id);
    }

    public void deleteCart(final int id) {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mDao.deleteCart(id);
            }
        });
    }

    public LiveData<List<Checkout>> getAllCheckouts() {
        return mDao.getAllCheckout();
    }

    public void insertCheckout(final Checkout checkout) {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mDao.insertCheckout(checkout);
            }
        });
    }
}
