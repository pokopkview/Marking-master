package com.intelligent.marking.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.intelligent.marking.R;

public class SelectTopItemView extends View {
    Context mContext;
    Paint paint;

    public SelectTopItemView(Context context) {
        this(context,null);
    }

    public SelectTopItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SelectTopItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        float round = mContext.getResources().getDimensionPixelSize(R.dimen.x23);
        float width = mContext.getResources().getDimensionPixelSize(R.dimen.x306);
        System.out.println(round);
        final Rect block = new Rect(0, 0, (int)width, (int)round);
        canvas.drawRect(block, paint);
        final RectF rectF = new RectF(0, round * 2 , width , round);
        canvas.drawRoundRect(rectF, round, round, paint);
    }
}
