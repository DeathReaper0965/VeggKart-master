package com.example.veggkart;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by HIMANSHU on 6/10/2016.
 */

public class ModifiedTextView extends TextView {
    private Paint marginPaint;
    private Paint linePaint;
    private int paperColor;
    private float margin;

    public ModifiedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ModifiedTextView(Context context) {
        super(context);
        init();
    }

    public ModifiedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
   public void onDraw(Canvas canvas)
    {
        canvas.drawColor(paperColor);
        canvas.drawLine(0,0,0,getMeasuredHeight(),linePaint);
        canvas.drawLine(0,getMeasuredHeight(),getMeasuredWidth(),getMeasuredHeight(),linePaint);
        canvas.drawLine(margin,0,margin,getMeasuredHeight(),marginPaint);
        canvas.save();
        canvas.translate(margin,0);
        super.onDraw(canvas);
        canvas.restore();
    }

    private void init() {
        Resources myResources=getResources();
        marginPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        marginPaint.setColor(myResources.getColor(R.color.view_margin));
        linePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(myResources.getColor(R.color.view_lines));
        paperColor=myResources.getColor(R.color.view_paper);
        margin=myResources.getDimension(R.dimen.view_margin);
    }

}
