package com.clb.apis.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import java.time.LocalDateTime;

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
public class CardDto {

	private Long id;
	private String title;
	private String contents;
	private Long boardId;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public CardDto(Card source) {
		copyProperties(source, this);
		this.boardId = source.getBoard().getId();
	}
	
	public Card toEntity() {
		return Card.builder()
				.id(id)
				.title(title)
				.contents(contents)
				.board(Board.builder().id(boardId).build())
				.build();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", id)
				.append("title", title)
				.append("contents", contents)
				.append("boardId", boardId)
				.append("createdDate", createdDate)
				.append("modifiedDate", modifiedDate)
				.toString();
	}
}
