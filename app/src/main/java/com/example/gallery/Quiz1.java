package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Quiz1 extends AppCompatActivity {

    TextView name6,score;
    ImageView eik_quiz;
    Button la,mple,athina,sif,Sinexia;
    private int timh = 1;
    private int pontoi =0;
    MyTts myTts;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        la = findViewById(R.id.la);
        mple = findViewById(R.id.mple);
        athina = findViewById(R.id.athina);
        sif = findViewById(R.id.sif);
        eik_quiz = findViewById(R.id.eik_quiz);
        name6 = findViewById(R.id.name6);
        score = findViewById(R.id.score);
        Sinexia = findViewById(R.id.Sinexia);
        preferences = getSharedPreferences("score_user",MODE_PRIVATE);
        myTts= new MyTts(this);
        Epom_eik();
        Onoma();
    }
    //Χρησιμοποιεί τα preferences για να φέρει τις πληροφορίες για το όνομα του χρήστη
    private void Onoma(){
        SharedPreferences sharedPreferences = getSharedPreferences("Onom_user", MODE_PRIVATE);
        String data = sharedPreferences.getString("Onoma","Guest");
        name6.setText(data);
    }
    //Με παρόμοιο σχεδιασμό έχουμε ένα πλήθος που δείχνει με μια σειρά κάποιες και μόλις ο χρήστης πατήσει ένα κουμπι που έχει μια απάντηση δίπλα
    //θα του δείξει αν την βρήκε σωστά μεγαλώνοντας ένα πλήθος στο κάτω μέρος της οθόνης, ακόμη με το πάτημα του κουμπιού αλλάζει την εικόνα
    //μόλις φτάσει στην τελευταία εικόνα εμφανίζει ένα κουμπί για να συνεχίσει και απενεργοποιεί όλα τα κοθμπία που είχαν τις απαντήσεις ώστε να μην μπορεί ανα τα πατήσει
    //και εδώ χρησιμοποιούνται μεταβλητές για το layout height και layout width
    public void Epom_eik(){
        if (timh ==1){
            eik_quiz.setImageResource(R.drawable.la);
            // Παίρνουμε τα τρέχοντα LayoutParams για να διατηρήσουμε τα υπάρχοντα margins
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) eik_quiz.getLayoutParams();
            // Νέες τιμές για το πλάτος και το ύψος
            int newWidth = dpToPx(356); // Αυτή η τιμή θα αλλάξει το layout_width
            int newHeight = dpToPx(216); // Αυτή η τιμή θα αλλάξει το layout_height
            // Ρυθμίζουμε το νέο width και height
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            // Αντιγραφή του αρχικού marginTop και διατήρηση των υπόλοιπων margins
            int marginTop = layoutParams.topMargin;
            layoutParams.setMargins(layoutParams.leftMargin, dpToPx(236), layoutParams.rightMargin, layoutParams.bottomMargin);
            // Εφαρμογή των αλλαγών στο ImageView
            eik_quiz.setLayoutParams(layoutParams);
        } else if (timh==2) {
            eik_quiz.setImageResource(R.drawable.chair);
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) eik_quiz.getLayoutParams();
            // Νέες τιμές για το πλάτος και το ύψος
            int newWidth = dpToPx(249); // Αυτή η τιμή θα αλλάξει το layout_width
            int newHeight = dpToPx(289); // Αυτή η τιμή θα αλλάξει το layout_height
            // Ρυθμίζουμε το νέο width και height
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            // Αντιγραφή του αρχικού marginTop και διατήρηση των υπόλοιπων margins
            int marginTop = layoutParams.topMargin;
            layoutParams.setMargins(layoutParams.leftMargin, dpToPx(208), layoutParams.rightMargin, layoutParams.bottomMargin);
            // Εφαρμογή των αλλαγών στο ImageView
            eik_quiz.setLayoutParams(layoutParams);
        } else if (timh==3) {
            eik_quiz.setImageResource(R.drawable.sifnos);
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) eik_quiz.getLayoutParams();
            // Νέες τιμές για το πλάτος και το ύψος
            int newWidth = dpToPx(259); // Αυτή η τιμή θα αλλάξει το layout_width
            int newHeight = dpToPx(279); // Αυτή η τιμή θα αλλάξει το layout_height
            // Ρυθμίζουμε το νέο width και height
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            // Αντιγραφή του αρχικού marginTop και διατήρηση των υπόλοιπων margins
            int marginTop = layoutParams.topMargin;
            layoutParams.setMargins(layoutParams.leftMargin, dpToPx(190), layoutParams.rightMargin, layoutParams.bottomMargin);
            // Εφαρμογή των αλλαγών στο ImageView
            eik_quiz.setLayoutParams(layoutParams);
        }else if (timh==4){
            eik_quiz.setImageResource(R.drawable.athina4);
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) eik_quiz.getLayoutParams();
            // Νέες τιμές για το πλάτος και το ύψος
            int newWidth = dpToPx(259); // Αυτή η τιμή θα αλλάξει το layout_width
            int newHeight = dpToPx(279); // Αυτή η τιμή θα αλλάξει το layout_height
            // Ρυθμίζουμε το νέο width και height
            layoutParams.width = newWidth;
            layoutParams.height = newHeight;
            // Αντιγραφή του αρχικού marginTop και διατήρηση των υπόλοιπων margins
            int marginTop = layoutParams.topMargin;
            layoutParams.setMargins(layoutParams.leftMargin, dpToPx(190), layoutParams.rightMargin, layoutParams.bottomMargin);
            // Εφαρμογή των αλλαγών στο ImageView
            eik_quiz.setLayoutParams(layoutParams);
        }else{
            timh=0;
            la.setClickable(false);
            mple.setClickable(false);
            sif.setClickable(false);
            athina.setClickable(false);
            //Εμφανίζει το κουμπί για τνα συνεχίσει ο χρήστης στην εφαρμογή
            Sinexia.setVisibility(View.VISIBLE);
            //Μεταφέρει με τα preferences πληροφορίες για το score του χρήστη
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("score", score.getText().toString());
            editor.apply();
        }
    }
    //Εδώ βλέπουμε αν το πλήθος είνα ιίσο με έναν αριθμό που είναι η σωστή απάντηση, αν είναι ανεβάζει το πλήθος των πόντων κατα 1
    public void mple_ep(View view){
        if (timh == 2) {
            pontoi+=1;
        }
        timh+=1;
        score.setText("("+pontoi+"/"+(timh-1)+")");
        Epom_eik();
    }
    //Εδώ βλέπουμε αν το πλήθος είνα ιίσο με έναν αριθμό που είναι η σωστή απάντηση, αν είναι ανεβάζει το πλήθος των πόντων κατα 1
    public void la_ep(View view){
        if (timh == 1) {
            pontoi+=1;
        }
        timh+=1;
        score.setText("("+pontoi+"/"+(timh-1)+")");
        Epom_eik();
    }
    //Εδώ βλέπουμε αν το πλήθος είνα ιίσο με έναν αριθμό που είναι η σωστή απάντηση, αν είναι ανεβάζει το πλήθος των πόντων κατα 1
    public void sif_ep(View view){
        if (timh == 3) {
            pontoi+=1;
        }
        timh+=1;
        score.setText("("+pontoi+"/"+(timh-1)+")");
        Epom_eik();
    }
    //Εδώ βλέπουμε αν το πλήθος είνα ιίσο με έναν αριθμό που είναι η σωστή απάντηση, αν είναι ανεβάζει το πλήθος των πόντων κατα 1
    public void athina_ep(View view){
        if (timh == 4) {
            pontoi+=1;
        }
        timh+=1;
        score.setText("("+pontoi+"/"+(timh-1)+")");
        Epom_eik();
    }
    // Μέθοδος για μετατροπή από dp σε pixels για το μέγεθος του Layout height και layout width
    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
    //Συνεχίζει στην επόμενη σελίδα
    public void Cont5(View view){
        if (pontoi==0){
            myTts.speak("Κάποιος δεν πρόσεχε στην παρουσίαση");
        } else if (pontoi==1) {
            myTts.speak("Στην τύχη αν τα έβαζες, θα έπιανες περισσότερα");
        }else if (pontoi==2) {
            myTts.speak("Δέν πειράζει, θα τα πάς καλύτερα την επόμενη φορά");
        }else if (pontoi==3) {
            myTts.speak("Πολύ καλή προσπάθεια!");
        }else{
            myTts.speak("Τα πήγες τέλεια, Μπράβο!");
        }
        Intent intent=new Intent(Quiz1.this,sxolia.class);
        startActivity(intent);
        // Apply smooth transition animations
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}