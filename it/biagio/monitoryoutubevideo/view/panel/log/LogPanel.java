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



package it.biagio.monitoryoutubevideo.view.panel.log;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.text.DefaultCaret;

import it.biagio.monitoryoutubevideo.model.info.VideoInfo;
import it.biagio.monitoryoutubevideo.view.Const;
import it.biagio.monitoryoutubevideo.view.panel.APanel;



/**
 * Class for the panel with the log text area.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
@SuppressWarnings("serial")
public class LogPanel extends APanel
{
	/**
	 * The format of the date
	 */
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
	
	/**
	 * The string used for the "newline"
	 */
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	
	
	/** ScrollPane to scroll the log text area */
	private JScrollPane logScrollPane;
	
	/** The log text area */
	private JTextArea logTextArea;
	
	/** The caret of the log text area */
	private DefaultCaret caret;
	
	
	
	/** A panel with all filters */
	private JPanel filtersPanel;
	
	/** Label which describes the filter panel */
	private JLabel showLabel;
	
	/** Filter to show the title */
	private JToggleButton titleToggleButton;
	
	/** Filter to show the user */
	private JToggleButton userToggleButton;
	
	/** Filter to show the subscribers number */
	private JToggleButton subscribersCountToggleButton;
	
	/** Filter to show the views number */
	private JToggleButton viewsCountToggleButton;
	
	/** Filter to show the like number */
	private JToggleButton likeCountToggleButton;
	
	/** Filter to show the unlike number */
	private JToggleButton unlikeCountToggleButton;
	
	
	
	/**
	 * Create a panel with a log text area
	 */
	public LogPanel() {
		super(new BorderLayout(0, 10));
		
		logTextArea = new JTextArea();
		logTextArea.setEditable(false);
		logTextArea.setFont(Const.LOG_TEXT_AREA_FONT);
		logTextArea.setForeground(Const.LOG_TEXT_AREA_FOREGROUND_COLOR);
		logTextArea.setBackground(Const.LOG_TEXT_AREA_BACKGROUND_COLOR);
		
		logScrollPane = new JScrollPane(logTextArea);
		logScrollPane.setBorder(BorderFactory.createLineBorder(Const.LOG_TEXT_AREA_BORDER_COLOR, 1, true));
		
		caret = (DefaultCaret) logTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		showLabel = new JLabel(Const.FILTERS_LABEL_TEXT);
		
		titleToggleButton = new JToggleButton(Const.TITLE_TOGGLE_BUTTON_ICON, false);
		titleToggleButton.setToolTipText(Const.TITLE_TOGGLE_BUTTON_TOOL_TIP_TEXT);
		
		userToggleButton = new JToggleButton(Const.USER_TOGGLE_BUTTON_ICON, false);
		userToggleButton.setToolTipText(Const.USER_TOGGLE_BUTTON_TOOL_TIP_TEXT);
		
		subscribersCountToggleButton = new JToggleButton(Const.SUBSCRIBERS_COUNT_TOGGLE_BUTTON_ICON, true);
		subscribersCountToggleButton.setToolTipText(Const.SUBSCRIBERS_TOGGLE_BUTTON_TOOL_TIP_TEXT);
		
		viewsCountToggleButton = new JToggleButton(Const.VIEWS_COUNT_TOGGLE_BUTTON_ICON, true);
		viewsCountToggleButton.setToolTipText(Const.VIEWS_COUNT_TOGGLE_BUTTON_TOOL_TIP_TEXT);
		
		likeCountToggleButton = new JToggleButton(Const.LIKE_COUNT_TOGGLE_BUTTON_ICON, true);
		likeCountToggleButton.setToolTipText(Const.LIKE_COUNT_TOGGLE_BUTTON_TOOL_TIP_TEXT);
		
		unlikeCountToggleButton = new JToggleButton(Const.UNLIKE_COUNT_TOGGLE_BUTTON_ICON, true);
		unlikeCountToggleButton.setToolTipText(Const.UNLIKE_COUNT_TOGGLE_BUTTON_TOOL_TIP_TEXT);
		
		filtersPanel = new JPanel(new GridLayout(1, 7, 10, 10));
		filtersPanel.setOpaque(false);
		filtersPanel.add(showLabel);
		filtersPanel.add(titleToggleButton);
		filtersPanel.add(userToggleButton);
		filtersPanel.add(subscribersCountToggleButton);
		filtersPanel.add(viewsCountToggleButton);
		filtersPanel.add(likeCountToggleButton);
		filtersPanel.add(unlikeCountToggleButton);
		
		add(logScrollPane, BorderLayout.CENTER);
		add(filtersPanel, BorderLayout.SOUTH);
	}
	
	
	
	/**
	 * Append a video info in the log text area
	 * 
	 * @param videoInfo - the info related to the video
	 */
	public synchronized void appendLog(VideoInfo videoInfo) {
		if (videoInfo != null) {
			String log = DATE_FORMAT.format(videoInfo.getDate()) + LINE_SEPARATOR;
			if (videoInfo.isGeneralError())
				log += "ERROR:\t" + videoInfo.getGeneralErrorDetails() + LINE_SEPARATOR;
			else {
				if (titleToggleButton.isSelected())
					log += Const.TITLE_LOG_TEXT + ":\t" +
						(videoInfo.isTitleError() ? Const.ERROR_LOG_TEXT + ": " + videoInfo.getTitleErrorDetails() : videoInfo.getTitle()) + LINE_SEPARATOR;
				if (userToggleButton.isSelected())
					log += Const.USER_LOG_TEXT + ":\t" +
						(videoInfo.isUserError() ? Const.ERROR_LOG_TEXT + ": " + videoInfo.getUserErrorDetails() : videoInfo.getUser()) + LINE_SEPARATOR;
				if (subscribersCountToggleButton.isSelected())
					log += Const.SUBSCRIBERS_COUNT_LOG_TEXT + ":\t" +
						(videoInfo.isSubscribersCountError() ? Const.ERROR_LOG_TEXT + ": " + videoInfo.getSubscribersCountErrorDetails() : videoInfo.getSubscribersCount()) + LINE_SEPARATOR;
				if (viewsCountToggleButton.isSelected())
					log += Const.VIEWS_COUNT_LOG_TEXT + ":\t" +
						(videoInfo.isViewsCountError() ? Const.ERROR_LOG_TEXT + ": " + videoInfo.getViewsCountErrorDetails() : videoInfo.getViewsCount()) + LINE_SEPARATOR;
				if (likeCountToggleButton.isSelected())
					log += Const.LIKE_COUNT_LOG_TEXT + ":\t" +
						(videoInfo.isLikeCountError() ? Const.ERROR_LOG_TEXT + ": " + videoInfo.getLikeCountErrorDetails() : videoInfo.getLikeCount()) + LINE_SEPARATOR;
				if (unlikeCountToggleButton.isSelected())
					log += Const.UNLIKE_COUNT_LOG_TEXT + ":\t" +
						(videoInfo.isUnlikeCountError() ? Const.ERROR_LOG_TEXT + ": " + videoInfo.getUnlikeCountErrorDetails() : videoInfo.getUnlikeCount()) + LINE_SEPARATOR;
			}
			log += LINE_SEPARATOR;
			logTextArea.append(log);
		}
	}
	
	/**
	 * Clear the log text area
	 */
	public synchronized void clearLogTextArea() {
		logTextArea.setText("");
	}
}