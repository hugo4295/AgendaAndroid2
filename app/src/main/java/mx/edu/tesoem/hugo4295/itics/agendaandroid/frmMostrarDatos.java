package mx.edu.tesoem.hugo4295.itics.agendaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class frmMostrarDatos extends AppCompatActivity {

    TextView lblnombre, lblusuario, lblcontra, lblcorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle parametros = this.getIntent().getExtras();
        setContentView(R.layout.activity_frm_mostrar_datos);
        lblnombre = (TextView) findViewById(R.id.lblnombre);
        lblusuario = (TextView) findViewById(R.id.lblusuario);
        lblcontra = (TextView) findViewById(R.id.lblcontra);
        lblcorreo = (TextView) findViewById(R.id.lblcorreo);

        lblnombre.setText(parametros.getString("nombre").toString());
        lblusuario.setText(parametros.getString("usuario").toString());
        lblcontra.setText(parametros.getString("contra").toString());
        lblcorreo.setText(parametros.getString("correo").toString());
    }

    public void regresa(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
