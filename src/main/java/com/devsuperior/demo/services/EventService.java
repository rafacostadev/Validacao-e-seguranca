package com.devsuperior.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	CityRepository cityRepository;

	@Transactional(readOnly = true)
	public Page<EventDTO> findAll(Pageable pageable) {
		return eventRepository.findAll(pageable).map(x -> new EventDTO(x));
	}

	@Transactional(readOnly = true)
	public EventDTO insert(EventDTO dto) {
		Event event = new Event();
		event.setCity(cityRepository.getReferenceById(dto.getCityId()));
		event.setDate(dto.getDate());
		event.setName(dto.getName());
		event.setUrl(dto.getUrl());
		return new EventDTO(eventRepository.save(event));
	}
}
