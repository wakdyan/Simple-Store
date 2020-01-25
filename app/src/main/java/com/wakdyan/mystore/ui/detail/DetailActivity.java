package com.wakdyan.mystore.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.wakdyan.mystore.R;
import com.wakdyan.mystore.data.local.Cart;
import com.wakdyan.mystore.data.local.Product;
import com.wakdyan.mystore.ui.cart.CartActivity;
import com.wakdyan.mystore.utils.ViewModelFactory;

public class DetailActivity extends AppCompatActivity {
    public final static String ID = "id";

    private DetailViewModel mViewModel;
    private ImageButton mButtonFavorite;
    private Cart mCart;
    private Product mProduct;

    private int mAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());
        mViewModel = new ViewModelProvider(this, factory).get(DetailViewModel.class);

        TextView textDescription = findViewById(R.id.tv_detail_description);
        TextView textPrice = findViewById(R.id.tv_detail_price);
        TextView textName = findViewById(R.id.tv_detail_name);
        ImageView imageProduct = findViewById(R.id.iv_detail_image);
        mButtonFavorite = findViewById(R.id.bt_favorite);
        FloatingActionButton fab = findViewById(R.id.fab);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Product");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final Product product = getIntent().getParcelableExtra(ID);

        if (product != null) {
            int id = product.getId();
            double voteAverage = product.getVoteAverage();
            String name = product.getName();
            String url = product.getUrl();
            String description = product.getDescription();
            String price = product.getPrice();

            mViewModel.setId(id);
            textName.setText(name);
            textPrice.setText(price);
            textDescription.setText(description);

            Glide.with(getApplicationContext())
                    .load(url)
                    .into(imageProduct);

            mProduct = new Product(id, voteAverage, name, description, price, url);
        }

        mViewModel.getProduct().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(final Product product) {
                if (product != null) {
                    mButtonFavorite.setBackground(getApplicationContext().getDrawable(R.drawable.ic_favorite_active));
                    mButtonFavorite.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mViewModel.deleteProduct(product);
                        }
                    });
                } else {
                    mButtonFavorite.setBackground(getApplicationContext().getDrawable(R.drawable.ic_favorite_inactive));
                    mButtonFavorite.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mViewModel.insertProduct(mProduct);
                        }
                    });
                }
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAmount += 1;
                mCart = new Cart(mProduct.getId(),
                        mAmount,
                        mProduct.getVoteAverage(),
                        mProduct.getName(),
                        mProduct.getDescription(),
                        mProduct.getPrice(),
                        mProduct.getUrl()
                );

                mViewModel.insertCart(mCart);

                Snackbar.make(view, "Added to cart", Snackbar.LENGTH_LONG).setAction("View chart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DetailActivity.this, CartActivity.class);
                        startActivity(intent);
                    }
                }).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
