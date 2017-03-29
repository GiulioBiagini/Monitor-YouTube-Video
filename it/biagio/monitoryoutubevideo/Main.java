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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.biagio.monitoryoutubevideo.model.html.HTMLReader;
import it.biagio.monitoryoutubevideo.model.info.VideoInfo;
import it.biagio.monitoryoutubevideo.model.io.IO;
import it.biagio.monitoryoutubevideo.view.MainFrame;
import it.biagio.monitoryoutubevideo.view.panel.buttons.ButtonsListener;
import it.biagio.monitoryoutubevideo.view.panel.url.UrlListener;



/**
 * Class where the main operations are performed: the view is launched, the
 * operations executed on the view are handled and the lower level objects
 * such as the HTMLReader and the I/O operations are used.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class Main implements UrlListener, ButtonsListener
{
	/**
	 * The name of the program
	 */
	private static final String PROGRAM_NAME = "Monitor YouTube Video v.1.1";
	
	
	
	/**
	 * The time in millisec before the first download will start
	 */
	private static final long DELAY = 0L;
	
	/**
	 * The time il millisec between each download (5 mins)
	 */
	private static final long PERIOD = 5 * 60 * 1000;
	
	
	
	/**
	 * The state that identifies the program which is downloading info
	 */
	private static final int STATE_START = 0;
	
	/**
	 * The state that identifies the program which is ignoring the timer
	 */
	private static final int STATE_PAUSE = 1;
	
	/**
	 * The state that identifies the program which is not downloading the info
	 */
	private static final int STATE_STOP = 2;
	
	
	
	/** The file chooser used to select the file in which to save the info */
	private static JFileChooser fileChooser;
	
	
	
	static {
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("./"));
	}
	
	
	
	/** The state of the program */
	int state;
	
	/** The timer used to exec a task at regular interval */
	private Timer timer;
	
	/** The list of info memorized */
	private ArrayList<VideoInfo> videosInfo;
	
	/** The GUI */
	private MainFrame mainFrame;
	
	
	
	/**
	 * Init the program's internal state and show the GUI
	 */
	private Main() {
		state = STATE_STOP;
		
		videosInfo = new ArrayList<VideoInfo>();
		
		mainFrame = new MainFrame(PROGRAM_NAME, this, this);
		onUrlChanged(mainFrame.getUrlTextFieldContent());
		mainFrame.setPauseButtonEnabled(false);
		mainFrame.setStopButtonEnabled(false);
		mainFrame.setClearButtonEnabled(false);
		mainFrame.setSaveButtonEnabled(false);
		mainFrame.setVisible(true);
	}
	
	
	
	/**
	 * The action performed by the timer at regualr intervals
	 */
	private void actionTimerTick() {
		// if PAUSE the "tick" is ignored
		if (state != STATE_PAUSE) {
			// read the info
			VideoInfo videoInfo = HTMLReader.getVideoInfo(mainFrame.getUrlTextFieldContent());
			
			// if there is not a general error, memorize the info
			if (!videoInfo.isGeneralError()) {
				videosInfo.add(videoInfo);
				if (!mainFrame.isSaveButtonEnabled())
					mainFrame.setSaveButtonEnabled(true);
			}
			
			// log into the log text area
			mainFrame.appendLog(videoInfo);
			if (!mainFrame.isClearButtonEnabled())
				mainFrame.setClearButtonEnabled(true);
		}
	}
	
	
	
	@Override
	public void onUrlChanged(String url) {
		if (!url.isEmpty()) {
			if (!mainFrame.isStartButtonEnabled())
				mainFrame.setStartButtonEnabled(true);
		} else if (mainFrame.isStartButtonEnabled())
			mainFrame.setStartButtonEnabled(false);
	}
	
	@Override
	public void onUrlEnter() {
		// if the start button is enabled, start the monitor
		if (mainFrame.isStartButtonEnabled())
			onStart();
	}
	
	@Override
	public void onStart() {
		if (state != STATE_START) {
			if (mainFrame.isUrlTextFieldEnabled())
				mainFrame.setUrlTextFieldEnabled(false);
			if (mainFrame.isStartButtonEnabled())
				mainFrame.setStartButtonEnabled(false);
			if (!mainFrame.isPauseButtonEnabled())
				mainFrame.setPauseButtonEnabled(true);
			if (!mainFrame.isStopButtonEnabled())
				mainFrame.setStopButtonEnabled(true);
			
			// if STOP create a new timer and start it
			if (state == STATE_STOP) {
				timer = new Timer(true);
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						actionTimerTick();
					}
				}, DELAY, PERIOD);
			}
			
			state = STATE_START;
		}
	}
	
	@Override
	public void onPause() {
		if (state != STATE_PAUSE) {
			if (mainFrame.isUrlTextFieldEnabled())
				mainFrame.setUrlTextFieldEnabled(false);
			if (!mainFrame.isStartButtonEnabled())
				mainFrame.setStartButtonEnabled(true);
			if (mainFrame.isPauseButtonEnabled())
				mainFrame.setPauseButtonEnabled(false);
			if (!mainFrame.isStopButtonEnabled())
				mainFrame.setStopButtonEnabled(true);
			
			state = STATE_PAUSE;
		}
	}
	
	@Override
	public void onStop() {
		if (state != STATE_STOP) {
			onUrlChanged(mainFrame.getUrlTextFieldContent());
			if (!mainFrame.isStartButtonEnabled())
				mainFrame.setStartButtonEnabled(true);
			if (mainFrame.isPauseButtonEnabled())
				mainFrame.setPauseButtonEnabled(false);
			if (mainFrame.isStopButtonEnabled())
				mainFrame.setStopButtonEnabled(false);
			
			// stop the timer
			timer.cancel();
			timer.purge();
			
			state = STATE_STOP;
		}
	}
	
	@Override
	public void onClear() {
		if (mainFrame.isClearButtonEnabled())
			mainFrame.setClearButtonEnabled(false);
		if (mainFrame.isSaveButtonEnabled())
			mainFrame.setSaveButtonEnabled(false);
		
		// clear the log text area and the infos memorized so far
		mainFrame.clearLogTextArea();
		videosInfo.clear();
	}
	
	@Override
	public boolean onSave() {
		// a file has been selected
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				// write the infos on the file
				IO.writeCSV(videosInfo, file);
				JOptionPane.showMessageDialog(mainFrame, "Info saved correctly", PROGRAM_NAME, JOptionPane.INFORMATION_MESSAGE);
				return true;
			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(mainFrame, "ERROR: " + ex.getMessage(), PROGRAM_NAME, JOptionPane.ERROR_MESSAGE);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(mainFrame, "ERROR: " + ex.getMessage(), PROGRAM_NAME, JOptionPane.ERROR_MESSAGE);
			}
		}
		return false;
	}
	
	@Override
	public void onExit() {
		// there are info memorized and the user choose to save them
		if (videosInfo.size() > 0 && JOptionPane.showConfirmDialog(mainFrame, "Save the info memorized so far?", PROGRAM_NAME, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
			if (!onSave())
				return;
		
		// stop the timer
		if (state != STATE_STOP) {
			timer.cancel();
			timer.purge();
		}
		
		// close the gui
		mainFrame.dispose();
	}
	
	
	
	/*
	 * MAIN
	 */
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
			
		} catch (InstantiationException ex) {
			
		} catch (IllegalAccessException ex) {
			
		} catch (UnsupportedLookAndFeelException ex) {
			
		}
		
		new Main();
	}
}