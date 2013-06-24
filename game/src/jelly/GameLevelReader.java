package jelly;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Accesses a level text file
 * I have used a generic TextFileReader from my lib
 * As the program grows will re write some parts
 * @author Almas
 * @version 0.4
 */
public class GameLevelReader
{
  private ArrayList<String> lines = new ArrayList<String>();
	
	private final String FILE;
	private InputStream in;
	
	/**
	 * @param filename - name of the file with relative or absolute path + extension
	 */
	public GameLevelReader(int level)
	{
		FILE = "res/levels/level" + level + ".txt";
		in = getClass().getResourceAsStream(FILE);
		read();
	}
	
	/**
	 * @param newLines - array list of lines to write to a file
	 */
	public void setLines(ArrayList<String> newLines) {
		lines = newLines;
	}
	
	/**
	 * @return - array list containing lines from the text file
	 */
	public String getLine(int index) {
		return lines.get(index);
	}
	
	/**
	 * @param line - string to be added at the end of the text file
	 */
	public void append(String line) {
		lines.add(line);
	}
	
	/**
	 * Reads lines of string from file on the disk into arraylist
	 */
	public void read()
	{
		lines.clear();
		
		try
		{
			BufferedReader bf;
			
			try {
				bf = new BufferedReader(new FileReader(FILE));
			}
			catch(Exception e) {
				bf = new BufferedReader(new InputStreamReader(in));
			}
			
			String line = "";
			
			while ((line = bf.readLine()) != null)
				lines.add(line);
			
			bf.close();
		}
		catch(Exception e) {System.out.println("#Error: couldn't read from file: " + FILE);}
	}
	
	/**
	 * Writes to a file on the disk
	 * Use after all manipulations with the file
	 */
	public void write()
	{
		try
		{
			File tmp = File.createTempFile("tmp", "");
			BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));

		    for (String line : lines)
		    	bw.write(String.format("%s%n", line));
		    
		    bw.close();

		    File oldFile = new File(FILE);
		    if (oldFile.delete())
		        tmp.renameTo(oldFile);
		}
		catch(Exception e) {System.out.println("#Error: couldn't write to file: " + FILE);}
	}

	/**
	 * @return - length of the level
	 */
	public int getLevelLength() {
		return lines.get(0).length()*Global.SPRITE_SIZE;
	}
}
