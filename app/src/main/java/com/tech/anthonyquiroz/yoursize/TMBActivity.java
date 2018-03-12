package com.tech.anthonyquiroz.yoursize;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class TMBActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner sp_genero, sp_actividad;
    EditText et_edad;
    TextView resultado, titulo_resultado;
    int genero_seleccion;
    int actividad_seleccion;
    double TMB;
    double peso;
    double altura;
    double edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmb);
        sp_genero = (Spinner) findViewById(R.id.sp_genero);
        Intent i = getIntent();
        sp_actividad = (Spinner) findViewById(R.id.sp_actividad);
        resultado = (TextView) findViewById(R.id.tv_resultadoTMB);
        titulo_resultado= (TextView) findViewById(R.id.tv_titulo_resultado);
        altura = i.getDoubleExtra("altura", 0);
        peso = i.getDoubleExtra("peso", 0);
        et_edad= (EditText) findViewById(R.id.ET_Edad);
        ArrayAdapter<CharSequence> genero_adapter = ArrayAdapter.createFromResource
                (this, R.array.op_genero, R.layout.spinner_item);
        sp_genero.setOnItemSelectedListener(this);
        sp_genero.setAdapter(genero_adapter);

        ArrayAdapter<CharSequence> actividad_adapter = ArrayAdapter.createFromResource
                (this, R.array.op_actividad, R.layout.spinner_item);
        sp_actividad.setOnItemSelectedListener(this);
        sp_actividad.setAdapter(actividad_adapter);
    }



    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        switch (arg0.getId()) {
            case R.id.sp_genero:
                genero_seleccion = sp_genero.getSelectedItemPosition();

            case R.id.sp_actividad:
                actividad_seleccion=sp_actividad.getSelectedItemPosition();


        }

    }








    public void CalcularIMBOnClick(View v) {

     try {


        edad = Double.parseDouble((et_edad.getText().toString()));
        switch (genero_seleccion){

    case 0:
        TMB = (10 * peso) + (6.25 * (altura * 100)) - (5 * edad) + 5;
       // resultado.setText("Su Indice Metabolico Basal es de: " + String.format("%.0f", TMB));
        break;
            case 1:
                TMB = (10 * peso) + (6.25 * (altura * 100)) - (5 * edad) - 161;
            //    resultado.setText("Su Indice Metabolico Basal es de: " + String.format("%.0f", TMB));
break;
        }

        switch (actividad_seleccion) {
            case 0:
                TMB= TMB*1.2;
                resultado.setText("" + String.format("%.0f", TMB));
                titulo_resultado.setText("Resultado:");
                break;
            case 1:
                TMB= TMB*1.375;
                resultado.setText("" + String.format("%.0f", TMB));
                break;
            case 2:
                TMB=TMB*1.55;
                resultado.setText("" + String.format("%.0f", TMB));
                break;
            case 3:
                TMB=TMB*1.725;
                resultado.setText("" + String.format("%.0f", TMB));
break;
            case 4:
                TMB=TMB*1.9;
                resultado.setText("" + String.format("%.0f", TMB));
break;

        }



        }
     catch (Exception e) {
         Toast.makeText(this, "Datos no validos", Toast.LENGTH_SHORT).show();

     }

     }






        public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
