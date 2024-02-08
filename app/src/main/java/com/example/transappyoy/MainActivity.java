package com.example.transappyoy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.transappyoy.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    HashMap<String, String> wordsForTr = new HashMap<>();
    Random rnd = new Random();
    String wordToTranslate;
    List<String> wordsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String, String> words = new HashMap<>();
        words.put("share", "делить");
        words.put("network", "сеть");
        words.put("web", "паутина");
        words.put("browser", "браузер");
        words.put("access", "доступ");
        words.put("provider", "провайдер");
        words.put("link", "ссылка");
        words.put("hyperlink", "гиперссылка");
        words.put("application", "приложение");
        words.put("procedure", "процедура");
        words.put("associate", "соединять");
        words.put("protect", "защищать");
        words.put("memory", "память");
        words.put("host", "хост");
        words.put("formatted", "форматированный");
        words.put("markup", "разметка");
        words.put("sequence", "последовательность");
        words.put("similarity", "сходство");
        words.put("retain", "сохранять");
        words.put("locate", "размещать");
        words.put("value", "значение");
        words.put("consecutive", "последовательный");
        words.put("multiple", "кратный");
        words.put("abacus", "счеты");
        words.put("calculus", "исчисление");
        words.put("manipulate", "управлять");
        words.put("keyboard", "клавиатура");
        words.put("mouse", "мышь");
        words.put("equation", "уравнение");
        words.put("guard", "защищать");
        words.put("template", "шаблон");
        words.put("pseudocode", "псевдокод");
        words.put("burden", "издержки");
        words.put("consume", "потреблять");
        words.put("emphasize", "выделять");
        Log.d("dasd", words.size() + "");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonEn){
                    Set<String> englishWords = words.keySet();
                    List<String> engWordsForTrans = new ArrayList<>(englishWords);
                    wordsList = engWordsForTrans;
                    wordToTranslate = setNewWord(rnd.nextInt(wordsList.size()));
                }
                if (checkedId == R.id.radioButtonRu){
                    Collection<String> russianWords = words.values();
                    List<String> rusWordsForTrans = new ArrayList<>(russianWords);
                    wordsList = rusWordsForTrans;
                    wordToTranslate = setNewWord(rnd.nextInt(wordsList.size()));
                }
            }
        });
        binding.radioButtonRu.setChecked(true);
        binding.textViewWordToTranslate.setText(wordToTranslate);
        binding.buttonCheckTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(binding.buttonCheckTranslate.getText().toString())){
                    int check = 0;
                    if (binding.radioButtonRu.isChecked()){
                        check = 1;
                    }
                    else {
                        check = 2;
                    }
                    try {
                        int finalCheck = check;
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String userInput = binding.editTextTextUserTranslate.getText().toString();
                                if (finalCheck == 1) {
                                    boolean foundMatch = false;
                                    for (String word : words.keySet()) {
                                        if (userInput.equalsIgnoreCase(word)) {
                                            foundMatch = true;
                                            break;
                                        }
                                    }
                                    if (foundMatch) {
                                        binding.textViewResult.setText("Правильно");
                                    } else {
                                        binding.textViewResult.setText("Не правильно");
                                    }
                                }
                                if (finalCheck == 2){
                                    boolean foundMatch = false;
                                    for (String word : words.values()) {
                                        if (userInput.equalsIgnoreCase(word)) {
                                            foundMatch = true;
                                            break;
                                        }
                                    }
                                    if (foundMatch) {
                                        binding.textViewResult.setText("Правильно");
                                    } else {
                                        binding.textViewResult.setText("Не правильно");
                                    }
                                }
                            }
                        }); thread.start();
                    } catch (Exception exception) {
                        Log.d("TAG", "onClick: " + exception.toString());
                    }


                    wordToTranslate = setNewWord(rnd.nextInt(wordsList.size()));
                    binding.textViewWordToTranslate.setText(wordToTranslate);
                }
                else {
                    Toast.makeText(MainActivity.this,
                            "Поле с переводом не должно быть пустым", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String setNewWord(int numWord){
        return wordsList.get(numWord);
    }
}