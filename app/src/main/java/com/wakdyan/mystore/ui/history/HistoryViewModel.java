package com.wakdyan.mystore.ui.history;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.wakdyan.mystore.data.local.Checkout;
import com.wakdyan.mystore.data.local.ProductRepository;

import java.util.List;

public class HistoryViewModel extends ViewModel {
    private ProductRepository mProductRepository;

    public HistoryViewModel(Application application) {
        mProductRepository = new ProductRepository(application);
    }

    public LiveData<List<Checkout>> getAllCheckouts() {
        return mProductRepository.getAllCheckouts();
    }
}