package com.devsuperior.rbclients.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.rbclients.dto.ClientDTO;
import com.devsuperior.rbclients.entities.Client;
import com.devsuperior.rbclients.repositories.ClientRepository;
import com.devsuperior.rbclients.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;

	/*
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> list = repository.findAll();
		//converter Client para ClientDTO
		List<ClientDTO> listDto = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
		return listDto;
	}
	*/
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> page = repository.findAll(pageRequest);
		Page<ClientDTO> pageDTO = page.map(x -> new ClientDTO(x));
		return pageDTO;
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		//Client entity = repository.findById(id).get();
		//ClientDTO dto = new ClientDTO(entity);
		//return dto;
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		
		//Recuperar pelo id
		Client entity = repository.getOne(id);
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		//salvar no banco
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}


