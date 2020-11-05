package com.workstudy.workshop;

import android.icu.lang.UProperty;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Question {
    protected int id;
    protected String nom;
    protected String categorie;
    protected ArrayList<Reponse> reponse;

    public Reponse getReponseById(int idReponse){
        return this.reponse.get(idReponse - 1);
    }

    public void  get_Elements(Button btn1, Button btn2, TextView question){
        btn1.setText(this.reponse.get(0).nom);
        btn2.setText(this.reponse.get(1).nom);
        question.setText(this.nom);
    }
}
