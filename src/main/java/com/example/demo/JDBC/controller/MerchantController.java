package com.example.demo.JDBC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.JDBC.Dao.MerchantDao;
import com.example.demo.JDBC.Model.CreateMerchantModel;
import com.example.demo.JDBC.Model.Response;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class MerchantController {

	@Autowired
	private MerchantDao dao;
	
	@PostMapping("/create")
	public ResponseEntity<Response> createMerchant(@RequestBody CreateMerchantModel datas){
		return ResponseEntity.ok(dao.createMerchant(datas));
	}
	
	@PutMapping("/updateone")
	public ResponseEntity<Response> updateMerchant(@RequestBody CreateMerchantModel datas){
		return ResponseEntity.ok(dao.updateMerchant(datas));
	}
	
	@GetMapping("/getone")
	public ResponseEntity<Response> getOneMerchant(@RequestParam int id){
		return ResponseEntity.ok(dao.getOneMerchant(id));
	}
	
	@DeleteMapping("/deleteone")
	public ResponseEntity<Response> deleteOneMerchant(@RequestParam int id){
		return ResponseEntity.ok(dao.deleteOneMerchant(id));
	}
	
	@PostMapping("/createtransaction")
	public ResponseEntity<Response> createTransaction(@RequestBody CreateMerchantModel datas){
		return ResponseEntity.ok(dao.createTransaction(datas));
	}
	
	@GetMapping("/gettransactiondata")
	public ResponseEntity<Response> getTransactionData(){
		return ResponseEntity.ok(dao.getTransactionData());
	}
}