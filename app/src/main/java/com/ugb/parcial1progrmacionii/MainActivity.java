package com.ugb.parcial1progrmacionii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TabHost tbh;

    TextView tempVal, txtAguaIngresada, resultadoAgua, resultadoMensaje;
    Double numAguaIngresada;
    Spinner spn;
    Button btn1,btn2;

    conversores miObj = new conversores();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            tbh = findViewById(R.id.tbhCalculadoras);
            tbh.setup();

            tbh.addTab(tbh.newTabSpec( "CALCAGUA").setContent(R.id.tabCalculadoraAgua).setIndicator("Calculadora Agua", null));
            tbh.addTab(tbh.newTabSpec( "CONVERSOR").setContent(R.id.tabArea).setIndicator("Conversor Area", null));


            btn1=findViewById(R.id.btnConvertirArea);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    spn=findViewById(R.id.spnArea);
                    int de = spn.getSelectedItemPosition();
                    spn=findViewById(R.id.spnDArea);
                    int a =spn.getSelectedItemPosition();
                    tempVal=findViewById(R.id.txtCantidadArea);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());
                    double respuesta = miObj.convertir(0,de,a,cantidad);
                    Toast.makeText(getApplicationContext(), "Respuesta "+ respuesta, Toast.LENGTH_SHORT).show();
                }
            });


            btn2=findViewById(R.id.btnCalcularAgua);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    tempVal = findViewById(R.id.txtAguaConsumida);
                    numAguaIngresada = Double.parseDouble(tempVal.getText().toString());

                    resultadoAgua = findViewById(R.id.lblRespuestaNum);
                    resultadoMensaje = findViewById(R.id.lblMensjae);

                    resultadoMensaje.setText("A Cancelar");
                    if (numAguaIngresada<18){
                        resultadoAgua.setText("$" + 6);
                    } else if (numAguaIngresada>=19 && numAguaIngresada<=28){
                        resultadoAgua.setText("$" + (6 + ((numAguaIngresada-18)*0.45)));
                    } else if (numAguaIngresada>=29){
                        resultadoAgua.setText("$" + (6 + (((numAguaIngresada-18)*0.45) + (numAguaIngresada-28)*0.65))) ;
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

        }
    }
}
class conversores{
    double [][] valores ={
            {1,1.4308,0.1329,1000,5791.21,14310.25,899.772}
    };
    public double convertir (int opcion, int de, int a, double cantidad){
        return valores[opcion][de]*cantidad/valores[opcion][a];
    }
}