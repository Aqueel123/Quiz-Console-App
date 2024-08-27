package com.home.telusko.model;

import java.util.Arrays;

public class Question {
	private int id;
	private String question;
	private String option[];
	private String answer;
	
	public Question(int id, String question, String[] option, String answer) {
		super();
		this.id = id;
		this.question = question;
		this.option = option;
		this.answer = answer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String[] getOption() {
		return option;
	}
	public void setOption(String[] option) {
		this.option = option;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", question=" + question + ", option=" + Arrays.toString(option) + ", answer="
				+ answer + "]";
	}
}
