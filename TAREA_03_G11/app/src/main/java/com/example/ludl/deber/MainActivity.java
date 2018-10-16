package com.example.ludl.deber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton1 = (Button) findViewById(R.id.ingresar);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String user = ((EditText)findViewById(R.id.nombre)).getText().toString();
               String pas = ((EditText)findViewById(R.id.password)).getText().toString();
               if (user.equals("luis") && pas.equals("dominguez")){

                   Intent newform = new Intent(MainActivity.this,MainLuisDominguez.class);
                   startActivity(newform);
               } else if (user.equals("ramiro") && pas.equals("leiton")){
                    Intent newform = new Intent(MainActivity.this,MainLuis2.class);
                    startActivity(newform);

                }else{
                   Toast.makeText(getApplicationContext(),"Usuario Incorrecto",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
