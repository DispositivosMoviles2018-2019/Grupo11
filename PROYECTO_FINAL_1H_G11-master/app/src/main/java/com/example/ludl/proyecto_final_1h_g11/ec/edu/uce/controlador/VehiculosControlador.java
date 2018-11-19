package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.controlador;

import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.ServicioOperaciones;
import com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo.Vehiculo;

import java.util.List;

public class VehiculosControlador {

    public ServicioOperaciones op = new ServicioOperaciones();

    public void VehiculosControlador() {
    }

    public void guardarVehiculo(Vehiculo vehiculo) {
        op.guardarVehiculo(vehiculo);
    }

    public VehiculosControlador() {
    }

    public List<Vehiculo> getList() {
        return op.leerVehiculos();
    }

    public boolean eliminar(Vehiculo vehiculo) {
        return op.eliminarVehiculo(vehiculo);
    }
}