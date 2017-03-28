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



package it.biagio.monitoryoutubevideo.view;



import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import it.biagio.monitoryoutubevideo.model.info.VideoInfo;
import it.biagio.monitoryoutubevideo.view.panel.buttons.ButtonsListener;
import it.biagio.monitoryoutubevideo.view.panel.buttons.ButtonsPanel;
import it.biagio.monitoryoutubevideo.view.panel.log.LogPanel;
import it.biagio.monitoryoutubevideo.view.panel.url.UrlListener;
import it.biagio.monitoryoutubevideo.view.panel.url.UrlPanel;



/**
 * Class for the view.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	/** The panel shown on the top */
	private JPanel northPanel;
	
	/** The panel shown on the middle */
	private JPanel middlePanel;
	
	/** The panel shown on the bottom */
	private JPanel southPanel;
	
	
	
	/** The panel containing the url field */
	private UrlPanel urlPanel;
	
	/** The panel containing the log text area */
	private LogPanel logPanel;
	
	/** The panel containing all the buttons */
	private ButtonsPanel buttonsPanel;
	
	
	
	/**
	 * Create a frame
	 * 
	 * @param PROGRAM_NAME - the name of the program, shown in the title bar
	 * @param urlListener - the listener for the url field actions
	 * @param buttonsListener - the listener for the buttons actions
	 */
	public MainFrame(final String PROGRAM_NAME, UrlListener urlListener, ButtonsListener buttonsListener) {
		super(PROGRAM_NAME);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Const.FRAME_BACKGROUND_COLOR);
		setSize(Const.MAIN_FRAME_WIDTH, Const.MAIN_FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		urlPanel = new UrlPanel(urlListener);
		
		logPanel = new LogPanel();
		
		buttonsPanel = new ButtonsPanel(buttonsListener);
		
		northPanel = new JPanel(new BorderLayout());
		northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		northPanel.setOpaque(false);
		northPanel.add(urlPanel);
		
		middlePanel = new JPanel(new BorderLayout());
		middlePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		middlePanel.setOpaque(false);
		middlePanel.add(logPanel);
		
		southPanel = new JPanel(new BorderLayout());
		southPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
		southPanel.setOpaque(false);
		southPanel.add(buttonsPanel);
		
		add(northPanel, BorderLayout.NORTH);
		add(middlePanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}
	
	
	
	/**
	 * Enables or disables the url field
	 * 
	 * @param enabled - true to enable the url field, false otherwise
	 */
	public void setUrlTextFieldEnabled(boolean enabled) {
		urlPanel.setUrlTextFieldEnabled(enabled);
	}
	
	/**
	 * Enables or disables the start button
	 * 
	 * @param enabled - true to enable the button, false otherwise
	 */
	public void setStartButtonEnabled(boolean enabled) {
		buttonsPanel.setStartButtonEnabled(enabled);
	}
	
	/**
	 * Enables or disables the pause button
	 * 
	 * @param enabled - true to enable the button, false otherwise
	 */
	public void setPauseButtonEnabled(boolean enabled) {
		buttonsPanel.setPauseButtonEnabled(enabled);
	}
	
	/**
	 * Enables or disables the stop button
	 * 
	 * @param enabled - true to enable the button, false otherwise
	 */
	public void setStopButtonEnabled(boolean enabled) {
		buttonsPanel.setStopButtonEnabled(enabled);
	}
	
	/**
	 * Enables or disables the clear button
	 * 
	 * @param enabled - true to enable the button, false otherwise
	 */
	public void setClearButtonEnabled(boolean enabled) {
		buttonsPanel.setClearButtonEnabled(enabled);
	}
	
	/**
	 * Enables or disables the save button
	 * 
	 * @param enabled - true to enable the button, false otherwise
	 */
	public void setSaveButtonEnabled(boolean enabled) {
		buttonsPanel.setSaveButtonEnabled(enabled);
	}
	
	/**
	 * Enables or disables the exit button
	 * 
	 * @param enabled - true to enable the button, false otherwise
	 */
	public void setExitButtonEnabled(boolean enabled) {
		buttonsPanel.setExitButtonEnabled(enabled);
	}
	
	
	
	/**
	 * Check if the url field is enabled or disabled
	 * 
	 * @return true if the url field is enabled, false otherwise
	 */
	public boolean isUrlTextFieldEnabled() {
		return urlPanel.isUrlTextFieldEnabled();
	}
	
	/**
	 * Check if the start button is enabled or disabled
	 * 
	 * @return true if the start button is enabled, false otherwise
	 */
	public boolean isStartButtonEnabled() {
		return buttonsPanel.isStartButtonEnabled();
	}
	
	/**
	 * Check if the pause button is enabled or disabled
	 * 
	 * @return true if the pause button is enabled, false otherwise
	 */
	public boolean isPauseButtonEnabled() {
		return buttonsPanel.isPauseButtonEnabled();
	}
	
	/**
	 * Check if the stop button is enabled or disabled
	 * 
	 * @return true if the stop button is enabled, false otherwise
	 */
	public boolean isStopButtonEnabled() {
		return buttonsPanel.isStopButtonEnabled();
	}
	
	/**
	 * Check if the clear button is enabled or disabled
	 * 
	 * @return true if the clear button is enabled, false otherwise
	 */
	public boolean isClearButtonEnabled() {
		return buttonsPanel.isClearButtonEnabled();
	}
	
	/**
	 * Check if the save button is enabled or disabled
	 * 
	 * @return true if the save button is enabled, false otherwise
	 */
	public boolean isSaveButtonEnabled() {
		return buttonsPanel.isSaveButtonEnabled();
	}
	
	/**
	 * Check if the exit button is enabled or disabled
	 * 
	 * @return true if the exir button is enabled, false otherwise
	 */
	public boolean isExitButtonEnabled() {
		return buttonsPanel.isExitButtonEnabled();
	}
	
	
	
	/**
	 * Get the content of the url field
	 * 
	 * @return the content of the url field
	 */
	public String getUrlTextFieldContent() {
		return urlPanel.getUrlTextFieldContent();
	}
	
	/**
	 * Append a video info in the log text area
	 * 
	 * @param videoInfo - the info related to the video
	 */
	public void appendLog(VideoInfo videoInfo) {
		logPanel.appendLog(videoInfo);
	}
	
	/**
	 * Clear the log text area
	 */
	public void clearLogTextArea() {
		logPanel.clearLogTextArea();
	}
}