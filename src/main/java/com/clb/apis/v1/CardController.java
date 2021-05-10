package com.clb.apis.v1;

import static com.clb.apis.dto.ApiResult.succeed;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clb.apis.dto.ApiResult;
import com.clb.models.Card;
import com.clb.services.CardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CardController {

	private final CardService cardService;
	
	@GetMapping("")
	public ApiResult<List<Card>> getCardList(){
		List<Card> cards = cardService.getCardList();
		return succeed(cards);
	}
	
	@GetMapping("/{id}")
	public ApiResult<Card> getCardById(@PathVariable("id") Long cardId){
		Card card = cardService.getCardById(cardId);
		return succeed(card);
	}
	
	@PostMapping("")
	public ApiResult<Card> createCard(@RequestBody Card card){
		Card createdCard = cardService.createCard(card);
		return succeed(createdCard);
	}
	
	@PutMapping("/{id}")
	public ApiResult<Card> replaceCard(
			@PathVariable("id") Long cardId,
			@RequestBody Card card)
	{
		Card replacedCard = cardService.replaceCard(cardId, card);
		return succeed(replacedCard);
	}
	
	@DeleteMapping("/{id}")
	public ApiResult<Long> deleteCard(@PathVariable("id") Long cardId){
		cardService.deleteCard(cardId);
		return succeed(cardId);
	}
}
