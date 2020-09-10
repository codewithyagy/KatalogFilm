package com.adeeva.movieworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private ImageView foto;
    private TextView nama, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        foto = findViewById(R.id.imageView);
        nama = findViewById(R.id.textView2);
        email =findViewById(R.id.email);
    }
}