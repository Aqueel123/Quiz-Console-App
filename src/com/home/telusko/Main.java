package com.home.telusko;

import com.home.telusko.service.QuizService;
import com.home.telusko.service.QuizServiceImple;

public class Main {

	public static void main(String[] args) {
		QuizService qService = new QuizServiceImple();
		//Static Quiz App
//		qService.displayQuestion();
//		qService.score();
		
		//Dynamic Quiz App
		qService.displayDynamicQuestion();
		qService.displayDynamicScore();
	}

}
