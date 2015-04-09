package com.chefsfeed.proto.chefsfeed;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class MainPagerActivity extends ActionBarActivity implements View.OnClickListener{

    private static final int ANIMATION_DURATION = 300;
    private ViewPager mViewPager;
    private FragmentStatePagerAdapter mCFViewPagerAdapter;
    private ImageButton mDrawerButton;
    private RelativeLayout mFadeLayout;
    private LinearLayout mDrawer;
    private boolean mInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pager);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mDrawer = (LinearLayout) findViewById(R.id.drawer);

        mFadeLayout = (RelativeLayout) findViewById(R.id.drawer_background);

        Integer colorFrom = getResources().getColor(R.color.transparent);
        Integer colorTo = getResources().getColor(R.color.faded);
        final ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                mFadeLayout.setBackgroundColor((Integer) animator.getAnimatedValue());
            }

        });

        mDrawer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(!mInit) {
                    mDrawer.setTranslationY(mDrawer.getHeight());
                    mDrawer.setVisibility(View.GONE);
                    mInit = true;
                }
            }
        });

        mDrawerButton = (ImageButton) findViewById(R.id.drawer_button_on);
        mDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawer.getVisibility() == View.VISIBLE) {
                    mDrawer.animate().translationY(mDrawer.getHeight()).setDuration(ANIMATION_DURATION)
                            .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            if(mDrawer.getTranslationY() == mDrawer.getHeight()) {
                                mDrawer.setVisibility(View.GONE);
                            }
                        }
                    });
                    colorAnimation.reverse();

                } else {
                    Log.d(getClass().getSimpleName(), "second " + mDrawer.getTranslationY());
                    mDrawer.setVisibility(View.VISIBLE);
                    mDrawer.animate().translationY(0).setDuration(ANIMATION_DURATION);
                    colorAnimation.start();
                }
            }
        });

        findViewById(R.id.scene_button).setOnClickListener(this);
        findViewById(R.id.saved_button).setOnClickListener(this);
        findViewById(R.id.search_button).setOnClickListener(this);
        findViewById(R.id.profile_button).setOnClickListener(this);
        mCFViewPagerAdapter = new ScenePagerAdapter();
        mViewPager.setAdapter(mCFViewPagerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scene_button:
                mCFViewPagerAdapter = new ScenePagerAdapter();
                mViewPager.setAdapter(mCFViewPagerAdapter);
                break;
            case R.id.saved_button:
                mCFViewPagerAdapter = new SavedPagerAdapter();
                mViewPager.setAdapter(mCFViewPagerAdapter);
                break;
            case R.id.search_button:
                mCFViewPagerAdapter = new SearchPagerAdapter();
                mViewPager.setAdapter(mCFViewPagerAdapter);
                break;
            case R.id.profile_button:
                mCFViewPagerAdapter = new ProfilePagerAdapter();
                mViewPager.setAdapter(mCFViewPagerAdapter);
                break;
        }
    }

    private class ScenePagerAdapter extends FragmentStatePagerAdapter {

        public ScenePagerAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int i) {
            SceneFragment frag = new SceneFragment();
            return frag;
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

    private class SavedPagerAdapter extends FragmentStatePagerAdapter {

        public SavedPagerAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int i) {
            SavedFragment frag = new SavedFragment();
            return frag;
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

    private class ProfilePagerAdapter extends FragmentStatePagerAdapter {

        public ProfilePagerAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int i) {
            ProfileFragment frag = new ProfileFragment();
            return frag;
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

    private class SearchPagerAdapter extends FragmentStatePagerAdapter {

        public SearchPagerAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int i) {
            SearchFragment frag = new SearchFragment();
            return frag;
        }

        @Override
        public int getCount() {
            return 1;
        }
    }
}
