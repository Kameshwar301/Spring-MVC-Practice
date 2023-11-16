package com.example.demo.JPA.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.JPA.Model.CreateModel;
import com.example.demo.JPA.Model.Response;
import com.example.demo.JPA.Repository.PracticeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class PracticeService {

	Response res = new Response();
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PracticeRepository repo;

	public Response createEmp(CreateModel datas) {
		String uuid = UUID.randomUUID().toString();
		datas.setsNo(uuid);
		try {
			repo.save(datas);
		
			res.setResponseCode(200);
			res.setResponseMsg("Success");
			res.setData("Data is inserted");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			res.setResponseCode(500);
			res.setResponseMsg("error");
			res.setData("Data is not inserted");
		}
		return res;
	}

	public Response updateEmp(CreateModel datas) {
		try {
			Optional<CreateModel> user = repo.findById(datas.getEmpId());
			
			CreateModel cm = user.get();
			cm.setPhone(datas.getPhone());
			
			repo.save(cm);
			
			res.setResponseCode(200);
			res.setResponseMsg("Success");
			res.setData("Data Updated Successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			res.setResponseCode(500);
			res.setResponseMsg("error");
			res.setData("Internl Server Error");
		}
		return res;
	}

	public Response oneEmp(int empId) {
		try {
			res.setResponseCode(200);
			res.setResponseMsg("Success");
			res.setData("Data Updated Successfully");
			res.setjData(repo.findById(empId));
		} catch (Exception e) {
			e.printStackTrace();
			
			res.setResponseCode(500);
			res.setResponseMsg("error");
			res.setData("Internl Server Error");
		}
		return res;
	}

	public Response allEmp() {
		try {
//			List<CreateModel> list = repo.findAll();
//			for(Object lData : list) {
//				res.setjData(list);
//			}
			res.setResponseCode(200);
			res.setResponseMsg("Success");
			res.setData("Data is visible");
			res.setjData(repo.findAll());
			
		} catch (Exception e) {
			e.printStackTrace();
			
			res.setResponseCode(500);
			res.setResponseMsg("error");
			res.setData("Internl Server Error");
		}
		return res;
	}

	public Response deleteOne(int empId) {
		try {
			repo.deleteById(empId);
			res.setResponseCode(200);
			res.setResponseMsg("Success");
			res.setData("Data is deleted");
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setResponseCode(500);
			res.setResponseMsg("error");
			res.setData("Internl Server Error");
		}
		return res;
	}
	
}
