package info.gokit.androidarch;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import info.gokit.androidarch.databinding.ActivityMainBinding;
import info.gokit.androidarch.model.Product;
import info.gokit.androidarch.ui.ProductAdapter;
import info.gokit.androidarch.ui.ProductClickCallback;
import info.gokit.androidarch.viewmodel.NameViewModel;
import info.gokit.androidarch.viewmodel.ProductListViewModel;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {


    private ProductAdapter mProductAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mBinding.message.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mBinding.message.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mBinding.message.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    private NameViewModel mModel;
    private ActivityMainBinding mBinding;

    private final ProductClickCallback mProductClickCallback = new ProductClickCallback() {
        @Override
        public void onClick(Product product) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                Log.i("gokit", "Product click");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mProductAdapter = new ProductAdapter(mProductClickCallback);
        mBinding.productsList.setAdapter(mProductAdapter);


        final ProductListViewModel viewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
        viewModel.getProducts().observe(this, products -> {
            if( products != null) {
                mBinding.setIsLoading(false);
                mProductAdapter.setProductList(products);
            } else {
                mBinding.setIsLoading(true);
            }
            mBinding.executePendingBindings();
        });


        mBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBinding.updateMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String anotherName = "Update button clicked";
                mModel.getmCurrentName().setValue(anotherName);
            }
        });

        mModel = ViewModelProviders.of(this).get(NameViewModel.class);
        mModel.getmCurrentName().observe(this, new Observer<String>() {

            @Override
            public void onChanged(@Nullable String s) {
                mBinding.message.setText(s);
            }
        });


    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return null;
    }
}
