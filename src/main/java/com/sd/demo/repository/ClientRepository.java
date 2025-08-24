package com.sd.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sd.demo.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

}
