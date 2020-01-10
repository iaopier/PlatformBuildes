package com.service.pb.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.pb.project.Models.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
