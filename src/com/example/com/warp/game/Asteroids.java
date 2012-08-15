package com.example.com.warp.game;

import java.util.Random;

import android.content.res.Resources;

public class Asteroids extends Sprite {
	public int destinationX;
	private int speed;
	public int destinationY;
	private int screenHeight;
	private int screenWidth;
	private Random rnd;
	private int maxSpeed;
	private float rotationSpeed;


public Asteroids(int ScreenWidth,int ScreenHeight,Random rnd)
{
	super();
	maxSpeed=2;
	rotationSpeed=1f;
	bitmapID= R.drawable.asteroid;
this.screenWidth=ScreenWidth;
this.screenHeight=ScreenHeight;
this.rnd=rnd;
}
	public void Load(Resources resources)
	{
		super.Load(resources);
	}
	public void Update()
	{
		
		Vector2D position=new Vector2D(x,y);
		Vector2D destination=new Vector2D(destinationX, destinationY);
		
		Vector2D direction =Vector2D.difference(destination, position); // gives the direction needed to travel from position to target
		direction.normalize( ); // we need it as a unit vector
		Vector2D directionSpeed=direction;
		directionSpeed.multiply(speed);
		position =Vector2D.sum(position,directionSpeed);
		//x=(int)position.x;
		//y=(int)position.y;
		if(x>destinationX)
			x=x-speed;
		if(x<destinationX)
			x=x+speed;
		
		if(y>destinationY)
			y=y-speed;
		if(y<destinationY)
			y=y+speed;
		x=(float)position.x;
		y=(float)position.y;
		
		if(bitmap==null)
			return;
		//Check of Asteroid reached destination
		if(Math.abs(x-destinationX)<4+speed && Math.abs(y-destinationY)<4+speed)
			setNewPosition();
	Angle=Angle+rotationSpeed;
		
		
	}
	public void setNewPosition() {
	
		int originX=screenWidth/2;
		int originY=screenHeight/2;
    	double degree=rnd.nextInt(360);
		int radius=(int) (screenHeight)/2;
		x = originX +(int)(Math.cos(degree)*radius);
    	y=  originY +(int) (Math.sin(degree)*radius);
    	speed=1+rnd.nextInt(maxSpeed);
    	destinationX = originX +(int)(Math.cos(Math.toRadians(degree+180))*(radius));
    	destinationY=  originY +(int) (Math.sin(Math.toRadians(degree+180))*(radius));
    	scale=rnd.nextFloat()*2;
    	rotationSpeed=-4+rnd.nextInt(8);
	}

}
