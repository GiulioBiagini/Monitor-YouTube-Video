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



package it.biagio.monitoryoutubevideo.model.io;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import it.biagio.monitoryoutubevideo.model.info.VideoInfo;



/**
 * Class to write to a file a list of videos info.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class IO
{
	/**
	 * The format of the date
	 */
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * The string used to divide each datum
	 */
	private static final String DATA_SEPARATOR = ",";
	
	/**
	 * The string used to divide each VideoInfo
	 */
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	
	
	/**
	 * Write the videos info into a csv (comma separated value) file
	 * 
	 * @param videosInfo - the list of info to write
	 * @param file - the file in which to write the info
	 * @throws IOException - if while the writing an error occurs
	 */
	public static void writeCSV(ArrayList<VideoInfo> videosInfo, File file) throws IOException {
		if (videosInfo == null)
			throw new IllegalArgumentException("Unable to write a null list of videos info");
		if (file == null)
			throw new IllegalArgumentException("Unable to write the list in a null file");
		
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
		for (VideoInfo videoInfo : videosInfo) {
			if (videoInfo != null && !videoInfo.isGeneralError())
				bufferedWriter.write(
					DATE_FORMAT.format(videoInfo.getDate()) + DATA_SEPARATOR +
					(videoInfo.isTitleError() ? "" : videoInfo.getTitle().replace(DATA_SEPARATOR, " ")) + DATA_SEPARATOR +
					(videoInfo.isUserError() ? "" : videoInfo.getUser().replace(DATA_SEPARATOR, " ")) + DATA_SEPARATOR + 
					(videoInfo.isSubscribersCountError() ? "" : videoInfo.getSubscribersCount()) + DATA_SEPARATOR +
					(videoInfo.isViewsCountError() ? "" : videoInfo.getViewsCount()) + DATA_SEPARATOR +
					(videoInfo.isLikeCountError() ? "" : videoInfo.getLikeCount()) + DATA_SEPARATOR +
					(videoInfo.isUnlikeCountError() ? "" : videoInfo.getUnlikeCount()) + LINE_SEPARATOR
				);
		}
		bufferedWriter.close();
	}
}