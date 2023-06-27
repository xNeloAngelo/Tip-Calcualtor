package com.example.tipkalk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView textViewNaslov;
    private TextView textViewCijena;
    private EditText editTextCijena;
    private Button buttonBrzi5;
    private Button buttonBrzi10;
    private Button buttonBrzi15;
    private TextView textViewBrziTip;
    private SeekBar seekBarTipPoIzboru;
    private TextView textViewTipPoIzboru;
    private TextView textViewTipIznos;
    private TextView textViewUkupanCijena;
    private TextView textViewPodijeliRacun;
    private SeekBar seekBarPodijeliRacun;
    private TextView textViewTipPoOsobni;
    private Button buttonIzracunaj;
    private  TextView textViewRacunPoOsobi;
    private double racun;
    private int tip = 0;
    private int brojOsoba = 1;


    private void izracunajTip(){

        double ukupniTip =  racun * tip / 100;
        double ukupanRacun = racun + ukupniTip;

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        textViewTipIznos.setText("Tip Iznosi : "+decimalFormat.format(ukupniTip)+ " KM");
        textViewUkupanCijena.setText("Ukupna Cijena : "+decimalFormat.format(ukupanRacun)+" KM");
    }

    private void podijeliRacun()
    {
        if (racun!=0)
        {
            double ukupanTip = racun * tip / 100;
            double ukupanRacun = racun + ukupanTip;
            double podijela = ukupanTip/ brojOsoba;
            double ukupanRacunPodijela = ukupanRacun/brojOsoba;
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            textViewTipPoOsobni.setText("Tip Po Osobi : " + decimalFormat.format(podijela)+ "KM");
            textViewPodijeliRacun.setText("Podijeli Racun : " + String.valueOf(brojOsoba));
            textViewRacunPoOsobi.setText("Racun Po Osobi : "+decimalFormat.format(ukupanRacunPodijela)+" KM");
        }
        else
        {
            textViewTipPoOsobni.setText("Tip Po Osobi : 0.00 KM");
            textViewPodijeliRacun.setText("Podijeli Racun : 1");
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewRacunPoOsobi = findViewById(R.id.textViewRacunPoOsobi);
        editTextCijena = findViewById(R.id.editTextCijena);
        buttonBrzi5 = findViewById(R.id.buttonBrzi5);
        buttonBrzi10 = findViewById(R.id.buttonBrzi10);
        buttonBrzi15 = findViewById(R.id.buttonBrzi15);
        textViewTipPoIzboru = findViewById(R.id.textViewTipPoIzboru);
        seekBarTipPoIzboru = findViewById(R.id.seekBarTipPoIzboru);
        seekBarPodijeliRacun = findViewById(R.id.seekBarPodijeliRacun);
        textViewTipPoOsobni = findViewById(R.id.textViewTipPoOsobi);
        textViewUkupanCijena = findViewById(R.id.textViewUkupnaCijena);
        buttonIzracunaj = findViewById(R.id.buttonIzracunaj);
        textViewTipIznos = findViewById(R.id.textViewTipIznos);
        textViewPodijeliRacun = findViewById(R.id.textViewPodijeliRacun);


        buttonBrzi5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tip = 5;
                textViewTipPoIzboru.setText("Tip Po Izboru : 5%");
                seekBarTipPoIzboru.setProgress(0);
                izracunajTip();
                podijeliRacun();
            }
        });
        buttonBrzi10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tip = 10;
                textViewTipPoIzboru.setText("Tip Po Izboru : 10%");
                seekBarTipPoIzboru.setProgress(0);
                izracunajTip();
                podijeliRacun();
            }
        });

        buttonBrzi15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tip = 15;
                textViewTipPoIzboru.setText("Tip Po Izboru : 15%");
                seekBarTipPoIzboru.setProgress(0);
                izracunajTip();
                podijeliRacun();
            }
        });

        seekBarTipPoIzboru.setMax(23);
        seekBarTipPoIzboru.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tip = progress + 1;
                textViewTipPoIzboru.setText("Custom Tip: " + String.valueOf(tip) + "%");
                izracunajTip();
                podijeliRacun();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        buttonIzracunaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                izracunajTip();
                podijeliRacun();
            }
        });

        seekBarPodijeliRacun.setMax(4);
        seekBarPodijeliRacun.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brojOsoba = progress + 1;
                textViewPodijeliRacun.setText("Podijeli Racun : " + String.valueOf(brojOsoba));
                podijeliRacun();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        editTextCijena.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence c, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence c, int i, int i1, int i2) {
                if (c.toString().isEmpty()) {
                    racun = 0;
                } else {
                    racun = Double.parseDouble(c.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable c) {

            }
        });

    }
}