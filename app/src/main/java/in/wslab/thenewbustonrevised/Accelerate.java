package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ranvir on 25/10/16.
 */
public class Accelerate extends Activity implements SensorEventListener {

    float x, y, sensorX, sensorY;
    SensorManager sm;
    Bitmap ball;
    MyBringBackSurface ourSurfaceView;

    class MyBringBackSurface extends SurfaceView implements Runnable {

        SurfaceHolder surfaceHolder;
        Thread ourThread = null;
        Boolean isRunning = false;

        public MyBringBackSurface(Context context) {
            super(context);
            surfaceHolder = getHolder();
            ourThread = new Thread(this);
            ourThread.start();
        }

        public void pause() {
            isRunning = false;
            while (true) {
                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        public void resume() {
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }


        @Override
        public void run() {

            while (isRunning) {
                if (!surfaceHolder.getSurface().isValid()) {
                    continue;
                }

                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawRGB(02, 02, 150);

                float centerX = canvas.getWidth() / 2;
                float centerY = canvas.getHeight() / 2;
                // Drawing Ball
                canvas.drawBitmap(ball, centerX + sensorX * 20, centerY + sensorY * 20, null);

                surfaceHolder.unlockCanvasAndPost(canvas);


            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
        x = y = sensorX = sensorY = 0;
        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.resume();
        setContentView(ourSurfaceView);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = event.values[0];
        sensorY = event.values[1];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        sm.unregisterListener(this);
        super.onPause();
    }
}
