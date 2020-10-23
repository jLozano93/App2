package com.example.app2;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Controlador implements View.OnClickListener, View.OnFocusChangeListener {

    private Logica logica;
    private MainActivity activity;
    private String[] colores = {"#26c74e", "#ff9e00", "#b20000"};

    public Controlador(MainActivity activity) {
        this.activity = activity;
        logica = new Logica();
    }

    /**
     * Al pulsar el botón, se comprueba si los campos nombre o apellido están vacíos. De ser así, se
     * notifica al usuario y no se realiza ninguna acción. Además, quito el foco del elemento que lo
     * tenga para asegurarme de que si se ha introducido una nota mayor de 10 antes de pulsar el
     * botón, esta se actualice con el método onFocusChange. Al final, llama a un método que
     * lanza efectos de sonido desde la Activity según el estado de la nota
     */
    @Override
    public void onClick(View v) {
        if (!activity.getNombre().isEmpty() && !activity.getApellido().isEmpty()) {
            View actual = activity.getCurrentFocus();
            if (actual != null) actual.clearFocus();
            float media = logica.getMedia(activity.getNotas(), activity);
            int estado = logica.getEstado();
            activity.setTitulo(media, colores[estado]);
            activity.efectoSonido(estado);
        } else {
            Toast.makeText(activity,"Debes introducir nombre y apellidos",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Al perder el foco sobre un TextView, comprobamos si la nota es mayor que 10 y, de ser así,
     * la fijamos en 10.
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            EditText et = (EditText) v;
            String nota = et.getText().toString();
            if (!nota.isEmpty() && Integer.parseInt(nota) > 10) {
                et.setText("10");
            }
        }
    }
}
