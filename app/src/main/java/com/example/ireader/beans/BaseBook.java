package com.example.ireader.beans;

import java.io.Serializable;

/**
 * Created by ChenR on 2018/3/26.
 */

public abstract class BaseBook implements Serializable{

    private long id;
    // In general, the file name represents the title;
    private String name; // saved file name;
    private String title; // book name;

    private String savedFilePath; // complete file path


}
