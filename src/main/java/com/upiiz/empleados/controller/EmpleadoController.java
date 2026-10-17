package com.upiiz.empleados.controller;

import com.upiiz.empleados.entity.Empleado;
import com.upiiz.empleados.response.ApiResponseCustom;
import com.upiiz.empleados.service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Empleados", description = "Endpoints para la gestión de recursos de Empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService service;

    @Operation(summary = "Crear un empleado", description = "Registra un nuevo empleado en el sistema")
    @RequestBody(
            description = "Objeto empleado a crear",
            required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Empleado.class))
    )
    @ApiResponse(responseCode = "201", description = "Recurso creado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseCustom.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos o incompletos")
    @ApiResponse(responseCode = "409", description = "Conflicto (duplicado)")
    @ApiResponse(responseCode = "500", description = "Error del servidor")
    @PostMapping
    public ResponseEntity<ApiResponseCustom<Empleado>> crear(@org.springframework.web.bind.annotation.RequestBody Empleado empleado) {
        try {
            Empleado nuevo = service.guardar(empleado);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponseCustom<>("Éxito", "Recurso creado correctamente", nuevo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponseCustom<>("Error", "Error del servidor", null));
        }
    }

    @Operation(summary = "Obtener todos", description = "Retorna la lista completa de empleados")
    @ApiResponse(responseCode = "200", description = "Recurso encontrado")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @GetMapping
    public ResponseEntity<ApiResponseCustom<List<Empleado>>> listarTodos() {
        List<Empleado> lista = service.listarTodos();
        return ResponseEntity.ok(new ApiResponseCustom<>("Éxito", "Recurso encontrado", lista));
    }

    @Operation(summary = "Obtener por ID", description = "Busca un empleado específico por su ID")
    @ApiResponse(responseCode = "200", description = "Recurso encontrado")
    @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    @ApiResponse(responseCode = "404", description = "No encontrado")
    @ApiResponse(responseCode = "500", description = "Error interno")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseCustom<Empleado>> obtenerPorId(@PathVariable Long id) {
        Optional<Empleado> empleado = service.buscarPorId(id);
        if (empleado.isPresent()) {
            return ResponseEntity.ok(new ApiResponseCustom<>("Éxito", "Recurso encontrado", empleado.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseCustom<>("Error", "No encontrado", null));
        }
    }

    @Operation(summary = "Actualizar empleado", description = "Modifica los datos de un empleado existente")
    @RequestBody(
            description = "Nuevos datos del empleado",
            required = true,
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Empleado.class))
    )
    @ApiResponse(responseCode = "200", description = "Recurso actualizado")
    @ApiResponse(responseCode = "400", description = "Casos de error")
    @ApiResponse(responseCode = "404", description = "Casos de error")
    @ApiResponse(responseCode = "409", description = "Casos de error")
    @ApiResponse(responseCode = "500", description = "Casos de error")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseCustom<Empleado>> actualizar(@PathVariable Long id, @org.springframework.web.bind.annotation.RequestBody Empleado empleado) {
        Optional<Empleado> actualizado = service.actualizar(id, empleado);
        if (actualizado.isPresent()) {
            return ResponseEntity.ok(new ApiResponseCustom<>("Éxito", "Recurso actualizado", actualizado.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseCustom<>("Error", "No encontrado para actualizar", null));
        }
    }

    @Operation(summary = "Eliminar empleado", description = "Elimina un empleado por su ID")
    @ApiResponse(responseCode = "200", description = "Eliminación exitosa")
    @ApiResponse(responseCode = "400", description = "Casos de error")
    @ApiResponse(responseCode = "404", description = "Casos de error")
    @ApiResponse(responseCode = "500", description = "Casos de error")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseCustom<Void>> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.ok(new ApiResponseCustom<>("Éxito", "Eliminación exitosa", null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseCustom<>("Error", "No encontrado para eliminar", null));
        }
    }
}