package com.example.filewriterrider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
           EditText textBox = (EditText) view.findViewById(R.id.editor);
           String text = textBox.getText().toString();
           fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
           fos.write(text.getBytes());
           Toast.makeText(this, "Фаил успешно сохранен", Toast.LENGTH_LONG).show();
       } catch (FileNotFoundException e){
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
           Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
       }finally {
           try {
               if (fos != null) {
                   fos.close();
               }
           } catch (IOException e) {
               e.printStackTrace();
               Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
           }
       }
   }
//открытие файла


}