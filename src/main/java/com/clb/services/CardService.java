package com.clb.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clb.models.Card;
import com.clb.repositories.CardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
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
	
	public void createCard(Card card) {
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
