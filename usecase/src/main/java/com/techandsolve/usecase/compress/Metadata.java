package com.techandsolve.usecase.compress;

import org.apache.pdfbox.cos.COSName;


public class Metadata {

    private int width;
    private int height;
    private float displayX;
    private float displayY;
    private COSName objectName;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getDisplayX() {
        return displayX;
    }

    public void setDisplayX(float displayX) {
        this.displayX = displayX;
    }

    public float getDisplayY() {
        return displayY;
    }

    public void setDisplayY(float displayY) {
        this.displayY = displayY;
    }

    @Override
    public String toString() {
        return String.format("displayX: %s displayY: %s, w: %s h: %s", displayX, displayY, width, height);
    }

    public COSName getObjectName() {
        return objectName;
    }

    public void setObjectName(COSName objectName) {
        this.objectName = objectName;
    }
}

