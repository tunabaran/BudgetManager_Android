package com.tunabaranurut.budgetmanager_android.view.compound;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by tunabaranurut on 27.12.2018.
 */

public abstract class CompoundLayout extends FrameLayout {

    private FrameLayout layout;

    public CompoundLayout(@LayoutRes int layoutRes, Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        layout = (FrameLayout) LayoutInflater.from(context).inflate(layoutRes, this);

        init(null);

        bindViews(layout);
    }

    public CompoundLayout(@LayoutRes int layoutRes, @StyleableRes int[] attrRes, @NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        layout = (FrameLayout) LayoutInflater.from(context).inflate(layoutRes, this);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                attrRes,
                0, 0);
        init(typedArray);

        bindViews(layout);

    }

    public abstract void init(TypedArray attrs);

    public abstract void bindViews(View layout);

//    public void setVisibility(int visibility){
//        layout.setVisibility(visibility);
//    }w

    public ViewGroup getLayout() {
        return layout;
    }
}

