/*
Aplicacion en la que introducimos nombre y apellido
 */

package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvNotaMedia, tvNombreCompleto, tvNotaNum;
    private EditText etNombre, etApellidos, etDI, etMoviles, etAD;
    private Button btnValidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_foreground);
        inicializarReferencias();
        configurarEscuchadores();
    }

    /**
     * Asigna todas las referencias necesarias para el funcionamiento de la Activity en el arranque
     * para que solo consuma en este instante la CPU necesaria para realizar el método findViewById.
     * De esta manera es mucho más organizado y cómodo añadir nuevas referencias (como en el caso
     * de querer añadir una nueva asignatura).
     */
    private void inicializarReferencias() {
        tvNotaMedia = findViewById(R.id.tvNotaMedia);
        tvNombreCompleto = findViewById(R.id.tvNombreCompleto);
        tvNotaNum = findViewById(R.id.tvNotaNum);
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellido);
        etDI = findViewById(R.id.etNotaDI);
        etMoviles = findViewById(R.id.etNotaMoviles);
        etAD = findViewById(R.id.etNotaAD);
        btnValidar = findViewById(R.id.btnValidar);
    }

    /**
     * Asigna a cada elemento referenciado que lo necesite su escuchador, ayuda a tener organizado el
     * código si necesitasemos añadir más.
     */
    private void configurarEscuchadores() {
        Controlador ctr = new Controlador(this);
        btnValidar.setOnClickListener(ctr);
        etDI.setOnFocusChangeListener(ctr);
        etMoviles.setOnFocusChangeListener(ctr);
        etAD.setOnFocusChangeListener(ctr);
    }

    /**
     * Crea un ArrayList con las notas introducidas en la interfaz. De esta manera, solo necesitamos
     * añadir en este método cualquier nueva asignatura que hayamos creado y la aplicación seguirá
     * funcionando sin necesidad de tocar el Controlador o la Lógica.
     * No realiza ninguna comprobación con dichas notas, de eso se encargará la Lógica.
     *
     * @return Devuelve un ArrayList de Editable con todas las notas del alumno.
     */
    public ArrayList<String> getNotas() {
        ArrayList<String> notas = new ArrayList<>();
        notas.add(etDI.getText().toString());
        notas.add(etAD.getText().toString());
        notas.add(etMoviles.getText().toString());
        return notas;
    }

    /**
     * Establece los TextView de la parte superior de la Activity. Para ello, recurre al estado de
     * los atributos etNombre y etApellido. Los parámetros media y estado se calculan en la Lógica.
     *
     * @param media  Media de las asignaturas introducidas en la interfaz.
     * @param estado Cadena con un color en formato RGB.
     */
    public void setTitulo(float media, String estado) {
        tvNombreCompleto.setText(etNombre.getText().toString().toUpperCase() + " " + etApellidos.getText().toString());
        tvNotaMedia.setText("Nota Media:");
        tvNotaNum.setText(String.format("%.2f", media));
        tvNotaNum.setTextColor(Color.parseColor(estado));

    }

    public String getNombre() {
        return etNombre.getText().toString();
    }

    public String getApellido() {
        return etApellidos.getText().toString();
    }

    /**
     * Reproduce distintos sonidos en función del estado de la calificación
     * @param estado Entero con uno de los posibles estados.
     */
    public void efectoSonido(int estado) {
        switch (estado) {
            case 0:
                MediaPlayer media = MediaPlayer.create(this, R.raw.aprobado);
                media.start();
                break;
            case 1:
                media = MediaPlayer.create(this, R.raw.regular);
                media.start();
                break;
            case 2:
                media = MediaPlayer.create(this, R.raw.suspenso);
                media.start();
                break;
        }
    }
}