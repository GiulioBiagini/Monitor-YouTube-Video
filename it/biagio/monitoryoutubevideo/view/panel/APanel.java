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



package it.biagio.monitoryoutubevideo.view.panel;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import it.biagio.monitoryoutubevideo.view.Const;



/**
 * Class for the panels.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
@SuppressWarnings("serial")
public abstract class APanel extends JPanel
{
	/** The panel which contains the elements */
	private JPanel middlePanel;
	
	
	
	/**
	 * Create a panel with the given layout manager and an empty border all around
	 * 
	 * @param layoutManager - the layout manager to use
	 * @param top - the width of the top border in pixel
	 * @param left - the width of the left border in pixel
	 * @param bottom - the width of the bottom border in pixel
	 * @param right - the width of the right border in pixel
	 */
	public APanel(LayoutManager layoutManager) {
		super(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Const.PANEL_BORDER_COLOR, 1, true));
		
		middlePanel = new JPanel(layoutManager);
		middlePanel.setBackground(Const.PANEL_BACKGROUND_COLOR);
		middlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		super.add(middlePanel, BorderLayout.CENTER);
	}
	
	
	
	@Override
	public Component add(Component component) {
		return middlePanel.add(component);
	}
	
	@Override
	public void add(Component component, Object constraints) {
		middlePanel.add(component, constraints);
	}
}