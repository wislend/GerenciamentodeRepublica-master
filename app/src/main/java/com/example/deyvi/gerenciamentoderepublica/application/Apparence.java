package com.example.deyvi.gerenciamentoderepublica.application;

public class Apparence {


    private int colorPrimary;
    private int colorPrimaryDark; //será um percentual mais escuro que a color primária
    private String appName;
    private String welcomeImageURL;
    private String welcomeTitle;
    private String welcomeSubtitle;

    public int getColorPrimary() {
        return colorPrimary;
    }

    public void setColorPrimary(int colorPrimary) {
        this.colorPrimary = colorPrimary;
    }

    public int getColorPrimaryDark() {
        return colorPrimaryDark;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getWelcomeImageURL() {
        return welcomeImageURL;
    }

    public void setWelcomeImageURL(String welcomeImageURL) {
        this.welcomeImageURL = welcomeImageURL;
    }

    public String getWelcomeTitle() {
        return welcomeTitle;
    }

    public void setWelcomeTitle(String welcomeTitle) {
        this.welcomeTitle = welcomeTitle;
    }

    public String getWelcomeSubtitle() {
        return welcomeSubtitle;
    }

    public void setWelcomeSubtitle(String welcomeSubtitle) {
        this.welcomeSubtitle = welcomeSubtitle;
    }
}
