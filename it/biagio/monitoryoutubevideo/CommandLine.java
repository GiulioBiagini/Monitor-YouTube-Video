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



import java.io.File;

import it.biagio.monitoryoutubevideo.model.info.VideoInfo;
import it.biagio.monitoryoutubevideo.model.info.VideoInfoFormatter;
import it.biagio.monitoryoutubevideo.model.io.IO;
import it.biagio.monitoryoutubevideo.model.timer.Timer;
import it.biagio.monitoryoutubevideo.model.timer.TimerListener;



/**
 * Class that manage the command_line-mode monitoring.
 * This class get info at regular intervals from the specified url
 * and store the retrieved infos into the specified file.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class CommandLine implements TimerListener
{
	/** The timer used to get the video info at regular intervals */
	private Timer timer;
	
	/** The file into which save the info */
	private File file;
	
	/** If show logs on the screen */
	private boolean log;
	
	
	
	/**
	 * Create an object that monitors the url and store infos into a file
	 * 
	 * @param url - the youtube video's url 
	 * @param file - the file into which to store the retrieved infos
	 * @param log - true to show logs on the screen
	 */
	public CommandLine(String url, String file, boolean log) {
		this.file = new File(file);
		this.log = log;
		this.timer = new Timer(false, this);
		this.timer.start(url);
	}
	
	
	
	@Override
	public void onTimeExpired(VideoInfo videoInfo) {
		// log
		System.out.print(VideoInfoFormatter.toLog(videoInfo, true, log, log, log, log, log, log));
		// save the info
		try {
			IO.append(VideoInfoFormatter.toCSV(videoInfo), file);
			System.out.println("- SAVED -");
		} catch (Exception ex) {
			System.err.println("- UNABLE TO SAVE: " + ex.getMessage());
		}
		System.out.println();
	}
}