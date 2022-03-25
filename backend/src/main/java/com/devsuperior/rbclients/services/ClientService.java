package com.devsuperior.rbclients.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.rbclients.dto.ClientDTO;
import com.devsuperior.rbclients.entities.Client;
import com.devsuperior.rbclients.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> list = repository.findAll();
		//converter Client para ClientDTO
		List<ClientDTO> listDto = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
		return listDto;
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		//Client entity = repository.findById(id).get();
		//ClientDTO dto = new ClientDTO(entity);
		//return dto;
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.get();
		return new ClientDTO(entity);
	}

}
