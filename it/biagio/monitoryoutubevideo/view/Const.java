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



import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;



/**
 * Calss for the constants related to the view.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class Const
{
	static {
		IconFontSwing.register(FontAwesome.getIconFont());
	}
	
	
	
	/*
	 * DIMENSIONS
	 */
	
	public static final int MAIN_FRAME_WIDTH = 600;
	
	public static final int MAIN_FRAME_HEIGHT = 600;
	
	
	
	/*
	 * COLORS
	 */
	
	public static final Color FRAME_BACKGROUND_COLOR = new Color(242, 248, 240);// #f2f8f0
	
	
	
	public static final Color PANEL_BACKGROUND_COLOR = Color.WHITE;// ffffff
	
	public static final Color PANEL_BORDER_COLOR = new Color(204, 204, 204);// #cccccc
	
	
	
	public static final Color URL_FIELD_BORDER_COLOR = Color.RED;// ff0000
	
	
	
	public static final Color LOG_TEXT_AREA_FOREGROUND_COLOR = Color.RED;// ff0000
	
	public static final Color LOG_TEXT_AREA_BACKGROUND_COLOR = Color.BLACK;// 000000
	
	public static final Color LOG_TEXT_AREA_BORDER_COLOR = Color.RED;// ff0000
	
	
	
	public static final Color BUTTON_FOREGROUND_COLOR = Color.WHITE;// #ffffff
	
	public static final Color PRIMARY_BUTTON_BACKGROUND_COLOR = new Color(66, 139, 202);// #428bca
	
	public static final Color PRIMARY_BUTTON_BORDER_COLOR = new Color(53, 126, 189);// #357ebd
	
	public static final Color SUCCESS_BUTTON_BACKGROUND_COLOR = new Color(92, 184, 92);// #5cb85c
	
	public static final Color SUCCESS_BUTTON_BORDER_COLOR = new Color(76, 174, 76);// #4cae4c
	
	public static final Color WARNING_BUTTON_BACKGROUND_COLOR = new Color(236, 151, 31);// #ec971f
	
	public static final Color WARNING_BUTTON_BORDER_COLOR = new Color(213, 133, 18);// #d58512
	
	public static final Color DANGER_BUTTON_BACKGROUND_COLOR = new Color(201, 48, 44);// #c9302c
	
	public static final Color DANGER_BUTTON_BORDER_COLOR = new Color(172, 41, 37);// #ac2925
	
	
	
	/*
	 * FONTS
	 */
	
	public static final Font URL_FIELD_FONT = new Font(Font.SERIF, Font.BOLD, 11);
	
	public static final Font LOG_TEXT_AREA_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 11);
	
	
	
	/*
	 * STRINGS
	 */
	
	public static final String FILTERS_LABEL_TEXT = "Show:";
	
	
	
	public static final String TITLE_TOGGLE_BUTTON_TOOL_TIP_TEXT = "Title";
	
	public static final String USER_TOGGLE_BUTTON_TOOL_TIP_TEXT = "User";
	
	public static final String SUBSCRIBERS_TOGGLE_BUTTON_TOOL_TIP_TEXT = "Subscribers number";
	
	public static final String VIEWS_COUNT_TOGGLE_BUTTON_TOOL_TIP_TEXT = "Views number";
	
	public static final String LIKE_COUNT_TOGGLE_BUTTON_TOOL_TIP_TEXT = "Like number";
	
	public static final String UNLIKE_COUNT_TOGGLE_BUTTON_TOOL_TIP_TEXT = "Unlike number";
	
	
	
	public static final String START_BUTTON_TOOL_TIP_TEXT = "Start the monitoring";
	
	public static final String PAUSE_BUTTON_TOOL_TIP_TEXT = "Pause the monitoring";
	
	public static final String STOP_BUTTON_TOOL_TIP_TEXT = "Stop the monitoring";
	
	public static final String CLEAR_BUTTON_TOOL_TIP_TEXT = "Clear the info memorized so far";
	
	public static final String SAVE_BUTTON_TOOL_TIP_TEXT = "Save the info memorized so far";
	
	public static final String EXIT_BUTTON_TOOL_TIP_TEXT = "Exit the program";
	
	
	
	/*
	 * ICONS
	 */
	
	public static final Icon YOUTUBE_ICON = IconFontSwing.buildIcon(FontAwesome.YOUTUBE, 22, Color.RED);
	
	
	
	public static final Icon TITLE_TOGGLE_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.TERMINAL, 12);
	
	public static final Icon USER_TOGGLE_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.USER_O, 12);
	
	public static final Icon SUBSCRIBERS_COUNT_TOGGLE_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.BELL_O, 12);
	
	public static final Icon VIEWS_COUNT_TOGGLE_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.EYE, 12);
	
	public static final Icon LIKE_COUNT_TOGGLE_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.THUMBS_O_UP, 12);
	
	public static final Icon UNLIKE_COUNT_TOGGLE_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.THUMBS_O_DOWN, 12);
	
	
	
	public static final Icon START_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.PLAY_CIRCLE_O, 25, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon PAUSE_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.PAUSE_CIRCLE_O, 25, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon STOP_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.STOP_CIRCLE_O, 25, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon CLEAR_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.ERASER, 25, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon SAVE_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.DOWNLOAD, 25, BUTTON_FOREGROUND_COLOR);
	
	public static final Icon EXIT_BUTTON_ICON = IconFontSwing.buildIcon(FontAwesome.POWER_OFF, 25, BUTTON_FOREGROUND_COLOR);
}