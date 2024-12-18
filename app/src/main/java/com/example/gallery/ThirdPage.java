package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdPage extends AppCompatActivity {

    TextView name4;
    SQLiteDatabase db;
    MyTts myTts;
    ImageView imageView2;
    EditText laikiagora_text;
    //Δημιουργώ μια μεταβλητή για να ξέρω αμα μιλάει η όχι και να μπορώ με το πάτημα του κουμπιού να την απενεργοποιήσω
    boolean Speaker = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_page);
        laikiagora_text =findViewById(R.id.laikiagora_text);
        name4 = findViewById(R.id.name4);
        imageView2 = findViewById(R.id.imageView2);
        //loadPreferences();
        Onoma();
        db = openOrCreateDatabase("Image_Data",MODE_PRIVATE,null);
        db.execSQL("Create table if not exists Image_Data(" +
                "Id integer primary key autoincrement,"+
                "Name Text UNIQUE," +
                "Information Text," +
                "Path Text)");
        insertImagedata();
        myTts= new MyTts(this);
    }
    //Εργασία πρώτη
    //Χρησιμοποιεί τα preferences για να φέρει τις πληροφορίες τις εικόνας
    /*
    private void loadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("Dedomena", MODE_PRIVATE);
        String data = sharedPreferences.getString("laikiagora","Δεν πέρασαν τα δεδομένα");
        laikiagora_text.setText(data);
    }
     */
    //Εργασία Δεύτερη
    private void insertImagedata(){
        //Παίρνουμε τα δεδομένα απο την βάση
        Cursor cursor = db.rawQuery("SELECT * FROM Image_Data WHERE Name = ?", new String[]{"Λαϊκή αγορά"});
        if (cursor.moveToFirst()) {
            // Προσπάθεια λήψης του index για τη στήλη "Information"
            int infoIndex = cursor.getColumnIndex("Information");
            int infoPath = cursor.getColumnIndex("Path");

            // Έλεγχος ότι η στήλη υπάρχει για να πάρουμε μετά τα δεδομένα
            if (infoIndex != -1 && infoPath != -1 ) {
                String information = cursor.getString(infoIndex);
                String imagePath  = cursor.getString(infoPath);

                // Παίρνουμε το resource ID από το όνομα του resource
                int resId = getResources().getIdentifier(imagePath, "drawable", getPackageName());
                if (resId != 0) {
                    // Φορτώνουμε την εικόνα με Bitmap από το resource ID
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
                    //Εμφανίζουμε την εικόνα και το κείμενο
                    imageView2.setImageBitmap(bitmap);
                    laikiagora_text.setText(information);
                }else {
                    Toast.makeText(this, "Η εικόνα δεν βρέθηκε", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Η στήλη Information δεν βρέθηκε!", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Δεν πέρασαν τα δεδομένα!", Toast.LENGTH_SHORT).show();
        }
        // Close the cursor when done to free up resources
        cursor.close();
    }

    //Χρησιμοποιεί τα preferences για να φέρει τις πληροφορίες για το όνομα του χρήστη
    private void Onoma(){
        SharedPreferences sharedPreferences = getSharedPreferences("Onom_user", MODE_PRIVATE);
        String data = sharedPreferences.getString("Onoma","Guest");
        name4.setText(data);
    }
    public void TextSpeak(View view){
        if (Speaker == false){
            myTts.speak(laikiagora_text.getText().toString());
            Speaker = true;
        }else {
            myTts.stop();
            Speaker = false;
        }
    }

    //Έχει ένα κουμπί για να πάει στην επόμενη σελίδα
    public void Sinexia1(View view){
        myTts.shutdown();
        Intent intent=new Intent(ThirdPage.this,FourthPage.class);
        startActivity(intent);
        // Apply smooth transition animations
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    //Έχει ένα κουμπί για να πάει στην προηγούμενη σελίδα
    public void Piso1(View view){
        myTts.shutdown();
        Intent intent=new Intent(ThirdPage.this,SecondPage.class);
        startActivity(intent);
        // Apply smooth transition animations
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}