package com.example.currentaffairs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Upload extends AppCompatActivity {

    ImageView image_view;
    Button create_news;
    EditText title, description;
    byte[] imageData;
    NewsDatabase newsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        create_news = (Button) findViewById(R.id.create_news);
        newsDatabase = new NewsDatabase(Upload.this);
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        image_view = (ImageView) findViewById(R.id.image_view);

        image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imageIntent.setType("image/*");
                startActivityForResult(imageIntent, 1);
            }
        });

        create_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase(title, description);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null){

            Uri selected = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selected);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                imageData = byteArrayOutputStream.toByteArray();
                image_view.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Integer newImage = R.drawable.download_3;
            Bitmap newBitmap = BitmapFactory.decodeFile(newImage.toString());
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            newBitmap.compress(Bitmap.CompressFormat.JPEG, 0, byteArray);
            imageData = byteArray.toByteArray();
            image_view.setImageBitmap(newBitmap);
        }
    }

    public void saveToDatabase(EditText nTitle, EditText nDescription){
        String newsTitle = nTitle.getText().toString();
        String newsDescription = nDescription.getText().toString();
        if (!newsTitle.isEmpty() && !newsDescription.isEmpty()){
            newsDatabase.insertNews(newsTitle, newsDescription, imageData);
            Toast.makeText(getApplicationContext(), "Successfully uploaded the news", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getApplicationContext(), "Failed to upload the news", Toast.LENGTH_SHORT).show();
        }
    }
}