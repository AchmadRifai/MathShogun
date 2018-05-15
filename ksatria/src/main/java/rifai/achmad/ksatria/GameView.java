package rifai.achmad.ksatria;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.*;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class GameView extends SurfaceView  
{
       /**����������� ��������*/
      // private Bitmap background;
      // private Bitmap knight;
       
       /**������ ������ Sprite*/
       private KnightView knight;
       private BackgroundView background;
       private EnemyView musuh;
       // ��������� ����������
       private WindowManager mWindowManager;
       private DisplayMetrics metrics; 
       
       public DisplayMetrics getMetrics() {
			return metrics;
		}
	
	
		public void setMetrics(DisplayMetrics metrics) {
			this.metrics = metrics;
		}
	
		private Display mDisplay;
       
       /**������ ������ GameManager*/
       private GameManager gameLoopThread;
       
       /**���� ���� ���������*/
       private SurfaceHolder holder;
 
       //�����������
       public GameView(Context context) 
       {
             super(context);
             holder = getHolder();
             
             gameLoopThread = new GameManager(this);
             holder.addCallback(new SurfaceHolder.Callback() 
             {
                    public void surfaceDestroyed(SurfaceHolder holder) 
                    {
                        boolean retry = true;
                        gameLoopThread.setRunning(false);
                        while (retry) {
                               try {
                                     gameLoopThread.join();
                                     retry = false;
                               } catch (InterruptedException e) {
                               }
                        }
                    }
 
                    public void surfaceCreated(SurfaceHolder holder) 
                    {
                        gameLoopThread.setRunning(true);
                        gameLoopThread.start();
                    }
                    
                    // ����� ���?
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) 
                    {
                    }
                    
                    //
             });
             // Get an instance of the WindowManager
             mWindowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
             mDisplay = mWindowManager.getDefaultDisplay();
             
             metrics = new DisplayMetrics();
             mDisplay.getMetrics(metrics);
             //drow k and bg
             
             knight = new KnightView(this, KnightView.AnimationType.BREATH);
             background = new BackgroundView(this);
             
             //loadGlobalResources();
             initWorldState();
       }
       
       int nextAnimation = 0;
       // (Controllers) Display touch events
       
       
       // !!! ����� ����� ����������� � GameManager !!!
       
       
       public boolean onTouchEvent(MotionEvent event) 
       {
    	   //������� �������� �� �������
    	   //nextAnimation = (nextAnimation+1) % KnightView.AnimationType.values().length;
    	   //knight.setAnimationState(KnightView.AnimationType.values()[ nextAnimation ]);
    	   
    	   
    	   //������ ����� ������
    	   if( event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE ) 
    	   {
    		   if(event.getY() <= metrics.heightPixels / 3) { 
    			   
    			   if( knight.getYSpeed() == 0 && knight.getYAccel() == 0) {
    				   knight.setVelocity( knight.getXSpeed(), -150);
    				   knight.setAccelerates( knight.getXAccel(), 15);
    				   knight.setAnimationState(KnightView.AnimationType.MOVE);
    			   }
    			   
    		   } else
    		   if (event.getY() > metrics.heightPixels / 3) {
    			   float presure = event.getPressure() * 1.4f;
    			  
    			   int xSpeed = (int)(15.f * presure) * (event.getX() > metrics.widthPixels / 2 ? 1 : -1); // �������� ��������� �� �������� �������� / 800, ��������
    			   if(knight.getYAccel() == 0)
    				   knight.setVelocity(xSpeed, 0);
    			   knight.setAnimationState(KnightView.AnimationType.MOVE);
    		   }
    		   return true;
    	   }
    	   
    	   
    	   if(event.getAction() == MotionEvent.ACTION_UP )
    	   {
    		   knight.setXAccel(5);
    		   knight.setAnimationState(KnightView.AnimationType.BREATH);
    		   return true;
    	   }
    	   
           return super.onTouchEvent(event);
       }
       
       
       private void initWorldState()
       {
    	   knight.move(mDisplay.getWidth()/2 - knight.getWidth()/2, mDisplay.getHeight() - (int)(knight.getHeight()*1.2f) );
       }
       /* ��������� ������� ���� 
       private void loadGlobalResources()
       {
           Bitmap resBackground = BitmapFactory.decodeResource(getResources(), R.drawable.background);
           background = Bitmap.crefateScaledBitmap(resBackground, metrics.widthPixels, metrics.heightPixels, true);
       }
       */
       protected void update(long ticksPerSecond)
       {
    	   knight.update(ticksPerSecond);
       }
 
       //������ ���� �������� �� ������ ����
      
       protected void onDraw(Canvas canvas) 
       {
    	   // ������ ����
            background.onDraw(canvas); 
           // �����  
             knight.onDraw(canvas);
       }
       
}