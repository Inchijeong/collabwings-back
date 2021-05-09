package com.clb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
		Optional<Card> maybecard = cardRepository.findById(cardId);
		return maybecard.get();
	}
	
	public void createCard(Card card) throws Exception{
		cardRepository.save(card);
	}
	
	public void replaceCard(Long cardId, Card card) {
		cardRepository.findById(cardId).ifPresent(c -> cardRepository.save(card));
	}
	
	public void modifyCard(Long cardId, Card card) {
		cardRepository.findById(cardId).ifPresent(c -> cardRepository.save(card));
	}
	
	public void deleteCard(Long cardId) {
		cardRepository.deleteById(cardId);
	}
}
