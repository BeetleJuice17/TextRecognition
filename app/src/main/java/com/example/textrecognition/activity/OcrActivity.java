package com.example.textrecognition.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.textrecognition.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OcrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);
    }

    public File save_file(String result){
        try {
            String filename = "result.txt";
            File file = new File(getFilesDir(), filename);
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(result.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}