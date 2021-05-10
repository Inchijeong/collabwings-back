package com.clb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clb.models.Card;
import com.clb.repositories.CardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

	private final CardRepository cardRepository;

	public List<Card> getCardList() {		
		List<Card> cards = cardRepository.findAll();
		return cards;
	}
	
	public Card getCardById(Long cardId) {
		Optional<Card> maybeCard = cardRepository.findById(cardId);
		return maybeCard.orElseThrow();
	}
	
	public Card createCard(Card card) {
		Card createdCard = cardRepository.save(card);
		return createdCard;
	}
	
	@Transactional
	public Card replaceCard(Long cardId, Card card) {
		cardRepository.findById(cardId).orElseThrow();
		Card replacedCard = cardRepository.save(card);
		return replacedCard;
	}
	
	public void deleteCard(Long cardId) {
		cardRepository.deleteById(cardId);
	}
}
