package com.abozhikov.AutoServiceManager.repo;

import com.abozhikov.AutoServiceManager.model.Client;
import com.abozhikov.AutoServiceManager.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRequestRepo extends JpaRepository<ServiceRequest, Long> {
   @Query("SELECT s.carBrand, COUNT(s) FROM ServiceRequest s GROUP BY s.carBrand ORDER BY COUNT(s) DESC")
    List<Object[]> countServicesByCarBrand();

}
