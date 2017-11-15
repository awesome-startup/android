package info.gokit.androidarch.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import info.gokit.androidarch.db.entity.ProductEntity;
import info.gokit.androidarch.model.Product;

/**
 * Created by llitfkitfk on 11/13/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductEntity> productList;

    public ProductAdapter(ProductClickCallback mProductClickCallback) {

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        public ProductViewHolder(View itemView) {
            super(itemView);
        }
    }
}
