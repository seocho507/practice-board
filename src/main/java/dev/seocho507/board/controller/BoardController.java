package dev.seocho507.board.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.seocho507.board.controller.request.BoardRequest;
import dev.seocho507.board.controller.response.BoardResponse;
import dev.seocho507.board.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/")
	public String index() {
		return "boards/index";
	}

	@GetMapping("/post")
	public String post(Model model) {
		model.addAttribute("form", new BoardRequest());
		return "boards/postform";
	}

	@PostMapping("/post")
	public String post(@Valid @ModelAttribute(name = "form") BoardRequest request,
					   BindingResult bindingResult,
					   Authentication authentication) {

		if (bindingResult.hasErrors()) {
			return "boards/postform";
		}

		boardService.create(request, authentication.getName());
		return "boards/list";
	}

	@GetMapping("/list")
	public String boards(Model model, Pageable pageable) {
		Page<BoardResponse> boards = boardService.findAll(pageable).map(BoardResponse::from);
		model.addAttribute("boards", boards);
		return "boards/list";
	}

	@GetMapping("/list/{boardId}")
	public String board(@PathVariable Long boardId, Model model) {
		final BoardResponse response = BoardResponse.from(boardService.findById(boardId));
		model.addAttribute("board", response);
		return "boards/detail";
	}
}
