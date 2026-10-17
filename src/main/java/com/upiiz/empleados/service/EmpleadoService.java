package com.upiiz.empleados.service;

import com.upiiz.empleados.entity.Empleado;
import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> listarTodos();
    Optional<Empleado> buscarPorId(Long id);
    Empleado guardar(Empleado empleado);
    Optional<Empleado> actualizar(Long id, Empleado empleado);
    boolean eliminar(Long id);
}