package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.vista;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ludl.proyecto_final_1h_g11.R;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador.ReservaControlador;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Reserva;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;


public class InsertarReserva extends AppCompatActivity {
    //Declaracion de Variables
    Button regresar;
    Button reservar;

    //Datos Insertar
    EditText num_reserva;
    EditText placa;
    EditText correo;
    EditText celular;

    String date_reserva;
    CalendarView selecfecha_reserva;
    Date fechaDate_reserva;
    TextView auxFecha_reserva;

    String date_entrega;
    CalendarView selecfecha_entrega;
    Date fechaDate_entrega;
    TextView auxFecha_entrega;

    EditText valor_reserva;

    Boolean val_placa = false;

    Reserva auxreserva;
    ReservaControlador controladorReserva;

    public ReservaControlador getReservaControlador() {
        return new ReservaControlador();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        controladorReserva=getReservaControlador();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserva);

        //Inicializar Variales y captura de datos

        regresar = (Button) findViewById(R.id.btn_regresar1);
        reservar = (Button) findViewById(R.id.btn_reservar);

        num_reserva = (EditText) findViewById(R.id.txt_numero_reserva);
        placa = (EditText) findViewById(R.id.txt_placa_reserva);
        correo = (EditText) findViewById(R.id.txt_email_reserva);
        celular = (EditText) findViewById(R.id.txt_celular);

        selecfecha_reserva = (CalendarView) findViewById(R.id.calendario_prestamo);
        auxFecha_reserva = (TextView) findViewById(R.id.text_fecha_prestamo);

        selecfecha_entrega = (CalendarView) findViewById(R.id.calendario_entrega);
        auxFecha_entrega = (TextView) findViewById(R.id.text_fecha_entrega);

        valor_reserva = (EditText) findViewById(R.id.txt_valor_reserva);


        //Captura y conversion de Fecha de reserva
        selecfecha_reserva.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int anio, int mes, int dia) {
                mes = mes + 1;
                date_reserva = anio + "/" + mes + "/" + dia;
                DateFormat formato;
                formato = new java.text.SimpleDateFormat("yyyy/MM/dd");
                try {
                    fechaDate_reserva = (Date) formato.parse(date_reserva);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                auxFecha_reserva.setText(date_reserva);
            }
        });

        //Captura y conversion de Fecha de entrega
        selecfecha_entrega.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int anio, int mes, int dia) {
                mes = mes + 1;
                date_entrega = anio + "/" + mes + "/" + dia;
                DateFormat formato;
                formato = new java.text.SimpleDateFormat("yyyy/MM/dd");
                try {
                    fechaDate_entrega = (Date) formato.parse(date_entrega);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                auxFecha_entrega.setText(date_entrega);
            }
        });

        Intent i = getIntent();

        auxreserva = (Reserva) i.getSerializableExtra("reserva");
        //   if (auxreserva != null) cargarReserva(auxreserva);


        //Evento Regresar
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newform = new Intent(InsertarReserva.this, VistaReserva.class);
                finish();
                startActivity(newform);
            }
        });


    }


    public void saveReserva(View view) {
        Pattern plc = Pattern.compile("^([A-Z]{3}-[0-9]{3,4})$");
        if (plc.matcher(placa.getText().toString()).matches() == false) {
            placa.setError("Placa Incorrecta");
            Toast.makeText(this, "Formato Incorrecto placa", Toast.LENGTH_SHORT).show();
            val_placa = false;
        } else {
            placa.setError(null);
            val_placa = true;
            try {
//nueva reserva
                if (auxreserva == null) {
                    auxreserva = new Reserva();
                    auxreserva.setId(null);
                    //Toast.makeText(this, "No hay reservas", Toast.LENGTH_SHORT).show();
                }

                //datos quemas de prueba
                auxreserva.setVehiculo_id(1);
                auxreserva.setEmail("miemail");
                auxreserva.setCelular("asd");


//guardar los campos en el objeto reserta
                //    auxVehiculo.setVehiculo(vehiculo.getText().toString());

              //  auxreserva.setId(num_reserva.getText().toString());

              String msg= controladorReserva.guadarReserva(auxreserva,placa.getText().toString());
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                this.mensaje("Datos Guardados");
                this.mensaje("Reserva Guardada");

                placa.setText("");

                correo.setText("");
                celular.setText("");

                valor_reserva.setText("");


                Intent newform = new Intent(InsertarReserva.this, VistaReserva.class);
                finish();
                startActivity(newform);

            } catch (Exception ex) {
                ex.printStackTrace();
                this.mensaje("Datos No Guardados");
            }
            Toast.makeText(this, "Formato Correcto", Toast.LENGTH_SHORT).show();
        }

    }

    public void mensaje(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }

    public void Aceptar(View view) {
        Intent newform = new Intent(InsertarReserva.this, VistaReserva.class);
        finish();
        startActivity(newform);
    }

}

