package info.gokit.androidarch.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by llitfkitfk on 11/13/17.
 */

public class NameViewModel extends ViewModel {


    private MutableLiveData<String> mCurrentName;

    public MutableLiveData<String> getmCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<>();
        }
        return mCurrentName;
    }

}
