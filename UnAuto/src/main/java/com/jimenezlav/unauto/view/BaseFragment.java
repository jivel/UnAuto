package com.jimenezlav.unauto.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by jimenezlav on 19/03/16.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        initView(view, savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        unbindViews();
        super.onDestroyView();
    }

    /**
     * Replace all the annotated fields with ButterKnife annotations with the proper value
     */
    private void bindViews(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    private void unbindViews() {
        ButterKnife.unbind(this);
    }

    /**
     * Use this method to initialize view components.
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * Specify the layout of the fragment to be inflated in the {@link BaseFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    protected abstract int getFragmentLayout();

}
