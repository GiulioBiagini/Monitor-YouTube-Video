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



package it.biagio.monitoryoutubevideo.view.panel.buttons;



/**
 * Interface for the actions on the buttons.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public interface ButtonsListener
{
	/**
	 * Action triggered by the pressure on the start button
	 */
	public void onStart();
	
	/**
	 * Action triggered by the pressure on the pause button
	 */
	public void onPause();
	
	/**
	 * Action triggered by the pressure on the stop button
	 */
	public void onStop();
	
	/**
	 * Action triggered by the pressure on the clear button
	 */
	public void onClear();
	
	/**
	 * Action triggered by the pressure on the save button
	 * 
	 * @return true if the file has been saved, false otherwise
	 */
	public boolean onSave();
	
	/**
	 * Action triggered by the pressure on the exit button
	 */
	public void onExit();
}