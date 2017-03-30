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



package it.biagio.monitoryoutubevideo;



/**
 * The main class.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class Main
{
	/**
	 * The name of the program
	 */
	private static final String PROGRAM_NAME = "Monitor YouTube Video v.2.0";
	
	
	
	public static void main(String[] args) {
		if (args.length == 0)
			new Gui(PROGRAM_NAME);
		else if (args.length == 2)
			new CommandLine(args[0], args[1], false);
		else if (args.length == 3 && args[0].equals("-l"))
			new CommandLine(args[1], args[2], true);
		else
			System.err.println(
				"usage:" + System.lineSeparator() +
				"\tmonitor youtube video" + System.lineSeparator() +
				"\t\tstart the program showing the gui" + System.lineSeparator() +
				"\tmonitor youtube video [-l] <url> <output_file>" + System.lineSeparator() +
				"\t\tstart the program monitoring the <url> and saving info into <output_file>" + System.lineSeparator() +
				"\t\t-l: to log info as well as timestamp (always shown) on the screen"
			);
	}
}