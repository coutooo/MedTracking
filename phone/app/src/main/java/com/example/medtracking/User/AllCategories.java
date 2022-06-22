package com.example.medtracking.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.medtracking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


// da jeito para o firestore https://www.youtube.com/watch?v=y2op1D0W8oE

public class AllCategories extends AppCompatActivity {

    int[] imageId = {R.drawable.ic_user,R.drawable.indenavarrete}; // mudar para query

    int size;

    String[] beds;  // mudar para query  // = {"Bed 1", "Bed 2"}

    String[] nameAndCause;  // mudar para query  //"Joao, Caiu de Cavalo","Joana, Acidente Mota"

    String[] inAndout;  // mudar para query  = {"in:18-06/2022\n\nout:--/--/--","in:05-04/2022\n\nout:20/09/2022"};



    ImageView backBtn;
    RelativeLayout rTratamento,rMedicamentos,rInventario,rBatimentos,rBluetooth,rNFC;

    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter writingTagFilters[];
    boolean writeMode;
    Tag myTag;

    Context context;
    // teste
    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("Nurse/ZoyuBzAMxop39jzD9Gi7");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        context = this;

        fetchData();
        //hooks
        backBtn = findViewById(R.id.back_pressed);
        rTratamento = findViewById(R.id.relativeLayoutTratamentos);
        rMedicamentos = findViewById(R.id.relativeLayoutMedicamentos);
        //rInventario = findViewById(R.id.relativeLayoutInventario);
        //rBatimentos = findViewById(R.id.relativeLayoutBatimentos);
        rBluetooth = findViewById(R.id.relativeLayoutBluetooth);
        rNFC = findViewById(R.id.relativeLayoutNFC);

        // botao para andar para tras
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllCategories.super.onBackPressed();
            }
        });

        // botao do cartao de informacao
        rTratamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AllCategories.this, UserActivity.class);
                i.putExtra("npacients",size);
                startActivity(i);

                Log.d("Tratamentos","Tratamentos");
            }
        });

        // botao do cartao de pacients
        rMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(AllCategories.this, BedsActivity.class);
                i.putExtra("nameAndCause",nameAndCause);
                i.putExtra("inAndout",inAndout);
                i.putExtra("beds",beds);
                i.putExtra("npacients",size);
                startActivity(i);


                Log.d("Medicamentos","Medicamentos");
            }
        });



        //botao do bluetooth
        rBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Bluetooth","Bluetooth");
                Intent i = new Intent(AllCategories.this, BluetoothActivity.class);
                startActivity(i);
            }
        });

        //botao do nfc
        rNFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("NFC","NFC");
                Intent i = new Intent(AllCategories.this, NFCActivity.class);
                startActivity(i);
            }
        });




        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter==null) {
            Toast.makeText(this, "This device doesn't support NFC", Toast.LENGTH_SHORT).show();
            //finish();
        }
        readFromIntent(getIntent());
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        writingTagFilters = new IntentFilter[] { tagDetected };


    }

    private void readFromIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs = null;
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }
            buildTagViews(msgs);
        }
    }

    private void buildTagViews(NdefMessage[] msgs) {
        Log.d("NFC", "here");
        if (msgs == null || msgs.length == 0) return;
        String text = "";
        Log.d("NFC", "here");
        byte payload[] = msgs[0].getRecords()[0].getPayload();
        String textEnconding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16"; // Get text encoding
        int languageCodeLength = payload[0] & 0063; // Get language code e.g. "en"

        try {
            // Get the text
            text = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEnconding);
        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncoding", e.toString());
        }
        Toast.makeText(this, "NFC Content: " + text, Toast.LENGTH_SHORT).show();
        String str = "Pacients/" + text.replace("PlainText|", "");
        Log.d("FIREBASE", str);
        DocumentReference mDocRef = FirebaseFirestore.getInstance().document(str);
        mDocRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String name = documentSnapshot.getId().toString();
                Log.d("FIREBASE", name);

                Intent i = new Intent(AllCategories.this, TarefaActivity.class);
                i.putExtra("bed",name);
                i.putExtra("nameAndCause",documentSnapshot.getString("nameAndCause"));
                i.putExtra("imageid",R.drawable.indenavarrete);
                i.putExtra("inAndOut",documentSnapshot.getString("inAndout"));
                startActivity(i);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AllCategories.this, "Error!", Toast.LENGTH_SHORT).show();
                Log.d("FIREBASE", e.toString());
            }
        });



    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        readFromIntent(intent);
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        writeModeOff();
    }

    @Override
    protected void onResume() {
        super.onResume();
        writeModeOn();
    }

    /**
     * Enable Write
     */

    private void writeModeOn() {
        writeMode = true;
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, writingTagFilters, null);
    }

    /**
     * Disable Write
     */

    private void writeModeOff() {
        writeMode = false;
        nfcAdapter.disableForegroundDispatch(this);
    }



    public void fetchData () {

        FirebaseFirestore.getInstance().collection("Pacients")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            size = task.getResult().size();
                            nameAndCause = new String[size];
                            beds = new String[size];
                            inAndout = new String[size];
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                 // ta certo
                                int position = document.getLong("bedId").intValue()-1;  // ta certo
                                String namenCause = document.getString("nameAndCause");          // ta certo
                                String bed = "Bed "+ document.getLong("bedId").toString();
                                String io = document.getString("inAndout");



                                Log.d("position", String.valueOf(position));
                                Log.d("String",namenCause);
                                Log.d("size", String.valueOf(size));

                                nameAndCause[position] = namenCause;
                                beds[position] = bed;
                                inAndout[position] = io;

                                Log.d("array final",nameAndCause[position]);
                            }
                        } else {
                            Log.d("asdsadsadsa", "Error getting documents: ", task.getException());
                        }
                    }

                });
    }
}
