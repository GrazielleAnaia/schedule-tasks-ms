package com.grazielleanaia.bff_schedulingtask_api.business.dto.out;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class ViaCepDTOResponse {
    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

}
