package com.example.pictureeditor;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;


public class TouchListener implements View.OnTouchListener {
    EditImageActivity mainActivity;
    GestureDetectorCompat gestureDetectorCompat;
    int totalPaths;

    public TouchListener(EditImageActivity ma) {
        this.mainActivity = ma;
        gestureDetectorCompat = new GestureDetectorCompat(this.mainActivity, new MyGestureListener());
        totalPaths = -1;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureDetectorCompat.onTouchEvent(motionEvent);
        int maskedAction = motionEvent.getActionMasked();
        switch(maskedAction){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    totalPaths++;
                    int id = totalPaths + motionEvent.getPointerId(i);
                    System.out.println("Add path: "+id);
                    mainActivity.addPath(id, motionEvent.getX(i), motionEvent.getY(i));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    int id = totalPaths + motionEvent.getPointerId(i);
                    System.out.println("Update path: "+id);
                    mainActivity.updatePath(id, motionEvent.getX(i), motionEvent.getY(i));
                }
                break;
        }
        return true;
    }

    // Gets the number of paths. used for undo and clear.
    public int getTotalPaths(){
        return totalPaths;
    }

    // Used when the canvas is cleared.
    public void resetTotalPaths(){
        totalPaths = -1;
    }



    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            mainActivity.onDoubleTap(e);
            return super.onDoubleTap(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            mainActivity.onLongPress(e);
            super.onLongPress(e);
        }
    }
}
