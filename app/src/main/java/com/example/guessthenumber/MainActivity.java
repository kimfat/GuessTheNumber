package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    boolean gameStartStatus = false;
    int guessedNumber = -1;
    int minNumber = 1;
    int maxNumber;
    final Random random = new Random();

    TextView tvInfo;
    EditText etInput;
    Button enterButton;
    Button newEasyGameButton;
    Button newNormalGameButton;
    Button newHardGameButton;
    Button exitButton;
    Toast newGame;

    protected void startNewGame(int min, int max){
        gameStartStatus = true;
        minNumber = min;
        maxNumber = max;
        guessedNumber = random.nextInt(maxNumber - minNumber) + minNumber;
        String newText = "Угадайте число от " + minNumber + " до " + maxNumber;
        tvInfo.setText(newText.toCharArray(), 0, newText.length());
        newGame.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.mainTxt);
        etInput = (EditText) findViewById(R.id.editTextNumber);
        enterButton = (Button) findViewById(R.id.enterBtn);
        newEasyGameButton = (Button) findViewById(R.id.startNewEasyGameBtn);
        newNormalGameButton = (Button) findViewById(R.id.startNewNormalGameBtn);
        newHardGameButton = (Button) findViewById(R.id.startNewHardGameBtn);
        exitButton = (Button) findViewById(R.id.exitBtn);
        newGame = Toast.makeText(getApplicationContext(),
                "Новая игра началась", Toast.LENGTH_SHORT);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!gameStartStatus){
                    if(guessedNumber == -1){
                        Toast.makeText(getApplicationContext(), "Игра еще не началась", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Вы уже выйграли, можете начать новую игру", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    String currentEtInput = etInput.getText().toString();
                    if (currentEtInput.equals("")){
                        Toast.makeText(getApplicationContext(),"Введите ваше число в поле выше!", Toast.LENGTH_SHORT).show();
                    }else{
                        int currentInt = Integer.parseInt(currentEtInput);
                        if (currentInt < minNumber){
                            Toast.makeText(getApplicationContext(),"Ваше число меньше минимально возможного числа!", Toast.LENGTH_SHORT).show();
                        }else if(currentInt > maxNumber){
                            Toast.makeText(getApplicationContext(),"Ваше число больше максимального возможного числа!", Toast.LENGTH_SHORT).show();
                        }else if(currentInt < guessedNumber){
                            Toast.makeText(getApplicationContext(),"Ваше число меньше загаданного числа!", Toast.LENGTH_SHORT).show();
                        }else if(currentInt > guessedNumber) {
                            Toast.makeText(getApplicationContext(), "Ваше число больше загаданного числа!", Toast.LENGTH_SHORT).show();
                        }else if(currentInt == guessedNumber) {
                            Toast.makeText(getApplicationContext(), "Вы угадали загаданное число " + guessedNumber + "! Урааа", Toast.LENGTH_SHORT).show();
                            gameStartStatus = false;
                        }

                    }
                }
            }
        });

        newEasyGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame(1, 10);
            }
        });

        newNormalGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame(1, 100);
            }
        });

        newHardGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame(1, 1000);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}