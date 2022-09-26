package com.example.covidsymptoms;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class Mainpage extends AppCompatActivity {
    Button symptomsButton, respiratory_btn, heartRate_btn,uploadsignsbutton;
    String user_name = "vinodh";
    int differenceThreashhold = 12;
    int indexFrame = 0;
    int totalframes = 0;
    int outputHeartRate = 0;
    ArrayList<Float> bloodcolors = new ArrayList<Float>();
    private static int VIDEO_REQUEST = 101;
    private static int REQUEST_PERMISSION = 1;
    private static int READ_PERMISSION = 102;
    private static int WRITE_PERMISSION = 103;
    private Uri videoUri;
    Intent videoIntent;
    VideoView videoView;
    TextView respiratoryTextView;
    int HR;
    int RESR;
    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mainpage);
        respiratoryTextView = (TextView) findViewById(R.id.respiratoryTextView);
        videoView = findViewById(R.id.videoView);
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            user_name = "";
        } else {
            user_name = extras.getString("name");
        }
        symptomsButton = findViewById(R.id.symptomsButton);
        respiratory_btn = findViewById(R.id.respiratoryBtn);

        buttonClick();
        measuringRespiratoryRate();

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                String s = String.valueOf(result.getResultCode());
                if (result.getResultCode() == -1) {
                    Uri VideoUri = result.getData().getData();
                    String sampleuri = VideoUri.toString();
                    videoUri = Uri.parse(sampleuri);
                    redirectToVideo();
                    HeartRateCalculatorTask HRcal = new HeartRateCalculatorTask();
                    HRcal.execute(videoUri);
                }
            }
        });
        heartRate_btn = findViewById(R.id.heartRateBtn);
        heartRate_btn.setOnClickListener(new View.OnClickListener() {
            //@RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View v) {
                videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                videoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
                videoIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                videoIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                activityResultLauncher.launch(videoIntent);
            }
        });
        uploadsignsbutton=findViewById(R.id.uploadsign);
        uploadsignsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseLoader database= new DatabaseLoader(getApplicationContext());
                DataValues readings= new DataValues(HR,RESR,0,0,0,0,0,0,0,0,0,0,user_name);
                if(database.onInsert(readings)== true){
                    Toast.makeText(getApplicationContext(), "Data updated",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(bReceiver, new IntentFilter("message"));
    }

    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bReceiver);
    }

    public void buttonClick() {
        symptomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent symptomsIntent = new Intent(getApplicationContext(), SymptomsPage.class);
                Bundle b = new Bundle();
                String s1[]= new String[3];
                s1[0] = "vinodh";
                s1[1] = String.valueOf(HR);
                s1[2] = String.valueOf(RESR);
                symptomsIntent.putExtra("val", s1);
                startActivity(symptomsIntent);
            }
        });
    }

    public void measuringRespiratoryRate() {
        respiratory_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent respiratoryIntentService = new Intent(getApplicationContext(), RespiratoryRate.class);
                startService(respiratoryIntentService);
            }
        });
    }
    public void redirectToVideo() {
        videoView.setVideoURI(videoUri);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();
    }

    class HeartRateCalculatorTask extends AsyncTask<Uri, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.R)
        @Override
        protected Void doInBackground(Uri... uris) {
            MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
            try {
                metaRetriever.setDataSource(getApplicationContext(), videoUri);
            } catch (Exception e) {
                e.printStackTrace();
            }
            MediaPlayer forTime = MediaPlayer.create(getBaseContext(), videoUri);
            int videoDuration = forTime.getDuration();
            int processFramesPerSec = 12;
            int processtime = 100000;
            totalframes = (int) Math.floor(videoDuration / 1000) * processFramesPerSec;
            outputHeartRate = 1;
            indexFrame = 1;
            while (indexFrame < totalframes) {
                float currentColor = 0f;
                Bitmap currentFrameBitmap = metaRetriever.getFrameAtTime(processtime, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                processtime = processtime + 100000;
                Log.d("", "Processing frame number" + processtime);
                int i = 450;
                while (i <= 550) {
                    int j = 900;
                    while (j < 1200) {
                        currentColor = currentColor + Color.red(currentFrameBitmap.getPixel(i, j));
                        j++;
                    }
                    i++;
                }

                float previousColor = 1f;
                boolean isArrayListEmpty = (bloodcolors.size() != 0);
                if (isArrayListEmpty != false) {
                    int currentSize = bloodcolors.size();
                    previousColor = bloodcolors.get(currentSize - 1);
                }

                boolean isCountable = Math.abs(previousColor - currentColor) > differenceThreashhold;
                if (isCountable == true) {
                    outputHeartRate++;
                }
                bloodcolors.add(currentColor);

                Log.d("", "Processing frame number" + processtime);
                indexFrame++;

            }

            metaRetriever.release();
            Log.d("", "Sending heart rate" + outputHeartRate);

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView heartRateValue = findViewById(R.id.heartRateView);
            heartRateValue.setText("HeartRate Value : " + String.valueOf(outputHeartRate));

            HR = outputHeartRate;
        }
    }

    private BroadcastReceiver bReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String str = (String) intent.getExtras().get("success").toString();
            respiratoryTextView.setText("Respiratory rate is : " +str);
            RESR = Integer.parseInt(str);
        }
    };
    public DataValues uploadData(){
        DatabaseLoader databaseAction = new DatabaseLoader(getApplicationContext());
        DataValues reading = new DataValues(outputHeartRate,0,0,0,0,0,0,0,0,0,0,0,user_name);
        if(databaseAction.onInsert(reading)== true){
            Toast.makeText(getApplicationContext(), "Data updated",Toast.LENGTH_LONG).show();
        }
        return reading;
    }
}
