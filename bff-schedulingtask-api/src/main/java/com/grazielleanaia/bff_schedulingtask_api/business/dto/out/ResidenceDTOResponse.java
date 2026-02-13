package com.grazielleanaia.bff_schedulingtask_api.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ResidenceDTOResponse {

    private String street;

    private String complement;

    private String city;

    private String state;

    private Long zipCode;

    private Long id;
}
