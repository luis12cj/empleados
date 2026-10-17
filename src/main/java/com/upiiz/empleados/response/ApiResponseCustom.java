package com.upiiz.empleados.response;

public class ApiResponseCustom<T> {
    private String estado;
    private String mensaje;
    private T valores;

    public ApiResponseCustom(String estado, String mensaje, T valores) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.valores = valores;
    }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public T getValores() { return valores; }
    public void setValores(T valores) { this.valores = valores; }
}