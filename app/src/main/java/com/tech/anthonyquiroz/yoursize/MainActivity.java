package com.tech.anthonyquiroz.yoursize;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.tech.anthonyquiroz.yoursize.IMCActivity;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Crear las variables de los objetos que vamos a utilizar
    EditText ET_Altura,ET_Altura2,ET_Peso;
    Spinner sp_altura,sp_peso;
    TextView tvAltura,tvAltura2;
private Toolbar toolbar;
    double peso;
    double altura;
    double altura2;
    double peso2;
    int altura_seleccion;
    int peso_seleccion;
    int peso_recomendacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   tvAltura = (TextView) findViewById(R.id.tvAltura);
        tvAltura2 = (TextView) findViewById(R.id.tvAltura2);
        sp_altura = (Spinner) findViewById(R.id.sp_altura);
        sp_peso = (Spinner) findViewById(R.id.sp_peso);
        ET_Peso = (EditText) findViewById(R.id.ET_Peso);
        ET_Altura = (EditText) findViewById(R.id.ET_Altura);
        ET_Altura2 = (EditText) findViewById(R.id.ET_Altura2);

         setSupportActionBar(toolbar);
        sp_altura.setOnItemSelectedListener(this);
        sp_peso.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> altura_adapter = ArrayAdapter.createFromResource
                (this, R.array.op_altura, R.layout.spinner_item);
        sp_altura.setAdapter(altura_adapter);


        ArrayAdapter<CharSequence> peso_adapter = ArrayAdapter.createFromResource
                (this, R.array.op_peso, R.layout.spinner_item);
        sp_peso.setAdapter(peso_adapter);
    }





       public void onItemSelected (AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            switch (arg0.getId()) {
                case R.id.sp_altura:
                    altura_seleccion=sp_altura.getSelectedItemPosition();

                    if (altura_seleccion==0) {


//tvAltura.setText("Metros");
ET_Altura2.setVisibility(View.GONE);
tvAltura2.setVisibility(View.GONE);
                        ET_Altura2.setText("1");
                    }
                    if (altura_seleccion==1) {
  //                      tvAltura.setText("Pies");
                        ET_Altura2.setVisibility(View.VISIBLE);
tvAltura2.setVisibility(View.VISIBLE);

                    }
                case R.id.sp_peso:
                    peso_seleccion=sp_peso.getSelectedItemPosition();


            }

    }






    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    //Referencia a estos elementos




    public void CalcularOnClick(View v) {



        try {
            altura = Double.parseDouble((ET_Altura.getText().toString()));
            peso = Double.parseDouble(ET_Peso.getText().toString());
            altura2 = Double.parseDouble(ET_Altura2.getText().toString());

            if (altura_seleccion == 0) {
                Intent i = new Intent(this, IMCActivity.class);
                i.putExtra("altura", altura);

                if (peso_seleccion == 0) {
                    i.putExtra("peso", peso);

                }
                if (peso_seleccion == 1) {
                    i.putExtra("peso", peso / 2.2046);
                  i.putExtra("peso_recomendacion", 1);


                }
                if (altura > 0 && peso > 0) {
                    startActivity(i);
                }

                else{
                    Toast.makeText(this, "Datos no validos", Toast.LENGTH_SHORT).show();
                }
            }



            if (altura_seleccion == 1) {


                altura2 = altura2 / 12;
                Intent i = new Intent(this, IMCActivity.class);
                i.putExtra("altura", (altura + altura2) / 3.28);

                if (peso_seleccion == 0) {
                    i.putExtra("peso", peso);
                }
                    if (peso_seleccion == 1) {
                        i.putExtra("peso", peso / 2.2046);
                        i.putExtra("peso_recomendacion", 1);


                    }

                    if (altura > 0 && peso > 0) {
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(this, "Datos no validos", Toast.LENGTH_SHORT).show();
                    }
                }





        }
        catch (Exception e) {
            Toast.makeText(this, "Datos no validos", Toast.LENGTH_SHORT).show();
        }
    }

   public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.borrar:
                ET_Altura.setText("");
                ET_Peso.setText("");

                break;

            case R.id.salir:
System.exit(0);
                break;


        }
        return true;
    }

    public void TMBOnClick(View view) {
        try {
            altura = Double.parseDouble((ET_Altura.getText().toString()));
            peso = Double.parseDouble(ET_Peso.getText().toString());
            altura2 = Double.parseDouble(ET_Altura2.getText().toString());

            if (altura_seleccion == 0) {
                Intent i = new Intent(this, TMBActivity.class);
                i.putExtra("altura", altura);

                if (peso_seleccion == 0) {
                    i.putExtra("peso", peso);

                }
                if (peso_seleccion == 1) {
                    i.putExtra("peso", peso / 2.2046);


                }
                if (altura > 0 && peso > 0) {
                    startActivity(i);
                }

                else{
                    Toast.makeText(this, "Datos no validos", Toast.LENGTH_SHORT).show();
                }
            }



            if (altura_seleccion == 1) {


                altura2 = altura2 / 12;
                Intent i = new Intent(this, TMBActivity.class);
                i.putExtra("altura", (altura + altura2) / 3.28);

                if (peso_seleccion == 0) {
                    i.putExtra("peso", peso);
                }
                if (peso_seleccion == 1) {
                    i.putExtra("peso", peso / 2.2046);

                }

                if (altura > 0 && peso > 0) {
                    startActivity(i);
                }
                else {
                    Toast.makeText(this, "Datos no validos", Toast.LENGTH_SHORT).show();
                }
            }





        }
        catch (Exception e) {
            Toast.makeText(this, "Datos no validos", Toast.LENGTH_SHORT).show();
        }
    }



    }


