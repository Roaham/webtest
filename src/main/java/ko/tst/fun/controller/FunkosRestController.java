package ko.tst.fun.controller;

import jakarta.validation.Valid;
import ko.tst.fun.DTOs.EstadisticasResponse;
import ko.tst.fun.DTOs.FunkoCreateRequest;
import ko.tst.fun.DTOs.FunkoResponse;
import ko.tst.fun.DTOs.FunkoUpdateRequest;
import ko.tst.fun.services.FunkosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("${api.path:/api}/${api.version:v1}/funkos")
public class FunkosRestController {

    private final FunkosService servicio;

    public FunkosRestController(FunkosService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<FunkoResponse>> buscar(
            @RequestParam Optional<String> categoria,
            @RequestParam Optional<String> nombre,
            @RequestParam Optional<BigDecimal> precioMax) {
        return ResponseEntity.ok(servicio.buscar(categoria, nombre, precioMax));
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<EstadisticasResponse> estadisticas() {
        return ResponseEntity.ok(servicio.estadisticas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FunkoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.findById(id));
    }

    @PostMapping
    public ResponseEntity<FunkoResponse> crear(
            @Valid @RequestBody FunkoCreateRequest funko) {
        var creado = servicio.save(funko);
        return ResponseEntity
                .created(URI.create("/api/v1/funkos/" + creado.id()))
                .body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FunkoResponse> reemplazar(
            @PathVariable Long id, @Valid @RequestBody FunkoCreateRequest funko) {
        return ResponseEntity.ok(servicio.replace(id, funko));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FunkoResponse> actualizar(
            @PathVariable Long id, @Valid @RequestBody FunkoUpdateRequest funko) {
        return ResponseEntity.ok(servicio.update(id, funko));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        servicio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}