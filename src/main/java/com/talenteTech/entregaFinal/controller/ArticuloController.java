package com.talenteTech.entregaFinal.controller;

import com.talenteTech.entregaFinal.dto.ArticuloDTO;
import com.talenteTech.entregaFinal.entity.Articulo;
import com.talenteTech.entregaFinal.entity.Categoria;
import com.talenteTech.entregaFinal.services.ArticuloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping
    public ResponseEntity<List<ArticuloDTO>> listarArticulos() {
        List<ArticuloDTO> articulos = articuloService.listarArticulos().stream()
                .map(this::aDto)
                .toList();
        return ResponseEntity.ok(articulos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloDTO> obtenerArticuloPorId(@PathVariable Long id) {
        return articuloService.obtenerArticuloPorId(id)
                .map(this::aDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ArticuloDTO> crearArticulo(@RequestBody ArticuloDTO articuloDTO) {
        Articulo articuloNuevo = aEntidad(articuloDTO);
        articuloNuevo.setCodigo(null);
        Articulo articuloGuardado = articuloService.guardarArticulo(articuloNuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(aDto(articuloGuardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticuloDTO> actualizarArticulo(
            @PathVariable Long id,
            @RequestBody ArticuloDTO articuloDTO) {
        Optional<Articulo> articuloExistente = articuloService.obtenerArticuloPorId(id);
        if (articuloExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Articulo articuloActualizado = articuloService.actualizarArticulo(id, aEntidad(articuloDTO));
        return ResponseEntity.ok(aDto(articuloActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArticulo(@PathVariable Long id) {
        if (articuloService.obtenerArticuloPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        articuloService.eliminarArticulo(id);
        return ResponseEntity.noContent().build();
    }

    private Articulo aEntidad(ArticuloDTO articuloDTO) {
        Categoria categoria = null;
        if (articuloDTO.getCategoriaId() != null) {
            categoria = new Categoria();
            categoria.setId(articuloDTO.getCategoriaId());
        }

        return new Articulo(
                articuloDTO.getCodigo(),
                articuloDTO.getNombre(),
                articuloDTO.getPrecio(),
                categoria
        );
    }

    private ArticuloDTO aDto(Articulo articulo) {
        Long categoriaId = articulo.getCategoria() == null
                ? null
                : articulo.getCategoria().getId();
        return new ArticuloDTO(
                articulo.getCodigo(),
                articulo.getNombre(),
                articulo.getPrecio(),
                categoriaId
        );
    }
}
