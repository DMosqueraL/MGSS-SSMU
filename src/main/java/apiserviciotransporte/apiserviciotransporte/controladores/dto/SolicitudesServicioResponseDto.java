package apiserviciotransporte.apiserviciotransporte.controladores.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SolicitudesServicioResponseDto {
    private PaginationInfo paginationInfo;
    private List<SolicitudServicioDto> elements;


    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class PaginationInfo {
        private int totalPages;
        private long totalElements;
        private int currentPage;
        private int currentElements;
    }
}
