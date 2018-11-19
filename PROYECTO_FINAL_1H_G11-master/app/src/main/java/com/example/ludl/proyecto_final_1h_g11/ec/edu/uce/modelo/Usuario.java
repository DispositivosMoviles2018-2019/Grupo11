package com.example.ludl.proyecto_final_1h_g11.ec.edu.uce.modelo;

import java.io.Serializable;

public class Usuario implements Serializable{
    private String apellidos = "";
    private String nombres = "";
    private String cedula = "";
    private String contrasena = "";

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}