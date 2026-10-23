package ko.tst.fun.services;

import ko.tst.fun.DTOs.EstadisticasResponse;
import ko.tst.fun.DTOs.FunkoCreateRequest;
import ko.tst.fun.DTOs.FunkoResponse;
import ko.tst.fun.DTOs.FunkoUpdateRequest;

import java.math.BigDecimal;
import java.util.*;

public interface FunkosService {
    List<FunkoResponse> buscar(Optional<String> categoria, Optional<String> nombre,
                               Optional<BigDecimal> precioMax);
    FunkoResponse findById(Long id);
    FunkoResponse save(FunkoCreateRequest funko);
    FunkoResponse replace(Long id, FunkoCreateRequest funko);
    FunkoResponse update(Long id, FunkoUpdateRequest funko);
    void deleteById(Long id);
    EstadisticasResponse estadisticas();
}