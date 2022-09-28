package bd.edu.sub.subian.utils;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
public class CustomTabLayout extends SmartTabLayout {

    public CustomTabLayout(Context context) {
        super(context);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected TextView createDefaultTabView(CharSequence title) {
        TextView textView = super.createDefaultTabView(title);
        textView.setTypeface(Typeface.DEFAULT);
        return textView;
    }

}

