package com.wakdyan.mystore.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wakdyan.mystore.R;
import com.wakdyan.mystore.data.local.Product;
import com.wakdyan.mystore.utils.ViewModelFactory;

import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.rv_home);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());
            HomeViewModel viewModel = new ViewModelProvider(getActivity(), factory).get(HomeViewModel.class);
            List<Product> products = viewModel.getProducts();

            HomeAdapter adapter = new HomeAdapter();
            adapter.setProductList(products);
            adapter.notifyDataSetChanged();

            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(adapter);
        }
    }
}