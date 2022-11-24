package com.example.factorial;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //VIEWS
    Button button;
    TextView textViewResultado;

    //CONTROL DEVOLUCIÓN LLAMADA ACTIVITY
    private ActivityResultLauncher<Intent> mLauncherActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReferences();
        setListenersToButtons();
        // registro devolución de llamadas de resultados provenientes de otra activity
        mLauncherActivity=registroDevolucionLLamadaActivity();
    }

    /** Método que inicializa las Views de la UI
     *
     */
    private void initReferences() {
        textViewResultado=findViewById(R.id.resultado);
        button=findViewById(R.id.button);
    }

    /** Método que lanza la segunda activity.
     *
     */
    private void lanzarActivity2() {
        Intent i= new Intent(this, Activity2.class);
        mLauncherActivity.launch(i);
    }

    /** Método asigna escuchadores a botones
     *
     */
    private void setListenersToButtons() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {lanzarActivity2();}
        });
    }

    /** Método que registra la devolución de llamada de resultados provenientes de
     *  otra activity
     * @return ActivityResultLauncher<Intent> objeto que nos permitirá lanzar nuestra segunda activity
     */
    private ActivityResultLauncher<Intent> registroDevolucionLLamadaActivity() {
        return registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // código que se ejecuta cuando la segunda activity devuelve el control a esta
                        if(result.getResultCode()==RESULT_OK){
                            Intent data=result.getData();
                            devolverNumero(data);
                        }
                    }
                }
        );
    }

    /** Método que completa el TextView de la activity1 que al principio estaba vacío
     *
     * @param data el número del resultado
     */
    private void devolverNumero(Intent data) {
        textViewResultado.setText(data.getStringExtra(Activity2.NUMERO_FINAL));
    }
}