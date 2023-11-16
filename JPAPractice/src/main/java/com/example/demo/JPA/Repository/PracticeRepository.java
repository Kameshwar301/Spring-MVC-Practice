package com.example.demo.JPA.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.JPA.Model.CreateModel;

@Repository
public interface PracticeRepository extends JpaRepository<CreateModel, Integer> {
	
	CreateModel save(CreateModel entity);
//	Iterable<CreateModel>saveAll(CreateModel entities);
	Optional<CreateModel>findById(Integer id);
	boolean existsById(Integer id);
	List<CreateModel> findAll();
	List<CreateModel> findAllById(Iterable<Integer> id);
	void deleteAllById(Iterable<? extends Integer> id);
	void deleteAll(Iterable<? extends CreateModel> entities);
	void deleteAll();
}
