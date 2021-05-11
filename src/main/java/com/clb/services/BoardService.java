package com.clb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clb.models.Board;
import com.clb.repositories.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;

	public List<Board> getBoardList() {
		List<Board> boards = boardRepository.findAll();
		return boards;
	}

	public Board getBoardById(Long boardId) {
		Optional<Board> maybeBoard = boardRepository.findById(boardId);
		return maybeBoard.orElseThrow();
	}

	public Board createBoard(Board board) {
		Board createdBoard = boardRepository.save(board);
		return createdBoard;
	}

	@Transactional
	public Board replaceBoard(Long boardId, Board board) {
		boardRepository.findById(boardId).orElseThrow();
		Board replacedBoard = boardRepository.save(board);
		return replacedBoard;
	}

	public void deleteBoard(Long boardId) {
		boardRepository.deleteById(boardId);
	}
}
