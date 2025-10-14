package com.abozhikov.AutoServiceManager.repo;

import com.abozhikov.AutoServiceManager.model.Client;
import com.abozhikov.AutoServiceManager.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRequestRepo extends JpaRepository<ServiceRequest, Long> {
    Optional<ServiceRequest> findByClientName(String clientName);
}
