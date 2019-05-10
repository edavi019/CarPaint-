package com.example.edavi019.carpaint;

public class ResultLists {


    public String colorName;
    public String colorCode;
    public String hex;

    public ResultLists(String colorName, String colorCode, String hex) {

        this.colorName = colorName;
        this.colorCode = colorCode;
        this.hex = hex;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getColorName() {
        return colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getHex() {
        return hex;
    }
}
