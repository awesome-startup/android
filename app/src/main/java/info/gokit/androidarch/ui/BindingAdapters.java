package info.gokit.androidarch.ui;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by llitfkitfk on 11/13/17.
 */

public class BindingAdapters {

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
