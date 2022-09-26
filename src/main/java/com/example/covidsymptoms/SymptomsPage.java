package com.example.covidsymptoms;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SymptomsPage extends AppCompatActivity {


    float HEADACHE=0.0f;
    float DIARRHEA=0.0f;
    float SORE_THROAT=0.0f;
    float FEVER=0.0f;
    float MUSCLE_ACHE=0.0f;
    float LOSS_SMELL_TASTE=0.0f;
    float COUGH=0.0f;
    float SHORTNESS_BREATH=0.0f;
    float FEELING_TIRED=0.0f;
    float NAUSEA=0.0f;


    int HEART_RATE=0;
    int RESPIRATORY_RATE=0;
    Spinner symptoms_dropdown_spinner;
    RatingBar ratingBar;
    Button upload_symptoms_btn,updatevalue;
    String selectedSymptom = "";
    float selectedRating =0.0f;
    String user_name="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getIntent().getExtras();
        String[] array=b.getStringArray("val");
        HEART_RATE=Integer.parseInt(array[1]);
        RESPIRATORY_RATE=Integer.parseInt(array[2]);
        user_name = "vinodh";
        setContentView(R.layout.activity_symptoms_page);
        symptoms_dropdown_spinner = findViewById(R.id.symtpoms_dropdown);
        ratingBar = findViewById(R.id.symptom_level_rating);
        ArrayAdapter<String> spinner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.symptoms_array));
        spinner.setDropDownViewResource(android.R.layout.simple_list_item_1);
        symptoms_dropdown_spinner.setAdapter(spinner);
        updatevalue=findViewById(R.id.updatevaluebutton);
        updateSymptom();
        uploadSymptoms();
        ChangeSymptom();
    }
    public void updateSymptom(){
        updatevalue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                selectedRating = ratingBar.getRating();
                String select_sym= selectedSymptom;
                switch (select_sym){
                    case "HEADACHE":
                        HEADACHE=(float)selectedRating;
                        break;
                    case "DIARRHEA":
                        DIARRHEA=(float)selectedRating;
                        break;
                    case "SORE_THROAT":
                        SORE_THROAT=(float)selectedRating;
                        break;
                    case "FEVER":
                        FEVER=(float)selectedRating;
                        break;
                    case "MUSCLE_ACHE":
                        MUSCLE_ACHE=(float)selectedRating;
                        break;
                    case "LOSS_SMELL_TASTE":
                        LOSS_SMELL_TASTE=(float)selectedRating;
                        break;
                    case "COUGH":
                        COUGH=(float)selectedRating;
                        break;
                    case "SHORTNESS_BREATH":
                        SHORTNESS_BREATH=(float)selectedRating;
                        break;
                    case "FEELING_TIRED":
                        FEELING_TIRED=(float)selectedRating;
                        break;
                    case "NAUSEA":
                        NAUSEA=(float)selectedRating;
                        break;
                }
            }
        });

    }
    public void ChangeSymptom(){
        symptoms_dropdown_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ratingBar.setRating(0);
                selectedSymptom = parent.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    public void uploadSymptoms(){
        upload_symptoms_btn = findViewById(R.id.upload_symptoms);
        upload_symptoms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRating = ratingBar.getRating();
                DatabaseLoader databaseAction = new DatabaseLoader(getApplicationContext());
                DataValues readings = uploadData();
                if(databaseAction.onInsert(readings)){
                    Toast.makeText(getApplicationContext(), "Data updated",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public DataValues uploadData(){
        String currentRatingKey = selectedSymptom;
        DataValues reading = new DataValues(HEART_RATE,RESPIRATORY_RATE,HEADACHE,DIARRHEA,SORE_THROAT,FEVER,MUSCLE_ACHE,LOSS_SMELL_TASTE,COUGH,SHORTNESS_BREATH,FEELING_TIRED,NAUSEA,user_name);
        return reading;
    }

}
