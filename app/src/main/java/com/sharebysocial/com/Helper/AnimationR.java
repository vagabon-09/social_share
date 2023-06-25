package com.sharebysocial.com.Helper;

import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class AnimationR {
    public void leftZoomOut(ImageView imageView, int Duration) {
        Animation anim = new ScaleAnimation(500, 1, 500, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 1);
        anim.setDuration(Duration); // Animation duration in milliseconds
        anim.setFillAfter(true); // Keep the final state of the animation
        imageView.startAnimation(anim);
        anim.start();
    }

    public void rightZoomOut(ImageView imageView, int duration) {
        Animation anim = new ScaleAnimation(-500, 1, -500, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 1);
        anim.setDuration(duration); // Animation duration in milliseconds
        anim.setFillAfter(true); // Keep the final state of the animation
        imageView.startAnimation(anim);
        anim.start();
    }


}
