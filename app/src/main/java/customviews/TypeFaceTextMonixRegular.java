package customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by user on 2/28/2017.
 */

public class TypeFaceTextMonixRegular extends TextView {


    public TypeFaceTextMonixRegular(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public TypeFaceTextMonixRegular(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public TypeFaceTextMonixRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("Monix-Regular.otf", context);
        setTypeface(customFont);
    }
}
