package dev.seocho507.board.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.seocho507.board.controller.request.MemberJoinRequest;
import dev.seocho507.board.controller.request.MemberLoginRequest;
import dev.seocho507.board.controller.response.MemberResponse;
import dev.seocho507.board.domian.Member;
import dev.seocho507.board.dto.AuthMember;
import dev.seocho507.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/join")
	public String join(Model model) {
		model.addAttribute("requestDto", new MemberJoinRequest());
		return "joinform";
	}

	@PostMapping("/join")
	public String join(@Valid @ModelAttribute(name = "requestDto") MemberJoinRequest request,
					   BindingResult bindResult,
					   RedirectAttributes redirectAttributes) {

		if (bindResult.hasErrors()) {
			return "joinform";
		}

		final Member joinMember = memberService.join(MemberJoinRequest.toEntity(request));
		redirectAttributes.addAttribute("joinUserName", MemberResponse.from(joinMember).getUserName());
		return "redirect:/welcome";
	}

	@GetMapping("login")
	public String login(Model model) {
		model.addAttribute("form", new MemberLoginRequest());
		return "loginform";
	}

	@PostMapping("login")
	public String login(@Valid @ModelAttribute(name = "form") MemberLoginRequest request,
						HttpSession session,
						BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "loginform";
		}

		final AuthMember loginMember = memberService.login(request.getUserId(), request.getPassword());
		session.setAttribute("loginMember", loginMember.getUserId());
		return "redirect:/welcome";
	}

	@GetMapping("/checkUserId")
	@ResponseBody
	public boolean checkUserId(@RequestParam String userId) {
		return memberService.existsUserId(userId);
	}

}
