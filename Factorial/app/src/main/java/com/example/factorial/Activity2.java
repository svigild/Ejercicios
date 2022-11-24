package com.example.factorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Activity2 extends AppCompatActivity {

    //VIEWS
    EditText numero;
    Button button;

    //CONSTANTE
    static String NUMERO_FINAL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        numero = (EditText) findViewById(R.id.textView4);
        button = (Button) findViewById(R.id.button3);

        setListenersToButtons();
    }

    static long calcularFactorial (long n){
        if( n == 0 || n ==1){
            return 1;
        }
        else{
            return n * calcularFactorial(n-1);
        }
    }

    /**
     * Método que asigna escuchadores clic a los botones
     */
    private void setListenersToButtons() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(numero.getText())) {
                    Intent iDatos = new Intent();

                    long resultado = calcularFactorial(Long.parseLong(numero.getText().toString()));
                    String resultadoString = String.valueOf(resultado);

                    //Numero que se meta en el segundo valor es el que devuelve
                    iDatos.putExtra(NUMERO_FINAL, resultadoString);

                    setResult(RESULT_OK,iDatos);
                    finish();
                }else{
                    numero.setError("Introduce un número natural");

                }
            }
        });
    }
}