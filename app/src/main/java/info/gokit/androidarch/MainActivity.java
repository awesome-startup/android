package info.gokit.androidarch;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import info.gokit.androidarch.databinding.ActivityMainBinding;
import info.gokit.androidarch.model.NameViewModel;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
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

}
