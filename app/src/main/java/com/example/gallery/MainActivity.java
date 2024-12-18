package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;


public class MainActivity extends AppCompatActivity {
    Button Metafora,Eik_Page,ac_yes,ac_no;
    EditText Testh_keim;
    ImageView Pan_tets;
    TextView ac_text;
    //Βάση
    SQLiteDatabase db;
    SharedPreferences preferences;
    MyTts myTts;
    //Δημιουργώ μια μεταβλητή για να ξέρω αμα μιλάει η όχι και να μπορώ με το πάτημα του κουμπιού να την απενεργοποιήσω
    boolean Speaker = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Testh_keim = findViewById(R.id.Testh_keim);
        Metafora = findViewById(R.id.Metafora);
        Pan_tets = findViewById(R.id.Pan_tets);
        Eik_Page = findViewById(R.id.Eik_Page);
        ac_no = findViewById(R.id.ac_no);
        ac_yes = findViewById(R.id.ac_yes);
        ac_text =findViewById(R.id.ac_text);
        myTts= new MyTts(this);
        //Πρώτη Εργασία (Περνάει με Shared Preferences η πληροφορία)
        preferences = getSharedPreferences("Dedomena",MODE_PRIVATE);
        editPreferences();
        //Δεύτερη Εργασία (Χρησιμοποιεί Βάση για να αποθηκεύσει τις εικόνες και την πληροφορία τις εικόνας)
        db = openOrCreateDatabase("Image_Data",MODE_PRIVATE,null);
        db.execSQL("Create table if not exists Image_Data(" +
                "Id integer primary key autoincrement,"+
                "Name Text UNIQUE," +
                "Information Text," +
                "Path Text)");
        insertImagedata();
    }
    //Εργασία πρώτη
    //Κρατάμε σε ένα Shared preference τις λεπτομέρειες του κειμένου για την κάθε εικόνα
    private void editPreferences(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("key1","Ο Παναγιώτης Τέτση (1925-2016), είναι ένας Έλληνας ζωγράφος από την Ύδρα. Σπούδασε στην Ανώτατη Σχολή Καλών Τεχνών και έπειτα στη Σχολή Καλών Τεχνών στο Παρίσι (1953-1956) με υποτροφία, όπου διδάχθηκε χαλκογραφία. Ο Τέτσης είχε πλούσια καλλιτεχνική και διδακτική πορεία. Έκανε πολλές ατομικές εκθέσεις, συμμετείχε σε διεθνείς εκδηλώσεις όπως οι Μπιενάλε και δίδαξε σε διάφορα ιδρύματα, όπως η Σχολή Βακαλό και η Ανώτατη Σχολή Καλών Τεχνών, όπου διετέλεσε και πρύτανης. Το έργο του χαρακτηρίζεται από εξπρεσιονιστικά στοιχεία, με έμφαση στα χρώματα και το φως. Βραβεύτηκε το 2016 με το βραβείο Γιάννη Μόραλη και πέθανε τον Μάρτιο του ίδιου έτους, σε ηλικία 91 ετών.");
        editor.putString("karekla2","Ο Παναγιώτης Τέτσης ήταν ένας Έλληνας ζωγράφος που παρέμεινε πιστός στην παραστατική ζωγραφική, παρά τις διεθνείς τάσεις προς την αφαίρεση. Η τεχνική του, χειρονομιακή και ελεύθερη, επηρεάστηκε από τη μοντέρνα τέχνη, αλλά παρέμεινε επικεντρωμένη στο οπτικό ερέθισμα. Τα συνηθισμένα του θέματα περιλαμβάνουν τοπία, πορτρέτα και νεκρές φύσεις, με ιδιαίτερη αγάπη για την Ύδρα, τη Σίφνο και το αθηναϊκό τοπίο. Ήταν γνωστός για τη χρήση έντονων χρωμάτων, καταφέρνοντας να αποδώσει τη δύναμη του ελληνικού φωτός, κάτι που φαίνεται και στο έργο \"Οι μπλε καρέκλες ΙΙ\", όπου η χρήση ψυχρών τόνων ζωντανεύει το τοπίο.");
        editor.putString("laikiagora","Το έργο \"Λαϊκή αγορά\" του Παναγιώτη Τέτση είναι ένα από τα πιο γνωστά του και αποτελεί χαρακτηριστικό παράδειγμα της πλούσιας καλλιτεχνικής του γλώσσας. Εμπνευσμένος από τη λαϊκή αγορά έξω από το εργαστήριό του, ο Τέτσης αφιέρωσε σχεδόν τέσσερα χρόνια για να ολοκληρώσει μια σειρά έργων μήκους 58 μέτρων. Το δίπτυχο που παρουσιάζεται είναι μέρος αυτής της ζωοφόρου, όπου ο ζωγράφος αποδίδει το χάος της αγοράς με έντονα χρώματα και μεγάλες φιγούρες, προσκαλώντας το θεατή να συμμετάσχει στο κοινωνικό αυτό γεγονός.");
        editor.putString("athina4","Ο Παναγιώτης Τέτσης, στο Αθήνα IV, μαγεύει το κοινό με την απλότητα και την εκφραστικότητα του έργου του. Μέσα από την παραστατική του ζωγραφική, προσφέρει μια νέα οπτική της πόλης της Αθήνας, αναδεικνύοντας την ομορφιά και την ποιότητα των γραμμών της αρχιτεκτονικής της. Τα έργα του αποπνέουν μια μοναδική αίσθηση και αισθητική, κάνοντάς τα συναρπαστικά για τον θεατή.");
        editor.putString("Sifnos","Η Σίφνος Ι είναι εμπνευσμένη από το κυκλαδίτικο νησί της Σίφνου και αποτελεί ένα μοτίβο που επαναλαμβάνεται σε αρκετούς πίνακες του καλλιτέχνη, απεικονίζοντας μέσα από το εικαστικό του έργο διάφορα τοπία της Σίφνου με κύρια γνωρίσματά του η σχέση του φωτός με το ανάγλυφο της επιφάνειας και τη δυναμική του χρώματος. Το πράσινο και το βαθύ καφέ της γης λαμβάνουν πιο σκούρες αποχρώσεις ώστε να αποδώσουν την ένταση του μεσημεριάτικου φωτός. Οι αξίες του μαύρου θα αποδοθούν σε ένα μονοχρωματικό, μεγάλων διαστάσεων τόπο της Σίφνου. Το έργο Σίφνος Ι χαρακτηρίζεται από την ιδιαίτερη εξπρεσιονιστική διάθεση του Τέτση.");
        editor.apply();
    }
    //Εργασία Δεύτερη
    private void insertImagedata(){
        String deleteQuery = "DELETE FROM Image_Data WHERE Name = ?";
        db.execSQL(deleteQuery, new String[]{"Οι μπλέ καρέκλες II"});
        db.execSQL("Insert OR IGNORE into Image_Data(Name,Information,Path)values(?,?,?)",
                new Object[]{"Οι μπλέ καρέκλες II","Ο Παναγιώτης Τέτσης, ήταν ένας Έλληνας ζωγράφος που παρέμεινε πιστός στην παραστατική ζωγραφική, παρά τις διεθνείς τάσεις προς την αφαίρεση. Η τεχνική του, χειρονομιακή και ελεύθερη, επηρεάστηκε από τη μοντέρνα τέχνη, αλλά παρέμεινε επικεντρωμένη στο οπτικό ερέθισμα. Τα συνηθισμένα του θέματα περιλαμβάνουν τοπία, πορτρέτα και νεκρές φύσεις, με ιδιαίτερη αγάπη για την Ύδρα, τη Σίφνο και το αθηναϊκό τοπίο. Ήταν γνωστός για τη χρήση έντονων χρωμάτων, καταφέρνοντας να αποδώσει τη δύναμη του ελληνικού φωτός, κάτι που φαίνεται και στο έργο \"Οι μπλε καρέκλες ΙΙ\", όπου η χρήση ψυχρών τόνων ζωντανεύει το τοπίο.","fchair"});
        db.execSQL("Insert OR IGNORE into Image_Data(Name,Information,Path)values(?,?,?)",
                new Object[]{"Λαϊκή αγορά","Το έργο \"Λαϊκή αγορά\" του Παναγιώτη Τέτση, είναι ένα από τα πιο γνωστά του και αποτελεί χαρακτηριστικό παράδειγμα της πλούσιας καλλιτεχνικής του γλώσσας. Εμπνευσμένος από τη λαϊκή αγορά έξω από το εργαστήριό του, ο Τέτσης αφιέρωσε σχεδόν τέσσερα χρόνια για να ολοκληρώσει μια σειρά έργων μήκους 58 μέτρων. Το δίπτυχο που παρουσιάζεται είναι μέρος αυτής της ζωοφόρου, όπου ο ζωγράφος αποδίδει το χάος της αγοράς με έντονα χρώματα και μεγάλες φιγούρες, προσκαλώντας το θεατή να συμμετάσχει στο κοινωνικό αυτό γεγονός.","fla"});
        db.execSQL("Insert OR IGNORE into Image_Data(Name,Information,Path)values(?,?,?)",
                new Object[]{"Αθήνα IV","Ο Παναγιώτης Τέτσης, στο Αθήνα IV, μαγεύει το κοινό με την απλότητα και την εκφραστικότητα του έργου του. Μέσα από την παραστατική του ζωγραφική, προσφέρει μια νέα οπτική της πόλης της Αθήνας, αναδεικνύοντας την ομορφιά και την ποιότητα των γραμμών της αρχιτεκτονικής της. Τα έργα του αποπνέουν μια μοναδική αίσθηση και αισθητική, κάνοντάς τα συναρπαστικά για τον θεατή.","fathina4"});
        db.execSQL("Insert OR IGNORE into Image_Data(Name,Information,Path)values(?,?,?)",
                new Object[]{"Σίφνος I","Η Σίφνος Ι, είναι εμπνευσμένη από το κυκλαδίτικο νησί της Σίφνου και αποτελεί ένα μοτίβο που επαναλαμβάνεται σε αρκετούς πίνακες του καλλιτέχνη, απεικονίζοντας μέσα από το εικαστικό του έργο διάφορα τοπία της Σίφνου με κύρια γνωρίσματά του η σχέση του φωτός με το ανάγλυφο της επιφάνειας και τη δυναμική του χρώματος. Το πράσινο και το βαθύ καφέ της γης λαμβάνουν πιο σκούρες αποχρώσεις ώστε να αποδώσουν την ένταση του μεσημεριάτικου φωτός. Οι αξίες του μαύρου θα αποδοθούν σε ένα μονοχρωματικό, μεγάλων διαστάσεων τόπο της Σίφνου. Το έργο Σίφνος Ι χαρακτηρίζεται από την ιδιαίτερη εξπρεσιονιστική διάθεση του Τέτση.","fsifnos"});
    }


    //Αν ο χρήστης πατήσει πάνω στην εικόνα του Τετσή θα μάθει περισσότερες πληροφορίες για αυτόν σε περίπτωση που δεν ξέρει
    public void pl_tetsh(View view){
        Testh_keim.setVisibility(View.VISIBLE);
        Pan_tets.setVisibility(View.INVISIBLE);
        String data = preferences.getString("key1","Δεν πέρασαν τα δεδομένα");
        Testh_keim.setText(data);
    }
    //Αν πατήσει το κείμενο με τις πληροφορίες για τον τετσή θα ξαναεμφανίσει την εικόνα του Τετσή
    public void phot_emf(View view){
        Testh_keim.setVisibility(View.INVISIBLE);
        Pan_tets.setVisibility(View.VISIBLE);
    }
    //Μας οδηγεί στην επόμενη σελίδα με εικόνες και πληροφορίες για αυτές
    public void next_pg(View view){
        myTts.shutdown();
        Intent intent=new Intent(MainActivity.this,SecondPage.class);
        startActivity(intent);
        // Apply smooth transition animations
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    //Μας πηγαίνει σε μια σελίδα που έχει μόνο τις εικόνες χωρίς πληροφορίες
    public void Imagepage(View view){
        myTts.shutdown();
        Intent intent=new Intent(MainActivity.this,OnlyImages.class);
        startActivity(intent);
        // Apply smooth transition animations
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
    //Σε περίπτωση που ο χρήστης δεν θέλει να κάνει λογαριασμό του δίνουμε το όνομα Guest που θα εμφανίζεται πάνω αριστερα τις σελίδας
    public void AccountNO(View view){
        Metafora.setVisibility(View.VISIBLE);
        Eik_Page.setVisibility(View.VISIBLE);
        ac_no.setVisibility(View.INVISIBLE);
        ac_yes.setVisibility(View.INVISIBLE);
        ac_text.setVisibility(View.INVISIBLE);
        SharedPreferences sharedPreferences = getSharedPreferences("Onom_user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("Onoma");
        editor.apply();
    }
    //Αν πατήσει ναι τον μεταφέρουμε σε μια άλλη σελίδα για να κάνει λογαριασμό
    public void AccountYES(View view){
        myTts.shutdown();
        Intent intent=new Intent(MainActivity.this,AccountPage.class);
        startActivity(intent);
        // Apply smooth transition animations
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void TextSpeak(View view){
        if (Speaker == false){
            myTts.speak("Ο Παναγιώτης Τέτση (1925-2016), είναι ένας Έλληνας ζωγράφος από την Ύδρα. Σπούδασε στην Ανώτατη Σχολή Καλών Τεχνών και έπειτα στη Σχολή Καλών Τεχνών στο Παρίσι (1953-1956) με υποτροφία, όπου διδάχθηκε χαλκογραφία. Ο Τέτσης είχε πλούσια καλλιτεχνική και διδακτική πορεία. Έκανε πολλές ατομικές εκθέσεις, συμμετείχε σε διεθνείς εκδηλώσεις όπως οι Μπιενάλε και δίδαξε σε διάφορα ιδρύματα, όπως η Σχολή Βακαλό και η Ανώτατη Σχολή Καλών Τεχνών, όπου διετέλεσε και πρύτανης. Το έργο του χαρακτηρίζεται από εξπρεσιονιστικά στοιχεία, με έμφαση στα χρώματα και το φως. Βραβεύτηκε το 2016 με το βραβείο Γιάννη Μόραλη και πέθανε τον Μάρτιο του ίδιου έτους, σε ηλικία 91 ετών.");
            Speaker = true;
        }else {
            myTts.stop();
            Speaker = false;
        }
    }

}
