package com.example.sistemascasa.tigie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sistemascasa.tigie.db.BaseDatos;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.settings.ActivityEmail;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private Button btnSigIn;
    private Button btnFormEmail;
    private Context context;
    private EditText emailLogin;
    private  EditText passwordLogin;
    private BaseDatos dataBase;
    private ConstructorData constructorData;
    Boolean success;
    private EditText emailLoginV;

    String email;
    String pass;
    String status;
    String token;

    public static MainActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataBase = null;
        context  = getApplicationContext();
        constructorData = new ConstructorData(context);
        constructorData.startDb();

        ArrayList<Object> gotData = new ArrayList<Object>();
        dataBase = new BaseDatos(context);
        gotData = dataBase.getUserData();


        if(gotData == null) {
            status = "0";
        } else {


            int listSize = gotData.size();

            if(listSize > 0) {
                status = "1" ;
                email = gotData.get(0).toString();
                token = gotData.get(1).toString();

            } else {
                status = "0" ;
                email = "";
                token = "";
            }

        }

        super.onCreate(savedInstanceState);

        if (status.equals("1") || status.equals(1)) {

            setContentView(R.layout.activity_main);
            context = getApplicationContext();

            Intent intent_chapters = new Intent(getApplicationContext(), LinkerActivity.class);
            startActivity(intent_chapters);
            finish();
        } else {
            buttons ();
        }

    }

    public void buttons () {
        setContentView(R.layout.activity_login);

        btnSigIn = (Button) findViewById(R.id.sign_in);
        btnSigIn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                if (v == btnSigIn){
                    emailLogin      = (EditText) findViewById(R.id.email);
                    passwordLogin   = (EditText) findViewById(R.id.password);

                    if(emailLogin.getText().toString().equals("") || passwordLogin.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Los sentimos, debe ingresar las credenciales para acceder.", Toast.LENGTH_LONG).show();
                    } else {
                        validationCredentials(emailLogin.getText().toString(),  passwordLogin.getText().toString());
                    }
                }
            }
        });

        btnFormEmail = (Button) findViewById(R.id.send_email);
        btnFormEmail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent_chapters = new Intent(getApplicationContext(), ActivityEmail.class);
                startActivity(intent_chapters);

            }
        });
    }




    public void validationCredentials (String emailLogin, String passwordLogin) {

        email = emailLogin;
        pass  = passwordLogin;


        constructorData = new ConstructorData(context);
        success = constructorData.compareUser (email, pass);


        if (success) {
            Intent intent_chapters = new Intent(getApplicationContext(), LinkerActivity.class);
            startActivity(intent_chapters);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Los sentimos, sus credeciales son incorrectas.", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        instance = this;
    }

    @Override
    public void onPause() {
        super.onPause();
        instance = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        instance = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }

}
