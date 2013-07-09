package uk.ac.brighton.ab607.jelly.test;

import uk.ac.brighton.ab607.jelly.io.Out;

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
