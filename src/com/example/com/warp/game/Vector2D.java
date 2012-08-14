package com.example.com.warp.game;

public class Vector2D
{
public double x;
public double y;
    static public Vector2D sum(Vector2D u, Vector2D v)
    {
        return new Vector2D(u.x + v.x, u.y + v.y);
    }
    static public Vector2D difference(Vector2D u, Vector2D v)
    {
        return new Vector2D(u.x - v.x, u.y - v.y);
    }
    /** Scalar product. */
    static public Vector2D product(double r, Vector2D u)
    {
        return new Vector2D(r*u.x, r*u.y);
    }
    static public double dotproduct(Vector2D u, Vector2D v)
    { return u.x*v.x + u.y*v.y;}
    /** Returns an angle between -PI and PI expressing the turn from
    u to v. That is, u.turn(Vector2.angle(u,v)) points in the same
    direction as v.*/
    static public double angle(Vector2D u, Vector2D v)
    {
        /* Old way was nondirectional
        if (!u.nonzero() || !v.nonzero())
            return 0.0;
        double cosine = Vector2.dotproduct(u, v) /
            (u.magnitude() * v.magnitude());
        return Math.acos(cosine);
        */
        double turnangle = v.angle() - u.angle();
        if (turnangle > Math.PI)
            turnangle -= 2.0*Math.PI;
        if (turnangle < Math.PI)
            turnangle += 2.0*Math.PI;
        return turnangle;
    }
    /** Returns the actual angle between -PI/2 and 3PI/2 of the vector's
    direction. */
    public double angle()
    {
        double atanangle;
        if (!nonzero())
            return 0;
        if (x == 0)
        {
            if (y < 0)
                return -Math.PI / 2;
            else
                return Math.PI / 2;
        }
        else
        {
            atanangle = Math.atan(y/x); //Ranges -PI/2 to PI/2.
            if (x < 0)
                return (atanangle + Math.PI); //Ranges PI/2 to 3PI/2.
            return atanangle;
        }
    }
    public Vector2D(){x = 0.0; y = 0.0;}
    public Vector2D(double ix, double iy){x=ix; y=iy;}
    public Vector2D(Vector2D u){x=u.x; y=u.y;}
    public Vector2D(double angle){x=Math.cos(angle);y=Math.sin(angle);}
    public boolean nonzero(){return (x != 0.0 && y != 0.0);}
    public void copy(Vector2D v){x = v.x; y = v.y;}
    /** Shorter name for existing Point2D setLocation method. */
    public void set(double ix, double iy){x=ix; y=iy;}
    public void set(double angle){x=Math.cos(angle);y=Math.sin(angle);}
    public double magnitude(){return Math.sqrt(x*x + y*y);}
    public double distanceTo(Vector2D u)
{Vector2D dv = Vector2D.difference(this,u); return dv.magnitude();}
    public double setMagnitude(double newmag) //Returns old magnitude
    {
    double oldmag = magnitude();
        double multiplier;
        if (oldmag == 0.0)
    set(newmag, 0.0);
        else
        {
            multiplier = newmag/oldmag;
            multiply(multiplier);
        }
        return oldmag;
    }
    public double normalize()//Makes a unit vector, returns old magnitude.
    {
        return setMagnitude(1.0);
    }
    public void setzero(){set(0.0, 0.0);}
    public void add(Vector2D v){x += v.x; y += v.y;}
    public void subtract(Vector2D v){x -= v.x; y -= v.y;}
    public void multiply(double r){ x*=r; y*= r;}
    public void turn(double angle)
    {
        double c = Math.cos(angle), s = Math.sin(angle);
        double newx; //Need this so that you use the "old" x in the y turn line.
        newx = c*x - s*y;
        y = s*x + c*y;
        x = newx;
    }
    public String toString()
    {
        return "(" + (int)x + ", " + (int)y + ")";
    }
}
