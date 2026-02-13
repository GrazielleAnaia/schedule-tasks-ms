package com.grazielleanaia.bff_schedulingtask_api.business.dto.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CustomerDTORequest {


    private String name;

    private String email;

    private String password;

    private List<PhoneDTORequest> phoneList;

    private List<ResidenceDTORequest> residenceList;
}
