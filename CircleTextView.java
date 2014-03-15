package com.sukohi.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;

/*  Dependency: com.sukohi.lib.FitTextView  */

public class CircleTextView extends FitTextView {

	private int circleColor = Color.WHITE;
	private Paint paint = new Paint();
	
	public CircleTextView(Context context) {
		super(context);
	}
	
	public CircleTextView(Context context, AttributeSet attrs) {
		super(context, attrs);

		String circleColorString = attrs.getAttributeValue(null, "circleColor");
		
		if(circleColorString != null) {
			
			circleColor = Color.parseColor(circleColorString);
			
		}
		
		String circleText = attrs.getAttributeValue(null, "circleText");
		
		if(circleText != null) {
			
			setText(circleText);
			
		}
		
	}

	@Override
	protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		int viewLength = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(viewLength, viewLength);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {

		float radius = getHeight() * 0.5f;
		paint.reset();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(circleColor);
		canvas.drawCircle(radius, radius, radius, paint);
		
		paint.reset();
		float textSize = getTextSize();
		paint.setTextSize(textSize);
		paint.setColor(getCurrentTextColor());
		
		String text = getText().toString();
		float textWidth = paint.measureText(text);
		FontMetrics fontMetrics = paint.getFontMetrics();
		
		float x = radius - (textWidth*0.5f);
		float y = radius - ((fontMetrics.ascent + fontMetrics.descent) * 0.5f);
		canvas.drawText(text, x, y, paint);
		
	}
	
}
/*** Example

	// xml

    <com.sukohi.lib.CircleTextView 
        android:layout_width="wrap_content"
        android:layout_height="40dp"
        android:textSize="25sp"
        android:text="15"
        android:padding="10dp"
        android:textColor="@color/white"
        circleColor="#b61818"/>

***/
