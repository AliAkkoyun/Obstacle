package com.obstacleavoid.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;



public class GdxUtils {
    public static void clearScreen(){
        clearScreen(Color.BLACK);
    }
    public static void clearScreen(Color color){
        Gdx.gl.glClearColor(color.r,color.g, color.b,color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    private GdxUtils(){}
}