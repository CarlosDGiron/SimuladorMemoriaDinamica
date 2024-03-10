package modelo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cana0
 */
abstract class Persona {
    protected String nombres, apellidos, direccion, fechaDeNacimiento, numeroDeTelefono;

    public Persona() {
    }

    protected String getNombres() {
        return nombres;
    }

    protected void setNombres(String nombres) {
        this.nombres = nombres;
    }

    protected String getApellidos() {
        return apellidos;
    }

    protected void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    protected String getDireccion() {
        return direccion;
    }

    protected void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    protected String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    protected void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    protected String getNumeroDeTelefono() {
        return numeroDeTelefono;
    }

    protected void setNumeroDeTelefono(String numeroDeTelefono) {
        this.numeroDeTelefono = numeroDeTelefono;
    }
    
    protected void crear(){
    }
    
    protected void leer(){
    }
    
    protected void actualizar(){
    }
    
    protected void borrar(){
    }
}
