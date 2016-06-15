package com.example.music.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.activity.R;
import com.example.music.myview.CircleImageView;


public class DiscView extends FrameLayout{

	ImageView pin;//唱针
	CircleImageView civ;//显示专辑图片的圆形view
	FrameLayout disc;//黑色唱片+专辑图片

	public DiscView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.discview_layout, this);

		pin = (ImageView) view.findViewById(R.id.pin);
		civ = (CircleImageView) view.findViewById(R.id.disc_albumpic);
		disc = (FrameLayout) view.findViewById(R.id.disc);

	}

	/**
	 * 设置专辑图片
	 * @param bitmap
	 */
	public void setAlbumPic(Bitmap bitmap){
		civ.setImageBitmap(bitmap);
	}

	/**
	 * 设置专辑图片
	 * @param resId
	 */
	public void setAlbumPic(int resId){
		civ.setImageResource(resId);
	}

	/**
	 * 播放歌曲时，让唱片滚动，让唱针移动
	 */
	public void startRotate(){
		pin.clearAnimation();
		disc.clearAnimation();

		//唱针的旋转动画
		RotateAnimation pinAnim = new RotateAnimation(0, 
				25, 
				Animation.RELATIVE_TO_SELF, 
				0.0f, 
				Animation.RELATIVE_TO_SELF, 
				0.0f);
		pinAnim.setDuration(2000);
		//当动画执行完毕的时候，动画影像停留住
		pinAnim.setFillAfter(true);

		//唱盘动画
		RotateAnimation discAnim = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		discAnim.setDuration(10*1000);
		discAnim.setRepeatCount(Animation.INFINITE);
		//为动画设置一个加速器
		discAnim.setInterpolator(new LinearInterpolator());
		pin.startAnimation(pinAnim);
		disc.startAnimation(discAnim);

	}
	/**
	 * 歌曲停止播放时，动画停止
	 */
	public void stopRotate(){
		pin.clearAnimation();
		disc.clearAnimation();
		//唱针的旋转动画
		RotateAnimation pinAnim = new RotateAnimation(25, 
				0, 
				Animation.RELATIVE_TO_SELF, 
				0.0f, 
				Animation.RELATIVE_TO_SELF, 
				0.0f);
		pinAnim.setDuration(2000);
		pin.startAnimation(pinAnim);
	}
	
	public CircleImageView getCircleImageView(){
		return civ;
	}
}
//public class DiscView extends FrameLayout{
//
//	ImageView pin;
//	CircleImageView civ;
//	FrameLayout disc;
//	public DiscView(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View view = inflater.inflate(R.layout.disc_view, this);
//		pin = (ImageView) view.findViewById(R.id.iv_pin_disc);
//		civ = (CircleImageView) view.findViewById(R.id.iv_picture);
//		disc = (FrameLayout) view.findViewById(R.id.frament);
//	}
//	
//	public void setAlbumPic(int resId){
//		civ.setImageResource(resId);
//	}
//	
//	public void setAlbumPic(Bitmap bm){
//		civ.setImageBitmap(bm);
//	}
//	public void startRotate() {
//		pin.clearAnimation();
//		disc.clearAnimation();
//		
//		RotateAnimation pinAnim = new RotateAnimation(0,30, Animation.RELATIVE_TO_SELF,
//				0.0f, Animation.RELATIVE_TO_SELF, 0.1f);
//		pinAnim.setDuration(2000);
//		pinAnim.setFillAfter(true);
//		
//		RotateAnimation discAnim = new RotateAnimation(0,359, Animation.RELATIVE_TO_SELF,
//				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//		discAnim.setDuration(10*1000);
//		discAnim.setRepeatCount(Animation.INFINITE);
//		discAnim.setInterpolator(new LinearInterpolator());
//		
//		pin.startAnimation(pinAnim);
//		disc.startAnimation(discAnim);
//	}
//	public void stopRotate(){
//		pin.clearAnimation();
//		disc.clearAnimation();
//		RotateAnimation pinAnim = new RotateAnimation(-30,0, Animation.RELATIVE_TO_SELF,
//				0.0f, Animation.RELATIVE_TO_SELF, 0.1f);
//		pinAnim.setDuration(2000);
//		pin.startAnimation(pinAnim);
//	}
//	
//	public CircleImageView getCircleImageView(){
//		return civ;
//	}
//}
