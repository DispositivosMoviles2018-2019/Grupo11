package com.example.ludl.appdownloadimage;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //    private ImageView imagen;
    Button imagen;
    private Drawable mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = (Button) findViewById(R.id.imagen_descarga);
        imagen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.e("Estoy Aqui", "onClick");
        switch (v.getId()) {
            case R.id.imagen_descarga:
                descargar();
                break;
        }

    }

    public void descargar() {
        String urlDescargar = "https://blogs.sonymobile.com/es/files/2017/08/android-8-oreo-b29b6c96fac08090a80a1695bcffd924.jpg";
        new DescargarTask().execute(urlDescargar);
    }

    class DescargarTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... urlI) {
            String urlDescargar = urlI[0];
            HttpURLConnection conexion = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                URL url = new URL(urlDescargar);
                conexion = (HttpURLConnection) url.openConnection();
                conexion.connect();
                if (conexion.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Conexion Fallida";

                }

                input = conexion.getInputStream();
                String rutaFichero = getFilesDir() + "/Imagen.jpg";
                output = new FileOutputStream(rutaFichero);

                byte[] data = new byte[1024];
                int count;
                while ((count = input.read(data)) != -1) {
                    output.write(data, 0, count);

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Error" + e.getMessage();

            } catch (IOException e) {
                e.printStackTrace();
                return "Error" + e.getMessage();
            } finally {
                try {
                    if (input != null) input.close();
                    if (output != null) output.close();
                    if (conexion != null) conexion.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }


            return "Descarga Correcta";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

        }
    }

}
