package com.example.abjs.moviedb.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.abjs.moviedb.R;
// Front page to enter the application
public class MainActivity extends AppCompatActivity {
    ImageView img;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
//        img = (ImageView)findViewById(R.id.imgv);
        btn = (Button)findViewById(R.id.btnChangeImage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainPage.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
