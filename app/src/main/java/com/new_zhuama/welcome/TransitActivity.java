package com.new_zhuama.welcome;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.base.base.ui.BaseActivity;
import com.new_zhuama.R;

import butterknife.Bind;

/**
 * 中转登录注册页面
 * Created by tongyang on 16/7/21.
 */
public class TransitActivity extends BaseActivity {


    @Bind(R.id.iv_drama_icon)
    ImageView iv_drama_icon;
    @Bind(R.id.iv_like)
    ImageView iv_like;
    @Bind(R.id.iv_like1)
    ImageView iv_like1;
    @Bind(R.id.iv_follow)
    ImageView iv_follow;
    @Bind(R.id.iv_hey)
    ImageView iv_hey;
    @Bind(R.id.iv_message)
    ImageView iv_message;

    private int ivLikeWidth, ivHeyWidth, ivMessageWidth, ivOthlikeWidth, ivFollowWidth;
    private int ivLikeHeight, ivHeyHeight, ivMessageHeight, ivOthlikeHeight, ivFollowHeight;


    @Override
    protected int getLayoutResources() {
        return R.layout.activity_transit;
    }

    @Override
    public void initView() {
        super.initView();

        int[] ivLikeMatix = measureImageWidth(R.mipmap.ic_welcome_like);
        int[] ivHeyMatix = measureImageWidth(R.mipmap.ic_welcome_hey);
        int[] ivMessageMatix = measureImageWidth(R.mipmap.ic_welcome_message);
        int[] ivOtherMatix = measureImageWidth(R.mipmap.ic_welcome_like1);
        int[] ivFollowMatix = measureImageWidth(R.mipmap.ic_welcome_follow);

        ivLikeWidth = ivLikeMatix[0];
        ivLikeHeight = ivLikeMatix[1];

        ivHeyWidth = ivHeyMatix[0];
        ivHeyHeight = ivHeyMatix[1];

        ivMessageWidth = ivMessageMatix[0];
        ivMessageHeight = ivMessageMatix[1];

        ivOthlikeWidth = ivOtherMatix[0];
        ivOthlikeHeight = ivOtherMatix[1];

        ivFollowWidth = ivFollowMatix[0];
        ivFollowHeight = ivFollowMatix[1];

        mParentView.post(new Runnable() {
            @Override
            public void run() {

                int top = iv_drama_icon.getTop();
                int left = iv_drama_icon.getLeft();
                int right = iv_drama_icon.getRight();
                int bottom = iv_drama_icon.getBottom();


                Log.d("tong", iv_drama_icon.getTop() + " top " + iv_drama_icon.getLeft() + " left " + iv_drama_icon.getRight() + " right " + iv_drama_icon.getBottom() + "");

                ObjectAnimator mLikeX = ObjectAnimator.ofFloat(iv_like, "X", iv_like.getX(),
                        left - ivLikeWidth - ivLikeWidth / 2);
                ObjectAnimator mLikeY = ObjectAnimator.ofFloat(iv_like, "Y", iv_like.getY(),
                        iv_like.getY() - ivLikeHeight);

                ValueAnimator mLikeWidthAn = ValueAnimator.ofInt(0, ivLikeWidth);
                mLikeWidthAn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();

                        ViewGroup.LayoutParams lp = iv_like.getLayoutParams();
                        lp.width = value;
                        iv_like.setLayoutParams(lp);

                    }
                });

                final ObjectAnimator mHeyAnimatorX = ObjectAnimator.ofFloat(iv_hey, "X", iv_hey.getX(),
                        left - ivHeyWidth - 20);
                final ObjectAnimator mHeyAnimatorY = ObjectAnimator.ofFloat(iv_hey, "Y", iv_hey.getY(),
                        top - (bottom - top) / 3);

                final ValueAnimator mHeyWidthAn = ValueAnimator.ofInt(0, ivHeyWidth);
                mHeyWidthAn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();

                        ViewGroup.LayoutParams lp = iv_hey.getLayoutParams();
                        lp.width = value;
                        iv_hey.setLayoutParams(lp);

                    }
                });

                final ObjectAnimator mMessageX = ObjectAnimator.ofFloat(iv_message, "X", iv_message.getX(),
                        iv_message.getX() - (right - left) / 3);
                final ObjectAnimator mMessageY = ObjectAnimator.ofFloat(iv_message, "Y", iv_message.getY(),
                        top - ivMessageHeight - top / 4);
                final ValueAnimator mMessageWidthAn = ValueAnimator.ofInt(0, ivMessageWidth);
                mMessageWidthAn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();

                        ViewGroup.LayoutParams lp = iv_message.getLayoutParams();
                        lp.width = value;
                        iv_message.setLayoutParams(lp);

                    }
                });

                final ObjectAnimator mOtherLikeX = ObjectAnimator.ofFloat(iv_like1, "X", iv_like1.getX(),
                        right + ivOthlikeWidth / 2);
                final ObjectAnimator mOtherLikeY = ObjectAnimator.ofFloat(iv_like1, "Y", iv_like1.getY(),
                        top - (bottom - top) / 3);
                final ValueAnimator mOtherLikeWidthAn = ValueAnimator.ofInt(0, ivOthlikeWidth);
                mOtherLikeWidthAn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();

                        ViewGroup.LayoutParams lp = iv_like1.getLayoutParams();
                        lp.width = value;
                        iv_like1.setLayoutParams(lp);

                    }
                });

                final ObjectAnimator mFollowX = ObjectAnimator.ofFloat(iv_follow, "X", iv_follow.getX(),
                        right + ivFollowWidth / 2 + 20);
                final ObjectAnimator mFollowY = ObjectAnimator.ofFloat(iv_follow, "Y", iv_follow.getY(),
                        iv_follow.getY() - ivFollowHeight);
                final ValueAnimator mFollowWidthAn = ValueAnimator.ofInt(0, ivFollowWidth);
                mFollowWidthAn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();

                        ViewGroup.LayoutParams lp = iv_follow.getLayoutParams();
                        lp.width = value;
                        iv_follow.setLayoutParams(lp);

                    }
                });

                AnimatorSet set = new AnimatorSet();
                set.play(mLikeX).with(mLikeY).with(mLikeWidthAn);
                set.setInterpolator(new BounceInterpolator());
                set.setDuration(500);
                set.start();
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        AnimatorSet set1 = new AnimatorSet();
                        set1.play(mHeyAnimatorX).with(mHeyAnimatorY).with(mHeyWidthAn);
                        set1.setInterpolator(new BounceInterpolator());
                        set1.setDuration(500);
                        set1.start();
                        set1.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                AnimatorSet set2 = new AnimatorSet();
                                set2.play(mMessageX).with(mMessageY).with(mMessageWidthAn);
                                set2.setInterpolator(new BounceInterpolator());
                                set2.setDuration(500);
                                set2.start();
                                set2.addListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        AnimatorSet set3 = new AnimatorSet();
                                        set3.play(mOtherLikeX).with(mOtherLikeY).with(mOtherLikeWidthAn);
                                        set3.setInterpolator(new BounceInterpolator());
                                        set3.setDuration(500);
                                        set3.start();
                                        set3.addListener(new AnimatorListenerAdapter() {
                                            @Override
                                            public void onAnimationEnd(Animator animation) {
                                                super.onAnimationEnd(animation);
                                                AnimatorSet set4 = new AnimatorSet();
                                                set4.play(mFollowX).with(mFollowY).with(mFollowWidthAn);
                                                set4.setInterpolator(new BounceInterpolator());
                                                set4.setDuration(500);
                                                set4.start();
                                                set4.addListener(new AnimatorListenerAdapter() {
                                                    @Override
                                                    public void onAnimationEnd(Animator animation) {
                                                        super.onAnimationEnd(animation);
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }


    private int[] measureImageWidth(int imageResouce) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(getResources(), imageResouce, options);

        return new int[]{options.outWidth, options.outHeight};
    }

}
