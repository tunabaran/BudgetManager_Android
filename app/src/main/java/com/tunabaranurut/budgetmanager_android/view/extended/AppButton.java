package com.tunabaranurut.budgetmanager_android.view.extended;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.Button;

import com.tunabaranurut.budgetmanager_android.R;

/**
 * Created by tunabaranurut on 27.12.2018.
 */

public class AppButton extends AppCompatButton {


    public AppButton(Context context) {
        super(context);
    }

    public AppButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public AppButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.AppButton,
                0, 0);

        int color = typedArray.getColor(R.styleable.AppButton_tintColor,getResources().getColor(R.color.rouge));

//        ViewCompat.setBackgroundTintList(this,ColorStateList.valueOf(getResources().getColor(color)));
        ViewCompat.setBackgroundTintList(this,ColorStateList.valueOf(color));
    }

    public static Drawable setTint(Drawable drawable, int color) {
        final Drawable newDrawable = DrawableCompat.wrap(drawable);
        newDrawable.clearColorFilter();
        DrawableCompat.setTint(newDrawable, color);
        return newDrawable;
    }
}
