package com.wakdyan.mystore.ui.history;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wakdyan.mystore.data.local.Checkout;
import com.wakdyan.mystore.data.local.ProductRepository;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private ProductRepository mProductRepository;

    public HistoryViewModel(Application application) {
        super(application);

        mProductRepository = new ProductRepository(application);
    }

    public LiveData<List<Checkout>> getAllCheckouts() {
        return mProductRepository.getAllCheckouts();
    }
}