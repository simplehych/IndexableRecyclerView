package com.peopletech.organization;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * @author hych
 * @date 2019/2/20 09:29
 */
public class OgzSearchActivity extends AppCompatActivity {

    private View mSearchLayout;
    private View mAnimHolderIcon;
    private View mAnimHolderLayout;
    private long duration = 300;
    private int bgColor = Color.parseColor("#66000000");
    private OgzSearchFragment mSearchFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ogz_act_search);
        mSearchLayout = findViewById(R.id.ogz_search_layout);
        mAnimHolderIcon = findViewById(R.id.ogz_search_anim_holder_icon);
        mAnimHolderLayout = findViewById(R.id.ogz_search_anim_holder_layout);


        mSearchFragment = (OgzSearchFragment) getSupportFragmentManager().findFragmentById(R.id.ogz_search_content);

        initView();
    }

    private void hideSearchFragment() {
        if (mSearchFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(mSearchFragment).commit();
        }
    }

    private void showSearchFragment() {
        if (mSearchFragment.isHidden()) {
            getSupportFragmentManager().beginTransaction().show(mSearchFragment).commit();
        }
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setUpEnterAnimation();
            setUpExitAnimation();
        } else {
            setUpView();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setUpEnterAnimation() {
        Transition transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.arc_motion);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setUpExitAnimation() {
        Fade fade = new Fade();
        getWindow().setReturnTransition(fade);
        fade.setDuration(duration);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealShow() {
        ViewAnimUtils.animateRevealShow(this,
                mSearchLayout,
                mAnimHolderIcon.getWidth() / 2,
                bgColor,
                duration,
                new ViewAnimUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {

                    }

                    @Override
                    public void onRevealShow() {
                        setUpView();
                    }
                });
    }

    private void animateRevealHide() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewAnimUtils.animateRevealHide(this,
                    mSearchLayout,
                    mAnimHolderIcon.getWidth() / 2,
                    bgColor,
                    duration,
                    new ViewAnimUtils.OnRevealAnimationListener() {
                        @Override
                        public void onRevealHide() {
                            defBackPressed();
                        }

                        @Override
                        public void onRevealShow() {

                        }
                    });
        }
    }

    private void setUpView() {
        Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        animation.setDuration(duration);
        mAnimHolderLayout.startAnimation(animation);
        mAnimHolderLayout.setVisibility(View.VISIBLE);
        mAnimHolderIcon.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        mAnimHolderIcon.setVisibility(View.VISIBLE);
        animateRevealHide();
    }

    private void defBackPressed() {
        super.onBackPressed();
    }
}
