package com.service.pb.project.Service;

import java.util.List;

import com.service.pb.project.Models.Client;

public interface ClientService {
    List<Client> findAll();
    Client findById(long id);
    Client save(Client post);
}
