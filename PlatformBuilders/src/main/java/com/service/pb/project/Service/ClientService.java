package com.service.pb.project.Service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.pb.project.Models.Client;
import com.service.pb.project.Models.ClientIdade;

public interface ClientService {
	List<Client> findAll();
	List<ClientIdade> getAllClients(Integer pageNo, Integer pageSize, String name, String cpf);
    Client findById(long id);
    Client save(Client client);
    boolean deleteByCpf(String cpf);
	Optional<Client> findByCpf(String cpf); 
}
