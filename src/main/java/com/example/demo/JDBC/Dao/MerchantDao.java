package com.example.demo.JDBC.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.example.demo.JDBC.Model.CreateMerchantModel;
import com.example.demo.JDBC.Model.Response;
import com.example.demo.JDBC.service.MerchantService;

@Component
public class MerchantDao implements MerchantService {
	
		Response res = new Response();
		String url = "jdbc:mysql://127.0.0.1:3306/training";
		String username = "root";
		String password = "Mobi@2023";
	
	@Override
	public Response createMerchant(CreateMerchantModel datas) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		datas.setCreatedDate(date);
		datas.setUpdatedDate(date);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try(Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();) {
				
				String insertQuery = "INSERT INTO training.merchant(m_name,m_email,m_phone,created_date,created_by,updated_date,updated_by)" 
						+ "values('"+datas.getName()+"','"+datas.getEmail()+"',"+datas.getPhone()+",'"+datas.getCreatedDate()+"','"+datas.getCreatedBy()+"','"+datas.getUpdatedDate()+"','"+datas.getUpdatedBy()+"');";
				
				System.out.println(insertQuery);
				st.executeUpdate(insertQuery);
				
				res.setResponseCode(200);
				res.setResponseMsg("Success");
				res.setData("Data inserted successfully");
			} catch (Exception e) {
				e.printStackTrace();
				
				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Internal server error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public Response updateMerchant(CreateMerchantModel datas) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		datas.setUpdatedDate(date);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try(Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();) {
				
				String updateQuery = "UPDATE training.merchant SET m_email='"+datas.getEmail()+"', m_phone="+datas.getPhone()+" WHERE m_id='"+datas.getId()+"';";
				
				System.out.println(updateQuery);
				st.executeUpdate(updateQuery);
				
				res.setResponseCode(200);
				res.setResponseMsg("Success");
				res.setData("Data updated successfully");
			} catch (Exception e) {
				e.printStackTrace();
				
				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Data are not updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Response getOneMerchant(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String selectQuery = "SELECT * FROM training.merchant where m_id="+id+";";
			System.out.println(selectQuery);
			
			try(Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(selectQuery);) {
				
				JSONArray jsonArray = new JSONArray();
				while(rs.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("name", rs.getString("m_name"));
					jsonObject.put("email", rs.getString("m_email"));
					jsonObject.put("phone", rs.getString("m_phone"));
					
					jsonArray.add(jsonObject);
				}
				
				res.setResponseCode(200);
				res.setResponseMsg("Success");
				res.setData("Data are retrived successfully");
				res.setjData(jsonArray);
					
			} catch (Exception e) {
				e.printStackTrace();
				
				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Data are not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Response deleteOneMerchant(int id) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try(Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();) {
				
				String deleteQuery = "DELETE FROM training.merchant WHERE m_id = "+id+";";
				st.executeUpdate(deleteQuery);
				
				res.setResponseCode(200);
				res.setResponseMsg("Success");
				res.setData("Data are deleted");
				
			} catch (Exception e) {
				e.printStackTrace();
				
				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Data are not deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Response createTransaction(CreateMerchantModel datas) {
		String uuid = UUID.randomUUID().toString();
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		String payment = "offline";
		
		datas.setTransactionReference(uuid);
		datas.setTransactionDate(date);
		datas.setTransactionType(payment);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try(Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();) {
				
				String insertQuery = "INSERT INTO training.transaction(t_id,t_ref,t_type,t_date) "
						+"values("+datas.getTransactionId()+",'"+datas.getTransactionReference()+"','"+datas.getTransactionType()+"','"+datas.getTransactionDate()+"');";
				System.out.println(insertQuery);
				st.executeUpdate(insertQuery);
				
				res.setResponseCode(200);
				res.setResponseMsg("Success");
				res.setData("Data inserted successfully");
				
			} catch (Exception e) {
				e.printStackTrace();
				
				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Data not inserted successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getTransactionData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String selectQuery = "SELECT m.m_name, m.m_email,t.t_ref,t.t_type,t.t_date FROM training.transaction as t inner join training.merchant as m on t.m_ref = m.m_id;";
			System.out.println(selectQuery);
			
			try(Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(selectQuery);) {
				
				JSONArray jsonArray = new JSONArray();
				while(rs.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("name", rs.getString("m_name"));
					jsonObject.put("email", rs.getString("m_email"));
					jsonObject.put("transactionReference", rs.getString("t_ref"));
					jsonObject.put("transactionType", rs.getString("t_type"));
					jsonObject.put("transactionDate", rs.getString("t_date"));
					
					jsonArray.add(jsonObject);
				}
				
				res.setResponseCode(200);
				res.setResponseMsg("Success");
				res.setData("Data are retrived successfully");
				res.setjData(jsonArray);
					
			} catch (Exception e) {
				e.printStackTrace();
				
				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Data are not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
