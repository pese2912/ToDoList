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
public class MainActivity extends Activity {
   CanvasView canvasView;
   Button circleBtn;
   Button nextBtn;
   private final int CircleNumber = 1;
   private SeekBar sb;
   private int bright=70;
   private String text;
   
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      
      sb = (SeekBar)findViewById(R.id.seekBar1);
      sb.setProgress(bright);
      sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         
         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
         }
         
         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress,
               boolean fromUser) {
            // TODO Auto-generated method stub
            bright=progress;
         }
      });
      canvasView = (CanvasView) findViewById(R.id.canvasView);
      circleBtn = (Button) findViewById(R.id.circleBtn);
      nextBtn = (Button) findViewById(R.id.nextbtn);
      circleBtn.setOnClickListener(circleClickListener);
      nextBtn.setOnClickListener(nextClickListener);
   }
   Button.OnClickListener circleClickListener = new Button.OnClickListener() {   
      public void onClick(View v) {    	  
         EditText  textView1 = (EditText)findViewById(R.id.textView1);
          text= textView1.getText().toString();
          if(text.equals(""))
          Toast.makeText(MainActivity.this, "且 老阑 利绢林技夸", Toast.LENGTH_SHORT).show();          
         canvasView.setSelectedNumber(CircleNumber,bright,text);          
      Log.d("text",text);}
   };
   
   Button.OnClickListener nextClickListener = new Button.OnClickListener() {   
	      public void onClick(View v) {
	    	  
	    	  Intent intent = new Intent(v.getContext(),NextActivity.class);
      		  startActivity(intent);
      		   finish();
	      };
	   };
}

class CanvasView extends View {
	protected String mRecordingFile;
	   SoundPool sound = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
	   int soundId;
   private static final String TAG = "CanvasView";
   private Paint pnt;
   private final int color = Color.BLACK;
   private final int width = 0;
   private Path path;
   private int x, y;
   private String text;
   private boolean isInitialize = false;
   private int SelectedNumber = 0;
   private final int CircleNumber = 1;
   private Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.bubble);
  
   private  float CircleSize = 70;
   
   public CanvasView(Context context) {
      super(context);
      
      soundId = sound.load(context,R.raw.snd,1);
      preparePaint();
   }
   public CanvasView(Context context, AttributeSet attribs) {
      super(context, attribs);
      preparePaint();
   }
   
   public CanvasView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

   public void setSelectedNumber(int num, float num2, String data) {
      this.SelectedNumber = num;
      this.CircleSize = num2+50;
      this.text = data;
      Log.d("text",text);
   }

   public void preparePaint() {
      path = new Path();
      pnt = new Paint();
      pnt.setAntiAlias(true);
      pnt.setColor(color);
      //pnt.setTextSize((int)size[i]);
      pnt.setStrokeWidth(width);
      pnt.setStyle(Paint.Style.STROKE);      
   }
   
   public void drawCircle(float x, float y) {
      path.addCircle(x, y, CircleSize, Path.Direction.CW);
      str_text[cnt]=text;
      str_x[cnt]=x;
      size[cnt] = CircleSize;
      str_y[cnt++]=y;
      sound.play(soundId, 1f, 1f, 0, 0, 1f);
   }
   @Override
   protected void onDraw(Canvas canvas) {
      //canvas.drawBitmap(b, 0, 0,pnt);
      if (!isInitialize) {
         canvas.drawColor(Color.WHITE);
         isInitialize = true;
      }      
      //canvas.drawPath(path, pnt);
      for(int i =0; i<cnt; i++)
      {
         pnt.setTextSize((int)size[i]);
         canvas.drawText(str_text[i], str_x[i]-(int)size[i]*3, str_y[i]+(int)(size[i]*0.38), pnt);         
         //canvas.drawBitmap(b, str_x[i]-340, str_y[i]-545, null);      
         
         int w = b.getWidth();
         int h = b.getHeight();
         Rect dst = new Rect((int)str_x[i]-(int)size[i]*6,(int)str_y[i]-(int)size[i]*6, ((int)str_x[i]-(int)size[i]*6)+ (int)(size[i]*12),((int)str_y[i]-(int)size[i]*6)+(int)(size[i]*12));                  
         canvas.drawBitmap(b, null, dst, null);
      }
   }
   @Override
   public boolean onTouchEvent(MotionEvent event) {
	
      this.x = (int) event.getX();
      this.y = (int) event.getY();
      Log.w(TAG, "x = " + x + ", y = " + y);
      Log.d("text", text);
      switch (event.getAction()) {
//      case MotionEvent.ACTION_DOWN:
//         break;
//      case MotionEvent.ACTION_MOVE:
//         break;
      case MotionEvent.ACTION_UP:
         switch (SelectedNumber) {
         case CircleNumber:
            drawCircle(x, y);
            break;
         default:
            break;
         }
         invalidate();
      }
      
         Log.d("text",text);
         
      
   
          return true;
   }
}