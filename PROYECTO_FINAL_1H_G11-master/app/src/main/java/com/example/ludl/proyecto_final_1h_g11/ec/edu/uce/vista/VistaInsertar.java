package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.vista;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ludl.proyecto_final_1h_g11.R;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador.VehiculosControlador;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Vehiculo;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;


public class VistaInsertar extends AppCompatActivity {

    EditText vehiculo;
    EditText placa;
    EditText marca;
    //Date fecha;
    String date;
    CalendarView selecfecha;
    Date fechaDate;
    TextView auxFecha;
    EditText costo;
    Boolean matricula;
    EditText color;
    Button btnSave;
    //EditText correo;

    Boolean val_placa=false;

    Vehiculo  auxVehiculo;
    Spinner opcionMatricula;
    String[] opciones =  {"Si","No"};


    public VehiculosControlador getVehiculoControlador(){

        return new VehiculosControlador();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertar);

        btnSave = (Button)findViewById(R.id.btn_insertar);
        vehiculo = (EditText)findViewById(R.id.txt_vehiculo);
        placa = (EditText)findViewById(R.id.txt_placa);
        marca=(EditText) findViewById(R.id.txt_marca);
        costo=(EditText) findViewById(R.id.txt_costo);
        //correo = (EditText)  findViewById(R.id.txt_correo);

        selecfecha=(CalendarView) findViewById(R.id.calendario);
        auxFecha=(TextView) findViewById(R.id.text_fecha);

        opcionMatricula=(Spinner) findViewById(R.id.matricula);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,opciones);
        opcionMatricula.setAdapter(adapter);

        color=(EditText) findViewById(R.id.txt_color);


        selecfecha.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int anio, int mes, int dia) {
                mes=mes+1;
                date = anio+"/"+mes+"/"+dia;
                DateFormat formato;
                formato= new java.text.SimpleDateFormat("yyyy/MM/dd");
                try {
                    fechaDate=(Date)formato.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                auxFecha.setText(date);
            }
        });
        Intent i=getIntent();
         auxVehiculo=(Vehiculo)i.getSerializableExtra("vehiculo");
        if(auxVehiculo!=null)
            cargarVehiculo(auxVehiculo);
            //Toast.makeText(this, date,Toast.LENGTH_SHORT).show();

    }

    public void saveVehiculo(View v){

        Pattern plc = Pattern.compile("^([A-Z]{3}-[0-9]{3,4})$");
         if (plc.matcher(placa.getText().toString()).matches()== false){
             placa.setError("Placa Incorrecta");
             Toast.makeText(this, "Formato Incorrecto",Toast.LENGTH_SHORT).show();
             val_placa=false;
         }else {
             placa.setError(null);
             val_placa=true;
             try{

                 String seleccion=opcionMatricula.getSelectedItem().toString();
                 if(seleccion.equals("Si")){
                     matricula=true;
                 }else if (seleccion.equals("No")){
                     matricula=false;
                 }

                 EditText auxColor=(EditText) findViewById(R.id.txt_color);

                 if(auxVehiculo==null)
                     auxVehiculo= new Vehiculo();

                 auxVehiculo.setVehiculo(vehiculo.getText().toString());
                 auxVehiculo.setPlaca(placa.getText().toString());
                 auxVehiculo.setMarca(marca.getText().toString());
                 auxVehiculo.setFecFabricacion(fechaDate);
                 auxVehiculo.setCosto(Double.valueOf(costo.getText().toString()));
                 auxVehiculo.setMatriculado(matricula);
                 auxVehiculo.setColor(color.getText().toString());
                 //auxVehiculo.setCorreo(correo.getText().toString());


                 //System.out.println(vehiculo.getText().toString());

                 this.getVehiculoControlador().guardarVehiculo(auxVehiculo);

                 this.mensaje("Datos Guardados");

                 vehiculo.setText("");
                 placa.setText("");
                 marca.setText("");
                 //correo.setText("");


                 costo.setText("");
                 auxColor.setText("");

                 Intent newform = new Intent(VistaInsertar.this,VistaVehiculos.class);
                 finish();
                 startActivity(newform);

             }catch (Exception ex){
                 ex.printStackTrace();
                 this.mensaje("Datos No Guardados");
             }
             Toast.makeText(this, "Formato Correcto",Toast.LENGTH_SHORT).show();
         }
//        try{
//
//            String seleccion=opcionMatricula.getSelectedItem().toString();
//            if(seleccion.equals("Si")){
//                matricula=true;
//            }else if (seleccion.equals("No")){
//                matricula=false;
//            }
//
//            EditText auxColor=(EditText) findViewById(R.id.txt_color);
//
//            if(auxVehiculo==null)
//            auxVehiculo= new Vehiculo();
//
//            auxVehiculo.setVehiculo(vehiculo.getText().toString());
//            auxVehiculo.setPlaca(placa.getText().toString());
//            auxVehiculo.setMarca(marca.getText().toString());
//            auxVehiculo.setFecFabricacion(fechaDate);
//            auxVehiculo.setCosto(Double.valueOf(costo.getText().toString()));
//            auxVehiculo.setMatriculado(matricula);
//            auxVehiculo.setColor(color.getText().toString());
//            //auxVehiculo.setCorreo(correo.getText().toString());
//
//
//            //System.out.println(vehiculo.getText().toString());
//
//            this.getVehiculoControlador().guardarVehiculo(auxVehiculo);
//
//            this.mensaje("Datos Guardados");
//
//            vehiculo.setText("");
//            placa.setText("");
//            marca.setText("");
//            //correo.setText("");
//
//
//            costo.setText("");
//            auxColor.setText("");
//
//            Intent newform = new Intent(VistaInsertar.this,VistaVehiculos.class);
//            finish();
//            startActivity(newform);
//
//        }catch (Exception ex){
//            ex.printStackTrace();
//            this.mensaje("Datos No Guardados");
//        }
    }

    public void mensaje(String texto){
        Toast.makeText(this, texto,Toast.LENGTH_LONG).show();
    }


    public  void  Aceptar(View view){
        Intent newform = new Intent(VistaInsertar.this,VistaVehiculos.class);
        finish();
        startActivity(newform);
    }

    private void cargarVehiculo(Vehiculo v){
        btnSave.setText("Actualizar");
        this.vehiculo.setText(v.getVehiculo());
        this.placa.setText(v.getPlaca());
        this.marca.setText(v.getMarca());
        //this.correo.setText(v.getCorreo());
        this.auxFecha.setText(v.getFecFabricacion().toString());
        Boolean estadomatricula = v.isMatriculado();
        if(estadomatricula){
          //  Toast.makeText(this, estadomatricula.toString(),Toast.LENGTH_SHORT).show();
        }

        this.costo.setText(v.getCosto().toString());
        this.color.setText(v.getColor());
        //lo mismo para el resto de campos


    }

    public  void  RegresarVehiculo(View view){
        Intent newform = new Intent(VistaInsertar.this,VistaVehiculos.class);
        finish();
        startActivity(newform);
    }


}