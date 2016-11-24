package com.dosweedos.myapplication;

import android.net.Uri;

/**
 * Created by root on 11/23/16.
 */

public class UserBean {
    public String name;
    public String photourl;
    public UserBean() {

    }

    public UserBean(String name, String photourl) {
        this.name = name;
        this.photourl = photourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }
}
