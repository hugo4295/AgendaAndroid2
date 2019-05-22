package mx.edu.tesoem.hugo4295.itics.agendaandroid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    GridView gvdatos;
    int filastotales=0;
    String[] contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvdatos = (GridView) findViewById(R.id.GVDatos);
        gvdatos.setOnItemClickListener(this);
        if (!verifica_existencia()){
            String[] Elementos = {"Nombre","Usuario","Contraseña","Correo"};
            ArrayAdapter<String> elementosgv = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Elementos);
            gvdatos.setAdapter(elementosgv);
        }else{
            leerarchivo();
        }
    }

    private boolean verifica_existencia(){
        File archivo = new File(this.getFilesDir(),"Datos.txt");
        if (archivo.exists()){
            return true;
        } else {
            return false;
        }
    }

    public void btnRegistrar(View v){
        Intent intent = new Intent(this,FrmRegistraActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void leerarchivo(){
        try {
            BufferedReader archivo = new BufferedReader(new InputStreamReader(openFileInput("Datos.txt")));
            String cadena = "";
            String[] datos;
            int lineas=0;
            while ((cadena = archivo.readLine()) != null){
                lineas++;
            }
            archivo.close();
            // se reabre el archivo para llevar el arreglo
            //se multiplica el numero de lineas por el numero de datos, en este caso por 4
            // para generar el arreglo de esa dimension
            contenido = new String[(lineas*4)+4];
            int filas=4;
            contenido[0]="Nombre";
            contenido[1]="Usuario";
            contenido[2]="Contraseña";
            contenido[3]="Correo";
            archivo = new BufferedReader(new InputStreamReader(openFileInput("Datos.txt")));
            while ((cadena = archivo.readLine()) != null){
                datos = cadena.split(",");
                contenido[filas] = datos[0];
                contenido[filas+1] = datos[1];
                contenido[filas+2] = datos[2];
                contenido[filas+3] = datos[3];
                filas+=4;
            }
            filastotales=filas;
            archivo.close();
            ArrayAdapter<String> elementosgv = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contenido);
            gvdatos.setAdapter(elementosgv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View elemento, int position, long id) {

        if ((position>3)){
            if (checaposicion(position, filastotales)){
                String nombre = contenido[position];
                String usuario = contenido[position+1];
                String contra = contenido[position+2];
                String correo = contenido[position+3];

               Intent datos = new Intent(this, frmMostrarDatos.class);
                datos.putExtra("nombre",nombre);
                datos.putExtra("usuario",usuario);
                datos.putExtra("contra",contra);
                datos.putExtra("correo",correo);
                startActivity(datos);
                finish();
            }
        }
    }

    private boolean checaposicion(int p, int totales){
        for (int i = 4; i < totales; i+=4){
            if (i == p) return true;
        }
        return false;
    }
}
