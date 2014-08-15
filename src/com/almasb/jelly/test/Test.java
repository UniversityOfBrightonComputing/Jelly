package com.almasb.jelly.test;

import com.almasb.jelly.io.Out;

public class Test
{
	public static void main(String[] args)
	{
		String line = "";
		
		for (int i = 0; i < 100; i++)
			line += (int)(Math.random()*3);
		
		Out.msg(line);
	}
}
