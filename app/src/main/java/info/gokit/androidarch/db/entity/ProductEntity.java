package info.gokit.androidarch.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import info.gokit.androidarch.model.Product;

/**
 * Created by llitfkitfk on 11/13/17.
 */

@Entity(tableName = "products")
public class ProductEntity implements Product {

    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
