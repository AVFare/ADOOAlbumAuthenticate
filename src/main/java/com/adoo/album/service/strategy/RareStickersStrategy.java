package com.adoo.album.service.strategy;

import com.adoo.album.infrastructure.persistence.repository.StickerRepository;
import com.adoo.album.model.dto.ReporteDTO;
import com.adoo.album.model.entity.Sticker;
import org.springframework.stereotype.Component;
// ... (otros imports) ...
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RareStickersStrategy implements IReporteStrategy {

    private final StickerRepository stickerRepository;

    public RareStickersStrategy(StickerRepository stickerRepository) {
        this.stickerRepository = stickerRepository;
    }

    @Override
    public String getTipoReporte() {
        return "RARE_STICKERS";
    }

    @Override
    public ReporteDTO generar() {
        List<Sticker> stickersRaras = stickerRepository.findTop10ByOrderByRarezaDescStockDisponibleAsc();
        
        List<Map<String, Object>> resultados = stickersRaras.stream()
         .map(s -> {
             Map<String, Object> stickerMap = new java.util.HashMap<>();
             stickerMap.put("sticker_id", s.getId());
             // ... (resto de los .put) ...
             stickerMap.put("album_titulo", s.getAlbum().getTitulo()); 
             return stickerMap;
         })
         .collect(Collectors.toList());

        return new ReporteDTO(
            getTipoReporte(),
            "Figuritas con menor stock disponible (las más difíciles de obtener).",
            resultados
        );
    }
}