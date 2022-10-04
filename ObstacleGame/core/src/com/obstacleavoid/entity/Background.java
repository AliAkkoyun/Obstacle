package com.obstacleavoid.entity;

public class Background {
    private float x;
    private float y;
    private float width;
    private float height;

    private int backgroundOffSet =2;


    public Background() {
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }
    public void setSize(float width, float height){
        this.width = width;
        this.height = height;

    }
//    public float backgroundLoop(){
//
//        while (true){
//
//            int currentBackgroundOffSet = backgroundOffSet +=2f;
//
//            if(backgroundOffSet % GameConfig.WORLD_HEIGHT == 0f){
//                backgroundOffSet = 0;
//            }
//
//            return currentBackgroundOffSet;
//        }
//
//    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
