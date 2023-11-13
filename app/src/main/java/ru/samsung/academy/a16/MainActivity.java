package ru.samsung.academy.a16;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import ru.samsung.academy.a16.databinding.ActivityMainBinding;
import ru.samsung.academy.a16.utils.EditTextFilter;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int randomNum;
    private int trays;
    private Boolean again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        init();
        initView();
    }

    private void initView() {
        binding.inputText.setFilters(new InputFilter[]{new EditTextFilter(0, 20)});
        binding.buttonCheck.setOnClickListener(v -> {
            if (again) {
                init();
                return;
            }
            String number = binding.inputText.getText().toString();
            if (number.length() == 0) {
                return;
            }
            if (trays > 0) {
                if (Integer.parseInt(number) == randomNum) {
                    String res = "Вы побелили!!! Число - " + number;
                    binding.textStatus.setText(res);
                    binding.inputText.setText("");
                    binding.buttonCheck.setText("Заново");
                    again = true;
                } else if (Integer.parseInt(number) > randomNum) {
                    binding.textStatus.setText("Ваше число больше загаданного");
                    binding.inputText.setText("");
                    trays--;
                    binding.textTry.setText(new StringBuilder("Осталось попыток ").append(trays));
                } else {
                    binding.textStatus.setText("Ваше число меньше загаданного");
                    binding.inputText.setText("");
                    trays--;
                    binding.textTry.setText(new StringBuilder("Осталось попыток ").append(trays));
                }
            }
            if (trays == 0) {
                binding.textStatus.setText(new StringBuilder("Вы проиграли :( Загаданное число - ").append(randomNum));
                binding.buttonCheck.setText("Заново");
                again = true;
            }
        });
    }

    private void init() {
        randomNum = (int) (Math.random() * 100) % 21;
        trays = 3;
        binding.textTry.setText(new StringBuilder("Осталось попыток ").append(trays));
        binding.textStatus.setText("");
        binding.buttonCheck.setText("Проверить");
        again = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("TAG", "onOptionsItemSelected");
        int id = item.getItemId();
        PopupMenu popupMenu = new PopupMenu(this, binding.appBarLayout);
        if (id == R.id.font) {
            Log.d("TAG", "font");
            popupMenu.inflate(R.menu.menu_font);
            popupMenu.setOnMenuItemClickListener(v -> {
                if (v.getItemId() == R.id.times_new_roman) {
                    setFont(getResources().getFont(R.font.timesnewromanpsmt));
                }
                else if (v.getItemId() == R.id.ms_cans_serif) {
                    setFont(getResources().getFont(R.font.microsoftsansserif));
                }
                else {
                    setFont(getResources().getFont(R.font.couriernew));
                }
                return true;
            });
            popupMenu.show();
            return true;
        }
        else if (id == R.id.color) {
            Log.d("TAG", "color");
            popupMenu.inflate(R.menu.menu_color);
            popupMenu.setOnMenuItemClickListener(v -> {
                if (v.getItemId() == R.id.green) {
                    setColor(getResources().getColor(R.color.green, getTheme()));
                }
                else if (v.getItemId() == R.id.blue) {
                    setColor(getResources().getColor(R.color.blue, getTheme()));
                }
                else if (v.getItemId() == R.id.pink) {
                    setColor(getResources().getColor(R.color.ping, getTheme()));
                }
                else {
                    setColor(getResources().getColor(R.color.red, getTheme()));
                }
                return true;
            });
            popupMenu.show();
            return true;

        }
        else return super.onOptionsItemSelected(item);
    }

    private void setFont(Typeface typeface) {
        binding.textTitle.setTypeface(typeface);
        binding.textTry.setTypeface(typeface);
        binding.textStatus.setTypeface(typeface);
        binding.inputText.setTypeface(typeface);
        binding.buttonCheck.setTypeface(typeface);
    }
    private void setColor(int color) {
        binding.textTitle.setTextColor(color);
        binding.textTry.setTextColor(color);
        binding.textStatus.setTextColor(color);
        binding.inputText.setHintTextColor(color);
        binding.buttonCheck.setBackgroundColor(color);
    }


}