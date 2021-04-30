package com.example.pictureeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button pictureButton;
    static final int REQUEST_IMAGE= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pictureButton = findViewById(R.id.pictureButton);
        pictureButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == pictureButton.getId()){
            Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(takePicIntent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(takePicIntent, REQUEST_IMAGE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Intent startSecondActivity = new Intent(this, EditImageActivity.class);
            startSecondActivity.putExtra("image", extras);
            startActivity(startSecondActivity);
        }
    }
}