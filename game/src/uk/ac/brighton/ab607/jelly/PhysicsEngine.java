package uk.ac.brighton.ab607.jelly;

import static uk.ac.brighton.ab607.jelly.global.Global.SPRITE_SIZE;

/**
 * Does all the checking required for collisions
 * Also provides some simple physics features
 * @author Almas
 * @version 0.8
 */
public class PhysicsEngine
{
	public enum Side
	{
		TOP, BOTTOM, LEFT, RIGHT
	};
	
	public static final int G = 4;
	
	/**
	 * @param obj1
	 * @param obj2
	 * @return - true if objects are colliding in any way, false otherwise
	 */
	public static boolean isColliding(GameObject obj1, GameObject obj2)
	{
		if (obj1.getX() + SPRITE_SIZE > obj2.getX() && obj1.getX() < obj2.getX() + SPRITE_SIZE 
				&& obj1.getY() < obj2.getY() + SPRITE_SIZE && obj1.getY() + SPRITE_SIZE > obj2.getY())
			return true;
		
		return false;
	}

	/**
	 * Check if obj1's <code>side</code> is colliding with obj2
	 * @param obj1
	 * @param obj2
	 * @param side - side of object 1
	 * @return - true if this side is colliding, false otherwise
	 */
	public static boolean isCollidingX(GameObject obj1, GameObject obj2, Side side)
	{
		if (!(obj1.getY() < obj2.getY() + SPRITE_SIZE && obj1.getY() + SPRITE_SIZE > obj2.getY()))
			return false;
		
		if (side == Side.RIGHT) return obj1.getX() + SPRITE_SIZE == obj2.getX();
		if (side == Side.LEFT) return obj1.getX() == SPRITE_SIZE + obj2.getX();
		
		return false;	//keep compiler happy
	}
	
	/**
	 * Check if obj1's <code>side</code> is colliding with obj2
	 * @param obj1
	 * @param obj2
	 * @param side - side of object 1
	 * @return - true if this side is colliding, false otherwise
	 */
	public static boolean isCollidingY(GameObject obj1, GameObject obj2, Side side)
	{
		if (!(obj1.getX() < obj2.getX() + SPRITE_SIZE && obj1.getX() + SPRITE_SIZE > obj2.getX()))
			return false;
		
		if (side == Side.TOP) return obj1.getY() == SPRITE_SIZE + obj2.getY();
		if (side == Side.BOTTOM) return obj1.getY() + SPRITE_SIZE == obj2.getY();
		
		return false;	//keep compiler happy
	}
	
	static class Gravity
	{
		public static void pull(GameObject obj, GameObject[] platforms) {
			obj.moveY(-G, platforms);
		}
	}
}
