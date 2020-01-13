package com.service.pb.project.Service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.service.pb.project.Models.Client;
import com.service.pb.project.Models.ClientIdade;
import com.service.pb.project.Repository.ClientRepository;
import com.service.pb.project.Service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public Client findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client save(Client client) {
		clientRepository.save(client);
		return client;
	}

	@Override
	public List<ClientIdade> getAllClients(Integer pageNo, Integer pageSize, String name, String cpf){
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Client queryClient = new Client();
		if (name.isEmpty()) {
			queryClient.setCpf(cpf);
		} else if (cpf.isEmpty()) {
			queryClient.setName(name);
		}
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "dataNascimento");
		Example<Client> clientExample = Example.of(queryClient, matcher);
		Page<Client> pagedResult = clientRepository.findAll(clientExample, paging);
		List<ClientIdade> clientOutput = new ArrayList<ClientIdade>();
		if (pagedResult.hasContent()) {
			for(Client client : pagedResult.getContent()) {
				ClientIdade clientIdade = new ClientIdade();
				clientIdade.setCpf(client.getCpf());
				clientIdade.setDataNascimento(client.getDataNascimento());
				clientIdade.setName(client.getName());
				clientIdade.setIdade(new Date(System.currentTimeMillis()));
				clientOutput.add(clientIdade);
			}
			return clientOutput;
		} else {
			return new ArrayList<ClientIdade>();
		}
	}
	
	@Override
	public boolean deleteByCpf(String cpf) {
		Client queryClient = new Client();
		queryClient.setCpf(cpf);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "dataNascimento","name");
		Example<Client> clientExample = Example.of(queryClient, matcher);
		if(clientRepository.findOne(clientExample).isPresent()) {
			try{
				clientRepository.delete(clientRepository.findOne(clientExample).get());
				return true;
			}catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	
	@Override
	public Optional<Client> findByCpf(String cpf) {
		Client queryClient = new Client();
		queryClient.setCpf(cpf);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "dataNascimento","name");
		Example<Client> clientExample = Example.of(queryClient, matcher);
		return clientRepository.findOne(clientExample);
	}
}

