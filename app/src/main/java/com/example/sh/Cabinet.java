package com.example.sh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Cabinet extends AppCompatActivity {

    public ImageView test,test2,test3,test4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabinet);
        findImageView();
        setOnClickImageView();
    }

    public void findImageView() {
         test = findViewById(R.id.cabinet);
         test2 = findViewById(R.id.handbook);
         test3 = findViewById(R.id.record);
         test4 = findViewById(R.id.disease);

    }

    public void setOnClickImageView(){
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest();
            }
        });

        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest2();
            }
        });

        test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest3();
            }
        });

        test4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaskTest4();
            }
        });



    }
    public void openTaskTest() {
        Intent intent = new Intent(this, Cabinet.class);
        startActivity(intent);
    }


    public void openTaskTest2() {
        Intent intent = new Intent(this, HandBook.class);
        startActivity(intent);
    }

    public void openTaskTest3() {
        Intent intent = new Intent(this, Record.class);
        startActivity(intent);
    }

    public void openTaskTest4() {
        Intent intent = new Intent(this, Disease.class);
        startActivity(intent);
    }

}