package com.clb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clb.models.Project;
import com.clb.repositories.ProjectRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@Service
@RequiredArgsConstructor
public class ProjectService {

	private final ProjectRepository projectRepository;

	public List<Project> getProjectList() {
		List<Project> projects = projectRepository.findAll();
		return projects;
	}

	public Project getProjectById(Long projectId) {
		Optional<Project> maybeProject = projectRepository.findById(projectId);
		return maybeProject.orElseThrow();
	}

	public Project createProject(Project project) {
		Project createdProject = projectRepository.save(project);
		return createdProject;
	}

	@Transactional
	public Project replaceProject(Long projectId, Project project) {
		projectRepository.findById(projectId).orElseThrow();
		Project replacedProject = projectRepository.save(project);
		return replacedProject;
	}

	public void deleteProject(Long projectId) {
		projectRepository.deleteById(projectId);
	}
}
