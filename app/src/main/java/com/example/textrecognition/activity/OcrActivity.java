package com.example.textrecognition.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
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

    private void shareFileViaVK() {
        String filePath = "path_to_your_file";
        File file = new File(filePath);
        Uri fileUri = FileProvider.getUriForFile(this, "com.your.package.name.fileprovider", file);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("file/*");
        intent.putExtra(Intent.EXTRA_STREAM, fileUri);
        intent.setPackage("com.vkontakte.android");
        startActivity(Intent.createChooser(intent, "Share via"));
    }

    private void shareFileViaWhatsApp() {
        String filePath = "path_to_your_file";
        File file = new File(filePath);
        Uri fileUri = FileProvider.getUriForFile(this, "com.your.package.name.fileprovider", file);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("file/*");
        intent.putExtra(Intent.EXTRA_STREAM, fileUri);
        intent.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(intent, "Share via"));
    }

    private void shareFileViaTelegram() {
        String filePath = "path_to_your_file";
        File file = new File(filePath);
        Uri fileUri = FileProvider.getUriForFile(this, "com.your.package.name.fileprovider", file);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("file/*");
        intent.putExtra(Intent.EXTRA_STREAM, fileUri);
        intent.setPackage("org.telegram.messenger");
        startActivity(Intent.createChooser(intent, "Share via"));
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