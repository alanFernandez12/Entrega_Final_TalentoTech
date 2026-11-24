
// Paquete que contiene las interfaces de acceso a datos
package com.talenteTech.entregaFinal.repository;


import com.talenteTech.entregaFinal.entity.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Interfaz que define operaciones de acceso a datos para artículos
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    List<Articulo> findAll(); // Método para obtener todos los artículos
    Optional<Articulo> findById(Long id); // Método para obtener un artículo por ID
    Articulo save(Articulo articulo); // Método para guardar o actualizar un artículo
    void deleteById(Long id); // Método para eliminar un artículo por ID
}
