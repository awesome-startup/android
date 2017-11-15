package info.gokit.androidarch.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import info.gokit.androidarch.db.dao.ProductDao;
import info.gokit.androidarch.db.entity.ProductEntity;

/**
 * Created by llitfkitfk on 11/14/17.
 */

@Database(entities = {ProductEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    static final String DATABASE_NAME = "app-db";

    public abstract ProductDao productDao();

}
