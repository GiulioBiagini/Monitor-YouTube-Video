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
	/**
	 * Write a string appending it at the end of a file (if the file already
	 * exists) or overwriting it.
	 * 
	 * @param string - the string to write
	 * @param file - the file in which to write the string
	 * @param append - if the string have to be appended or the file have to
	 * be overwritten
	 * @throws IOException - if while writing an error occurs
	 */
	private static void write(String string, File file, boolean append) throws IOException {
		if (file == null)
			throw new IllegalArgumentException("Unable to write in a null file");
		if (string != null) {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, append));
			bufferedWriter.write(string);
			bufferedWriter.close();
		}
	}
	
	
	
	/**
	 * Write a string in a file overwriting it if already exists
	 * 
	 * @param string - the string to write
	 * @param file - the file in which to write the string
	 * @throws IOException - if while writing an error occurs
	 */
	public static void overwrite(String string, File file) throws IOException {
		write(string, file, false);
	}
	
	/**
	 * Write a string appending it at the end of a file.
	 * 
	 * @param string - the string to write
	 * @param file - the file in which to write the string
	 * @throws IOException - if while writing an error occurs
	 */
	public static void append(String string, File file) throws IOException {
		write(string, file, true);
	}
}