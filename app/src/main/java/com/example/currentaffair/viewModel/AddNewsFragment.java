package com.example.currentaffair.viewModel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.currentaffair.NewsDatabase;
import com.example.currentaffair.R;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class AddNewsFragment extends Fragment {
    NewsDatabase database;
    ImageView image_view;
    Button create_news;
    EditText title, description;
    byte[] imageData;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_upload, container, false);

        create_news = (Button) view.findViewById(R.id.create_news);
        database = new NewsDatabase(getActivity());
        title = (EditText) view.findViewById(R.id.title);
        description = (EditText) view.findViewById(R.id.description);
        image_view = (ImageView) view.findViewById(R.id.image_view);

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
                checkIfImageIsNull();
            }
        });
        return view;
    }



    public void saveToDatabase(EditText nTitle, EditText nDescription, byte[] imgByte){
        String newsTitle = nTitle.getText().toString();
        String newsDescription = nDescription.getText().toString();
        if (!newsTitle.isEmpty() && !newsDescription.isEmpty()){
            database.insertNews(getDate(), newsTitle, newsDescription, imgByte);
            Toast.makeText(getActivity(), "Successfully uploaded the news", Toast.LENGTH_SHORT).show();
            title.setText("");
            description.setText("");

        }else {
            Toast.makeText(getActivity(), "Failed to upload the news", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkIfImageIsNull(){
        if (imageData == null){
            Integer newImage = R.drawable.download_3;
            Bitmap newBitmap = BitmapFactory.decodeFile(newImage.toString());
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArray);
            imageData = byteArray.toByteArray();
            saveToDatabase(title, description, imageData);
        }else {
            saveToDatabase(title, description, imageData);
        }
    }

    public  String getDate(){
        Date objDate = new Date();
        String strDateFormat = "MMM-dd-yyyy hh:mm:ss a"; //Date format is Specified
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); //Date format string is passed as an argument to the Date format object
        String current_time = objSDF.format(objDate).toString();

        return current_time;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null){

            Uri selected = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),selected);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                imageData = byteArrayOutputStream.toByteArray();
                image_view.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {

        }
    }
}
