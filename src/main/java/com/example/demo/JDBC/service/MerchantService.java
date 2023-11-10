package com.example.demo.JDBC.service;

import org.springframework.stereotype.Service;

import com.example.demo.JDBC.Model.CreateMerchantModel;
import com.example.demo.JDBC.Model.Response;

@Service
public interface MerchantService {
	
	public Response createMerchant(CreateMerchantModel datas);
	public Response updateMerchant(CreateMerchantModel datas);
	public Response getOneMerchant(int id);
	public Response deleteOneMerchant(int id);
	
	public Response createTransaction(CreateMerchantModel datas);
	public Response getTransactionData();

}
