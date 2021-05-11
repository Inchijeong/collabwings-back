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
import com.clb.apis.dto.BoardDto;
import com.clb.models.Board;
import com.clb.services.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("")
	public ApiResult<List<BoardDto>> getBoardList(){
		List<BoardDto> boards = boardService.getBoardList()
				.stream()
				.map(BoardDto::new)
				.collect(Collectors.toList());
		return succeed(boards);
	}
	
	@GetMapping("/{id}")
	public ApiResult<BoardDto> getBoardById(@PathVariable("id") Long boardId){
		Board board = boardService.getBoardById(boardId);
		log.info(board.toString());
		return succeed(new BoardDto(board));
	}
	
	@PostMapping("")
	public ApiResult<BoardDto> createBoard(@RequestBody BoardDto board){
		Board createdBoard = boardService.createBoard(board.toEntity());
		log.info("board:" + createdBoard);
		return succeed(new BoardDto(createdBoard));
	}
	
	@PutMapping("/{id}")
	public ApiResult<BoardDto> replaceBoard(
			@PathVariable("id") Long boardId,
			@RequestBody BoardDto board)
	{
		Board replacedBoard = boardService.replaceBoard(boardId, board.toEntity());
		return succeed(new BoardDto(replacedBoard));
	}
	
	@DeleteMapping("/{id}")
	public ApiResult<Long> deleteBoard(@PathVariable("id") Long boardId){
		boardService.deleteBoard(boardId);
		return succeed(boardId);
	}
}
