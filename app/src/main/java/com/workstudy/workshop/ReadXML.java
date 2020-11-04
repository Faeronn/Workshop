package com.workstudy.workshop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import com.workstudy.workshop.Question;


import androidx.appcompat.app.AppCompatActivity;

public class ReadXML {

    private static ArrayList<Question> questionList = new ArrayList<>();
    private static ArrayList<Reponse>  reponseList  = new ArrayList<>();
    private static Question question = new Question();
    private static Question retournQuestion = new Question();
    private static Reponse  reponse  = new Reponse();

    public static Question GetOneQuestion (Context context, final int idQuestion){
        try{
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            DefaultHandler handler = new DefaultHandler(){
                String currentValue = "";
                boolean currentElement = false;
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                    currentElement = true;
                    currentValue = "";
                    if(localName.equals("question")){
                        question = new Question();
                        question.reponse = new ArrayList<>();
                    }
                    else if(localName.equals("reponse")) {
                        reponse = new Reponse();
                    }
                }
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    currentElement = false;

                    if (localName.equalsIgnoreCase("id_question")){
                        question.id = Integer.parseInt(currentValue);
                    }
                    if (question.id == idQuestion){
                        if (localName.equalsIgnoreCase("nom_question")){
                            question.nom = currentValue;
                        }
                        else if (localName.equalsIgnoreCase("categorie")){
                            question.categorie = currentValue;
                        }
                        else if (localName.equalsIgnoreCase("id_reponse")){
                            reponse.id = Integer.parseInt(currentValue);
                        }
                        else if (localName.equalsIgnoreCase("nom_reponse")){
                            reponse.nom = currentValue;
                        }
                        else if (localName.equalsIgnoreCase("reponse")){

                            question.reponse.add(reponse);
                        }
                        else if (localName.equalsIgnoreCase("question")){
                            retournQuestion = question;
                        }
                    }
                }
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (currentElement) {
                        currentValue = currentValue +  new String(ch, start, length);
                    }
                }
            };

            InputStream istream = context.getAssets().open("questions.xml");
            parser.parse(istream,handler);

        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return retournQuestion;
    }

    public static ArrayList<Question> GetManyQuestion (Context context, final ArrayList<Integer> idQuestionList){

        try{
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            DefaultHandler handler = new DefaultHandler(){
                String currentValue = "";
                boolean currentElement = false;
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                    currentElement = true;
                    currentValue = "";
                    if(localName.equals("question")){
                        question = new Question();
                        question.reponse = new ArrayList<>();
                    }
                    else if(localName.equals("reponse")) {
                        reponse = new Reponse();
                    }
                }
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    currentElement = false;

                    if (localName.equalsIgnoreCase("id_question")){
                        question.id = Integer.parseInt(currentValue);
                    }

                    for (int idQuestion : idQuestionList) {
                        if (question.id == idQuestion){
                            if (localName.equalsIgnoreCase("nom_question")){
                                question.nom = currentValue;
                            }
                            else if (localName.equalsIgnoreCase("categorie")){
                                question.categorie = currentValue;
                            }
                            else if (localName.equalsIgnoreCase("id_reponse")){
                                reponse.id = Integer.parseInt(currentValue);
                            }

                            else if (localName.equalsIgnoreCase("nom_reponse")){
                                reponse.nom = currentValue;
                            }
                            else if (localName.equalsIgnoreCase("reponse")){

                                question.reponse.add(reponse);
                            }
                            else if (localName.equalsIgnoreCase("question")){

                                questionList.add(question);
                            }
                        }
                    }
                }
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (currentElement) {
                        currentValue = currentValue +  new String(ch, start, length);
                    }
                }
            };

            InputStream istream = context.getAssets().open("questions.xml");
            parser.parse(istream,handler);

        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return questionList;
    }

    public static ArrayList<Question> GetAllQuestions (Context context){

        try{
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            DefaultHandler handler = new DefaultHandler(){
                String currentValue = "";
                boolean currentElement = false;
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                    currentElement = true;
                    currentValue = "";
                    if(localName.equals("question")){
                        question = new Question();
                        question.reponse = new ArrayList<>();
                    }
                    else if(localName.equals("reponse")) {
                        reponse = new Reponse();
                    }
                }
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    currentElement = false;

                    if (localName.equalsIgnoreCase("id_question")){
                        question.id = Integer.parseInt(currentValue);
                    }
                    else if (localName.equalsIgnoreCase("nom_question")){
                        question.nom = currentValue;
                    }
                    else if (localName.equalsIgnoreCase("categorie")){
                        question.categorie = currentValue;
                    }
                    else if (localName.equalsIgnoreCase("id_reponse")){
                        reponse.id = Integer.parseInt(currentValue);
                    }

                    else if (localName.equalsIgnoreCase("nom_reponse")){
                        reponse.nom = currentValue;
                    }
                    else if (localName.equalsIgnoreCase("reponse")){

                        question.reponse.add(reponse);
                    }
                    else if (localName.equalsIgnoreCase("question")){

                        questionList.add(question);
                    }
                }
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (currentElement) {
                        currentValue = currentValue +  new String(ch, start, length);
                    }
                }
            };

            InputStream istream = context.getAssets().open("questions.xml");
            parser.parse(istream,handler);

        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return questionList;
    }
}
