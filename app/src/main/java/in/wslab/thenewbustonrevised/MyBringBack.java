package in.wslab.thenewbustonrevised;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

/**
 * Created by ranvir on 23/10/16.
 */
public class MyBringBack extends View {

    Bitmap gBall;

    float changeingY, chageingX;
    Typeface font;

    public MyBringBack(Context context) {
        super(context);
        gBall = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
        changeingY = 0;
       // font=Typeface.createFromAsset(context.getAssets(),"lade");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        Paint textPaint=new Paint();
        textPaint.setARGB(50,255,200,200);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);
        //textPaint.setTypeface(font);
        canvas.drawText("Ranvir",canvas.getHeight()/2,200,textPaint);

        canvas.drawBitmap(gBall, canvas.getWidth() / 2, changeingY, null);
        if (changeingY < canvas.getHeight()) {
            changeingY += 10;
        } else {
            changeingY = 0;
        }
        // Own recatngle
        Rect middleRect = new Rect();

        //Setting Ractangle Property
        middleRect.set(0, 400, canvas.getWidth(), 550);

        //Intilze Color
        Paint ourBlue = new Paint();

        //Assign Color To Color Instance
        ourBlue.setColor(Color.BLUE);

        // Canvas draw rect
        canvas.drawRect(middleRect, ourBlue);
        invalidate();
    }
}
