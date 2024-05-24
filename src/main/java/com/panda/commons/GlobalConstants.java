package com.panda.commons;


import java.io.File;

public class GlobalConstants {
    private static GlobalConstants GlobalConstants;
    private final String userPandaPageUrl = "http://live.techpanda.org/";
    private final String adminNopComPageUrl = "https://admin-demo.nopcommerce.com/";
    private final String projectPath = System.getProperty("user.dir");
    private final String osName = System.getProperty("os.name");
    private final String uploadFile = projectPath + File.separator + "uploadFiles" + File.separator;
    private final String downloadFile = projectPath + File.separator + "downloadFiles";
    private final String browserLog = projectPath + File.separator + "browserLogs";
    private final long shortTimeout = 5;
    private final long longTimeout = 30;
    private final int retryTestFail = 3;

    private GlobalConstants() {
    }

    public static GlobalConstants getGlobalConstants() {
        if (GlobalConstants == null) {
            GlobalConstants = new GlobalConstants();
        }
        return GlobalConstants;
    }

    public String getuserPandaPageUrl() {
        return userPandaPageUrl;
    }

    public String getAdminNopComPageUrl() {
        return adminNopComPageUrl;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public String getOsName() {
        return osName;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public String getDownloadFile() {
        return downloadFile;
    }

    public String getBrowserLog() {
        return browserLog;
    }

    public long getShortTimeout() {
        return shortTimeout;
    }

    public long getLongTimeout() {
        return longTimeout;
    }

    public int getRetryTestFail() {
        return retryTestFail;
    }
}
