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

import it.biagio.monitoryoutubevideo.model.info.VideoInfo;



/**
 * Interface used by the timer to notify the time expiration.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public interface TimerListener
{
	/**
	 * Action triggered by the timer when the time between two
	 * notifications has come to the end
	 * 
	 * @param videoInfo - the object containing the info retrieved
	 */
	public void onTimeExpired(VideoInfo videoInfo);
}