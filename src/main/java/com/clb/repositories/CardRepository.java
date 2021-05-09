package com.clb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clb.models.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
