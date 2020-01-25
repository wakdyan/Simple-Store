package com.wakdyan.mystore.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    //    Product Entity
    @Query("Select * from product Order By id DESC")
    LiveData<List<Product>> getAllProduct();

    @Query("select * from product where id = :id")
    LiveData<Product> getProduct(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Delete
    void deleteProduct(Product product);

    //    Cart Entity
    @Query("Select * from cart Order By id DESC")
    LiveData<List<Cart>> getAllCart();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCart(Cart cart);

    @Query("select * from cart where id = :id")
    LiveData<Cart> getCart(int id);

    @Query("delete from cart where id= :id")
    void deleteCart(int id);

    //    Checkout
    @Query("select * from checkout order by id DESC")
    LiveData<List<Checkout>> getAllCheckout();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCheckout(Checkout checkout);
}
