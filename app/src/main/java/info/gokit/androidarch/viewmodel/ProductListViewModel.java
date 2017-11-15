package info.gokit.androidarch.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import java.util.List;

import info.gokit.androidarch.db.DatabaseCreator;
import info.gokit.androidarch.db.entity.ProductEntity;
import info.gokit.androidarch.model.Product;

/**
 * Created by llitfkitfk on 11/13/17.
 */

public class ProductListViewModel extends AndroidViewModel {


    private static final MutableLiveData ABSENT = new MutableLiveData();

    {
        ABSENT.setValue(null);
    }

    private LiveData<List<ProductEntity>> mObservabelProducts;

    public ProductListViewModel(@NonNull Application application) {
        super(application);

        final DatabaseCreator databaseCreator = DatabaseCreator.getInstance(this.getApplication());
        LiveData<Boolean> databaseCreated = databaseCreator.isDatabaseCreated();

        mObservabelProducts = Transformations.switchMap(databaseCreated, isDbCreated -> {
                if(!Boolean.TRUE.equals(isDbCreated)) {
                    return ABSENT;
                } else {
                    return databaseCreator.getDatabase().productDao().loadAllProducts();
                }
            }
        );
        databaseCreator.createDB(this.getApplication());
    }

    public LiveData<List<ProductEntity>> getProducts() {
        return mObservabelProducts;
    }
}
