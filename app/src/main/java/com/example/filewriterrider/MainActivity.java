package com.example.filewriterrider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private static String FILE_NAME = "content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


     //  сохранение файла
   public void saveText(View view){
       FileOutputStream fos = null;

       try {
           EditText textBox = (EditText) findViewById(R.id.editor);//создаем текст
           String text = textBox.getText().toString(); // nahodim etot text

           fos = openFileOutput(FILE_NAME, MODE_PRIVATE);// nahodim file i stavim private tk files mogut bit ispolzovani tolko vnutri nashego prilozhenia i dostupni tolko vladelcu prilojenia
           fos.write(text.getBytes());//sozdaem massiv baitov po razmeru string
           Toast.makeText(this, "Фаил успешно сохранен", Toast.LENGTH_LONG).show();
       } catch (FileNotFoundException e){ //obrabotka oshibok tsli fail ne naiden
           e.printStackTrace();
       } catch (IOException e) { // oshibka esli fail naiden no net dostupa na zapis'
           e.printStackTrace();
           Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
       }finally {
           try {                 //proverka ne null li nash potok zapisey
               if (fos != null) { // esli null to zakrit'
                   fos.close();
               }
           } catch (IOException e) {
               e.printStackTrace();
               Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
           }
       }
   }
//открытие файла
    public void openText(View view){
        FileInputStream fin = null; // sozdaem input kak peremennuu
        TextView textView = (TextView) findViewById(R.id.text); // nahodim pole dlya vivoda

        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()]; //massiv faila po razmeru dostupnih baytov
            fin.read(bytes); //chitaem file v massiv baytov kuda zapisat tekst dlya sohraneniya
            String text = new String(bytes); //peredaem v string
            textView.setText(text); //vivodim na ekran
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {

            try {
                if(fin != null){
                    fin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}