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
import com.clb.apis.dto.CardDto;
import com.clb.models.Card;
import com.clb.services.CardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CardController {

	private final CardService cardService;
	
	@GetMapping("")
	public ApiResult<List<CardDto>> getCardList(){
		List<CardDto> cards = cardService.getCardList()
				.stream()
				.map(CardDto::new)
				.collect(Collectors.toList());
		return succeed(cards);
	}
	
	@GetMapping("/{id}")
	public ApiResult<CardDto> getCardById(@PathVariable("id") Long cardId){
		Card card = cardService.getCardById(cardId);
		return succeed(new CardDto(card));
	}
	
	@PostMapping("")
	public ApiResult<CardDto> createCard(@RequestBody CardDto card){
		Card createdCard = cardService.createCard(card.toEntity());
		return succeed(new CardDto(createdCard));
	}
	
	@PutMapping("/{id}")
	public ApiResult<CardDto> replaceCard(
			@PathVariable("id") Long cardId,
			@RequestBody CardDto card)
	{
		Card replacedCard = cardService.replaceCard(cardId, card.toEntity());
		return succeed(new CardDto(replacedCard));
	}
	
	@DeleteMapping("/{id}")
	public ApiResult<Long> deleteCard(@PathVariable("id") Long cardId){
		cardService.deleteCard(cardId);
		return succeed(cardId);
	}
}
