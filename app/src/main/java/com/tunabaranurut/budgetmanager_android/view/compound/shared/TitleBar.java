package com.tunabaranurut.budgetmanager_android.view.compound.shared;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.tunabaranurut.budgetmanager_android.R;
import com.tunabaranurut.budgetmanager_android.view.compound.CompoundLayout;
import com.tunabaranurut.budgetmanager_android.view.extended.AppButton;

/**
 * Created by tunabaranurut on 27.12.2018.
 */

public class TitleBar extends CompoundLayout {

    private TextView titleTv;
    private AppButton backBtn;

    private String title;

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(R.layout.compount_titlebar, R.styleable.TitleBar, context, attrs);

        this.titleTv.setText(title);
    }

    @Override
    public void init(TypedArray attrs) {
        title = attrs.getString(R.styleable.TitleBar_title);
    }

    @Override
    public void bindViews(View layout) {
        backBtn = layout.findViewById(R.id.back_btn);
        titleTv = layout.findViewById(R.id.title_tv);
    }

    public void setBackBtnOnClickListener(View.OnClickListener onClickListener){
        backBtn.setOnClickListener(onClickListener);
    }
}