package com.example.sistemascasa.tigie.settings;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.db.BaseDatos;
import com.example.sistemascasa.tigie.db.ConstructorData;



import java.util.*;
import javax.mail.*;

public class ActivityEmail extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private  EditText edEmail;
    private  EditText edOcupacion;
    private EditText edEmpresa;
    private EditText edAlias;
    private EditText ePass;

    private  EditText edName;
    String tokenSecurity;
    String name;
    String email;
    String cadena;
    String cadena2;
    String ocupacion;
    String empresa;
    String concatenacion;
    String alias;
    String password;

    private Context context;


    private int mProgressStatus = 0;
    private ConstructorData constructorData;
    Boolean success;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_email);

        context = getApplicationContext();
        constructorData = null;

        ePass       = (EditText) findViewById(R.id.EditTextPass);
        edName      = (EditText) findViewById(R.id.EditTextName);
        edAlias     = (EditText) findViewById(R.id.EditTextAlias);
        edOcupacion = (EditText) findViewById(R.id.EditTextEmail2);
        edEmpresa   = (EditText) findViewById(R.id.EditTextFeedbackBody);

        Button btnSend = (Button) findViewById(R.id.ButtonSendFeedback);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alias       = edAlias.getText().toString();
                name        = edName.getText().toString();
                ocupacion   = edOcupacion.getText().toString();
                empresa     = edEmpresa.getText().toString();
                password    = ePass.getText().toString();

                concatenacion = ocupacion + "  " + empresa;

                if(ocupacion.equals("") || ocupacion == null )
                    ocupacion = "Sin ocupacion";

                if(empresa.equals("") || empresa == null )
                    empresa = "Sin empresa";


                Random rnd = new Random();
                cadena = String.valueOf(rnd.nextDouble()).substring(0, 5);
                cadena2 = String.valueOf(rnd.nextDouble()).substring(0, 3);

                if(alias.equals("")) {
                    Toast.makeText(getApplicationContext(), "Los sentimos, el Alias es obligatorio.", Toast.LENGTH_LONG).show();
                }  else if (name.equals("")) {
                    Toast.makeText(getApplicationContext(), "Los sentimos, el Nombre es obligatorio.", Toast.LENGTH_LONG).show();
                } else if (password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Los sentimos, la contraseña es obligatoria.", Toast.LENGTH_LONG).show();
                } else {
                    SendMail2 task = new SendMail2(getApplicationContext());
                    task.execute();
                }

            }

        });

    }


    private class SendMail2  extends AsyncTask<Void,Void,Void> {

        private Context context;
        private Session session;
        private String email;
        private String subject;
        private String message;
        private Button buttonSent;
        ProgressBar progressBar1;
        private  ConstructorData constructorData;
        Boolean success1;
        private BaseDatos dataBase;

        public SendMail2(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
            buttonSent = (Button) findViewById(R.id.ButtonSendFeedback);
            progressBar1.setVisibility(View.VISIBLE);
            buttonSent.setEnabled(false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
            progressBar1.setVisibility(View.INVISIBLE);
            buttonSent = (Button) findViewById(R.id.ButtonSendFeedback);
            buttonSent.setEnabled(false);
            Toast.makeText(getApplicationContext(),"Usuario Registrado",Toast.LENGTH_LONG).show();

            constructorData = new ConstructorData(context);
            constructorData.startDb();
            success1 = constructorData.insertUserData(alias, password, 0);

            if(success1 == true) {
                finish();
            } else {
                finish();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

    }

}
