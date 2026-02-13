package com.grazielleanaia.bff_schedulingtask_api.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PhoneDTOResponse {


    private String number;
    private Long id;
}
