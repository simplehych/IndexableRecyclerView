package com.peopletech.organization;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

/**
 * @author hych
 * @date 2019/2/20 09:29
 */
public class OgzSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private View mSearchLayout;
    private View mSearchTop;
    private View mAnimHolderIcon;
    private View mAnimHolderLayout;
    private long animDuration = 300;
    private long transitionDuration = 100;
    private int bgColor = Color.parseColor("#66000000");
    private OgzSearchFragment mSearchFragment;
    private View mSearchBlank;
    private EditText mSearchEdit;
    private View mSearchDel;
    private View mSearchCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ogz_act_search);
        findView();
        initView();
    }

    private void findView() {
        mSearchLayout = findViewById(R.id.ogz_search_layout);
        mSearchTop = findViewById(R.id.ogz_search_top);

        mAnimHolderIcon = findViewById(R.id.ogz_search_anim_holder_icon);
        mAnimHolderLayout = findViewById(R.id.ogz_search_anim_holder_layout);

        mSearchBlank = findViewById(R.id.ogz_search_blank);
        mSearchFragment = (OgzSearchFragment) getSupportFragmentManager().findFragmentById(R.id.ogz_search_content);

        mSearchEdit = (EditText) findViewById(R.id.ogz_search_frame_et);
        mSearchDel = findViewById(R.id.ogz_search_frame_del);
        mSearchCancel = findViewById(R.id.ogz_search_frame_cancel);
    }

    private void initView() {
        mSearchTop.setVisibility(View.GONE);
        initAnimator();

        mSearchDel.setOnClickListener(this);
        mSearchCancel.setOnClickListener(this);
    }

    private void initAnimator() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setUpEnterAnimation();
            setUpExitAnimation();
        } else {
            showHolderView();
            showSearchTop();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ogz_search_frame_del) {
            mSearchEdit.setText(null);
            hideSearchContent();
            hideSearchBlank();

        } else if (id == R.id.ogz_search_frame_cancel) {
            onBackPressed();
        }
    }

    private void showSearchContent() {
        if (mSearchFragment.isHidden()) {
            getSupportFragmentManager().beginTransaction().show(mSearchFragment).commit();
        }
    }

    private void hideSearchContent() {
        if (mSearchFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(mSearchFragment).commit();
        }
        showSearchBlank();
    }

    private void showSearchBlank() {
        mSearchBlank.setVisibility(View.VISIBLE);
    }

    private void hideSearchBlank() {
        mSearchBlank.setVisibility(View.GONE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setUpEnterAnimation() {
        Transition transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.arc_motion);
        transition.setDuration(transitionDuration);
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
        fade.setDuration(transitionDuration);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealShow() {
        ViewAnimUtils.animateRevealShow(this,
                mSearchLayout,
                mAnimHolderIcon.getWidth() / 2,
                bgColor,
                animDuration,
                new ViewAnimUtils.OnRevealAnimationListener() {

                    @Override
                    public void onStart() {
                        showSearchTop();
                    }

                    @Override
                    public void onEnd() {
                        showHolderView();
                    }
                });
    }

    private void animateRevealHide() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewAnimUtils.animateRevealHide(this,
                    mSearchLayout,
                    mAnimHolderIcon.getWidth() / 2,
                    bgColor,
                    animDuration,
                    new ViewAnimUtils.OnRevealAnimationListener() {

                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onEnd() {
                            defBackPressed();
                        }
                    });
        }
    }

    private void showHolderView() {
        fadeView(mAnimHolderLayout);
        mAnimHolderLayout.setVisibility(View.VISIBLE);

        mAnimHolderIcon.setVisibility(View.GONE);
    }


    private void showSearchTop() {
        fadeView(mSearchTop);
        mSearchTop.setVisibility(View.VISIBLE);
    }

    private void fadeView(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        animation.setDuration(animDuration);
        view.startAnimation(animation);
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
