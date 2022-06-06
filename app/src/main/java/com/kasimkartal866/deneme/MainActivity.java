package com.kasimkartal866.deneme;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);
        Toast.makeText(this, "Toast Message", Toast.LENGTH_LONG).show();


        sharedPreferences = this.getSharedPreferences("com.kasimkartal866.deneme", Context.MODE_PRIVATE);
        int storedAge = sharedPreferences.getInt("storedAge", 0);
        textView.setText("Your Age: " + storedAge);

        if (storedAge == 0) {
            textView.setText("Your Age: ");
        } else {
            textView.setText("Your Age: " + storedAge);
        }

    }

    public void save(View view) {
        int userAge = Integer.parseInt(editText.getText().toString());
        textView.setText("Your age: " + userAge);
        sharedPreferences.edit().putInt("storedAge", userAge).apply();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Save");
        alert.setMessage("Are you sure?");
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Not Saved", Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();

    }

    public void delete(View view) {
        int storedData = sharedPreferences.getInt("storedAge", 0);
        if (storedData != 0) {
            sharedPreferences.edit().remove("storedAge").apply();
            textView.setText("Your Age: ");

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Delete");
            alert.setMessage("Are you sure?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_LONG).show();

                }
            });
            alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "Not Delete", Toast.LENGTH_LONG).show();
                }
            });
            alert.show();

        }
    }
}