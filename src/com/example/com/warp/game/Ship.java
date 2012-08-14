package com.example.com.warp.game;

import android.content.res.Resources;

public class Ship extends Sprite{

	public float TurnSpeed;

	public Ship() {
		super();
		bitmapID= R.drawable.ship;
		TurnSpeed=4;
	}

	public void Load(Resources resources)
	{
		super.Load(resources);
	}

}
