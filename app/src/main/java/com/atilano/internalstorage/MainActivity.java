package com.atilano.internalstorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnSave, btnLoad;
    EditText etMsg;
    TextView tvDisplay;

    FileOutputStream fos;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        etMsg = (EditText) findViewById(R.id.etMsg);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
    }

    public void saveMessage (View view){
        String message = etMsg.getText().toString();

        try{
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Message saved!", Toast.LENGTH_LONG).show();
    }

    public void loadMessage (View view){
        StringBuffer buffer = new StringBuffer();
        int read = 0;

        try {
            fis = openFileInput("output.txt");
            while ((read = fis.read()) != -1) {
                buffer.append((char) read);
            }
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }
}
