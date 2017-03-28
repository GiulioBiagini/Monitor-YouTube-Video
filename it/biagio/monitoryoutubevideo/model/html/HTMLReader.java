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



package it.biagio.monitoryoutubevideo.model.html;



import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import it.biagio.monitoryoutubevideo.model.info.VideoInfo;



/**
 * Class to connect with the youtube site, download the html document and
 * retrieve the informations parsing them directly from the html.
 * 
 * 
 * 
 * The panel which contains the info to retrieve has the following structure:
 * 
 * <div id="watch-header" ...>
 *   <div id="watch7-headlines" ...> ... </div>
 *   <div id="watch7-user-header ...> ... </div>
 *   <div id="watch8-action-buttons ...> ... </div>
 * </div>
 * 
 * In this class the div with id "watch-header" is called rootDiv.
 * 
 * 
 * 
 * The first div contains the TITLE of the video:
 * 
 * <div id="watch7-headlines">
 *   
 *   <div id="watch-headline-title">
 *     <h1> <span id="eow-title"> TITLE </span> </h1>
 *   </div>
 *   
 * </div>
 * 
 * 
 * 
 * The second div contains the USER PHOTO, the USER and the SUBSCRIBER COUNT:
 * 
 * <div id="watch7-user-header>
 *   
 *   <a><span><span><span>
 *     <img src="USER PHOTO (url) ">
 *     <span></span>
 *   </span></span></span></a>
 *   
 *   <div class="yt-user-info">
 *     <a> USER </a>
 *     <span title="Verificato"></span>// only if the user is verified
 *   </div>
 *   
 *   <span id="watch7-subscription-container">
 *     <span>
 *       <button> ... </button>
 *       <button> ... </button>
 *       <span> SUBSCRIBER COUNT </span>// hidden
 *       <span> SUBSCRIBER COUNT </span>// showed
 *       <span> ... </span>
 *     </span>
 *   </span>
 *   
 * </div>
 * 
 * 
 * 
 * The third div contains the VIEW COUNT, the LIKE COUNT and the DISLIKE COUNT:
 * 
 * <div id="watch8-action-buttons>
 * 
 *   <div id="watch8-secondary-actions"> ... </div>
 *   
 *   <div id="watch8-sentiment-actions">
 *     
 *     <div id="watch7-views-info">
 *       <div> VIEW COUNT </div>
 *       <div> ... </div>
 *     </div>
 *     
 *     <span>
 *       <span> <button> <span> LIKE COUNT </span> </button> </span>// showed
 *       <span> <button> <span> LIKE COUNT </span> </button> </span>// hidden
 *       <span> <button> <span> DISLIKE COUNT </span> </button> </span>// showed
 *       <span> <button> <span> DISLIKE COUNT </span> </button> </span>// hidden
 *     </span>
 *     
 *   </div>
 *   
 * </div>
 * 
 * 
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */ 
public class HTMLReader
{
	/** The div which contains all info to retrieve */
	private static Element rootDiv;
	
	
	
	/**
	 * Read html from url and update the rootDiv variable
	 * 
	 * @param url - the url to connect with
	 * @throws IOException - if the GET method used to connect to the url fails
	 * @throws HTMLReaderException - if it is not possible to find the rootDiv
	 */
	private static void read(String url) throws IOException, HTMLReaderException, IllegalArgumentException {
		Elements elements = Jsoup.connect(url).get().select("div#watch-header");
		if (elements.size() == 0)
			throw new HTMLReaderException("Unable to retrieve root div: div with id \"watch-header\" not found");
		if (elements.size() > 1)
			throw new HTMLReaderException("Unable to retrieve root div: more than one div with id \"watch-header\" found");
		rootDiv = elements.get(0);
	}
	
	
	
	/**
	 * Get the title
	 * 
	 * @return the title
	 * @throws HTMLReaderException - if it is not possible to retrieve the title
	 */
	private static String getTitle() throws HTMLReaderException {
		Elements elements = rootDiv.select("span#eow-title");
		if (elements.size() == 0)
			throw new HTMLReaderException("Unable to retrieve title: <span id=\"eow-title\"> not found");
		if (elements.size() > 1)
			throw new HTMLReaderException("Unable to retrieve title: more than one <span id=\"eow-title\"> found");
		return elements.get(0).text();
	}
	
	/**
	 * Get the user name
	 * 
	 * @return the user name
	 * @throws HTMLReaderException - if it is not possible to retrieve the user name
	 */
	private static String getUser() throws HTMLReaderException {
		Elements elements = rootDiv.select("div#watch7-user-header > div > a");
		if (elements.size() == 0 || elements.size() > 1) {
			elements = rootDiv.select("div#watch7-user-header");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve user: <div id=\"watch7-user-header\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve user: more than one <div id=\"watch7-user-header\"> found");
			elements = elements.select("div");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve user: <div> child of <div id=\"watch7-user-header\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve user: more than one <div> child of <div id=\"watch7-user-header\"> found");
			elements = elements.select("a");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve user: <a> child of <div> child of <div id=\"watch7-user-header\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve user: more than one <a> child of <div> child of <div id=\"watch7-user-header\"> found");
		}
		return elements.get(0).text();
	}
	
	/**
	 * Get the subscribers number
	 * 
	 * @return the subscribers number
	 * @throws HTMLReaderException - if it is not possible to retrieve the subscribers number
	 */
	private static int getSubscribersCount() throws HTMLReaderException {
		Elements elements = rootDiv.select("span#watch7-subscription-container > span > span");
		if (elements.size() == 0 || elements.size() > 3) {
			elements = rootDiv.select("span#watch7-subscription-container");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve subscribers count: <span id=\"watch7-subscription-container\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve subscribers count: more than one <span id=\"watch7-subscription-container\"> found");
			elements = elements.select("span");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve subscribers count: <span> child of <span id=\"watch7-subscription-container\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve subscribers count: more than one <span> child of <span id=\"watch7-subscription-container\"> found");
			elements = elements.select("span");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve subscribers count: <span> child of <span> child of <span id=\"watch7-subscription-container\"> not found");
			if (elements.size() > 3)
				throw new HTMLReaderException("Unable to retrieve subscribers count: more than three <span> children of <span> child of <span id=\"watch7-subscription-container\"> found");
		}
		try {
			return Integer.parseInt(elements.get(1).text().replace(".", ""));
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Unable to parse subscribers count: not a valid integer");
		}
	}
	
	/**
	 * Get the views number
	 * 
	 * @return the views number
	 * @throws HTMLReaderException - if it is not possible to retrieve the views number
	 */
	private static int getViewsCount() throws HTMLReaderException {
		Elements elements = rootDiv.select("div#watch7-views-info > div");
		if (elements.size() == 0 || elements.size() > 2) {
			elements = rootDiv.select("div#watch7-views-info");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve views count: <div id=\"watch7-views-info\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve views count: more than one <div id=\"watch7-views-info\"> found");
			elements = elements.select("div");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve views count: <div> child of <div id=\"watch7-views-info\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve views count: more than two <div> children of <div id=\"watch7-views-info\"> found");
		}
		try {
			return Integer.parseInt(elements.get(0).text().replace(".", "").replace(" visualizzazioni", ""));
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Unable to parse views count: not a valid integer");
		}
	}
	
	/**
	 * Get the like number
	 * 
	 * @return the like number
	 * @throws HTMLReaderException - if it is not possible to retrieve the like number
	 */
	private static int getLikeCount() throws HTMLReaderException {
		Elements elements = rootDiv.select("div#watch8-sentiment-actions > span > span:eq(0) > button > span");
		if (elements.size() == 0 || elements.size() > 1) {
			elements = rootDiv.select("div#watch8-sentiment-actions");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve like count: <div id=\"watch8-sentiment-actions\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve like count: more than one <div id=\"watch8-sentiment-actions\"> found");
			elements = elements.select("span");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve like count: <span> child of <div id=\"watch8-sentiment-actions\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve like count: more than one <span> child of <div id=\"watch8-sentiment-actions\"> found");
			elements = elements.select("span");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve like count: <span> child of <span> child of <div id=\"watch8-sentiment-actions\"> not found");
			if (elements.size() > 4)
				throw new HTMLReaderException("Unable to retrieve like count: more than four <span> child of <span> child of <div id=\"watch8-sentiment-actions\"> found");
			elements = elements.get(0).select("button");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve like count: <button> child of fourth <span> child of <span> child of <div id=\"watch8-sentiment-actions\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve like count: more than one <button> child of fourth <span> child of <span> child of <div id=\"watch8-sentiment-actions\"> found");
			elements = elements.select("span");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve like count: <span> child of <button> child of fourth <span> child of <span> child of <div id=\"watch8-sentiment-actions\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve like count: more than one <span> child of <button> child of fourth <span> child of <span> child of <div id=\"watch8-sentiment-actions\"> found");
		}
		try {
			return Integer.parseInt(elements.get(0).text().replace(".", ""));
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Unable to parse like count: not a valid integer");
		}
	}
	
	/**
	 * Get the unlike number
	 * 
	 * @return the unlike number
	 * @throws HTMLReaderException - if it is not possible to retrieve the unlike number
	 */
	private static int getUnlikeCount() throws HTMLReaderException {
		Elements elements = rootDiv.select("div#watch8-sentiment-actions > span > span:eq(2) > button > span");
		if (elements.size() == 0 || elements.size() > 1) {
			elements = rootDiv.select("div#watch8-sentiment-actions");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve unlike count: <div id=\"watch8-sentiment-actions\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve unlike count: more than one <div id=\"watch8-sentiment-actions\"> found");
			elements = elements.select("span");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve unlike count: <span> child of <div id=\"watch8-sentiment-actions\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve unlike count: more than one <span> child of <div id=\"watch8-sentiment-actions\"> found");
			elements = elements.select("span");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve unlike count: <span> child of <span> child of <div id=\"watch8-sentiment-actions\"> not found");
			if (elements.size() > 4)
				throw new HTMLReaderException("Unable to retrieve unlike count: more than four <span> child of <span> child of <div id=\"watch8-sentiment-actions\"> found");
			elements = elements.get(2).select("button");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve unlike count: <button> child of fourth <span> child of <span> child of <div id=\"watch8-sentiment-actions\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve unlike count: more than one <button> child of fourth <span> child of <span> child of <div id=\"watch8-sentiment-actions\"> found");
			elements = elements.select("span");
			if (elements.size() == 0)
				throw new HTMLReaderException("Unable to retrieve unlike count: <span> child of <button> child of fourth <span> child of <span> child of <div id=\"watch8-sentiment-actions\"> not found");
			if (elements.size() > 1)
				throw new HTMLReaderException("Unable to retrieve unlike count: more than one <span> child of <button> child of fourth <span> child of <span> child of <div id=\"watch8-sentiment-actions\"> found");
		}
		try {
			return Integer.parseInt(elements.get(0).text().replace(".", ""));
		} catch (NumberFormatException ex) {
			throw new IllegalArgumentException("Unable to parse unlike count: not a valid integer");
		}
	}
	
	
	
	/**
	 * Read html from url and retrieve all the info
	 * 
	 * @param url - the url to connect with
	 * @return all the info retrieved
	 * @throws IOException - if the GET method used to connect to the url fails
	 * @throws HTMLReaderException - if it is not possible to find the element which contains all info
	 */
	public static VideoInfo getVideoInfo(String url) {
		VideoInfo videoInfo = new VideoInfo();
		
		if (url == null) {
			videoInfo.setGeneralError("Unable to read from a null url");
			return videoInfo;
		}
		if (url.isEmpty()) {
			videoInfo.setGeneralError("Unable to read from an empty url");
			return videoInfo;
		}
		
		try {
			HTMLReader.read(url);
		} catch (IllegalArgumentException ex) {
			videoInfo.setGeneralError(ex.getMessage());
			return videoInfo;
		} catch (IOException ex) {
			videoInfo.setGeneralError(ex.getMessage());
			return videoInfo;
		} catch (HTMLReaderException ex) {
			videoInfo.setGeneralError(ex.getMessage());
			return videoInfo;
		}
		
		try {
			videoInfo.setTitle(HTMLReader.getTitle());
		} catch (HTMLReaderException ex) {
			videoInfo.setTitleError(ex.getMessage());
		}
		try {
			videoInfo.setUser(HTMLReader.getUser());
		} catch (HTMLReaderException ex) {
			videoInfo.setUserError(ex.getMessage());
		}
		try {
			videoInfo.setSubscribersCount(HTMLReader.getSubscribersCount());
		} catch (HTMLReaderException ex) {
			videoInfo.setSubscribersCountError(ex.getMessage());
		}
		try {
			videoInfo.setViewsCount(HTMLReader.getViewsCount());
		} catch (HTMLReaderException ex) {
			videoInfo.setViewsCountError(ex.getMessage());
		}
		try {
			videoInfo.setLikeCount(HTMLReader.getLikeCount());
		} catch (HTMLReaderException ex) {
			videoInfo.setLikeCountError(ex.getMessage());
		}
		try {
			videoInfo.setUnlikeCount(HTMLReader.getUnlikeCount());
		} catch (HTMLReaderException ex) {
			videoInfo.setUnlikeCountError(ex.getMessage());
		}
		
		return videoInfo;
	}
}