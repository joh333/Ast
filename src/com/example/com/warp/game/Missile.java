
package com.example.com.warp.game;
import java.util.Random;
import android.content.res.Resources;

public class Missile extends Sprite {
	public enum MissileState
	{
		idle,
		fired
	}
	public MissileState missileState;
	public int destinationX;
	private int speed;
	public int destinationY;
	private int screenHeight;
	private int screenWidth;
	private Random rnd;
	private int maxSpeed;


public Missile(int ScreenWidth,int ScreenHeight,Random rnd)
{
	super();
	maxSpeed=4;
	bitmapID= R.drawable.missile;
this.screenWidth=ScreenWidth;
this.screenHeight=ScreenHeight;
this.rnd=rnd;
missileState=MissileState.idle;
}
	public void Load(Resources resources)
	{
		super.Load(resources);
	}
	public void Update()
	{
		if(missileState==MissileState.fired)
		{
		Vector2D position=new Vector2D(x,y);
		Vector2D destination=new Vector2D(destinationX, destinationY);
		
		Vector2D direction =Vector2D.difference(destination, position); // gives the direction needed to travel from position to target
		direction.normalize( ); // we need it as a unit vector
		position =Vector2D.sum(position,Vector2D.product((double)speed,direction ));
		x=(int)position.x;
		y=(int)position.y;
		
			
			if(bitmap==null)
				return;
		//Check of Asteroid reached destination
		if(Math.abs(x-destinationX)<1+speed && Math.abs(y-destinationY)<1+speed)
		{
			missileState=MissileState.idle;
			x=-100;
			y=-100;
		}
	
		}
		
	}
	public void setTarget(float angle) {
	
		int originX=screenWidth/2;
		int originY=screenHeight/2;
    
		int radius=screenHeight/2;

		x = originX +(int)(Math.cos(Math.toRadians(angle))*(60f));
    	y=  originY +(int) (Math.sin(Math.toRadians(angle))*(60f));
    	speed=maxSpeed;
    	destinationX = originX +(int)(Math.cos(Math.toRadians(angle))*(radius));
    	destinationY=  originY +(int) (Math.sin(Math.toRadians(angle))*(radius));
		missileState=MissileState.fired;
		Angle=-angle;
	}

}

