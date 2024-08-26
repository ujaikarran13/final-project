package com.techelevator.bookmark.model;

import java.time.LocalDate;

/**
 * Model class representing a Bookmark.
 *   - Bookmarks are associated to a User.
 *   - They may also optionally have associated Tags.
 *   - A public Bookmark is visible to all Users.
 *   - A flagged Bookmark is marked for review by an admin.
 */
public class Bookmark {

    private int bookmarkId;
    private int userId;
    private String title;
    private String url;
    private String description;
    private LocalDate createDate;
    private boolean isPublic;
    private boolean isFlagged;
    private String allTags;


    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public String getAllTags() {
        return allTags;
    }

    public void setAllTags(String allTags) {
        this.allTags = allTags;
    }
}
