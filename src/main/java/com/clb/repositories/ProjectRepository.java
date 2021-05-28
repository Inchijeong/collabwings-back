package com.clb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clb.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	public List<Project> findAllByOrderByIdDesc();
}
