package com.henlinkeji.shenbian.shenbian.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.MapView;
import com.henlinkeji.shenbian.shenbian.R;


/**
 * Created by smyh on 2015/4/28.
 */
public class HomeFagment extends Fragment {

    MapView mMapView = null;
    private SearchView sv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_one, null);

        mMapView = (MapView) view.findViewById(R.id.map);
//        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

//        SearchView searchView =
//                (SearchView) view.findViewById(R.id.sv);
//
//        searchView.setIconifiedByDefault(false);
//
//        View underline = searchView.findViewById(R.id.search_plate);
//
//        Context context = getActivity();
//        underline.setBackgroundColor(context.getColor(R.color.white));


        return view;
    }

    //重写setMenuVisibility方法，不然会出现叠层的现象
    @Override
    public void setMenuVisibility(boolean menuVisibile) {
        super.setMenuVisibility(menuVisibile);
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisibile ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);



        //去掉搜索框的下划线


    }
}


