package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.vista;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ludl.proyecto_final_1h_g11.R;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador.VehiculosControlador;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.GlobalApplication;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Vehiculo;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.vista.adapter.AdapterVehiculo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VistaVehiculos extends AppCompatActivity {

    private AdapterVehiculo adapter;
    private Vehiculo seleccionado;
    ArrayList<Vehiculo> arrayOfUsers;

    public VehiculosControlador getVehiculoControlador() {

        return new VehiculosControlador();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehiculos);

        File file = new File(GlobalApplication.getAppContext().getFilesDir(), "vehiculo.json");
        if (!file.exists()) {
            Toast.makeText(getApplicationContext(),"No exsite vehiculos reistrados",Toast.LENGTH_SHORT).show();
        }else{
            arrayOfUsers = new ArrayList<Vehiculo>(getVehiculoControlador().getList());
            adapter = new AdapterVehiculo(this, arrayOfUsers);
            final ListView listView = (ListView) findViewById(R.id.listaVehiculos);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    seleccionado = adapter.getItem(position);

                    for (int a = 0; a < parent.getChildCount(); a++) {
                        parent.getChildAt(a).setBackgroundColor(Color.TRANSPARENT);
                    }
                    view.setBackgroundResource(R.color.yourColor);
                }
            });
        }
    }

    public void Editar(View view) {
        Intent newform = new Intent(VistaVehiculos.this, VistaInsertar.class);
        newform.putExtra("vehiculo",seleccionado);
        finish();
        startActivity(newform);
    }

    public void Insertar(View view) {

        Intent newform = new Intent(VistaVehiculos.this, VistaInsertar.class);
        finish();
        startActivity(newform);
    }

    public void Eliminar(View view) {
        Toast.makeText(getApplicationContext(), "Eliminando...", Toast.LENGTH_SHORT).show();
        if (getVehiculoControlador().eliminar(seleccionado)) {
            arrayOfUsers.remove(seleccionado);
            adapter.notifyDataSetChanged();
        }
    }

    public void Desconectar(View view) {
        Intent newform = new Intent(VistaVehiculos.this, MainActivity.class);
        finish();
        startActivity(newform);
    }
}