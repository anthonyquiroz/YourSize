package com.tech.anthonyquiroz.yoursize;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.system.ErrnoException;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class IMCActivity extends AppCompatActivity {
    TextView TxResultado, TxObservacion, TxRecomendacion, Tv_recomendacion, Tv_pesoideal;
    ProgressBar progressBar;

     private Toolbar toolbar;
double altura;
double peso;
int peso_recomendacion;
double peso_inicial;
double peso_limite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        Intent i = getIntent();
         altura = i.getDoubleExtra("altura", 0);
         peso = i.getDoubleExtra("peso", 0);
         peso_recomendacion= i.getIntExtra("peso_recomendacion",0);
        double imc = (peso / Math.pow(altura, 2));
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        TxResultado = (TextView) findViewById(R.id.tv_resultado);
        TxObservacion = (TextView) findViewById(R.id.observacion);
        TxRecomendacion = (TextView) findViewById(R.id.recomendacion);
        Tv_recomendacion = (TextView) findViewById(R.id.tv_recomendacion);
        Tv_pesoideal = (TextView) findViewById(R.id.tv_pesoideal);
        progressBar = (ProgressBar) findViewById(R.id.pgBar);
        final ImageView Imagene = (ImageView) findViewById(R.id.imagen);
        double diferencia;
        double recomendacion;
        /*
        *Bajo peso  <18.50
        *Normal 18,5 - 24.99
        * Sobrepeso >25.00
         */
        int situacion = 0;
        if (imc <= 18.5) {
            situacion = 1;
        }
        if (imc > 18.5 && imc < 24.5) {
            situacion = 2;
        }
        if (imc > 24.5) {
            situacion = 3;
        }
        if (imc > 30) {
            situacion = 4;
        }
        else {
            TxResultado.setText(("Calculo invalido"));
        }
        peso_inicial=18.6*Math.pow(altura, 2);
        peso_limite=24.6*Math.pow(altura, 2);

        switch (situacion) {

            case 1:
                diferencia = 18.6 - imc;
                recomendacion = diferencia * Math.pow(altura, 2);
                  Imagene.setImageResource(R.drawable.flaco);
                TxObservacion.setTextColor(getResources().getColor(R.color.colorYellow));
                  TxObservacion.setText(R.string.texto_peso_bajo);
                Toast.makeText(this, "Bajo peso", Toast.LENGTH_SHORT).show();
                TxResultado.setText("" + String.format("%.2f", imc) );
                progressBar.setProgress((int) (imc));
                progressBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
Tv_recomendacion.setText("Peso a ganar:");
               if (peso_recomendacion==0) {
                   TxRecomendacion.setText("" + String.format("%.2f", recomendacion)+" kg ");
                   Tv_pesoideal.setText(""+ String.format("%.2f", peso_inicial) + " kg - " +String.format("%.2f", peso_limite)+" kg");

               }
               else {
                   recomendacion=recomendacion*2.2046;
                   TxRecomendacion.setText("" + String.format("%.2f", recomendacion)+" lb ");
                   Tv_pesoideal.setText(""+ String.format("%.2f", peso_inicial*2.2046) + " lb - " +String.format("%.2f", peso_limite*2.2046)+" lb");
               }


                break;
            case 2:
                 Imagene.setImageResource(R.drawable.normal);
                TxObservacion.setText(R.string.texto_peso_normal);
                TxObservacion.setTextColor(getResources().getColor(R.color.colorGreen));

                Toast.makeText(this, "Peso normal", Toast.LENGTH_SHORT).show();
                TxResultado.setText("" + String.format("%.2f", imc) );
                //  TxResultado.setText((String.valueOf(imc)));
                progressBar.setProgress((int) (imc*2));
               // progressBar.setProgress(50);
                progressBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                TxRecomendacion.setText("Continúe así :)");

                if (peso_recomendacion==0) {
                    Tv_pesoideal.setText(""+ String.format("%.2f", peso_inicial) + " kg - " +String.format("%.2f", peso_limite)+" kg");
                }
                else {

                    Tv_pesoideal.setText(""+ String.format("%.2f", peso_inicial*2.2046) + " lb - " +String.format("%.2f", peso_limite*2.2046)+" lb");
                }

                break;
            case 3:
                Imagene.setImageResource(R.drawable.gordo);
                TxObservacion.setText(R.string.texto_peso_alto);
                TxObservacion.setTextColor(getResources().getColor(R.color.colorRed));
                diferencia= imc-24.6;
                recomendacion= diferencia*Math.pow(altura,2);
                Toast.makeText(this, "Sobrepeso", Toast.LENGTH_SHORT).show();
                TxResultado.setText("" + String.format("%.2f", imc) );
                Tv_recomendacion.setText("Peso a perder:");
                progressBar.setProgress((int) (imc*3));
                //progressBar.setProgress(80);
                progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

                if (peso_recomendacion==0) {
                    TxRecomendacion.setText("" + String.format("%.2f", recomendacion)+" kg ");
                    Tv_pesoideal.setText(""+ String.format("%.2f", peso_inicial) + " kg - " +String.format("%.2f", peso_limite)+" kg");

                }
                else {
                    recomendacion=recomendacion*2.2046;
                    TxRecomendacion.setText("" + String.format("%.2f", recomendacion)+" lb ");
                    Tv_pesoideal.setText(""+ String.format("%.2f", peso_inicial*2.2046) + " lb - " +String.format("%.2f", peso_limite*2.2046)+" lb");
                }
                break;

            case 4:
                Imagene.setImageResource(R.drawable.gordo);
                TxObservacion.setText(R.string.texto_peso_obeso);
                TxObservacion.setTextColor(getResources().getColor(R.color.colorRed));
                diferencia= imc-24.6;
                recomendacion= diferencia*Math.pow(altura,2);
                Toast.makeText(this, "Obeso", Toast.LENGTH_SHORT).show();
                TxResultado.setText("" + String.format("%.2f", imc) );
                Tv_recomendacion.setText("Peso a perder:");
                progressBar.setProgress(100);
                progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

                if (peso_recomendacion==0) {
                    TxRecomendacion.setText("" + String.format("%.2f", recomendacion)+" kg ");
                    Tv_pesoideal.setText(""+ String.format("%.2f", peso_inicial) + " kg - " +String.format("%.2f", peso_limite)+" kg");

                }
                else {
                    recomendacion=recomendacion*2.2046;
                    TxRecomendacion.setText("" + String.format("%.2f", recomendacion)+" lb ");
                    Tv_pesoideal.setText(""+ String.format("%.2f", peso_inicial*2.2046) + " lb - " +String.format("%.2f", peso_limite*2.2046)+" lb");
                }
                break;
        }
    }


     public void TMBOnClick(View v) {
     Intent e = new Intent(this, TMBActivity.class);
    e.putExtra("altura", altura);
         e.putExtra("peso",peso);
    startActivity(e);
    }

    public void volverOnClick(View v) {
        super.onBackPressed();
    }

}



