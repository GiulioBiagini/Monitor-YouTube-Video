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



import java.util.Date;



/**
 * Class for the video info.
 * 
 * @author Giulio Biagini - giulio.biagini90@gmail.com
 */
public class VideoInfo
{
	/** The date at which these info have been retrieved */
	private Date date;
	
	
	
	/** The title of the video */
	private String title;
	
	/** The name of the user who leaded the video */
	private String user;
	
	/** The number of subscribers of the user */
	private int subscribersCount;
	
	/** The number of views of the video */
	private int viewsCount;
	
	/** The number of like of the video */
	private int likeCount;
	
	/** The number of unlike of the video */
	private int unlikeCount;
	
	
	
	/** If there is a general error */
	private boolean generalError;
	
	/** If the title has an error */
	private boolean titleError;
	
	/** If the user has an error */
	private boolean userError;
	
	/** If the subscribers count has an error */
	private boolean subscribersCountError;
	
	/** If the views count has an error */
	private boolean viewsCountError;
	
	/** If the like count has an error */
	private boolean likeCountError;
	
	/** If the unlike count has an error */
	private boolean unlikeCountError;
	
	
	
	/** The details if there is a general error */
	private String generalErrorDetails;
	
	/** The details if the title has an error */
	private String titleErrorDetails;
	
	/** The details if the user has an error */
	private String userErrorDetails;
	
	/** The details if the subscribers count has an error */
	private String subscribersCountErrorDetails;
	
	/** The details if the views count has an error */
	private String viewsCountErrorDetails;
	
	/** The details if the like count has an error */
	private String likeCountErrorDetails;
	
	/** The details if the unlike count has an error */
	private String unlikeCountErrorDetails;
	
	
	
	/**
	 * Create a video info where the date is set to now and all values to:
	 * - empty: for strings;
	 * - 0: for integers.
	 */
	public VideoInfo() {
		this.date = new Date();
		this.generalError = false;
		setTitle("");
		setUser("");
		setSubscribersCount(0);
		setViewsCount(0);
		setLikeCount(0);
		setUnlikeCount(0);
	}
	
	
	
	/**
	 * Set the title of the video
	 * 
	 * @param title - the title of the video
	 */
	public void setTitle(String title) {
		titleError = false;
		this.title = title == null ? "" : title;
	}
	
	/**
	 * Set the name of the user who loaded the video
	 * 
	 * @param user - the name of the user who leaded the video
	 */
	public void setUser(String user) {
		userError = false;
		this.user = user == null ? "" : user;
	}
	
	/**
	 * Set the number of subscribers of the user
	 * 
	 * @param subscribersCount - the number of subscribers of the user
	 */
	public void setSubscribersCount(int subscribersCount) {
		subscribersCountError = false;
		this.subscribersCount = subscribersCount;
	}
	
	/**
	 * Set the number of views of the video
	 * 
	 * @param viewsCount - the number of views of the video
	 */
	public void setViewsCount(int viewsCount) {
		viewsCountError = false;
		this.viewsCount = viewsCount;
	}
	
	/**
	 * Set the number of like of the video
	 * 
	 * @param likeCount - the number of like of the video
	 */
	public void setLikeCount(int likeCount) {
		likeCountError = false;
		this.likeCount = likeCount;
	}
	
	/**
	 * Set the number of unlike of the video
	 * 
	 * @param unlikeCount - the number of unlike of the video
	 */
	public void setUnlikeCount(int unlikeCount) {
		unlikeCountError = false;
		this.unlikeCount = unlikeCount;
	}
	
	
	
	/**
	 * Set a general error
	 * 
	 * @param generalErrorDetails - the details of the error
	 */
	public void setGeneralError(String generalErrorDetails) {
		generalError = true;
		this.generalErrorDetails = generalErrorDetails == null ? "" : generalErrorDetails;
	}
	
	/**
	 * Set as an error the title of the video
	 * 
	 * @param titleErrorDetails - the details of the error
	 */
	public void setTitleError(String titleErrorDetails) {
		titleError = true;
		this.titleErrorDetails = titleErrorDetails == null ? "" : titleErrorDetails;
	}
	
	/**
	 * Set as an error the name of the user who loaded the video
	 * 
	 * @param userErrorDetails - the details of the error
	 */
	public void setUserError(String userErrorDetails) {
		userError = true;
		this.userErrorDetails = userErrorDetails == null ? "" : userErrorDetails;
	}
	
	/**
	 * Set as an error the number of subscribers of the user
	 * 
	 * @param subscribersCountErrorDetails - the details of the error
	 */
	public void setSubscribersCountError(String subscribersCountErrorDetails) {
		subscribersCountError = true;
		this.subscribersCountErrorDetails = subscribersCountErrorDetails == null ? "" : subscribersCountErrorDetails;
	}
	
	/**
	 * Set as an error the number of views of the video
	 * 
	 * @param viewsCountErrorDetails - the details of the error
	 */
	public void setViewsCountError(String viewsCountErrorDetails) {
		viewsCountError = true;
		this.viewsCountErrorDetails = viewsCountErrorDetails == null ? "" : viewsCountErrorDetails;
	}
	
	/**
	 * Set as an error the number of like of the video
	 * 
	 * @param likeCountErrorDetails - the details of the error
	 */
	public void setLikeCountError(String likeCountErrorDetails) {
		likeCountError = true;
		this.likeCountErrorDetails = likeCountErrorDetails == null ? "" : likeCountErrorDetails;
	}
	
	/**
	 * Set as an error the number of unlike of the video
	 * 
	 * @param unlikeCountErrorDetails - the details of the error
	 */
	public void setUnlikeCountError(String unlikeCountErrorDetails) {
		unlikeCountError = true;
		this.unlikeCountErrorDetails = unlikeCountErrorDetails == null ? "" : unlikeCountErrorDetails;
	}
	
	
	
	/**
	 * Check if there is a general error
	 * 
	 * @return true if there is a general error, false otherwise
	 */
	public boolean isGeneralError() {
		return generalError;
	}
	
	/**
	 * Check if the title is set as an error
	 * 
	 * @return true if title is set as an error, false otherwise
	 */
	public boolean isTitleError() {
		return titleError;
	}
	
	/**
	 * Check if the user is set as an error
	 * 
	 * @return true if user is set as an error, false otherwise
	 */
	public boolean isUserError() {
		return userError;
	}
	
	/**
	 * Check if the subscribers count is set as an error
	 * 
	 * @return true if subscribers count is set as an error, false otherwise
	 */
	public boolean isSubscribersCountError() {
		return subscribersCountError;
	}
	
	/**
	 * Check if the views count is set as an error
	 * 
	 * @return true if views count is set as an error, false otherwise
	 */
	public boolean isViewsCountError() {
		return viewsCountError;
	}
	
	/**
	 * Check if the like count is set as an error
	 * 
	 * @return true if like count is set as an error, false otherwise
	 */
	public boolean isLikeCountError() {
		return likeCountError;
	}
	
	/**
	 * Check if the unlike count is set as an error
	 * 
	 * @return true if unlike count is set as an error, false otherwise
	 */
	public boolean isUnlikeCountError() {
		return unlikeCountError;
	}
	
	
	
	/**
	 * Get the date at which these info have been retrieved
	 * 
	 * @return the date at which these info have been retrieved
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Get the title of the video
	 * 
	 * @return the title of the video
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Get the name of the user who leaded the video
	 * 
	 * @return the name of the user who leaded the video
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * Get the number of subscribers of the user
	 * 
	 * @return the number of subscribers of the user
	 */
	public int getSubscribersCount() {
		return subscribersCount;
	}
	
	/**
	 * Get the number of views of the video
	 * 
	 * @return the number of views of the video
	 */
	public int getViewsCount() {
		return viewsCount;
	}
	
	/**
	 * Get the number of like of the video
	 * 
	 * @return the number of like of the video
	 */
	public int getLikeCount() {
		return likeCount;
	}
	
	/**
	 * Get the number of unlike of the video
	 * 
	 * @return the number of unlike of the video
	 */
	public int getUnlikeCount() {
		return unlikeCount;
	}
	
	
	
	/**
	 * Get the general error details
	 * 
	 * @return the general error details
	 */
	public String getGeneralErrorDetails() {
		return generalErrorDetails;
	}
	
	/**
	 * Get the title error details
	 * 
	 * @return the title error details
	 */
	public String getTitleErrorDetails() {
		return titleErrorDetails;
	}
	
	/**
	 * Get the user error details
	 * 
	 * @return the user error details
	 */
	public String getUserErrorDetails() {
		return userErrorDetails;
	}
	
	/**
	 * Get the subscribers count error details
	 * 
	 * @return the subscribers count error details
	 */
	public String getSubscribersCountErrorDetails() {
		return subscribersCountErrorDetails;
	}
	
	/**
	 * Get the views count error details
	 * 
	 * @return the views count error details
	 */
	public String getViewsCountErrorDetails() {
		return viewsCountErrorDetails;
	}
	
	/**
	 * Get the like count error details
	 * 
	 * @return the like count error details
	 */
	public String getLikeCountErrorDetails() {
		return likeCountErrorDetails;
	}
	
	/**
	 * Get the unlike count error details
	 * 
	 * @return the unlike count error details
	 */
	public String getUnlikeCountErrorDetails() {
		return unlikeCountErrorDetails;
	}
}