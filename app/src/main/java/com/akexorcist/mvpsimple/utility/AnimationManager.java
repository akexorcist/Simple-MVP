package com.akexorcist.mvpsimple.utility;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.view.View;

import com.akexorcist.mvpsimple.R;

/**
 * Created by Akexorcist on 7/14/16 AD.
 */
public class AnimationManager {
    private static AnimationManager animationManager;

    public static AnimationManager getInstance() {
        if (animationManager == null) {
            animationManager = new AnimationManager();
        }
        return animationManager;
    }

    public static void clear() {
        animationManager = null;
    }

    public void applyViewFadeIn(View view) {
        Animator fadeInAnimator = AnimatorInflater.loadAnimator(Contextor.getInstance().getContext(), R.animator.animator_fade_in);
        fadeInAnimator.setTarget(view);
        fadeInAnimator.start();
        view.setVisibility(View.VISIBLE);
    }

    public void applyViewFadeOut(final View view) {
        Animator fadeInAnimator = AnimatorInflater.loadAnimator(Contextor.getInstance().getContext(), R.animator.animator_fade_out);
        fadeInAnimator.setTarget(view);
        fadeInAnimator.start();
        fadeInAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

}
