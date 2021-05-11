package com.clb.apis.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.clb.models.Project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProjectDto {

	private Long id;
	private String title;
	private List<BoardDto> boards;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public ProjectDto(Project source) {
		copyProperties(source, this);
		this.boards = 
				Optional.ofNullable(source.getBoards())
				.orElseGet(Collections::emptyList)
				.stream()
				.map(BoardDto::new)
				.collect(Collectors.toList());
	}
	
	public Project toEntity() {
		return Project.builder()
				.id(id)
				.title(title)
				.boards(boards != null ?
						boards.stream()
						.map(BoardDto::toEntity)
						.collect(Collectors.toList())
						: null)
				.build();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", id)
				.append("title", title)
				.append("boards", boards)
				.append("createdDate", createdDate)
				.append("modifiedDate", modifiedDate)
				.toString();
	}
}
