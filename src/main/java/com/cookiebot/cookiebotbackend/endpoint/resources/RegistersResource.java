package com.cookiebot.cookiebotbackend.endpoint.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cookiebot.cookiebotbackend.core.domain.Registers;
import com.cookiebot.cookiebotbackend.core.domain.UserRegisters;
import com.cookiebot.cookiebotbackend.dao.services.RegistersService;

@RestController
@RequestMapping(value="/registers")
public class RegistersResource {

	@Autowired
	private RegistersService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Registers>> findAll() {
		List<Registers> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Registers> findById(@PathVariable String id) {
		Registers obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Registers> insert(@RequestBody Registers registers) {
		registers = service.insert(registers);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(registers.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}/users", method=RequestMethod.POST)
	public ResponseEntity<Void> insertUser(@PathVariable String id, @RequestBody UserRegisters userRegisters) {
		service.insertUser(id, userRegisters);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}/users", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable String id, @RequestBody UserRegisters userRegisters) {
		service.deleteUser(id, userRegisters);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}/users", method=RequestMethod.PUT)
	public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody UserRegisters userRegisters) {
		service.updateUser(id, userRegisters);
		return ResponseEntity.noContent().build();
	}
}
