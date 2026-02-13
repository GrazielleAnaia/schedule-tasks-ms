package com.grazielleanaia.bff_schedulingtask_api.business;

import com.grazielleanaia.bff_schedulingtask_api.business.dto.in.CustomerDTORequest;
import com.grazielleanaia.bff_schedulingtask_api.business.dto.in.LoginDTORequest;
import com.grazielleanaia.bff_schedulingtask_api.business.dto.in.PhoneDTORequest;
import com.grazielleanaia.bff_schedulingtask_api.business.dto.in.ResidenceDTORequest;
import com.grazielleanaia.bff_schedulingtask_api.business.dto.out.CustomerDTOResponse;
import com.grazielleanaia.bff_schedulingtask_api.business.dto.out.PhoneDTOResponse;
import com.grazielleanaia.bff_schedulingtask_api.business.dto.out.ResidenceDTOResponse;
import com.grazielleanaia.bff_schedulingtask_api.business.dto.out.ViaCepDTOResponse;
import com.grazielleanaia.bff_schedulingtask_api.infrastructure.client.CustomerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CustomerService {
    private final CustomerClient customerClient;

    public ViaCepDTOResponse findCep(String cep) {
        return customerClient.buscaDadosViaCep(cep);
    }


    public CustomerDTOResponse createCustomer(CustomerDTORequest customerDTO) {
        return customerClient.createCustomer(customerDTO);
    }

    public String loginCustomer(LoginDTORequest loginDTORequest) {
        return customerClient.login(loginDTORequest);
    }

    public List<CustomerDTOResponse> getAllCustomers() {
        return customerClient.findAllCustomer();

    }

    public CustomerDTOResponse getCustomerByEmail(String email, String token) {
        return customerClient.findCustomerByEmail(email, token);
    }

    public void deleteCustomer(String email, String token) {
        customerClient.deleteCustomer(email, token);
    }

    public CustomerDTOResponse updateCustomer(CustomerDTORequest customerDTO, String token) {
        return customerClient.updateCustomer(customerDTO, token);
    }

    public ResidenceDTOResponse updateResidence(ResidenceDTORequest residenceDTO, Long id, String token) {
        return customerClient.updateResidence(residenceDTO, id, token);
    }

    public PhoneDTOResponse updatePhone(PhoneDTORequest phoneDTO, Long id, String token) {
        return customerClient.updatePhone(phoneDTO, id, token);
    }

    public ResidenceDTOResponse addResidence(ResidenceDTORequest residenceDTO, String token) {
        return customerClient.addResidence(residenceDTO, token);
    }

    public PhoneDTOResponse addPhone(PhoneDTORequest phoneDTO, String token) {
        return customerClient.addPhone(phoneDTO, token);
    }

}
