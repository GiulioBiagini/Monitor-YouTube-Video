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



package it.biagio.monitoryoutubevideo.view.utils;



import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;



/**
 * Class for the buttons.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
@SuppressWarnings("serial")
public abstract class AButton extends JButton
{
	/**
	 * Create a decorated button
	 * 
	 * @param icon - the icon
	 * @param toolTipText - the tool tip text
	 * @param backgroundColor - the color of the background
	 * @param borderColor - the color of the border
	 */
	public AButton(Icon icon, String toolTipText, Color backgroundColor, Color borderColor) {
		super(icon);
		setToolTipText(toolTipText);
		setBackground(backgroundColor);
		setBorder(BorderFactory.createLineBorder(borderColor, 1, true));
		// those two lines are needed on windows
		setContentAreaFilled(false);
		setOpaque(true);
	}
}