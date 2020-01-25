package com.wakdyan.mystore.ui.home;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.wakdyan.mystore.data.local.Product;
import com.wakdyan.mystore.data.local.ProductRepository;
import com.wakdyan.mystore.utils.DataDummy;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private ProductRepository mRepository;
//    private MutableLiveData<ArrayList<Product>> mLiveData;

    public HomeViewModel(Application application) {
        mRepository = new ProductRepository(application);
//        mLiveData = new MutableLiveData<>();
//        mLiveData.postValue(DataDummy.generateDataDummy());
    }

    public List<Product> getProducts() {
        return DataDummy.generateDataDummy();
    }


//    public MutableLiveData<ArrayList<Product>> getLiveData() {
//        return mLiveData;
//    }
}