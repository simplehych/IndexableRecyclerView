package com.peopletech.organization;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * @author hych
 * @date 2019/2/20 10:00
 */
public class ViewAnimUtils {
    interface OnRevealAnimationListener {
        void onStart();

        void onEnd();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void animateRevealShow(Context context, final View view, int startRadius, final int color, long duration, final OnRevealAnimationListener listener) {

        /**
         * 中心点
         */
//        int cx = (view.getLeft() + view.getRight()) / 2;
//        int cy = (view.getTop() + view.getBottom()) / 2;

        /**
         * 右上角
         */
        int cx = view.getRight();
        int cy = view.getTop();
        float finalRadius = (float) Math.hypot(view.getWidth(), view.getHeight());

        Animator animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, startRadius, finalRadius);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setBackgroundColor(color);
                if (listener != null) {
                    listener.onStart();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.VISIBLE);
                if (listener != null) {
                    listener.onEnd();
                }
            }
        });
        animator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void animateRevealHide(Context context, final View view, int finalRadius, final int color, long duration, final OnRevealAnimationListener listener) {
        /**
         * 中心点
         */
//        int cx = (view.getLeft() + view.getRight()) / 2;
//        int cy = (view.getTop() + view.getBottom()) / 2;
        /**
         * 右上角
         */
        int cx = view.getRight();
        int cy = view.getTop();
        int initialRadius = view.getWidth();
        Animator animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, finalRadius);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setBackgroundColor(color);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (listener != null) {
                    listener.onEnd();
                }
                view.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
    }
}
