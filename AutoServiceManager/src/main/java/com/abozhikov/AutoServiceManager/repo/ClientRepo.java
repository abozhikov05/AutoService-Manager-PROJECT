package com.abozhikov.AutoServiceManager.repo;

import com.abozhikov.AutoServiceManager.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {

}
