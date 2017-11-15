package info.gokit.androidarch.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import info.gokit.androidarch.db.entity.ProductEntity;

/**
 * Created by llitfkitfk on 11/14/17.
 */

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    LiveData<List<ProductEntity>> loadAllProducts();
}
