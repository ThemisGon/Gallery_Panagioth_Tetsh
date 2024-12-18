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

public class FifthPage extends AppCompatActivity {
    TextView name2;
    EditText sifnos1_text;
    SQLiteDatabase db;
    MyTts myTts;
    ImageView imageView4;
    //Δημιουργώ μια μεταβλητή για να ξέρω αμα μιλάει η όχι και να μπορώ με το πάτημα του κουμπιού να την απενεργοποιήσω
    boolean Speaker = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_page);
        sifnos1_text = findViewById(R.id.sifnos1_text);
        name2 = findViewById(R.id.name2);
        imageView4 = findViewById(R.id.imageView4);
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
        String data = sharedPreferences.getString("Sifnos","Δεν πέρασαν τα δεδομένα");
        //sifnos1_text.setText(data);
    }
     */
    //Εργασία Δεύτερη
    private void insertImagedata(){
        //Παίρνουμε τα δεδομένα απο την βάση
        Cursor cursor = db.rawQuery("SELECT * FROM Image_Data WHERE Name = ?", new String[]{"Σίφνος I"});
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
                    imageView4.setImageBitmap(bitmap);
                    sifnos1_text.setText(information);
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
        name2.setText(data);
    }
    public void TextSpeak(View view){
        if (Speaker == false){
            myTts.speak(sifnos1_text.getText().toString());
            Speaker = true;
        }else {
            myTts.stop();
            Speaker = false;
        }
    }

    //Έχει ένα κουμπί για να πάει στην επόμενη σελίδα
    public void Sinexia3(View view){
        myTts.shutdown();
       Intent intent=new Intent(FifthPage.this,Quiz1.class);
       startActivity(intent);
        // Apply smooth transition animations
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    //Έχει ένα κουμπί για να πάει στην προηγούμενη σελίδα
    public void Piso3(View view){
        myTts.shutdown();
        Intent intent=new Intent(FifthPage.this,FourthPage.class);
        startActivity(intent);
        // Apply smooth transition animations
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
