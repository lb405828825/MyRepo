package com.fineos.learpropertyanimator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;

    private ImageView mMenuView;

    private static boolean isMenuOpen = false;

    private int[] mResIds = {R.id.image_composer_camera,
            R.id.image_composer_music,
            R.id.image_composer_place,
            R.id.image_composer_sleep,
            R.id.image_composer_thought,
            R.id.image_composer_with,
    };

    private List<ImageView> mImageViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);

        mMenuView = (ImageView) findViewById(R.id.image_menu);
        mMenuView.setOnClickListener(this);

        mImageViews = new ArrayList<ImageView>();
        for (int i = 0; i < mResIds.length; i++) {
            ImageView imageView = (ImageView) findViewById(mResIds[i]);
            imageView.setOnClickListener(this);
            mImageViews.add(imageView);
        }

        //
    }

    public void BtnOnClick(View view) {

        //视图动画animation
//        TranslateAnimation animation = new TranslateAnimation(0, 200, 0, 300);
//        animation.setDuration(1000);
//        animation.setFillAfter(true);
//        imageView.startAnimation(animation);

        //属性动画 objectAnimator
        //ObjectAnimator + AnimatorSet
//        ObjectAnimator objectAnimatorTranslateX = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200f);
//        objectAnimatorTranslateX.setDuration(1000);
////        objectAnimatorTranslateX.start();
//        ObjectAnimator objectAnimatorTranslateY = ObjectAnimator.ofFloat(imageView, "translationY", 0f, 300f);
//        objectAnimatorTranslateY.setDuration(1000);
////        objectAnimatorTranslateY.start();
//        ObjectAnimator objectAnimatorRotation = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
//        objectAnimatorRotation.setDuration(1000);
////        objectAnimatorRotation.setStartDelay(500);
////        objectAnimatorRotation.start();
//        AnimatorSet animatorSet = new AnimatorSet();
////        animatorSet.playTogether(objectAnimatorTranslateX, objectAnimatorTranslateY, objectAnimatorRotation);
//        animatorSet.play(objectAnimatorTranslateX).with(objectAnimatorTranslateY);
//        animatorSet.play(objectAnimatorTranslateX).before(objectAnimatorRotation);
//        animatorSet.start();

      //PropertyValuesHolder ---为了使用多种动画方便, 使用PropertyValuesHolder
        PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX", 0f, 200f);
        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("translationY", 0f, 300f);
        PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("rotation", 0f, 360f);
        ObjectAnimator objectAni = ObjectAnimator.ofPropertyValuesHolder(imageView, p1, p2, p3);
        objectAni.setDuration(1000).start();

        //

    }

    public void ViewOnClick(View view) {
        Toast.makeText(this, "ImageView OnClick!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_menu:
                if (!isMenuOpen){
                    startAnimator();
                } else {
                    closeMenu();
                }
                break;
            case R.id.image_composer_camera:
                Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE );
                startActivity(takePicIntent);
                break;
            case R.id.image_composer_music:
                break;
            case R.id.image_composer_place:
                break;
            case R.id.image_composer_sleep:
                break;
            case R.id.image_composer_thought:
                Intent SendMsgIntent = new Intent(Intent.ACTION_SENDTO);
                SendMsgIntent.setData(Uri.parse("smsto:"));
                SendMsgIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(SendMsgIntent);
                break;
            case R.id.image_composer_with:
                break;
            default:
                break;
        }
    }

    private void closeMenu() {
        for (int i = 0; i < mResIds.length; i++) {
            ObjectAnimator objectAni = ObjectAnimator.ofFloat(mImageViews.get(i), "translationY", (i+1)*150f, 0f);
            objectAni.setDuration(200);
//            objectAni.setStartDelay(i*100);
            objectAni.start();
        }
        isMenuOpen = false;
    }

    private void startAnimator() {
        for (int i = 0; i < mResIds.length; i++) {
            ObjectAnimator objectAni = ObjectAnimator.ofFloat(mImageViews.get(i), "translationY", 0f, (i+1)*150f);
            objectAni.setDuration(500);
            objectAni.setStartDelay(i * i * 100);
            objectAni.setInterpolator(new BounceInterpolator());
//            objectAni.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//
//                }
//            });

            objectAni.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }
            });

//            objectAni.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//
//                }
//            });
            objectAni.start();
        }
        isMenuOpen = true;
    }

    public void valueOnClick(View view) {
        //ValueAnimator
        final Button valueBtn = (Button) findViewById(R.id.btn_value_animator);
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
//        valueAnimator.setDuration(5000);
//        valueAnimator.setInterpolator(new BounceInterpolator());
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                Integer value = (Integer) animation.getAnimatedValue();
//                valueBtn.setText("" + value);
//            }
//        });
//        valueAnimator.start();

        ValueAnimator vAnimator = ValueAnimator.ofObject(new TypeEvaluator<String>() {
            @Override
            public String evaluate(float fraction, String startValue, String endValue) {
                Log.d("liubo", "fraction = " + fraction + ", startValue = " + startValue + ", endValue = " + endValue);
                return "" + fraction*26;
            }
        }, "a", "z");
//        vAnimator.setInterpolator();
        vAnimator.setDuration(2000);
        vAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                String value = (String) animation.getAnimatedValue();
                Log.d("liubo", "value = " + value);
                valueBtn.setText("" + value);
            }
        });
        vAnimator.start();
    }
}
