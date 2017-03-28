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



import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.biagio.monitoryoutubevideo.view.Const;
import it.biagio.monitoryoutubevideo.view.panel.APanel;
import it.biagio.monitoryoutubevideo.view.utils.DangerButton;
import it.biagio.monitoryoutubevideo.view.utils.PrimaryButton;
import it.biagio.monitoryoutubevideo.view.utils.SuccessButton;
import it.biagio.monitoryoutubevideo.view.utils.WarningButton;



/**
 * Class for the panel with the buttons.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
@SuppressWarnings("serial")
public class ButtonsPanel extends APanel
{
	/** Button to start the monitoring */
	private PrimaryButton startButton;
	
	/** Button to pause the monitoring */
	private PrimaryButton pauseButton;
	
	/** Button to stop the monitoring */
	private PrimaryButton stopButton;
	
	/** Button to clear the info memorized */
	private WarningButton clearButton;
	
	/** Button to save the info memorized */
	private SuccessButton saveButton;
	
	/** Button to exit the program */
	private DangerButton exitButton;
	
	
	
	/** The listener to notify if any action is performed */
	private ButtonsListener listener;
	
	
	
	/**
	 * Create a pane with all buttons
	 * 
	 * @param listener - the object to notify if any action is performed
	 */
	public ButtonsPanel(ButtonsListener listener) {
		super (new GridLayout(1, 6, 10, 0));
		
		this.listener = listener;
		
		startButton = new PrimaryButton(Const.START_BUTTON_ICON, Const.START_BUTTON_TOOL_TIP_TEXT);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionStart();
			}
		});
		
		pauseButton = new PrimaryButton(Const.PAUSE_BUTTON_ICON, Const.PAUSE_BUTTON_TOOL_TIP_TEXT);
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionPause();
			}
		});
		
		stopButton = new PrimaryButton(Const.STOP_BUTTON_ICON, Const.STOP_BUTTON_TOOL_TIP_TEXT);
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionStop();
			}
		});
		
		clearButton = new WarningButton(Const.CLEAR_BUTTON_ICON, Const.CLEAR_BUTTON_TOOL_TIP_TEXT);
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionClear();
			}
		});
		
		saveButton = new SuccessButton(Const.SAVE_BUTTON_ICON, Const.SAVE_BUTTON_TOOL_TIP_TEXT);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionSave();
			}
		});
		
		exitButton = new DangerButton(Const.EXIT_BUTTON_ICON, Const.EXIT_BUTTON_TOOL_TIP_TEXT);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionExit();
			}
		});
		
		add(startButton);
		add(pauseButton);
		add(stopButton);
		add(clearButton);
		add(saveButton);
		add(exitButton);
	}
	
	
	
	/**
	 * The related action to the pressure on the start button
	 */
	private void actionStart() {
		if (listener != null)
			listener.onStart();
	}
	
	/**
	 * The related action to the pressure on the pause button
	 */
	private void actionPause() {
		if (listener != null)
			listener.onPause();
	}
	
	/**
	 * The related action to the pressure on the stop button
	 */
	private void actionStop() {
		if (listener != null)
			listener.onStop();
	}
	
	/**
	 * The related action to the pressure on the clear button
	 */
	private void actionClear() {
		if (listener != null)
			listener.onClear();
	}
	
	/**
	 * The related action to the pressure on the save button
	 */
	private void actionSave() {
		if (listener != null)
			listener.onSave();
	}
	
	/**
	 * The related action to the pressure on the exit button
	 */
	private void actionExit() {
		if (listener != null)
			listener.onExit();
	}
	
	
	
	/**
	 * Enables or disables the start button
	 * 
	 * @param enabled - true to enable the button, false otherwise
	 */
	public void setStartButtonEnabled(boolean enabled) {
		startButton.setEnabled(enabled);
	}
	
	/**
	 * Enables or disables the pause button
	 * 
	 * @param enabled - true to enable the button, false otherwise
	 */
	public void setPauseButtonEnabled(boolean enabled) {
		pauseButton.setEnabled(enabled);
	}
	
	/**
	 * Enables or disables the stop button
	 * 
	 * @param enabled - true to enable the button, false otherwise
	 */
	public void setStopButtonEnabled(boolean enabled) {
		stopButton.setEnabled(enabled);
	}
	
	/**
	 * Enables or disables the clear button
	 * 
	 * @param enabled - true to enable the button, false otherwise
	 */
	public void setClearButtonEnabled(boolean enabled) {
		clearButton.setEnabled(enabled);
	}
	
	/**
	 * Enables or disables the save button
	 * 
	 * @param enabled - true to enable the button, false otherwise
	 */
	public void setSaveButtonEnabled(boolean enabled) {
		saveButton.setEnabled(enabled);
	}
	
	/**
	 * Enables or disables the exit button
	 * 
	 * @param enabled - true to enable the button, false otherwise
	 */
	public void setExitButtonEnabled(boolean enabled) {
		exitButton.setEnabled(enabled);
	}
	
	
	
	/**
	 * Check if the start button is enabled or disabled
	 * 
	 * @return true if the start button is enabled, false otherwise
	 */
	public boolean isStartButtonEnabled() {
		return startButton.isEnabled();
	}
	
	/**
	 * Check if the pause button is enabled or disabled
	 * 
	 * @return true if the pause button is enabled, false otherwise
	 */
	public boolean isPauseButtonEnabled() {
		return pauseButton.isEnabled();
	}
	
	/**
	 * Check if the stop button is enabled or disabled
	 * 
	 * @return true if the stop button is enabled, false otherwise
	 */
	public boolean isStopButtonEnabled() {
		return stopButton.isEnabled();
	}
	
	/**
	 * Check if the clear button is enabled or disabled
	 * 
	 * @return true if the clear button is enabled, false otherwise
	 */
	public boolean isClearButtonEnabled() {
		return clearButton.isEnabled();
	}
	
	/**
	 * Check if the save button is enabled or disabled
	 * 
	 * @return true if the save button is enabled, false otherwise
	 */
	public boolean isSaveButtonEnabled() {
		return saveButton.isEnabled();
	}
	
	/**
	 * Check if the exit button is enabled or disabled
	 * 
	 * @return true if the exir button is enabled, false otherwise
	 */
	public boolean isExitButtonEnabled() {
		return exitButton.isEnabled();
	}
}