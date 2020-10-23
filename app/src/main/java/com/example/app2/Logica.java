package com.example.app2;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class Logica {

    //Límites para establecer el estado de la calificación.
    private final int LIMITE_ALTO = 6;
    private final int LIMITE_BAJO = 4;

    //Colores en formato RBG para asignar según el estado de la calificación.
    private final int ESTADO_BIEN = 0;
    private final int ESTADO_REGULAR = 1;
    private final int ESTADO_MAL = 2;

    private float media;

    /**
     * Calcula la media de las notas provenientes de un ArrayList de String. Realiza comprobacione e
     * informa al usuario de posibles errores en el formato.
     *
     * @param notas   ArrayList con todas las notas.
     * @param context Contexto en el cual se van a mostrar los mensajes.
     * @return Media de todas las notas
     */
    public float getMedia(ArrayList<String> notas, Context context) {
        float suma = 0;
        int nota;
        for (String index : notas) {
            try {
                nota = Integer.parseInt(index);
                suma += nota;
            } catch (Exception e) {
                /* La única excepción posible es que el campo esté vacío, todos los demás errores
                están controlados por la naturaleza del campo */
                Toast.makeText(context, "Calificación vacía, se estableció 0", Toast.LENGTH_LONG).show();
            }
        }
        media = (suma) / notas.size();
        return media;
    }

    /**
     * Comprueba la calificación media y genera un String con un color RGB en función de esta.
     *
     * @return String con la información del color RBG para aplicar a la calificación.
     */
    public int getEstado() {
        int estado = ESTADO_MAL;
        if (media >= LIMITE_ALTO) estado = ESTADO_BIEN;
        else if (media >= LIMITE_BAJO) estado = ESTADO_REGULAR;
        return estado;
    }
}
