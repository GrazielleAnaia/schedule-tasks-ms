package com.grazielleanaia.bff_schedulingtask_api.business;

import com.grazielleanaia.bff_schedulingtask_api.business.dto.out.ViaCepDTOResponse;
import com.grazielleanaia.bff_schedulingtask_api.infrastructure.client.CustomerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ViaCepService {

    private final CustomerClient customerClient;

    public ViaCepDTOResponse buscaEnderecoViaCep(String cep) {
        return customerClient.buscaDadosViaCep(cep);
    }
}

