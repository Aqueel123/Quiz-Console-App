package com.home.telusko.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import com.home.telusko.model.Question;
import com.home.telusko.util.DBConnection;

public class QuizServiceImple implements QuizService{
	Question question[] = new Question[5];
	String quesOpt1[] = {"2","4","1","8"};
	String ansSelection[] = new String[5];
	
	ArrayList<String> actDBAns = new ArrayList<>();
	ArrayList<String> selectedAns = new ArrayList<>();
	
	public QuizServiceImple() {
		question[0] = new Question(1, "Size of Int in Java", quesOpt1, "4");
		question[1] = new Question(2, "Size of Float in Java", quesOpt1, "4");
 		question[2] = new Question(3, "Size of Long in Java", quesOpt1, "8");
 		question[3] = new Question(4, "Size of Double in Java", quesOpt1, "8");
 		question[4] = new Question(5, "Size of Char in Java", quesOpt1, "2");
	}

	@Override
	public void displayQuestion() {
		try {
			int temp=0;
	 		for(Question q: question) {
	 			System.out.println("Ques No."+ q.getId() + " "+ q.getQuestion());
	 			System.out.print("Option ");
	 			for(int i=0; i<quesOpt1.length; i++) {
	 				System.out.print(quesOpt1[i] + ",");
	 			}
	 			System.out.println();
	 			try (Scanner sc = new Scanner(System.in)) {
					ansSelection[temp] =  sc.nextLine();
				}
	 			temp++;
	 		}
	 		
	 		for(String sel: ansSelection) {
	 			System.out.print("Answer Selected :: "+ sel);
	 			System.out.println();
	 		}
		} catch (Exception e) {
			System.err.println("Exception inside QuizServiceImple.displayQuestion() "+ e);
		}
		
	}
	
	public void score() {
		int score = 0;
		for(int i=0; i<question.length; i++) {
			if(question[i].getAnswer().equals(ansSelection[i])) {
				score+=1;
			}
		}
		System.out.println();
		System.out.println("Your total score is : "+ score);
	}

	@Override
	public void displayDynamicQuestion() {
		Connection conn = DBConnection.getConnecttio();
		Statement questStmt = null;
		Statement optStmt = null;
		ArrayList<String> option = null;
		Scanner sc = null;
		try {
			System.out.println("Inside QuizServiceImple.displayDynamicQuestion()");
			
			questStmt =  conn.createStatement();
			optStmt = conn.createStatement();
			ResultSet questRs = questStmt.executeQuery("SELECT ID, QUEST, ANSWER FROM QUESTION");
			
			while(questRs.next()) {
				String questId = questRs.getString("ID");
				System.out.println(questRs.getString("QUEST"));
				
				ResultSet optRs = optStmt.executeQuery("SELECT OPTIONS FROM OPTIONS WHERE QUEST_ID = "+questId);
				option = new ArrayList<>();
				while (optRs.next()) {
					option.add(optRs.getString("OPTIONS"));
//					System.out.println("Options : "+ optRs.getString("OPTIONS"));
				}
				System.out.println("Options : "+ option);
				actDBAns.add(questRs.getString("ANSWER"));
				sc = new Scanner(System.in);
				selectedAns.add(sc.nextLine());
			}
		} catch (Exception e) {
			System.err.println("Exception inside QuizServiceImple.displayDynamicQuestion() "+ e);
		}finally {
			try {
				conn.close();
				questStmt.close();
				optStmt.close();
				sc.close();
			} catch (SQLException e) {
				System.err.println("Exception inside QuizServiceImple.displayDynamicQuestion()");
			}
		}
	}
	
	public void displayDynamicScore() {
		int score = 0;
		for(int i=0; i<selectedAns.size(); i++) {
			if(selectedAns.get(i).equalsIgnoreCase(actDBAns.get(i))) {
				score+=1;
			}
		}
		/*
		Iterator<String> itrSel =  selectedAns.iterator();
		Iterator< String> itrAct = actDBAns.iterator();
		while(itrSel.hasNext() && itrAct.hasNext()) {
			if(itrSel.next().equalsIgnoreCase(itrAct.next())) {
				score=+1;
			}
		}
		*/
		System.out.println("Your total score is : "+ score);
	}
}
