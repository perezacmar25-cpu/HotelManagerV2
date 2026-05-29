package com.salesianostriana.dam.hotelmanager.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.hotelmanager.model.PlanComida;
import com.salesianostriana.dam.hotelmanager.service.base.BaseServiceImpl;

@Service
public class PlanComidaService extends BaseServiceImpl<PlanComida, Integer, JpaRepository<PlanComida, Integer>> {
}
