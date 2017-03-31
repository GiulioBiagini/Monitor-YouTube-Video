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
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.biagio.monitoryoutubevideo.model.info.VideoInfo;
import it.biagio.monitoryoutubevideo.model.info.VideoInfoFormatter;
import it.biagio.monitoryoutubevideo.model.io.IO;
import it.biagio.monitoryoutubevideo.model.timer.Timer;
import it.biagio.monitoryoutubevideo.model.timer.TimerListener;
import it.biagio.monitoryoutubevideo.view.MainFrame;
import it.biagio.monitoryoutubevideo.view.panel.buttons.ButtonsListener;
import it.biagio.monitoryoutubevideo.view.panel.url.UrlListener;



/**
 * Class that manage the gui-mode monitoring.
 * This class shows a gui with which user interacts to get info at regular
 * intervals from the specified url and store the retrieved infos into a file.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class Gui implements TimerListener, UrlListener, ButtonsListener
{
	/** The file chooser used to select the file in which to save the infos */
	private static JFileChooser fileChooser;
	
	
	
	/**
	 * Init the object used to choose the file into which to save the infos
	 */
	static {
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("./"));
	}
	
	
	
	/** The program name to show as title on the frames */
	private final String PROGRAM_NAME;
	
	/** The GUI */
	private MainFrame mainFrame;
	
	/** The timer used to get the video info at regular intervals */
	private Timer timer;
	
	/** The list of memorized infos */
	private ArrayList<VideoInfo> videosInfo;
	
	
	
	/**
	 * Create an object that shows a gui designed to manage the monitoring
	 * of an url and the downloading of certain video info
	 * 
	 * @param PROGRAM_NAME - the name of this program
	 */
	public Gui(final String PROGRAM_NAME) {
		this.PROGRAM_NAME = PROGRAM_NAME;
		
		timer = new Timer(true, this);
		
		videosInfo = new ArrayList<VideoInfo>();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
			
		} catch (InstantiationException ex) {
			
		} catch (IllegalAccessException ex) {
			
		} catch (UnsupportedLookAndFeelException ex) {
			
		}
		
		mainFrame = new MainFrame(PROGRAM_NAME, this, this);
		onUrlChanged(mainFrame.getUrlTextFieldContent());
		mainFrame.setPauseButtonEnabled(false);
		mainFrame.setStopButtonEnabled(false);
		mainFrame.setClearButtonEnabled(false);
		mainFrame.setSaveButtonEnabled(false);
		
		mainFrame.setVisible(true);
	}
	
	
	
	@Override
	public void onTimeExpired(VideoInfo videoInfo) {
		if (!mainFrame.isSaveButtonEnabled())
			mainFrame.setSaveButtonEnabled(true);
		if (!mainFrame.isClearButtonEnabled())
			mainFrame.setClearButtonEnabled(true);
		
		// log the info
		mainFrame.appendLog(VideoInfoFormatter.toLog(
			videoInfo,
			true,
			mainFrame.isTitleToggleButtonSelected(),
			mainFrame.isUserToggleButtonSelecte(),
			mainFrame.isSubscribersCountToggleButtonSelecte(),
			mainFrame.isViewsCountToggleButtonSelecte(),
			mainFrame.isLikeCountToggleButtonSelecte(),
			mainFrame.isUnlikeCountToggleButtonSelecte()
		));
		
		// save the info
		videosInfo.add(videoInfo);
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
		// if the start button is enabled, start the monitoring
		if (mainFrame.isStartButtonEnabled())
			onStart();
	}
	
	@Override
	public void onStart() {
		if (mainFrame.isUrlTextFieldEnabled())
			mainFrame.setUrlTextFieldEnabled(false);
		if (mainFrame.isStartButtonEnabled())
			mainFrame.setStartButtonEnabled(false);
		if (!mainFrame.isPauseButtonEnabled())
			mainFrame.setPauseButtonEnabled(true);
		if (!mainFrame.isStopButtonEnabled())
			mainFrame.setStopButtonEnabled(true);
		
		timer.start(mainFrame.getUrlTextFieldContent());
	}
	
	@Override
	public void onPause() {
		if (mainFrame.isUrlTextFieldEnabled())
			mainFrame.setUrlTextFieldEnabled(false);
		onUrlChanged(mainFrame.getUrlTextFieldContent());
		if (mainFrame.isPauseButtonEnabled())
			mainFrame.setPauseButtonEnabled(false);
		if (!mainFrame.isStopButtonEnabled())
			mainFrame.setStopButtonEnabled(true);
		
		timer.pause();
	}
	
	@Override
	public void onStop() {
		if (!mainFrame.isUrlTextFieldEnabled())
			mainFrame.setUrlTextFieldEnabled(true);
		onUrlChanged(mainFrame.getUrlTextFieldContent());
		if (mainFrame.isPauseButtonEnabled())
			mainFrame.setPauseButtonEnabled(false);
		if (mainFrame.isStopButtonEnabled())
			mainFrame.setStopButtonEnabled(false);
		
		timer.stop();
	}
	
	@Override
	public void onClear() {
		if (mainFrame.isClearButtonEnabled())
			mainFrame.setClearButtonEnabled(false);
		if (mainFrame.isSaveButtonEnabled())
			mainFrame.setSaveButtonEnabled(false);
		
		videosInfo.clear();
		mainFrame.clearLogTextArea();
	}
	
	@Override
	public void onSave() {
		// a file has been selected
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				// if file already exists and the user chosed to not overwrite it, use appendMode
				IO.open(
					file,
					file.exists() &&
					JOptionPane.showConfirmDialog(mainFrame, "The file already exists: overwrite it?", PROGRAM_NAME, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION
				);
				for (VideoInfo videoInfo : videosInfo)
					IO.write(VideoInfoFormatter.toCSV(videoInfo));
				IO.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(mainFrame, "ERROR - unable to save: " + ex.getMessage(), PROGRAM_NAME, JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(mainFrame, "Info saved correctly", PROGRAM_NAME, JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@Override
	public void onExit() {
		// there are not info memorized or the user chosed to exit anyway
		if (
			videosInfo.size() == 0 ||
			JOptionPane.showConfirmDialog(mainFrame, "There are unsaved info: exit anyway?", PROGRAM_NAME, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION
		) {
			timer.stop();
			mainFrame.dispose();
		}
	}
}