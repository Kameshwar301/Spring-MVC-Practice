package com.example.demo.JPA.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.JPA.Model.CreateModel;
import com.example.demo.JPA.Model.Response;
import com.example.demo.JPA.Service.PracticeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class PracticeController {
	
	@Autowired
	private PracticeService service;
	
	@PostMapping("/createJPA")
	public ResponseEntity<Response>createEmp(@RequestBody CreateModel datas){
	return ResponseEntity.ok(service.createEmp(datas));
	}
	
	@PutMapping("updateJpa")
	public ResponseEntity<Response>updateEmp(@RequestBody CreateModel datas){
		return ResponseEntity.ok(service.updateEmp(datas));
	}
	
	@GetMapping("/oneJpa")
	public ResponseEntity<Response>oneEmp(@RequestParam int empId){
		return ResponseEntity.ok(service.oneEmp(empId));
	}
	
	@GetMapping("/allJpa")
	public ResponseEntity<Response>allEmp(){
		return ResponseEntity.ok(service.allEmp());
	}
	
	@DeleteMapping("/deleteOneJpa")
	public ResponseEntity<Response>deleteOne(@RequestParam int empId){
		return ResponseEntity.ok(service.deleteOne(empId));
	}

}
