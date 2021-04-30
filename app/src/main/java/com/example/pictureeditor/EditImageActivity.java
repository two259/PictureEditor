package com.example.pictureeditor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EditImageActivity extends AppCompatActivity implements View.OnClickListener{

    MyCanvas myCanvas;
    Button redButton;
    Button blueButton;
    Button greenButton;
    Button undoButton;
    Button clearButton;
    Button doneButton;

    Intent backToMainActivity;

    TouchListener touchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);

        myCanvas = findViewById(R.id.myCanvas);

        redButton = findViewById(R.id.redButton);
        blueButton = findViewById(R.id.blueButton);
        greenButton = findViewById(R.id.greenButton);
        undoButton = findViewById(R.id.undoButton);
        clearButton = findViewById(R.id.clearButton);
        doneButton = findViewById(R.id.doneButton);

        redButton.setOnClickListener(this);
        blueButton.setOnClickListener(this);
        greenButton.setOnClickListener(this);
        undoButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);

        Bundle extras = getIntent().getBundleExtra("image");
        Bitmap image = (Bitmap) extras.get("data");
        myCanvas.setBackground(new BitmapDrawable(getResources(), image));


        touchListener = new TouchListener(this);

        myCanvas.setOnTouchListener(touchListener);

        backToMainActivity = new Intent(this, MainActivity.class);
    }

    public void addPath(int id, float x, float y) {
        myCanvas.addPath(id, x, y);
    }

    public void updatePath(int id, float x, float y) {
        myCanvas.updatePath(id, x, y);
    }

    public void removePath(int id) {
        myCanvas.removePath(id);
    }

    public void onDoubleTap(MotionEvent e) {
        // Insert one image
    }


    public void onLongPress(MotionEvent e) {
        // Insert other image
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == redButton.getId()){
            myCanvas.setActiveColor(0);
        }
        else if(v.getId() == blueButton.getId()){
            myCanvas.setActiveColor(1);
        }
        else if(v.getId() == greenButton.getId()){
            myCanvas.setActiveColor(2);
        }
        else if(v.getId() == undoButton.getId()){
            removePath(touchListener.getTotalPaths());
        }
        else if(v.getId() == clearButton.getId()){
            for(int i = 0; i <= touchListener.getTotalPaths(); i++){
                removePath(i);
            }
            touchListener.resetTotalPaths();
        }
        else if(v.getId() == doneButton.getId()){
            startActivity(backToMainActivity);
        }
    }
}
