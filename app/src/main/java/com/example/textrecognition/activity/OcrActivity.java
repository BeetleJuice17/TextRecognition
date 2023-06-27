package com.example.textrecognition.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import com.example.textrecognition.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;

public class OcrActivity extends AppCompatActivity {
    private Bitmap yourBitmap;
    private String imagePath;

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        imageView = findViewById(R.id.imageView);
    }

    private String saveImage(Bitmap image) {
        String savedImagePath = null;

        String imageFileName = "JPEG_" + System.currentTimeMillis() + ".jpg";
        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/YourAppName");
        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }

        if (success) {
            File imageFile = new File(storageDir, imageFileName);
            savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return savedImagePath;
    }

    @Override
    protected void onPause() {
        super.onPause();
        String imagePath = saveImage(yourBitmap);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        File file = new File(imagePath);
        file.delete();
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