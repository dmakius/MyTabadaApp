package com.example.mycameraapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
//    private static final String GrayScale ="$EFLlv!;,.";
    private static final String GrayScale ="@%#*+=-:.";
//    private static final String GrayScale ="$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/|()1{}[]?-+~<>i!lI;:,'^`/'._";
    private char[][] asciiMatrix;
    public static int index = 0;
    public final String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/MyCamera/";
    private static final int REQUEST_CODE =22;

    private String currentPhotoPath;

    Button btnpicture;
    ImageView imageView;
    TextView asciiText;
    private StringBuilder textToString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnpicture = findViewById(R.id.btncamera_id);
        imageView = findViewById(R.id.imageview1);
        asciiText = findViewById(R.id.asciiArt);
        btnpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                bwPhoto("/storage/emulated/0/Android/data/com.example.mycameraapp/files/Pictures/homer.jpg");
//                createAciiFile();

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "_";
                File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                try {
                    File imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
                    currentPhotoPath = imageFile.getAbsolutePath();
                    Uri imageUri = FileProvider.getUriForFile(MainActivity.this,
                            "com.example.mycameraapp.FileProvider", imageFile);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, REQUEST_CODE);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            bwPhoto(currentPhotoPath);
            createAciiFile();
            asciiText.setText(textToString);
        }else{
            Toast.makeText(this, "CANCELLED", Toast.LENGTH_SHORT);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void createAciiFile() {
        try{
            Toast.makeText(MainActivity.this, "ASCII building", Toast.LENGTH_SHORT).show();
            textToString = new StringBuilder();
            for(int row = asciiMatrix.length - 1; row > 0 ; row--){
                for(int col = 0; col < asciiMatrix[0].length; col++){
                    if(col == asciiMatrix[0].length -1){
                        textToString.append("\n");
                    }else{
                        textToString.append(asciiMatrix[row][col]);
                    }

                }
            }
            Toast.makeText(MainActivity.this, "ASCII Created", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bwPhoto(String currentPhotoPath) {
        Bitmap myBitmap = BitmapFactory.decodeFile(currentPhotoPath);
        myBitmap = myBitmap.copy(Bitmap.Config.ARGB_8888 , true);

        myBitmap = Bitmap.createScaledBitmap(myBitmap, 120, 120, false);
        Matrix matrix = new Matrix();

        matrix.postRotate(90);
        myBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);
        asciiMatrix = new char[myBitmap.getWidth()][myBitmap.getHeight()];

        for (int x = 0; x < myBitmap.getHeight(); x++) {
                for (int y = 0; y < myBitmap.getWidth(); y++) {
                    int pixel = myBitmap.getPixel(x,y);
                    int redValue = Color.red(pixel);
                    int blueValue = Color.blue(pixel);
                    int greenValue = Color.green(pixel);
                    int acsiiValue = (redValue+greenValue+blueValue)/3;
                    char ascii = toAscciArt(acsiiValue);
                    asciiMatrix[x][y] = ascii;
                    myBitmap.setPixel(x, y, Color.rgb(acsiiValue,acsiiValue,acsiiValue));
                }
            }
            imageView.setImageBitmap(myBitmap);
    }

    private char toAscciArt(int acsiiValue) {
        char ascii = GrayScale.charAt((int) Math.ceil((GrayScale.length() -1)* acsiiValue/255));
        return ascii;
    }
}