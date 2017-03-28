/*
 * Monitor YouTube Video is a program that allows the user to monitor the
 * statistisc of a YouTube video (such as title, user, subscribers number,
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



package it.biagio.monitoryoutubevideo.view.panel.url;



/**
 * Interface for the actions on the url
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public interface UrlListener
{
	/**
	 * Action triggered by the writing on the url field
	 * 
	 * @param url - the content of the url field
	 */
	public void onUrlChanged(String url);
	
	/**
	 * Action triggered by the "enter key" pressing
	 */
	public void onUrlEnter();
}