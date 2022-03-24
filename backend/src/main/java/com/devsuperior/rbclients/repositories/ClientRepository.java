package com.devsuperior.rbclients.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.rbclients.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>  {

}
