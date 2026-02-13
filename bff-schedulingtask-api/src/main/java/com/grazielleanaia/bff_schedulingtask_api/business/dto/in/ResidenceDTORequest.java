package com.grazielleanaia.bff_schedulingtask_api.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ResidenceDTORequest {

    private String street;

    private String complement;

    private String city;

    private String state;

    private Long zipCode;


}
