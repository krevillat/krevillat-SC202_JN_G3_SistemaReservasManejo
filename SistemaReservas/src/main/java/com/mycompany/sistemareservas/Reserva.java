/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemareservas;

/**
 *
 * @author karin
 */
public class Reserva {

        private static  int contadorReservas = 0;
        private int idReserva;
        private String fecha;
        private String hora;
        private String tipoLicencia;  // Moto o Carro
        private String tipoPrueba; //teorica practica
        private String estado; //activa cancelada 
        private Usuario usuario;
        private CentroDePruebas centro;
           
        
        public Reserva(Usuario usuario, CentroDePruebas centro, String fecha, String hora, String tipoLicencia, String tipoPrueba){
            
            this.idReserva = ++contadorReservas;
            this.usuario = usuario;
            this.centro = centro;
            this.fecha = fecha;
            this.hora = hora;
            this.tipoLicencia = tipoLicencia;
            this.tipoPrueba = tipoPrueba;
            this.estado = "Activa";
            
        }
        
        public int getIdReserva(){
            return idReserva;
        }
        
        public String getFecha(){
            return fecha;
        }
        
        public String getHora(){
            return hora;
        }
        
        public String getTipoLicencia(){
            return tipoLicencia;
        }
        
        public String getTipoPrueba(){
            return tipoPrueba;
        }
        
        public String getEstado(){
            return estado;
        }
        
        public void cancelarReserva() {
            this.estado = "Cancelada";
            System.out.println("Reserva #" + idReserva + "ha sido cancelada");
        }
        
        public void mostrarDetalle(){
            
            System.out.println("*****Detalle de la Reserva*****");
            System.out.println("ID Reserva: " + idReserva);
            System.out.println("Centro: " + centro.getNombre());
            System.out.println("Fecha: " + fecha);
            System.out.println("Hora: " + hora);
            System.out.println("Tipo de Licencia: " + tipoLicencia);
            System.out.println("Tipo de Prueba: " + tipoPrueba);
            System.out.println("Estado: " + estado);
            System.out.println("*******************************");
        }
  
}
