package com.example.api;

public class Data {

    //https://api.nasa.gov/EPIC/archive/natural/<DATE>/png/<IMAGE>.png?api_key=DEMO_<KEY>
    private static final String FORMAT = "https://api.nasa.gov/EPIC/archive/natural/%s/png/%s.png?api_key=%s";

    private String identifier;
    private String caption;
    private String image;
    private String date;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        String[] dateComponents = date.split(" ")[0].split("-");
        return String.format("%s/%s/%s", dateComponents[0], dateComponents[1], dateComponents[2]);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return String.format(FORMAT, getDate(), image, BuildConfig.NASA_ACCESS_KEY);
    }

}
