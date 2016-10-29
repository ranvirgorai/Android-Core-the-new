package in.wslab.thenewbustonrevised;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by ranvir on 29/10/16.
 */
public class GLTriangle {

    private float vertices[] = {
            0f, 1f, //p0
            1f, -1 //p1
            - 1f, -1f //p2

    };

    private FloatBuffer vertBuff;
    private short[] pIndex = {0, 1, 2};
    private ShortBuffer pBuff;

    public GLTriangle() {
        ByteBuffer byteBuff = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuff.order(ByteOrder.nativeOrder());
        vertBuff = byteBuff.asFloatBuffer();
        vertBuff.position(0);

        ByteBuffer pbBuff = ByteBuffer.allocateDirect(pIndex.length * 2);
        pbBuff.order(ByteOrder.nativeOrder());
        pBuff = pbBuff.asShortBuffer();
        pBuff.put(pIndex);
        pBuff.position(0);
    }

    public  void draw(GL10 gl){
        gl.glFrontFace(GL10.GL_CW);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(2,GL10.GL_FLOAT,0,vertBuff);
        gl.glDrawElements(GL10.GL_TRIANGLES,pIndex.length,GL10.GL_UNSIGNED_SHORT,pBuff);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
