package com.apporio.demotaxiappdriver.typeface;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextTypeItalics extends TextView {

    public TextTypeItalics(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public TextTypeItalics(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public TextTypeItalics(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("OpenSans_Italic.ttf", context);
        setTypeface(customFont);
    }
}