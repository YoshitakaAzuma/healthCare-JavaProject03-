package com.example.healthcare2.controller;

import java.net.URI;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.healthcare2.dto.SignupRequestDto;
import com.example.healthcare2.service.UserRegistrationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRegistrationService userRegistrationService;
    private static final String BASE_FRONTEND_URL = "http://localhost:8080";

    // ユーザー登録のエンドポイント
    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute SignupRequestDto signupRequestDto,RedirectAttributes attributes) {

        // バリデーションエラーのチェック
		/*if (bindingResult.hasErrors()) {
		    String errorMessage = bindingResult.getAllErrors().stream()
		            .map(error -> error.getDefaultMessage())
		            .collect(Collectors.joining(", "));
		    return redirectToErrorPage(errorMessage);
		}*/

        try {
            // ユーザー登録処理
        	
        	System.err.println("登録処理開始");
        	
            userRegistrationService.registerUser(signupRequestDto);
            attributes.addFlashAttribute("success","登録されました");
            return new ModelAndView("redirect:" + BASE_FRONTEND_URL + "/login");
        } catch (DataIntegrityViolationException ex) { // データベースの一意制約違反のハンドリング
            String errorMessage = "メールアドレスが既に存在します";
            return redirectToErrorPage(errorMessage);
        } catch (Exception ex) {
            // 登録エラーのハンドリング
            return redirectToErrorPage(ex.getMessage());
        }
    }

    // エラーメッセージを持ったエラーページへのリダイレクト
    private ModelAndView redirectToErrorPage(String errorMessage) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(BASE_FRONTEND_URL + "/signup")
                .queryParam("error", errorMessage);

        URI errorPageUri = builder.build().encode().toUri();
        return new ModelAndView("redirect:" + errorPageUri.toString());
    }
}
