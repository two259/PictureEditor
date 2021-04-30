package com.example.pictureeditor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MyCanvas extends View {
    HashMap <Integer, Path> activePaths;
    Paint pathPaint;

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activePaths = new HashMap<>();
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setColor(Color.RED);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(Path path: activePaths.values()){
            canvas.drawPath(path, pathPaint);
        }
        super.onDraw(canvas);
    }

    // Add a new path
    public void addPath(int id, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        activePaths.put(id, path);
        invalidate();
    }

    // Update a path.
    public void updatePath(int id, float x, float y) {
        Path path = activePaths.get(id);
        if(path != null){
            path.lineTo(x, y);
        }
        invalidate();
    }

    // Remove an existing path
    public void removePath(int id) {
        if(activePaths.containsKey(id)){
            activePaths.remove(id);
        }
        invalidate();
    }

    public void callInvalidate(){
        invalidate();
    }

    // Sets the color.
    public void setActiveColor(int color){
        if(color == 0){
            pathPaint.setColor(Color.RED);
        }
        else if(color == 1){
            pathPaint.setColor(Color.BLUE);
        }
        else if(color == 2){
            pathPaint.setColor(Color.GREEN);
        }
    }
}
