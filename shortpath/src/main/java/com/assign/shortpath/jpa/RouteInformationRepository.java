package com.assign.shortpath.jpa;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface RouteInformationRepository extends JpaRepository<RouteInformation,Long> {

}
