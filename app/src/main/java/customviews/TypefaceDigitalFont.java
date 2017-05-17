package customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lenovo-pc on 5/1/2017.
 */

public class TypefaceDigitalFont extends TextView {

    public TypefaceDigitalFont(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public TypefaceDigitalFont(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public TypefaceDigitalFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("digital-7.ttf", context);
        setTypeface(customFont);
    }

}

