package com.example.covidsymptoms;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button continueBtn;
    EditText userName;
    private static int VIDEO_REQUEST =101;
    private static int REQUEST_PERMISSION =1;
    private static int READ_PERMISSION = 102;
    private static int WRITE_PERMISSION = 103;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        redirectToMainPage();
    }
    public void redirectToMainPage(){
        continueBtn = findViewById(R.id.continue_button);
        userName = findViewById(R.id.name);
        String name = userName.getText().toString();
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mainpage.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
}