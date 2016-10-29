package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by ranvir on 24/10/16.
 */
public class GFXSurface extends Activity implements View.OnTouchListener{

     MyBringBackSurface ourSurfaceView;
      float x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ourSurfaceView=new MyBringBackSurface(this);
        ourSurfaceView.setOnTouchListener(this);
        x=0;
        y=0;
        setContentView(ourSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceView.pause();
    }



    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceView.resume();

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x=event.getX();
        y=event.getY();

        return false;
    }
}

class   MyBringBackSurface extends SurfaceView implements Runnable{

    SurfaceHolder surfaceHolder;
    Thread ourThread = null;
    Boolean isRunning=true;

    public  MyBringBackSurface(Context context){
        super(context);
        surfaceHolder=getHolder();
        ourThread=new Thread(this);
        ourThread.start();
    }

    public void pause(){
        isRunning=false;
        while (true){
            try {
                ourThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
    }
    public  void resume(){
        isRunning=true;
        ourThread=new Thread(this);
        ourThread.start();
    }


    @Override
    public void run() {

        while (isRunning){
            if(!surfaceHolder.getSurface().isValid()){
                continue;
            }

            Canvas canvas=surfaceHolder.lockCanvas();
            canvas.drawRGB(02,02,150);



                surfaceHolder.unlockCanvasAndPost(canvas);


        }

    }


}
