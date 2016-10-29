package in.wslab.thenewbustonrevised;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ranvir on 23/10/16.
 */
public class Camera extends Activity implements View.OnClickListener {
    Button click,setWall;
            ImageView imageView;
    final static int cameraData=0;
    Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        click=(Button)findViewById(R.id.btnClick);
        setWall=(Button)findViewById(R.id.btnSetWAll);
        imageView=(ImageView)findViewById(R.id.ivCamera);
        click.setOnClickListener(this);
        setWall.setOnClickListener(this);

        InputStream is =getResources().openRawResource(R.drawable.splashpreview);
        bmp= BitmapFactory.decodeStream(is);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnClick:
                Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,cameraData);

                break;
            case R.id.btnSetWAll:
                try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK){
            Bundle extra=data.getExtras();
             bmp=(Bitmap)extra.get("data");
            imageView.setImageBitmap(bmp);
        }
    }
}
