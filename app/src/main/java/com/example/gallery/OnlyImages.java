package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class OnlyImages extends AppCompatActivity {

    Button button2,button;
    TextView Imagetxt,name5;
    SharedPreferences preferences;
    ImageView imageView5;
    private int timh = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_images);
        preferences = getSharedPreferences("Text_Images",MODE_PRIVATE);
        Imagetxt = findViewById(R.id.Imagetxt);
        imageView5 = findViewById(R.id.imageView5);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        name5 = findViewById(R.id.name5);
        Dedomena();
        loadimage();
        Onoma();
    }
    //Χρησιμοποιεί τα preferences για να φέρει τις πληροφορίες για το όνομα του χρήστη
    private void Onoma(){
        SharedPreferences sharedPreferences = getSharedPreferences("Onom_user", MODE_PRIVATE);
        String data = sharedPreferences.getString("Onoma","Guest");
        name5.setText(data);
    }
    //περνάει την πρώτη εικόνα
    private void loadimage(){
        SharedPreferences sharedPreferences = getSharedPreferences("Text_Images", MODE_PRIVATE);
        String data = sharedPreferences.getString("kleidi1","Δεν πέρασαν τα δεδομένα");
        Imagetxt.setText(data);
        imageView5.setImageResource(R.drawable.chair);
    }
    //Κρατάει σε ένα preference τα ονόματα της κάθε εικόνας
    private void Dedomena(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("kleidi1","Οι μπλέ καρέκλες II");
        editor.putString("kleidi2","Λαϊκή αγορά");
        editor.putString("kleidi3","Αθήνα IV");
        editor.putString("kleidi4","Σίφνος I");
        editor.apply();
    }
    //Είναι το κουμπί που με τοπάτημά του αλλάζει η κάθε εικόνα και πηγαίνει στην επόμενη απο αυτή
    //Χρησιμοποιούμε ενα πλήθος που έχει όνομα timh για να κρατήσουμε την θέση της κάθε εικόνας και όταν αυτό το πλήθος αυξάνεται αλλάζει και η εικόνα
    //η αλλαγή αυτή γίνεται επειδή έχουμε βάλει σε κάθε εικόνα έναν αριθμό και όταν έχουμε αυτόν τον αριθμό με το πλήθος αλλάζουμε την εικόνα
    public void next_image(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("Text_Images", MODE_PRIVATE);
        timh+=1;
        if (timh ==1){
            String data = sharedPreferences.getString("kleidi1","Δεν πέρασαν τα δεδομένα");
            Imagetxt.setText(data);
            imageView5.setImageResource(R.drawable.chair);
            //Οι παρακάτω εντολές έχουν γίνει γαι να τοποθετηθούν καλύτερα οι εικόνες στο UI, κρατάμε ένα σταθερό ύψος απο πάνω για την κάθε εικόνα
            //και ανάλογα την διαμορφώνουμε στον χώρο
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) imageView5.getLayoutParams();
            // Νέες τιμές για το πλάτος και το ύψος
            int newWidth = dpToPx(249); // Αυτή η τιμή θα αλλάξει το layout_width
            int newHeight = dpToPx(359); // Αυτή η τιμή θα αλλάξει το layout_height
            // Ρυθμίζουμε το νέο width και height
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            // Αντιγραφή του αρχικού marginTop και διατήρηση των υπόλοιπων margins
            int marginTop = layoutParams.topMargin;
            layoutParams.setMargins(layoutParams.leftMargin, dpToPx(124), layoutParams.rightMargin, layoutParams.bottomMargin);
            // Εφαρμογή των αλλαγών στο ImageView
            imageView5.setLayoutParams(layoutParams);
        } else if (timh==2) {
            String data = sharedPreferences.getString("kleidi2","Δεν πέρασαν τα δεδομένα");
            Imagetxt.setText(data);
            imageView5.setImageResource(R.drawable.la);
            // Παίρνουμε τα τρέχοντα LayoutParams για να διατηρήσουμε τα υπάρχοντα margins
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) imageView5.getLayoutParams();
            // Νέες τιμές για το πλάτος και το ύψος
            int newWidth = dpToPx(344); // Αυτή η τιμή θα αλλάξει το layout_width
            int newHeight = dpToPx(228); // Αυτή η τιμή θα αλλάξει το layout_height
            // Ρυθμίζουμε το νέο width και height
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            // Αντιγραφή του αρχικού marginTop και διατήρηση των υπόλοιπων margins
            int marginTop = layoutParams.topMargin;
            layoutParams.setMargins(layoutParams.leftMargin, dpToPx(124), layoutParams.rightMargin, layoutParams.bottomMargin);
            // Εφαρμογή των αλλαγών στο ImageView
            imageView5.setLayoutParams(layoutParams);

        } else if (timh==3) {
            String data = sharedPreferences.getString("kleidi3","Δεν πέρασαν τα δεδομένα");
            Imagetxt.setText(data);
            imageView5.setImageResource(R.drawable.athina4);
            // Παίρνουμε τα τρέχοντα LayoutParams για να διατηρήσουμε τα υπάρχοντα margins
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) imageView5.getLayoutParams();
            // Νέες τιμές για το πλάτος και το ύψος
            int newWidth = dpToPx(330);   // Αυτή η τιμή θα αλλάξει το layout_width
            int newHeight = dpToPx(306);  // Αυτή η τιμή θα αλλάξει το layout_height
            // Ρυθμίζουμε το νέο width και height
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            // Αντιγραφή του αρχικού marginTop και διατήρηση των υπόλοιπων margins
            int marginTop = layoutParams.topMargin;
            layoutParams.setMargins(layoutParams.leftMargin, dpToPx(124), layoutParams.rightMargin, layoutParams.bottomMargin);
            // Εφαρμογή των αλλαγών στο ImageView
            imageView5.setLayoutParams(layoutParams);
        }else if (timh==4){
            String data = sharedPreferences.getString("kleidi4","Δεν πέρασαν τα δεδομένα");
            Imagetxt.setText(data);
            imageView5.setImageResource(R.drawable.sifnos);
            // Παίρνουμε τα τρέχοντα LayoutParams για να διατηρήσουμε τα υπάρχοντα margins
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) imageView5.getLayoutParams();
            // Νέες τιμές για το πλάτος και το ύψος
            int newWidth = dpToPx(320); // Αυτή η τιμή θα αλλάξει το layout_width
            int newHeight = dpToPx(306); // Αυτή η τιμή θα αλλάξει το layout_height
            // Ρυθμίζουμε το νέο width και height
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            // Αντιγραφή του αρχικού marginTop και διατήρηση των υπόλοιπων margins
            int marginTop = layoutParams.topMargin;
            layoutParams.setMargins(layoutParams.leftMargin, dpToPx(124), layoutParams.rightMargin, layoutParams.bottomMargin);
            // Εφαρμογή των αλλαγών στο ImageView
            imageView5.setLayoutParams(layoutParams);
            //Επειδή δεν υπάρχει εικόνα με αριθμό 5, μηδενίζουμε το πλήθος για να αρχίσει απο την αρχή πάλι
            timh = 0;
        }
    }
    //Αυτό το κουμπί έχει ίδια λειτουργία με το προηγούμενο κουμπί απλός είναι σε μια σειρά οι εικόνες και δείχνει την προηγούμενη απο την εικόνα που είμαστε πάλι σύμφωνα με το πλήθος
    public void previous_image(View view){
        //Η προηγούμενη τις τελευταίας είναι η πρώτη οπότε πηγαίνουμε στην τέταρτη εικόνα
        if (timh ==1){
            timh = 4;
            SharedPreferences sharedPreferences = getSharedPreferences("Text_Images", MODE_PRIVATE);
            String data = sharedPreferences.getString("kleidi4","Δεν πέρασαν τα δεδομένα");
            Imagetxt.setText(data);
            imageView5.setImageResource(R.drawable.sifnos);
            // Παίρνουμε τα τρέχοντα LayoutParams για να διατηρήσουμε τα υπάρχοντα margins
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) imageView5.getLayoutParams();
            // Νέες τιμές για το πλάτος και το ύψος
            int newWidth = dpToPx(320); // Αυτή η τιμή θα αλλάξει το layout_width
            int newHeight = dpToPx(306); // Αυτή η τιμή θα αλλάξει το layout_height
            // Ρυθμίζουμε το νέο width και height
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            // Αντιγραφή του αρχικού marginTop και διατήρηση των υπόλοιπων margins
            int marginTop = layoutParams.topMargin;
            layoutParams.setMargins(layoutParams.leftMargin, dpToPx(124), layoutParams.rightMargin, layoutParams.bottomMargin);
            // Εφαρμογή των αλλαγών στο ImageView
            imageView5.setLayoutParams(layoutParams);
        } else if (timh==2) {
            timh = 1;
            SharedPreferences sharedPreferences = getSharedPreferences("Text_Images", MODE_PRIVATE);
            String data = sharedPreferences.getString("kleidi1","Δεν πέρασαν τα δεδομένα");
            Imagetxt.setText(data);
            imageView5.setImageResource(R.drawable.chair);
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) imageView5.getLayoutParams();
            // Νέες τιμές για το πλάτος και το ύψος
            int newWidth = dpToPx(249); // Αυτή η τιμή θα αλλάξει το layout_width
            int newHeight = dpToPx(359); // Αυτή η τιμή θα αλλάξει το layout_height
            // Ρυθμίζουμε το νέο width και height
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            // Αντιγραφή του αρχικού marginTop και διατήρηση των υπόλοιπων margins
            int marginTop = layoutParams.topMargin;
            layoutParams.setMargins(layoutParams.leftMargin, dpToPx(124), layoutParams.rightMargin, layoutParams.bottomMargin);
            // Εφαρμογή των αλλαγών στο ImageView
            imageView5.setLayoutParams(layoutParams);
        } else if (timh==3) {
            timh = 2;
            SharedPreferences sharedPreferences = getSharedPreferences("Text_Images", MODE_PRIVATE);
            String data = sharedPreferences.getString("kleidi2","Δεν πέρασαν τα δεδομένα");
            Imagetxt.setText(data);
            imageView5.setImageResource(R.drawable.la);
            // Παίρνουμε τα τρέχοντα LayoutParams για να διατηρήσουμε τα υπάρχοντα margins
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) imageView5.getLayoutParams();
            // Νέες τιμές για το πλάτος και το ύψος
            int newWidth = dpToPx(344); // Αυτή η τιμή θα αλλάξει το layout_width
            int newHeight = dpToPx(228); // Αυτή η τιμή θα αλλάξει το layout_height
            // Ρυθμίζουμε το νέο width και height
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            // Αντιγραφή του αρχικού marginTop και διατήρηση των υπόλοιπων margins
            int marginTop = layoutParams.topMargin;
            layoutParams.setMargins(layoutParams.leftMargin, dpToPx(124), layoutParams.rightMargin, layoutParams.bottomMargin);
            // Εφαρμογή των αλλαγών στο ImageView
            imageView5.setLayoutParams(layoutParams);
        }else{
            timh = 3;
            SharedPreferences sharedPreferences = getSharedPreferences("Text_Images", MODE_PRIVATE);
            String data = sharedPreferences.getString("kleidi3","Δεν πέρασαν τα δεδομένα");
            Imagetxt.setText(data);
            imageView5.setImageResource(R.drawable.athina4);
            // Παίρνουμε τα τρέχοντα LayoutParams για να διατηρήσουμε τα υπάρχοντα margins
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) imageView5.getLayoutParams();
            // Νέες τιμές για το πλάτος και το ύψος
            int newWidth = dpToPx(330);   // Αυτή η τιμή θα αλλάξει το layout_width
            int newHeight = dpToPx(306);  // Αυτή η τιμή θα αλλάξει το layout_height
            // Ρυθμίζουμε το νέο width και height
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            // Αντιγραφή του αρχικού marginTop και διατήρηση των υπόλοιπων margins
            int marginTop = layoutParams.topMargin;
            layoutParams.setMargins(layoutParams.leftMargin, dpToPx(124), layoutParams.rightMargin, layoutParams.bottomMargin);
            // Εφαρμογή των αλλαγών στο ImageView
            imageView5.setLayoutParams(layoutParams);
        }
    }
    // Μέθοδος για μετατροπή από dp σε pixels για το μέγεθος του Layout height και layout width
    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    //Έχει ένα κουμπί για να πάει στην σελίδα που αρχίζουν οι εικόνες και η κάθε μια έχει τις πληροφορίες της
    public void go_plhr(View view){
        Intent intent=new Intent(OnlyImages.this,SecondPage.class);
        startActivity(intent);
        // Apply smooth transition animations
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}