package com.workstudy.workshop;

import java.util.ArrayList;

public class Reponse {
    protected int id;
    protected String nom;
    protected ArrayList<Ressource> ressources;

    public ArrayList<Ressource> getRessources (){
        return this.ressources;
    }
}
