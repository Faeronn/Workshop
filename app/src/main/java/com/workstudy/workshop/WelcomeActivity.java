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

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity{

    ArrayList<Question> questionList = new ArrayList<>();
    ArrayList<Reponse>  reponseList  = new ArrayList<>();
    Question question = new Question();
    Reponse  reponse  = new Reponse();

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        questionList = ReadXML.GetAllQuestions(WelcomeActivity.this);

        LinearLayout boutonsLayout = (LinearLayout) findViewById(R.id.buttonsLayout);
        TextView questionView = findViewById(R.id.questionView);
        for(Question question : questionList){

            questionView.setText(question.nom);

            for(Reponse reponse : question.reponse){
                Button btn = new Button(WelcomeActivity.this);
                btn.setText(reponse.nom);
                boutonsLayout.addView(btn);
            }
        }

    }
}
