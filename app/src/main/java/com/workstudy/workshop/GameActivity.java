package com.workstudy.workshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private ConstraintLayout dialogBox;
    private ConstraintLayout optionsBox;
    private ConstraintLayout badEnding;
    private ConstraintLayout goodEnding;
    private ConstraintLayout mainPage;

    private int    money     = 500000;
    private double pollution = 15.0;
    private double happiness = 50.0;

    private ArrayList<Question> questionList = new ArrayList<>();
    private ArrayList<Reponse>  reponseList  = new ArrayList<>();
    private ArrayList<Ressource>  ressourceList  = new ArrayList<>();
    private Question question = new Question();
    private Question question1 = new Question();
    private Question question2 = new Question();

    private Reponse  reponse  = new Reponse();

    private Random rand = new Random();

    private TextView txt_question;
    private TextView txtNomVisiteur;
    private ImageView imgPortrait;

    private Button option1Button;
    private Button option2Button;
    private Button acceptButton;
    private Button refuseButton;

    private Button reloadGoodButton;
    private Button reloadBadButton;
    private Button quitGoodButton;
    private Button quitBadButton;

    private Ressource ressource = new Ressource();
    private TextView textView;



    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        changeFrontStats();

        this.optionsBox = findViewById(R.id.layout_questions);
        this.dialogBox = findViewById(R.id.layout_dialogBox);
        this.badEnding = findViewById(R.id.layout_bad_ending);
        this.goodEnding = findViewById(R.id.layout_good_ending);
        this.mainPage = findViewById(R.id.layout_mainPage);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        /*
        * TODOLIST :
        * TODO: Ajouter les questions tirées du fichier XML à une liste
        * TODO: Changer le texte du TextView txt_question avec la question dynamiquement
        * TODO: Changer le texte des boutons (accept / refuse) avec les réponses dynamiquement
        * TODO: Changer les images des options en fonction des catégories
        * TODO: Changer l'image & le texte du visiteur dynamiquement
        * TODO: Gestion des modificateurs de ressources
        * TODO: Gestion des assets en fonction des modificateurs ou des réponses
         */


        txt_question = findViewById(R.id.txt_question);
        txtNomVisiteur = findViewById(R.id.txt_nomVisiteur);
        imgPortrait = findViewById(R.id.img_portraitVisiteur);
        option1Button = findViewById(R.id.btn_option1);
        option2Button = findViewById(R.id.btn_option2);
        acceptButton  = findViewById(R.id.btn_accept);
        refuseButton  = findViewById(R.id.btn_refuse);

        reloadGoodButton = findViewById(R.id.btn_good_reload);
        reloadBadButton  = findViewById(R.id.btn_bad_reload);
        quitGoodButton   = findViewById(R.id.btn_good_stop);
        quitBadButton    = findViewById(R.id.btn_bad_stop);

        //option1Button.setBackground("@drawable/" + categorie);

        option1Button.setOnClickListener(this);
        option2Button.setOnClickListener(this);
        acceptButton.setOnClickListener(this);
        refuseButton.setOnClickListener(this);

        reloadGoodButton.setOnClickListener(this);
        reloadBadButton.setOnClickListener(this);
        quitGoodButton.setOnClickListener(this);
        quitBadButton.setOnClickListener(this);

        //this.dialogBox.setVisibility(View.GONE);
        this.goodEnding.setVisibility(View.GONE);
        this.badEnding.setVisibility(View.GONE);

        txt_question.setText("Bienvenue à toi jeune Maire, ici tu vas pouvoir faire des choix qui impacteront directement ta ville et tes ressources (argent, pollution, satisfaction des habitants), fais attention ou tu pourrais le regretter… À quoi ressemblera ta ville du futur ?");
        refuseButton.setText("D'accord");
        txtNomVisiteur.setText("Narrateur");
        imgPortrait.setImageDrawable(getResources().getDrawable(R.drawable.inconnu));


        acceptButton.setVisibility(View.GONE);
        option1Button.setVisibility(View.GONE);
        option2Button.setVisibility(View.GONE);


    }

    private boolean enableButton = true;
    private boolean tuto = true;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_option1:
                this.optionsBox.setVisibility(View.GONE);
                this.dialogBox.setVisibility(View.VISIBLE);

                displayIconCategoryToDialog(question1);
                question1.get_Elements(acceptButton, refuseButton, txt_question);
                question = question1;
                ScriptAléa(question1.id);

                //TODO: Virer la question de la liste, puis shuffle la liste
                break;

            case R.id.btn_option2:
                this.optionsBox.setVisibility(View.GONE);
                this.dialogBox.setVisibility(View.VISIBLE);

                displayIconCategoryToDialog(question2);
                question2.get_Elements(acceptButton, refuseButton, txt_question);
                question = question2;

                ScriptAléa(question2.id);

                //WARN : L'option2 est bien différente de la 1, puisqu'il y a 2 questions différentes associées.
                //TODO: Virer la question de la liste, puis shuffle la liste
                break;

            case R.id.btn_accept:
                //TODO : on applique les modificateurs sur les ressources
                //TODO : on ajoute l'asset si nécéssaire (centrale)

                //enableButton = checkYouHaveEnoughtMoney(1);

                if(questionList.size() <= 0){
                    if (pollution > 70){
                        this.badEnding.setVisibility(View.VISIBLE);
                    }else{
                        this.goodEnding.setVisibility(View.VISIBLE);
                    }

                }

                if (enableButton){
                    changeHudValues(1);
                    this.optionsBox.setVisibility(View.VISIBLE);
                    this.dialogBox.setVisibility(View.GONE);
                    acceptButton.setTextColor(getResources().getColor(R.color.black));
                }else {
                    acceptButton.setTextColor(getResources().getColor(R.color.red));
                }

                break;

            case R.id.btn_refuse:
                //TODO : on applique les modificateurs sur les ressources

                //enableButton = checkYouHaveEnoughtMoney(2);
                if (!tuto){
                    if(questionList.size() <= 0){
                        this.goodEnding.setVisibility(View.VISIBLE);
                    }

                    if (enableButton){
                        changeHudValues(2);
                        this.optionsBox.setVisibility(View.VISIBLE);
                        this.dialogBox.setVisibility(View.GONE);
                        acceptButton.setTextColor(getResources().getColor(R.color.black));
                    }else {
                        acceptButton.setTextColor(getResources().getColor(R.color.red));
                    }
                }else
                {
                    tuto = false;
                    questionList = ReadXML.GetAllQuestions(GameActivity.this);

                    this.dialogBox.setVisibility(View.GONE);
                    this.acceptButton.setVisibility(View.VISIBLE);
                    this.option1Button.setVisibility(View.VISIBLE);
                    this.option2Button.setVisibility(View.VISIBLE);
                    ScriptAléa(0);
                }
                break;

            case R.id.btn_bad_reload:
                questionList.clear();
                ressourceList.clear();
                reponseList.clear();
                startActivity(new Intent(GameActivity.this, WelcomeActivity.class));
                finish();

                break;

            case R.id.btn_good_reload:
                questionList.clear();
                ressourceList.clear();
                reponseList.clear();
                startActivity(new Intent(GameActivity.this, WelcomeActivity.class));
                finish();
                break;

            case R.id.btn_bad_stop:
                finish();
                System.exit(0);

                break;

            case R.id.btn_good_stop:
                finish();
                System.exit(0);
                break;
        }
    }

    public void changeHudValues (int idReponse){

        reponse = question.getReponseById(idReponse);
        ressourceList = reponse.getRessources();
        for (Ressource ressource : ressourceList) {

            switch (ressource.idRessource){
                case 1:
                    money += ressource.stat;

                    if (money <= 0){
                        money = 0;

                        this.badEnding.setVisibility(View.VISIBLE);
                    }
                    break;

                case 2:
                    pollution += ressource.stat;

                    if (pollution < 0){
                        pollution = 0;
                    }
                    else if (pollution >= 100){
                        pollution = 100;
                        happiness = 0;


                        this.badEnding.setVisibility(View.VISIBLE);
                    }

                    if(pollution >= 65){
                        mainPage.setBackground(getResources().getDrawable(R.drawable.game_screen_allbad));
                    }else {
                        mainPage.setBackground(getResources().getDrawable(R.drawable.game_screen_allgood));
                    }
                    break;

                case 3:
                    happiness += ressource.stat;

                    if (happiness <= 0){
                        happiness = 0;

                        this.badEnding.setVisibility(View.VISIBLE);

                    }
                    else if (happiness > 100){
                        happiness = 100;
                    }
                    break;
            }
        }

        changeFrontStats();

    }

    public void changeFrontStats (){

        TextView money        = findViewById(R.id.money);
        money.setText("    " + this.money); //TODO : On ne devrait pas mettre d'espaces
        TextView pollution    = findViewById(R.id.pollution);
        pollution.setText("        " + this.pollution + "%");//TODO : On ne devrait pas mettre d'espaces
        TextView satisfaction = findViewById(R.id.satisfaction);
        satisfaction.setText("        " + this.happiness + "%"); ////TODO : On ne devrait pas mettre d'espaces

    }

    public boolean checkYouHaveEnoughtMoney (int idReponse){
        boolean enought = true;

        ressourceList = question.getReponseById(idReponse).getRessources();
        for (Ressource ressource : ressourceList){
            if(ressource.idRessource == 1){
                if(money < ressource.stat){
                    enought = false;
                }
            }
        }

        return enought;
    }

    private int indexQuestion;

    public void ScriptAléa(int IdquestionDelete){


        if(IdquestionDelete>=1){
            for (Question question : questionList){
                if (question.id == IdquestionDelete){
                    indexQuestion = questionList.indexOf(question);
                    questionList.remove(indexQuestion);
                    break;
                }
            }
        }

        if (questionList.size() < 2){

            if (questionList.size() == 1){
                question1 = questionList.get(0);
                option2Button.setVisibility(View.GONE);
            }
            else if (questionList.size() == 0){
                option1Button.setVisibility(View.GONE);
            }

        }else {
            int q1;
            int q2;

            if(questionList.size() == 2){
                q1 = 0;
                q2 = 1;

                question1 = questionList.get(q1);
                question2 = questionList.get(q2);
            }else {
                q1 = rand.nextInt(questionList.size()-1);
                question1 = questionList.get(q1);
                q2 = q1;
                while (q2 == q1) {
                    q2 = rand.nextInt(questionList.size() - 1);
                    question2 = questionList.get(q2);
                }
            }

            displayIconCategory(question1, option1Button);
            displayIconCategory(question2, option2Button);
        }

    }

    public void displayIconCategory(Question questionToDisplayIcon, Button button){
        switch (questionToDisplayIcon.categorie){
            case "Evenement":
                button.setBackground(getResources().getDrawable(R.drawable.evenement));
                break;

            case "Habitant":
                button.setBackground(getResources().getDrawable(R.drawable.habitant));
                break;

            case "Nature":
                button.setBackground(getResources().getDrawable(R.drawable.nature));
                break;

            case "Technologie":
                button.setBackground(getResources().getDrawable(R.drawable.technologie));
                break;

            case "Global":
                button.setBackground(getResources().getDrawable(R.drawable.global));
                break;

            case "Nucleaire":
                button.setBackground(getResources().getDrawable(R.drawable.evenement));
                break;
        }
    }

    public void displayIconCategoryToDialog(Question questionToDisplayIcon){
        switch (questionToDisplayIcon.categorie){
            case "Evenement":
                txtNomVisiteur.setText("Banquier");
                imgPortrait.setImageDrawable(getResources().getDrawable(R.drawable.banquier));
                break;

            case "Habitant":
                txtNomVisiteur.setText("Habitant");
                imgPortrait.setImageDrawable(getResources().getDrawable(R.drawable.personne));
                break;

            case "Nature":
                txtNomVisiteur.setText("Rosette");
                imgPortrait.setImageDrawable(getResources().getDrawable(R.drawable.vache));
                break;

            case "Technologie":
                txtNomVisiteur.setText("Banquier");
                imgPortrait.setImageDrawable(getResources().getDrawable(R.drawable.banquier));
                break;

            case "Global":
                txtNomVisiteur.setText("Habitant");
                imgPortrait.setImageDrawable(getResources().getDrawable(R.drawable.personne));
                break;

            case "Nucleaire":
                txtNomVisiteur.setText("Banquier");
                imgPortrait.setImageDrawable(getResources().getDrawable(R.drawable.banquier));
                break;
        }
    }
}