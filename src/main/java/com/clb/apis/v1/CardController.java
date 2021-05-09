package com.clb.apis.v1;

import static com.clb.apis.dto.ApiResult.failed;
import static com.clb.apis.dto.ApiResult.succeed;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import lombok.extern.java.Log;

@Log
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
	public ApiResult createCard(@RequestBody Card card){
		try {
			cardService.createCard(card);
			return succeed(card);
		}catch (Exception e) {
			return failed(e);
		}
	}
	
	@PutMapping("/{id}")
	public void replaceCard(
			@PathVariable("id") Long cardId,
			@RequestBody Card card)
	{
		cardService.replaceCard(cardId, card);
	}
	
	@PatchMapping("/{id}")
	public void modifyCard(
			@PathVariable("id") Long cardId,
			@RequestBody Card card)
	{
		cardService.modifyCard(cardId, card);
	}
	
	@DeleteMapping("/{id}")
	public ApiResult<Long> deleteCard(@PathVariable("id") Long cardId){
		cardService.deleteCard(cardId);
		return succeed(cardId);
	}
}
