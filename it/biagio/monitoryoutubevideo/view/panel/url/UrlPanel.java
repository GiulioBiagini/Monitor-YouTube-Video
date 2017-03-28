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



package it.biagio.monitoryoutubevideo.view.panel.url;



import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

import it.biagio.monitoryoutubevideo.view.Const;
import it.biagio.monitoryoutubevideo.view.panel.APanel;



/**
 * Class for the panel with the field where the user input the url.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
@SuppressWarnings("serial")
public class UrlPanel extends APanel
{
	/** Label to show the youtube logo */
	private JLabel urlLabel;
	
	/** TextField for the url */
	private JTextField urlTextField;
	
	
	
	/** The listener to notify if any action is performed */
	private UrlListener listener;
	
	
	
	/**
	 * Create a panel with the field for the url
	 * 
	 * @param listener - the listener to notify if any action is performed
	 */
	public UrlPanel(UrlListener listener) {
		super(new BorderLayout(10, 0));
		
		this.listener = listener;
		
		urlLabel = new JLabel(Const.YOUTUBE_ICON);
		
		urlTextField = new JTextField();
		urlTextField.setBorder(BorderFactory.createLineBorder(Const.URL_FIELD_BORDER_COLOR, 1, true));
		urlTextField.setFont(Const.URL_FIELD_FONT);
		urlTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					actionEnter();
				else
					actionChanged();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		add(urlLabel, BorderLayout.WEST);
		add(urlTextField, BorderLayout.CENTER);
	}
	
	
	
	/**
	 * The related action to the editing of the url field
	 */
	private void actionChanged() {
		if (listener != null)
			listener.onUrlChanged(urlTextField.getText());
	}
	
	/**
	 * The related action to the pressing of the key "enter" in the url field
	 */
	private void actionEnter() {
		if (listener != null)
			listener.onUrlEnter();
	}
	
	
	
	/**
	 * Enables or disables the url field
	 * 
	 * @param enabled - true to enable the url field, false otherwise
	 */
	public void setUrlTextFieldEnabled(boolean enabled) {
		urlTextField.setEnabled(enabled);
	}
	
	/**
	 * Check if the url field is enabled or disabled
	 * 
	 * @return true if the url field is enabled, false otherwise
	 */
	public boolean isUrlTextFieldEnabled() {
		return urlTextField.isEnabled();
	}
	
	/**
	 * Get the content of the url field
	 * 
	 * @return the content of the url field
	 */
	public String getUrlTextFieldContent() {
		return urlTextField.getText();
	}
}