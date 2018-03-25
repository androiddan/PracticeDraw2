package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice08XfermodeView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap1;
    Bitmap bitmap2;

    public Practice08XfermodeView(Context context) {
        super(context);
    }

    public Practice08XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice08XfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private final PorterDuffXfermode xfermode1;
    private final PorterDuffXfermode xfermode2;
    private final PorterDuffXfermode xfermode3;

    {
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
        xfermode1=new PorterDuffXfermode(PorterDuff.Mode.SRC);
        xfermode2=new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        xfermode3=new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 paint.setXfermode() 设置不同的结合绘制效果

        // 别忘了用 canvas.saveLayer() 开启 off-screen buffer
        canvas.saveLayer(0,0,getWidth(),getHeight(),paint);

        // 第一个：PorterDuff.Mode.SRC
        canvas.drawBitmap(bitmap1, 0, 0, paint);
        paint.setXfermode(xfermode1);
        canvas.drawBitmap(bitmap2, 0, 0, paint);
        //需要清除，否则会出现空白错误
        paint.setXfermode(null);


        // 第二个：PorterDuff.Mode.DST_IN
        canvas.drawBitmap(bitmap1, bitmap1.getWidth() + 100, 0, paint);
        paint.setXfermode(xfermode2);
        canvas.drawBitmap(bitmap2, bitmap1.getWidth() + 100, 0, paint);
        paint.setXfermode(null);

        // 第三个：PorterDuff.Mode.DST_OUT
        canvas.drawBitmap(bitmap1, 0, bitmap1.getHeight() + 20, paint);
        paint.setXfermode(xfermode3);
        canvas.drawBitmap(bitmap2, 0, bitmap1.getHeight() + 20, paint);
        paint.setXfermode(null);

        // 用完之后使用 canvas.restore() 恢复 off-screen buffer
        canvas.restore();
    }
}
