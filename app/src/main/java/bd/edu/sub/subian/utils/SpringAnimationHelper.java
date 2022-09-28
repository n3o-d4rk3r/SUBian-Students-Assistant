package bd.edu.sub.subian.utils;


import android.view.View;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;

public class SpringAnimationHelper {

    /**
     * A helper method to animate th view supplied
     * @param view The {@link View} to animate
     */
    public static void performAnimation(final View view){
        SpringSystem springSystem = SpringSystem.create();
        Spring spring = springSystem.createSpring();
        spring.addListener(new SimpleSpringListener(){
            @Override
            public void onSpringUpdate(Spring spring) {
                float value = (float) spring.getCurrentValue();
                float scale;
                if (value < 0.5f){
                    scale = 1.0f - (value * 0.5f);
                } else {
                    scale = 0.5f + (value * 0.5f);
                }
                view.setScaleX(scale);
                view.setScaleY(scale);
            }
        });
        spring.setEndValue(1);
    }
}
