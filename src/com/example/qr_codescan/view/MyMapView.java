package com.example.qr_codescan.view;

import java.math.BigDecimal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyMapView extends View {
	private Paint mPaint;
	private PointF pointF,pointM;
	private int width, height;
	private MyPoint myPoint;

	public MyMapView(Context context) {
		super(context);
		init();
	}

	public MyMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyMapView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(2);
		mPaint.setColor(Color.BLACK);
		mPaint.setAlpha(1);
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//myPoint=getMyPoint(event.getY(), event.getX());
		pointF=new PointF(event.getX(),event.getY());
		invalidate();
		return true;
	}

	//触摸坐标转换成我的坐标
	private MyPoint getMyPoint(float x,float y){
		BigDecimal xx=new BigDecimal(x);
		xx.setScale(0, BigDecimal.ROUND_HALF_UP);
		int xxx=xx.intValue();
		BigDecimal yy=new BigDecimal(y);
		yy.setScale(0, BigDecimal.ROUND_HALF_UP);
		int yyy=xx.intValue();
		return new MyPoint(xxx, yyy);
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w;
		height = h;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int s = 0;
		int ss = 0;
		for (int i = 0; i < 22; i++) {
			// 滑横线
			canvas.drawLine(0, s, width, s, mPaint);
			s = s + 40;
		}
		for (int i = 0; i < 48; i++) {
			// 滑竖线
			canvas.drawLine(ss, 0, ss, height, mPaint);
			ss = ss + 40;
		}
		if (pointF != null) {
			Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setStyle(Paint.Style.FILL);
			paint.setStrokeWidth(2);
			paint.setColor(Color.RED);
			canvas.drawCircle(pointF.x, pointF.y, 20, paint);
		}
		if (pointM != null) {
			Paint paint2=new Paint(Paint.ANTI_ALIAS_FLAG);
			paint2.setStyle(Paint.Style.FILL);
			paint2.setStrokeWidth(2);
			paint2.setColor(Color.BLUE);
			canvas.drawCircle(pointM.x, pointM.y, 20, paint2);
		}
	}

	public void ff(int x, int y) {
		pointF=new PointF(x,y);	
		invalidate();
	}
	public void my(int x,int y){
		pointM=new PointF(x*40, y*40);
		invalidate();
	}
}
