package com.fujitsu.bootcamp.miniproject.Exam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class ExamService {

    

    public ArrayList<ExamModel> generateQuestion(){
        
        String fileName="C:\\Users\\f.balili\\OneDrive - FUJITSU\\Desktop\\Java\\Mini Project\\miniproject (5)\\miniproject5\\miniproject5\\miniproject\\src\\main\\resources\\question.csv";
        String stringQuestion=new String();
        
        ArrayList<ExamModel> question=new ArrayList<ExamModel>();
        String[] splitted=null;
    
        try {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        
            while((stringQuestion = br.readLine()) != null){
                ExamModel exam=new ExamModel();
                splitted=stringQuestion.split(",");
                exam.setQuestion(splitted[0]);
                exam.setAnswer(splitted[1]);
                question.add(exam);
            }                       
            br.close();
        }catch (FileNotFoundException e) {
            System.out.println("File not Found");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        return question;//return arraylist of ExamModel Oject
        
    }


    public String[] getAnswerKey() {

        String path = "C:\\Users\\f.balili\\OneDrive - FUJITSU\\Desktop\\Java\\Mini Project\\miniproject (5)\\miniproject5\\miniproject5\\miniproject\\src\\main\\resources\\question.csv";
        String dataLine = "";
        Integer dataLineCounter = 0;
        String[] answerkey = { "null", "null", "null", "null", "null", "null", "null", "null", "null", "null" };

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((dataLine = br.readLine()) != null) {
                String[] dataValues = dataLine.split(",");
                answerkey[dataLineCounter] = dataValues[1];
                dataLineCounter++;
            }
            br.close();
        } catch (ArithmeticException ae) {
            System.out.println("Arithmetic Exception occured");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return answerkey;

    }


    public  Float getScore(AnswerSaver answer) {

        Float result = 0.0f;
        Float scorectr = 0.0f;
        String[] answerkey=this.getAnswerKey();

        if (answerkey[0].equalsIgnoreCase(answer.getAnswer1())) {
            scorectr++;
        }
        if (answerkey[1].equalsIgnoreCase(answer.getAnswer2())) {
            scorectr++;
        }
        if (answerkey[2].equalsIgnoreCase(answer.getAnswer3())) {
            scorectr++;
        }
        if (answerkey[3].equalsIgnoreCase(answer.getAnswer4())) {
            scorectr++;
        }
        if (answerkey[4].equalsIgnoreCase(answer.getAnswer5())) {
            scorectr++;
        }
        if (answerkey[5].equalsIgnoreCase(answer.getAnswer6())) {
            scorectr++;
        }
        if (answerkey[6].equalsIgnoreCase(answer.getAnswer7())) {
            scorectr++;
        }
        if (answerkey[7].equalsIgnoreCase(answer.getAnswer8())) {
            scorectr++;
        }
        if (answerkey[8].equalsIgnoreCase(answer.getAnswer9())) {
            scorectr++;
        }
        if (answerkey[9].equalsIgnoreCase(answer.getAnswer10())) {
            scorectr++;
        }

        result = (scorectr / 10) * 100;

        return result;
    }


    
    
}
