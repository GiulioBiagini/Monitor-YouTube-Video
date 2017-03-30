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



package it.biagio.monitoryoutubevideo.model.timer;



import java.util.TimerTask;

import it.biagio.monitoryoutubevideo.model.html.HTMLReader;



/**
 * Class for the timer.
 * The timer is an object that notify the listener whenever an attempt to
 * get the html from the specified url and the retrieving of the info is done.
 * 
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class Timer
{
	/**
	 * Time in millisec before the first notification is raised (immediately)
	 */
	private static final long DELAY = 0L;
	
	/**
	 * Time in millisec between two notifications (5 mins)
	 */
	private static final long PERIOD = /*5L * 60L **/ 5000L;
	
	
	
	/** The timer */
	private java.util.Timer timer;
	
	/** The internal state of the timer */
	private TimerState state;
	
	/** if the associated thread which performs the action should run as daemon */
	private boolean isDaemon;
	
	/** The object to notify */
	private TimerListener listener;
	
	
	
	/**
	 * Create a new timer
	 * 
	 * @param isDaemon - if the associated thread which performs the action
	 * should run as daemon
	 * @param listener - the object to notify
	 */
	public Timer(boolean isDaemon, TimerListener listener) {
		this.state = TimerState.STOP;
		this.isDaemon = isDaemon;
		this.listener = listener;
	}
	
	
	
	/**
	 * Start the timer
	 * 
	 * @param URL - the url from which the html is read
	 */
	public void start(final String URL) {
		if (state == TimerState.PAUSE)
			state = TimerState.START;
		else {
			state = TimerState.START;
			timer = new java.util.Timer(isDaemon);
			timer.scheduleAtFixedRate(
				new TimerTask() {
					@Override
					public void run() {
						if (state == TimerState.START && listener != null)
							listener.onTimeExpired(
								HTMLReader.getVideoInfo(URL)
							);
					}
				},
				DELAY,
				PERIOD
			);
		}
	}
	
	/**
	 * Stop the timer: 
	 */
	public void pause() {
		state = TimerState.PAUSE;
	}
	
	/**
	 * Pause the timer
	 */
	public void stop() {
		state = TimerState.STOP;
		
		timer.cancel();
		timer.purge();
	}
}