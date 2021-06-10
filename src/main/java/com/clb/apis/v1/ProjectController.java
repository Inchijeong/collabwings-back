package com.clb.apis.v1;

import static com.clb.apis.dto.ApiResult.succeed;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clb.apis.dto.ApiResult;
import com.clb.apis.dto.ProjectDto;
import com.clb.models.Project;
import com.clb.services.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects")
public class ProjectController {

	private final ProjectService projectService;
	
	@GetMapping("")
	public ApiResult<List<ProjectDto>> getProjects(){
		List<ProjectDto> projects = projectService.getProjectsByOrderByIdDesc()
				.stream()
				.map(ProjectDto::new)
				.collect(Collectors.toList());
		return succeed(projects);
	}
	
	@GetMapping("/{id}")
	public ApiResult<ProjectDto> getProjectById(@PathVariable("id") Long projectId){
		Project project = projectService.getProjectById(projectId);
		return succeed(new ProjectDto(project));
	}
	
	@PostMapping("")
	public ApiResult<ProjectDto> createProject(@RequestBody ProjectDto project){
		Project createdProject = projectService.createProject(project.toEntity());
		return succeed(new ProjectDto(createdProject));
	}
	
	@PutMapping("/{id}")
	public ApiResult<ProjectDto> replaceProject(
			@PathVariable("id") Long projectId,
			@RequestBody ProjectDto project)
	{
		Project replacedProject = projectService.replaceProject(projectId, project.toEntity());
		return succeed(new ProjectDto(replacedProject));
	}
	
	@DeleteMapping("/{id}")
	public ApiResult<Long> deleteProject(@PathVariable("id") Long projectId){
		projectService.deleteProject(projectId);
		return succeed(projectId);
	}
}
