package com.upiiz.empleados.service.impl;

import com.upiiz.empleados.entity.Empleado;
import com.upiiz.empleados.repository.EmpleadoRepository;
import com.upiiz.empleados.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository repository;

    @Override
    public List<Empleado> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Empleado> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        return repository.save(empleado);
    }

    @Override
    public Optional<Empleado> actualizar(Long id, Empleado empleadoActualizado) {
        return repository.findById(id).map(existente -> {
            existente.setNombre(empleadoActualizado.getNombre());
            existente.setApellido(empleadoActualizado.getApellido());
            existente.setPuesto(empleadoActualizado.getPuesto());
            existente.setSalario(empleadoActualizado.getSalario());
            existente.setFechaContratacion(empleadoActualizado.getFechaContratacion());
            return repository.save(existente);
        });
    }

    @Override
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}