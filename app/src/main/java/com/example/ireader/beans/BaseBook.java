package com.example.ireader.beans;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by ChenR on 2018/3/26.
 */

public abstract class BaseBook implements Serializable{

    protected long id;
    // In general, the file name represents the title;
    protected String name; // saved file name;
    protected String title; // book name;

    protected String savedFilePath; // complete file path
    protected Uri saveFileUri;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSavedFilePath() {
        return savedFilePath;
    }

    public void setSavedFilePath(String savedFilePath) {
        this.savedFilePath = savedFilePath;
    }

    public Uri getSaveFileUri() {
        return saveFileUri;
    }

    public void setSaveFileUri(Uri saveFileUri) {
        this.saveFileUri = saveFileUri;
    }
}
