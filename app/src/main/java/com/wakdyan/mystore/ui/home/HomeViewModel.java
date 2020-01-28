package com.wakdyan.mystore.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.wakdyan.mystore.data.local.Product;
import com.wakdyan.mystore.data.local.ProductRepository;
import com.wakdyan.mystore.utils.DataDummy;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private ProductRepository mRepository;

    public HomeViewModel(@NonNull Application application) {
        super(application);

        mRepository = new ProductRepository(application);
    }

    public List<Product> getProducts() {
        return DataDummy.generateDataDummy();
    }
}