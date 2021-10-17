package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        String picUrl="https://images.pexels.com/photos/1591447/pexels-photo-1591447.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";
        new imageLoadingTask().execute(picUrl);
    }
    public class imageLoadingTask extends AsyncTask<String,Void,Bitmap>{
                                                   // can use any type of value int ,string ,void,bitmap

        @Override
        protected void onPreExecute() {
            //main thread
            //start
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            //background thread
            //process thread
            String picurl= strings[0];
            try {
                URL url = new URL(picurl);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream= httpURLConnection.getInputStream();
                httpURLConnection.connect();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //main thread
            //end
            imageView.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }
    }
}