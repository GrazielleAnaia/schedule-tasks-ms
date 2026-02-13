package com.grazielleanaia.bff_schedulingtask_api.business.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomerDTOResponse {


    private String name;

    private String email;

    private String password;

    private List<PhoneDTOResponse> phoneList;

    private List<ResidenceDTOResponse> residenceList;
}
