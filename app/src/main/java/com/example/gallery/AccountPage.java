package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AccountPage extends AppCompatActivity {

    EditText user_txt,pass_txt;
    SQLiteDatabase db;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);
        user_txt =findViewById(R.id.user_txt);
        pass_txt =findViewById(R.id.pass_txt);
        //Βάση Δεδομένων που θα κρατάει το όνομα τον κωδικό και το σχόλιο ενός χρήστη
        db = openOrCreateDatabase("Stoixeia",MODE_PRIVATE,null);
        db.execSQL("Create table if not exists Xrhstes(" +
                "Id integer primary key autoincrement,"+
                "Username Text UNIQUE," +
                "Password Text," +
                "Sxolio Text)");
        preferences = getSharedPreferences("Onom_user",MODE_PRIVATE);

    }

    //Κουμπί που άμα πατήσει Σύνδεση θα ελέγξει αν δεν είναι κενά τα πεδία ,μετα αν υπάρχει ο χρήστης.
    public void Synd(View view){

        if (!user_txt.getText().toString().isEmpty()
        &&!pass_txt.getText().toString().isEmpty()){
            String User = user_txt.getText().toString();
            String pass = pass_txt.getText().toString();
            Cursor cursor = db.rawQuery("Select * from Xrhstes Where Username =? AND Password = ?", new String[]{User,pass});
            // Έλεγχος αν υπάρχει τουλάχιστον ένα αποτέλεσμα (δηλ. ο χρήστης βρέθηκε)
            if (cursor.moveToFirst()) {
                // Σύνδεση επιτυχής
                Toast.makeText(this, "Συνδεθήκατε επιτυχώς!", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Onoma",User);
                editor.apply();
                Intent intent=new Intent(AccountPage.this,SecondPage.class);
                startActivity(intent);
                // Apply smooth transition animations
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } else {
                // Ο χρήστης ή ο κωδικός είναι λάθος
                Toast.makeText(this, "Λάθος όνομα χρήστη ή κωδικός!", Toast.LENGTH_SHORT).show();
            }
            // Κλείσιμο του cursor
            cursor.close();

        }else{
            Toast.makeText(this,"Δεν έχετε συμπληρώσει όλα τα στοιχεία!",Toast.LENGTH_SHORT).show();
        }
    }
    //Όταν πατηθεί το κουμπί για την δημιουργία λογαριασμού ελέγχει αν ο χρήστης έχει συμπληρώσει τα πεδία, αν υπάρχει ήδη λογαριασμός με αυτά τα στοιχεία
    //και αν ο χρήστης βάλει σαν όνομα την τιμή Guest, την οποία δεν τον αφήνει μιας και είναι η Default τιμη για εναν επισκέπτη.
    public void Dhm(View view){
        if (!user_txt.getText().toString().isEmpty() &&!pass_txt.getText().toString().isEmpty()){
            String User = user_txt.getText().toString();
            String pass = pass_txt.getText().toString();
            Cursor cursor = db.rawQuery("Select * from Xrhstes Where Username =? AND Password = ?", new String[]{User,pass});
            // Έλεγχος αν υπάρχει τουλάχιστον ένα αποτέλεσμα (δηλ. ο χρήστης βρέθηκε άρα να μην δημιουργήσει λογαριασμό)
            if (cursor.moveToFirst()) {
                // Σύνδεση επιτυχής
                Toast.makeText(this, "Υπάρχει ήδη χρήστης με αυτά τα στοιχεία!", Toast.LENGTH_SHORT).show();
            } else {
                if (User.equals("Guest")){
                    Toast.makeText(this, "Δεν μπορείτε να πραγματοποιήσετε λογαριασμό με το όνομα Guest!", Toast.LENGTH_SHORT).show();
                }else {
                db.execSQL("Insert into Xrhstes(Username,Password,Sxolio)values(?,?,?)",
                        new Object[]{User,pass,""});
                // Εμφάνιση μηνύματος επιτυχίας
                Toast.makeText(this, "Ο λογαριασμός δημιουργήθηκε επιτυχώς!", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Onoma",User);
                editor.apply();
                Intent intent=new Intent(AccountPage.this,SecondPage.class);
                startActivity(intent);
                // Apply smooth transition animations
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
            // Κλείσιμο του cursor
            cursor.close();
            }else{
            Toast.makeText(this,"Δεν έχετε συμπληρώσει όλα τα στοιχεία!",Toast.LENGTH_SHORT).show();
        }
    }
}