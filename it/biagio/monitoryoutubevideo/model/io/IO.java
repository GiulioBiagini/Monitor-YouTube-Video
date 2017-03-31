/*
 * Monitor YouTube Video is a program that allows the user to monitor the
 * statistiscs of a YouTube video (such as title, user, subscribers number,
 * views number, like number, etc...) periodically at regular intervals.
 * 
 * Copyright (C) 2017 Giulio Biagini - giulio.biagini90@gmail.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */



package it.biagio.monitoryoutubevideo.model.io;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



/**
 * Class to write on a file.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class IO
{
	/** The object used to write on a file */
	private static BufferedWriter bufferedWriter;
	
	
	
	/**
	 * Open the stream
	 * 
	 * @param file - the file in which to write data
	 * @param appendMode - if the file exists true to append the string, false to overwrite
	 * @throws IOException - if an input/output error occurs
	 */
	public static void open(File file, boolean appendMode) throws IOException {
		bufferedWriter = new BufferedWriter(new FileWriter(file, appendMode));
	}
	
	/**
	 * Write the string
	 * 
	 * @param string - the string to write
	 * @throws IOException - if an input/output error occurs
	 */
	public static void write(String string) throws IOException {
		if (string != null && !string.isEmpty())
			bufferedWriter.write(string);
	}
	
	/**
	 * Close the stream
	 * 
	 * @throws IOException - if an input/output error occurs
	 */
	public static void close() throws IOException {
		bufferedWriter.close();
	}
}