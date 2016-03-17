package com.cloudsimple.demo_android;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomek on 3/17/16.
 */
public class ProductsListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Product>> {

    private Bus bus;
    private DemoPlayService service;
    private AsyncTaskLoader<List<Product>> productsLoader;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bus = DemoApplication.getInstance().bus;
        setListAdapter(new ProductAdapter(getActivity(), R.layout.adapter_product_row, new ArrayList<Product>()));
        productsLoader = new ProductsLoader(getActivity(), DemoApplication.getInstance().service);
        getLoaderManager().initLoader(666, null, this).forceLoad();
        Log.i("DEMO", "ProductsListFragment - CREATED");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Product product = (Product) getListAdapter().getItem(position);
        bus.post(product);
    }

    @Override
    public Loader<List<Product>> onCreateLoader(int id, Bundle args) {
        Log.i("DEMO", "ProductLoader - CREATED");
        return productsLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Product>> loader, List<Product> data) {
        setListAdapter(new ProductAdapter(getActivity(), R.layout.adapter_product_row, data));
    }

    @Override
    public void onLoaderReset(Loader<List<Product>> loader) {

    }

    private static class ProductsLoader extends AsyncTaskLoader<List<Product>> {
        private DemoPlayService service;
        public ProductsLoader(Context context, DemoPlayService service) {
            super(context);
            this.service = service;
        }

        @Override
        public List<Product> loadInBackground() {
            List<Product> products = service.getProducts();
            Log.i("DEMO", "Loaded prods: " + products.size());
            return products;
        }
    }

    private static class ProductAdapter extends ArrayAdapter<Product> {

        private int resId;
        private LayoutInflater layoutInflater;
        private List<Product> products;

        public ProductAdapter(Context context, int resource, List<Product> objects) {
            super(context, resource, objects);
            this.products = objects;
            this.resId = resource;
            this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Log.i("DEMO", "Adapter created: " + products.size());
        }

        @Override
        public int getCount() {
            return products.size();
        }

        @Override
        public Product getItem(int position) {
            return products.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View cellView = convertView;
            Cell cell;
            Product info = products.get(position);
            Log.i("DEMO", " >>> LOAD PROD: " + info.name);

            if (convertView == null) {
                cell = new Cell();
                cellView = layoutInflater.inflate(resId, null);
                cell.nameView = (TextView) cellView.findViewById(R.id.product_name);
                cell.categoryView = (TextView) cellView.findViewById(R.id.product_category);
                cellView.setTag(cell);
            } else {
                cell = (Cell) cellView.getTag();
            }
            cell.nameView.setText(info.name);
            cell.categoryView.setText(info.category);
            return cellView;
        }

        static class Cell {
        public TextView nameView;
        public TextView categoryView;
        }
    }
}
