package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created by ranvir on 29/10/16.
 */
public class GLExample extends Activity {

    GLSurfaceView ourSurface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurface=new GLSurfaceView(this);
        ourSurface.setRenderer(new GLRender());
        setContentView(ourSurface);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurface.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurface.onResume();
    }
}
