package com.clb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clb.models.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
