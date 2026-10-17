package com.upiiz.empleados.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Identificador único")
    private Long id;

    @Column(length = 100)
    @Schema(description = "Nombre del empleado", example = "Juan")
    private String nombre;

    @Column(length = 100)
    @Schema(description = "Apellido del empleado", example = "Pérez")
    private String apellido;

    @Column(length = 80)
    @Schema(description = "Cargo en la empresa", example = "Desarrollador Backend")
    private String puesto;

    @Column(precision = 10, scale = 2)
    @Schema(description = "Sueldo mensual", example = "15000.50")
    private BigDecimal salario;

    @Schema(description = "Fecha de ingreso", example = "2026-10-15")
    private LocalDate fechaContratacion;

    public Empleado() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }
    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }
    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }
}