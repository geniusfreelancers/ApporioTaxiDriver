package com.apporio.demotaxiappdriver.typeface;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextTypeRegular extends TextView {

    public TextTypeRegular(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public TextTypeRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public TextTypeRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("OpenSans_Regular.ttf", context);
        setTypeface(customFont);
    }
}