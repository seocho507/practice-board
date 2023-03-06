package dev.seocho507.board.controller;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.seocho507.board.controller.request.CommentRequest;
import dev.seocho507.board.service.BoardService;
import dev.seocho507.board.service.CommentService;
import dev.seocho507.board.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
	private final BoardService boardService;
	private final MemberService memberService;

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("form", new CommentRequest());
		return "comments/form";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute(name = "form") CommentRequest request,
					  Authentication authentication,
					  BindingResult bindingResult) {

		authentication.getName();

		return "";
	}
}
