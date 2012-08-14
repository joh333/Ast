package com.example.com.warp.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GraphicView extends SurfaceView implements SurfaceHolder.Callback {
    private int x;
    private TutorialThread _thread;
    private Ship ship;
	private int y;
	private int oldX;
	private int oldY;
	private int currentAction;
	private MotionEvent currentEvent;
	private int asteroidsCount;
	private List<Asteroids> asteroidsList;
	
	private List<Missile> missileList;
	private  Canvas canvas;
	private int screenHeight;
	private int screenWidth;
	private boolean Initiate;
	private Random rnd;
	private int missileCount;
	public GraphicView(Context context) {
        super(context);
        _thread = new TutorialThread(getHolder(), this);   
        getHolder().addCallback(this);

        setFocusable(true);
    
    }

	private void initiateGame() {
		
		rnd=new Random(1434);
		//Initiate player ship
		ship=new Ship();        
        ship.Load(getResources());

        ship.x=screenWidth/2-ship.bitmap.getWidth()/2;
        ship.y=screenHeight/2-ship.bitmap.getHeight()/2;
        asteroidsList=new ArrayList<Asteroids>();
        asteroidsCount=10;
        missileCount=10;
        //Initiate asteroids
      for(int i=0;i<asteroidsCount;i++)
      {
    	  Asteroids asteroid=new Asteroids(screenWidth,screenHeight,rnd);
    	  asteroid.Load(getResources());  
    	  asteroidsList.add(asteroid);
      }
      for(int i=0;i<asteroidsCount;i++)
      {
   PositionAsteriods();
      }
      
      //Initiate missiles
      missileList=new ArrayList<Missile>();
      //Initiate asteroids
      for(int i=0;i<missileCount;i++)
      {
    	  Missile missile =new Missile(screenWidth,screenHeight,rnd);
    	  missile.Load(getResources());  
    	  missileList.add(missile);
      }
	}
 
    private void PositionAsteriods() {
    	rnd=new Random(4835);
    for(Asteroids asteroid : asteroidsList)
    {
    	asteroid.setNewPosition();
    }
	}

	

	public void onDraw(Canvas canvas) {

        if(Initiate==false)
        {
        	screenHeight=canvas.getHeight();
        	screenWidth=canvas.getWidth();
            initiateGame();
        	Initiate=true;
        }

       canvas.drawColor(Color.BLACK);
       ship.Draw(canvas);
       for(Asteroids asteroid : asteroidsList)
       {
    	   asteroid.Update();
      	asteroid.Draw(canvas);

       }
       for(Missile missile :missileList)
       {
    	   missile.Update();
      	missile.Draw(canvas);

       }
      
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	int action= event.getAction();
    	currentAction=action;
    	currentEvent=event;
    	
        return true;
    }
    
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
   
    	
        _thread.setRunning(true);
        _thread.start();

    }
 
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    	 boolean retry = true;
    	    _thread.setRunning(false);
    	    while (retry) {
    	        try {
    	            _thread.join();
    	            retry = false;
    	        } catch (InterruptedException e) {
    	            // we will try it again and again...
    	        }
    	    }
    }
    class TutorialThread extends Thread {
        private SurfaceHolder _surfaceHolder;
        public GraphicView _panel;
        private boolean _run = false;

        public TutorialThread(SurfaceHolder surfaceHolder, GraphicView panel) {
            _surfaceHolder = surfaceHolder;
            _panel = panel;
          
        }
     
        public void setRunning(boolean run) {
            _run = run;
           
        }
     
        @Override
        public void run() {
        	 Canvas c;
             while (_run) {
                 c = null;
                 try {
                	 
                
                     c = _surfaceHolder.lockCanvas(null);
                
                     synchronized (_surfaceHolder) {
                         _panel.onDraw(c);
                    	 UpdateGame();
                     }
                 } finally {
                     // do this in a finally so that if an exception is thrown
                     // during the above, we don't leave the Surface in an
                     // inconsistent state
                     if (c != null) {
                         _surfaceHolder.unlockCanvasAndPost(c);
                     }
                 }
             }
        }
    }
	public void UpdateGame() {
if(currentAction==MotionEvent.ACTION_DOWN){
	if(currentEvent==null)
	return;
   		if((int)currentEvent.getX()<ship.x)
			ship.Angle=ship.Angle+ship.TurnSpeed;
    	if((int)currentEvent.getX()>ship.x)
    		ship.Angle=ship.Angle-ship.TurnSpeed;
		if(ship.Angle>360)
			ship.Angle=1;
		if(ship.Angle<1)
			ship.Angle=360;
    		Missile missile=getFreeMissile();
    		if(missile!=null)
    		missile.setTarget(ship.Angle);
	}
	}

	private Missile getFreeMissile() {
		Missile freeMissile;
		for(Missile missile:missileList)
		{
			if(missile.missileState==Missile.MissileState.idle)
			{
			return missile;
			}
		}
		return null;
	}
}