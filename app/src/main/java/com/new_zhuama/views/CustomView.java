package com.new_zhuama.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.new_zhuama.utils.MeasureUtil;

public class CustomView extends View{

	private static final int MAX_NUM_COLUMNS=7;
	private static final int MAX_NUM_ROW=6;


	private Paint mTxtPatin;


	private int mCurrentRow;
	private int mCurrentColumn;

	private int year=2016;
	private int month=4;
	
	private int y=50;

	private int[][] dayStrings=new int[7][7];

	public CustomView(Context context) {
		super(context);
		initPaint();
	}

	public CustomView(Context context, AttributeSet attr) {
		super(context, attr);
		initPaint();
	}

	private void initPaint() {
		mTxtPatin=new Paint(Paint.ANTI_ALIAS_FLAG);
		mTxtPatin.setColor(Color.BLACK);
		mTxtPatin.setStrokeWidth(10);
		mTxtPatin.setTextSize(40);
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);

		int heightSize = MeasureSpec.getSize(heightMeasureSpec);


		widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(widthSize,
				View.MeasureSpec.EXACTLY);
		heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(heightSize,
				View.MeasureSpec.EXACTLY);
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()){
			case MotionEvent.ACTION_UP:
				Log.v("TAG","1");
				int upx=(int)event.getX();
				int upy=(int)event.getY();
				performClick();
				doClick(upx,upy);
				break;
		}

		return true;
	}

	@Override
	public boolean performClick() {
		return super.performClick();
	}

	private void doClick(int upx, int upy) {
		int row=upy/mCurrentRow;
		int column=upx/7;

		Log.v("TAG",upx+"  "+upy);
		Log.v("TAG",7+"  "+mCurrentRow);

		Log.v("TAG","row="+row+"column="+column);

//		int text=dayStrings[x][y];
//
//		Log.v("TAG","doclick"+text);
//
//		Toast.makeText(getContext(),""+text,Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mCurrentRow=0;
		int i=0;
		int width=(MeasureUtil.getScreenSize(getContext())[0])/7;
		while(i<7){
			canvas.drawText("星期"+i, width*i+40, y, mTxtPatin);
			i++;
		}
		
		int weekday=time();
		int k=weekday;
		y+=40;
		for (int j = 1; j <= 30; j++) {
			canvas.drawText(""+j, width*weekday+40,y, mTxtPatin);
//			dayStrings[mCurrentRow][weekday]=j;
			weekday++;
			if ((j+k)%7==0) {
				y+=40;
				weekday=0;
				mCurrentRow++;
			}
		}
	}

	
	public int time(){
		boolean isRun = false;

		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			isRun = true;
		}

		int days = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			days = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		case 2:
			days = isRun ? 29 : 28;
			break;
		default:
			System.out.println("");
			break;

		}
		int sum1 = 0;
		for (int i = 1900; i < year; i++) {
			if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)
				sum1 += 366;
			else
				sum1 += 365;
		}

		int sum2 = 0;
		for (int i = 1; i < month; i++) {
			int dd = 0;
			switch (i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				dd = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				dd = 30;
				break;
			case 2:
				dd = isRun ? 29 : 28;
				break;
			}
			sum2 += dd;
		}

		int sumDays = sum1 + sum2;

		int weekday = sumDays % 7 + 1;
		if (weekday == 7) {
			weekday = 0;
		}

		return weekday;
		
	}

}
