package com.clb.apis.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.clb.models.Board;
import com.clb.models.Card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BoardDto {

	private Long id;
	private String title;
	private List<CardDto> cards  = new ArrayList<CardDto>();
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public BoardDto(Board source) {
		copyProperties(source, this);
		this.cards =  source.getCards().stream().map(CardDto::new).collect(Collectors.toList());
	}
	
	public Board toEntity() {
		return Board.builder()
				.id(id)
				.title(title)
				.cards(cards.stream().map(CardDto::toEntity).collect(Collectors.toList()))
				.build();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", id)
				.append("title", title)
				.append("cards", cards)
				.append("createdDate", createdDate)
				.append("modifiedDate", modifiedDate)
				.toString();
	}
}
