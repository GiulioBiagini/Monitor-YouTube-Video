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



package it.biagio.monitoryoutubevideo.model.info;



import java.text.DateFormat;
import java.text.SimpleDateFormat;



/**
 * Class to get strings representing the video's info.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class VideoInfoFormatter
{
	/**
	 * The format of the date
	 */
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
	
	/**
	 * The string used to divide each datum in the csv format
	 */
	private static final String CSV_DATA_SEPARATOR = ",";
	
	/**
	 * The string used to separe a line from another
	 */
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	
	
	/**
	 * Create a string with the specified informations, one per line
	 * 
	 * @param videoInfo - the video's info
	 * @param showTitle - if the title must appear in the output string
	 * @param showUser - if the user must appear in the output string
	 * @param showSubscribers  - if the subscribers number must appear in the output string
	 * @param showViews - if the views number must appear in the output string
	 * @param showLike - if the like number must appear in the output string
	 * @param showUnlike - if the unlike number must appear in the output string
	 * @return a string with the informations one per line
	 */
	public static String toLog(VideoInfo videoInfo, boolean showTitle, boolean showUser, boolean showSubscribers, boolean showViews, boolean showLike, boolean showUnlike) {
		if (videoInfo == null)
			return null;
		if (videoInfo.isGeneralError())
			return DATE_FORMAT.format(videoInfo.getDate()) + LINE_SEPARATOR +
				"ERROR:\t" + videoInfo.getGeneralErrorDetails() + LINE_SEPARATOR +
				LINE_SEPARATOR;
		return DATE_FORMAT.format(videoInfo.getDate()) + LINE_SEPARATOR +
			(showTitle ? "Title:\t" + (videoInfo.isTitleError() ? "ERROR: " + videoInfo.getTitleErrorDetails() : videoInfo.getTitle()) : "") + LINE_SEPARATOR +
			(showUser ? "User:\t" + (videoInfo.isUserError() ? "ERROR: " + videoInfo.getUserErrorDetails() : videoInfo.getUser()) : "") + LINE_SEPARATOR +
			(showSubscribers ? "Subs:\t" + (videoInfo.isSubscribersCountError() ? "ERROR: " + videoInfo.getSubscribersCountErrorDetails() : videoInfo.getSubscribersCount()) : "") + LINE_SEPARATOR +
			(showViews ? "Views:\t" + (videoInfo.isViewsCountError() ? "ERROR: " + videoInfo.getViewsCountErrorDetails() : videoInfo.getViewsCount()) : "") + LINE_SEPARATOR +
			(showLike ? "Like:\t" + (videoInfo.isLikeCountError() ? "ERROR: " + videoInfo.getLikeCountErrorDetails() : videoInfo.getLikeCount()) : "") + LINE_SEPARATOR +
			(showUnlike ? "Unlike:\t" + (videoInfo.isUnlikeCountError() ? "ERROR: " + videoInfo.getUnlikeCountErrorDetails() : videoInfo.getUnlikeCount()) : "") + LINE_SEPARATOR +
			LINE_SEPARATOR;
	}
	
	/**
	 * Create a string with informations in a csv (comma separated values) format:
	 * - the timestamp always appear;
	 * - if there is a general error all data have no value (empty value \"\");
	 * - if a datum has an error (eg: titleError, userError, ecc...) its data
	 *   will correspond to an empty value \"\".
	 * 
	 * @param videoInfo - the video's info
	 * @return a string with the informations in a csv format
	 */
	public static String toCSV(VideoInfo videoInfo) {
		if (videoInfo == null)
			return null;
		if (videoInfo.isGeneralError())
			return DATE_FORMAT.format(videoInfo.getDate()) + LINE_SEPARATOR +
				"" + CSV_DATA_SEPARATOR + "" + CSV_DATA_SEPARATOR + "" + CSV_DATA_SEPARATOR + "" + CSV_DATA_SEPARATOR + "" + CSV_DATA_SEPARATOR + "";
		return DATE_FORMAT.format(videoInfo.getDate()) + LINE_SEPARATOR +
			(videoInfo.isTitleError() ? "" : videoInfo.getTitle().replace(CSV_DATA_SEPARATOR, " ")) + CSV_DATA_SEPARATOR +
			(videoInfo.isUserError() ? "" : videoInfo.getUser().replace(CSV_DATA_SEPARATOR, " ")) + CSV_DATA_SEPARATOR +
			(videoInfo.isSubscribersCountError() ? "" : videoInfo.getSubscribersCount()) + CSV_DATA_SEPARATOR +
			(videoInfo.isViewsCountError() ? "" : videoInfo.getViewsCount()) + CSV_DATA_SEPARATOR +
			(videoInfo.isLikeCountError() ? "" : videoInfo.getLikeCount()) + CSV_DATA_SEPARATOR +
			(videoInfo.isUnlikeCountError() ? "" : videoInfo.getUnlikeCount()) + LINE_SEPARATOR;
	}
}