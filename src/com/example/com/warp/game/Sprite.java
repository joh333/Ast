package com.example.com.warp.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Sprite {
	public float x;
	public float y;
	public Bitmap bitmap;
	public int bitmapID;
	public float Angle;
	public float scale;

	public Sprite()
	{
		scale=1;
	}
	public void Load(Resources resources)
	{
		 bitmap= BitmapFactory.decodeResource(resources,bitmapID);
	}
	public void Draw(Canvas canvas)
	{
		if(bitmap==null || canvas==null)
			return;
		 Matrix transform = new Matrix();
		    //transform.setTranslate(x+bitmap.getWidth()/2, y+bitmap.getHeight()/2);
		    transform.preScale(scale,scale);
		    transform.preTranslate(x, y);
		    transform.preRotate(Angle,bitmap.getWidth()/2, bitmap.getHeight()/2);
		 
		    Paint paint = new Paint();
		    paint.setAlpha(255);

		  canvas.drawBitmap(bitmap,transform, paint);

	}
	}
	


