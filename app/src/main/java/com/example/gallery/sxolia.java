package com.example.gallery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class sxolia extends AppCompatActivity {

    TextView name7, score1;
    SQLiteDatabase db;
    EditText sxolio_xrhsth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sxolia);
        name7 = findViewById(R.id.name7);
        score1 = findViewById(R.id.score1);
        sxolio_xrhsth = findViewById(R.id.sxolio_xrhsth);
        // Δημιουργία της βάσης δεδομένων και του πίνακα αν δεν υπάρχει
        db = openOrCreateDatabase("Stoixeia", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Xrhstes (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT UNIQUE," +
                "Password TEXT," +
                "Sxolio TEXT)");

        Onoma();
        Score();
    }
    //Χρησιμοποιεί τα preferences για να φέρει τις πληροφορίες για το όνομα του χρήστη
    private void Onoma() {
        SharedPreferences sharedPreferences = getSharedPreferences("Onom_user", MODE_PRIVATE);
        String data = sharedPreferences.getString("Onoma", "Guest");
        name7.setText(data);
    }
    //Χρησιμοποιεί τα preferences για να φέρει τις πληροφορίες για το score του χρήστη
    public void Score() {
        SharedPreferences sharedPreferences = getSharedPreferences("score_user", MODE_PRIVATE);
        String data = sharedPreferences.getString("score", "(0/0)");
        score1.setText(data);
    }

    //Σε περίπτωση που ο χρήστης είναι συνδεδεμένος και γράψει σχόλιο ανανεώνει το σχόλιο το οποίο μπορεί να μην είχε τίποτα(πρώτη φορά στην εφαρμογή), ή να είχε κάτι άλλο και να το αλλάξει
    //σε περίπτωση που ξανα δεί την εφαρμογή και θέλει να αλλάξει το σχόλιό του
    public void updateUserComment(String username, String sxolio) {
        if (!sxolio_xrhsth.getText().toString().isEmpty()) {
            // Δημιουργία ή Ενημέρωση χρήστη με βάση το username
            SQLiteDatabase db = openOrCreateDatabase("Stoixeia", MODE_PRIVATE, null);

            // Έλεγχος αν υπάρχει το username
            Cursor cursor = db.rawQuery("SELECT * FROM Xrhstes WHERE Username = ?", new String[]{username});

            //Αν είναι Guest επειδή δεν έχει λογααριασμό με κώδικό αλλα μας ενδιαφέρουν τα σχολιά του το αποθηκέυει ανεξαρτήτος αν υπάρχει η όχι γιατι μπορούν να μπουν πολλα και διαφοερετικά άτομα
            //στην γκαλερί που να μην θέλουν να που το ίδιο πράγμα και για αυτό για τους Guest αποθηκεύεται έτσι
            if (cursor.getCount() > 0 && (!name7.getText().toString().equals("Guest"))) {
                // Αν υπάρχει, κάνουμε UPDATE το Sxolio
                db.execSQL("UPDATE Xrhstes SET Sxolio = ? WHERE Username = ?", new Object[]{sxolio, username});
            } else {
                // Αν δεν υπάρχει, κάνουμε INSERT το Username και το Sxolio
                db.execSQL("INSERT INTO Xrhstes (Username, Password, Sxolio) VALUES (?, '', ?)", new Object[]{username, sxolio});
            }
            cursor.close();
            db.close();
        }
    }

    //Εδώ ελέγχει αν το κείμενο είναι κενό η όχι για την αποθήκευση των σχολίων, σε περίπτωση που δεν είναι αλλάζει το σχόλιο του σε περίπτωηση που είναι συνδεδεμένος
    //αν είναι κενό δεν αποθηκεύει τίποτα.
    //Δημιουργεί ένα builder για να εμφανίσει τα σχόλια που έχουν αποθηκευτεί.
    public void Comments(View view) {
        if (!sxolio_xrhsth.getText().toString().isEmpty()) {
            updateUserComment(name7.getText().toString(), sxolio_xrhsth.getText().toString());
        }
        Cursor cursor = db.rawQuery("SELECT Username, Sxolio FROM Xrhstes", null);
        StringBuilder builder = new StringBuilder();
        while (cursor.moveToNext()) {
            builder.append("Όνομα: ").append(cursor.getString(0)).append("\n"); // getString(1) -> Username
            builder.append("Σχόλιο: ").append(cursor.getString(1)).append("\n"); // getString(3) -> Sxolio
            builder.append("-----------------------------------------------------\n");
        }
        //
        // builder.setLength(0); //Διαγραφή του builder
        showMessage("Διαθέσιμα σχόλια", builder.toString());
    }

    private void showMessage(String title, String info) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(info)
                .show();
    }
    //Είναι η λειτουργία του κουμπιού που μας οδηγεί στην παρουσίαση των εικόνων χωρίς πληροφορίες
    //Χρησιμοποιεί την λειτουργία updateUserComment για να αποθηκεύεσει τα σχόλια αν έχει γράψει κάτι στο κείμενο ο χρήστης.
    public void image(View view) {
        updateUserComment(name7.getText().toString(), sxolio_xrhsth.getText().toString());
        Intent intent = new Intent(sxolia.this, OnlyImages.class);
        startActivity(intent);
        // Apply smooth transition animations
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    //Είναι η λειτουργία του κουμπιού που μας οδηγεί στην παρουσίαση των εικόνων με πληροφορίες
    //Χρησιμοποιεί την λειτουργία updateUserComment για να αποθηκεύεσει τα σχόλια αν έχει γράψει κάτι στο κείμενο ο χρήστης.
    public void image_info(View view) {
        updateUserComment(name7.getText().toString(), sxolio_xrhsth.getText().toString());
        Intent intent = new Intent(sxolia.this, SecondPage.class);
        startActivity(intent);
        // Apply smooth transition animations
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
