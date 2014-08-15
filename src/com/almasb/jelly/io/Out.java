package com.almasb.jelly.io;

/**
 * Simple class that wraps up System's output methods
 * (messages and errors) for easier typing and debugging
 * @author Almas
 * @version 1.1
 */
public class Out
{
	/**
	 * Prints a value to screen
	 * @param value - the value to print
	 */
	public static void msg(int value) {
		msg(value + "");
	}
	
	/**
	 * Prints a message to screen
	 * @param mes - the message
	 */
	public static void msg(String mes) {
		System.out.println("#Program message: " + mes);
	}
	
	/**
	 * Prints the error message to screen
	 * @param mes - the error message
	 */
	public static void err(String mes) {
		System.out.println("#Program error: " + mes);
	}
}
