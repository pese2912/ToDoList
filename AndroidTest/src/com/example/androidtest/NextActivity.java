package com.example.androidtest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import static com.example.androidtest.CommonUtilities.*;
public class NextActivity extends Activity {
   CanvasView2 canvasView2;
   
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_next);     
      canvasView2 = (CanvasView2) findViewById(R.id.canvasView2);    
   }
  
}

class CanvasView2 extends View {
	
   private static final String TAG = "CanvasView";
   private Paint pnt;
   private final int color = Color.WHITE;
   private final int width = 0;

   private Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.bubble2);
 
   
   public CanvasView2(Context context) {
      super(context);
      pnt = new Paint();
      pnt.setAntiAlias(true);
      pnt.setColor(color);
      //pnt.setTextSize((int)size[i]);
      pnt.setStrokeWidth(width);
      pnt.setStyle(Paint.Style.STROKE);
   
   }
   public CanvasView2(Context context, AttributeSet attribs) {
      super(context, attribs);
      pnt = new Paint();
      pnt.setAntiAlias(true);
      pnt.setColor(color);
      //pnt.setTextSize((int)size[i]);
      pnt.setStrokeWidth(width);
      pnt.setStyle(Paint.Style.STROKE);
   }
   
   public CanvasView2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        pnt = new Paint();
        pnt.setAntiAlias(true);
        pnt.setColor(color);
        //pnt.setTextSize((int)size[i]);
        pnt.setStrokeWidth(width);
        pnt.setStyle(Paint.Style.STROKE);
    }
   
   @Override
   protected void onDraw(Canvas canvas) {
      //canvas.drawBitmap(b, 0, 0,pnt);
      //canvas.drawPath(path, pnt);
	   
      for(int i =0; i<cnt; i++)
      {
         pnt.setTextSize((int)size[i]);
         Log.d("str",str_text[i]);
         canvas.drawText(str_text[i], str_x[i]-(int)size[i]*3, str_y[i]+(int)(size[i]*0.38), pnt);         
         //canvas.drawBitmap(b, str_x[i]-340, str_y[i]-545, null);      
         
         int w = b.getWidth();
         int h = b.getHeight();
         Rect dst = new Rect((int)str_x[i]-(int)size[i]*6,(int)str_y[i]-(int)size[i]*6, ((int)str_x[i]-(int)size[i]*6)+ (int)(size[i]*12),((int)str_y[i]-(int)size[i]*6)+(int)(size[i]*12));                  
         canvas.drawBitmap(b, null, dst, null);
      }
   }
  
}