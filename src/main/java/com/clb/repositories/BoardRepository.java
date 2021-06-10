package com.clb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clb.models.Board;
import com.clb.models.Project;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

	public List<Board> findAllByOrderByIdDesc();
	public void deleteAll();
}
