package com.wakdyan.mystore.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wakdyan.mystore.R;
import com.wakdyan.mystore.data.local.Checkout;
import com.wakdyan.mystore.utils.ViewModelFactory;

import java.util.List;

public class HistoryFragment extends Fragment {
    private HistoryAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.rv_history);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            mAdapter = new HistoryAdapter();
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());
            HistoryViewModel viewModel = new ViewModelProvider(this, factory).get(HistoryViewModel.class);

            viewModel.getAllCheckouts().observe(getViewLifecycleOwner(), new Observer<List<Checkout>>() {
                @Override
                public void onChanged(List<Checkout> checkouts) {
                    mAdapter.setCheckouts(checkouts);
                    mAdapter.notifyDataSetChanged();
                }
            });

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}