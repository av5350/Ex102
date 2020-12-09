package com.example.ex102;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * The type Main activity.
 *
 * @author Itey Weintraub <av5350@bs.amalnet.k12.il>
 * @version 1
 * @since 1 /12/2020  short description:      This activity let the user try 3 Alert Dialog's situations.
 */
public class MainActivity extends AppCompatActivity {
    final String[] colors = {"Red", "Green", "Blue"};
    int[] color;
    AlertDialog.Builder adb;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
    }

    /**
     * Change background to one of those colors: red / green / blue
     *
     * @param view the view
     */
    public void changeBackground(View view) {
        color = new int[]{0, 0, 0};

        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("list of colors - one choice");
        adb.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                color[which] = 255;
                layout.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
            }
        });

        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Show to user an multiply choice of colors
     * any combination he clicks, its apply the layout background color
     *
     * @param view the view
     */
    public void multiColorChange(View view) {
        color = new int[]{0, 0, 0};

        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("list of colors - multi choice");
        adb.setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked)
                    color[which] = 255;
            }
        });

        adb.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                layout.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
            }
        });

        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Rest layout background to white.
     *
     * @param view the view
     */
    public void restBackground(View view) {
        layout.setBackgroundColor(0);
    }

    /**
     * Get an input from the user and show it with a Toast
     *
     * @param view the view
     */
    public void inputDialog(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("Try input!");
        final EditText input = new EditText(this);
        input.setHint("Type text here");
        adb.setView(input);

        adb.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputString = input.getText().toString();
                Toast.makeText(MainActivity.this, inputString, Toast.LENGTH_SHORT).show();
            }
        });

        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Create the options menu
     *
     * @param menu the menu
     * @return ture if success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * go to credits activity if it was clicked at the menu
     *
     * @param item the item in menu that was clicked
     * @return true - if it success
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = (String) item.getTitle();

        // go to credits activity if clicked
        if (title.equals("Creadits"))
        {
            Intent si = new Intent(this, CreaditsActivity.class);
            startActivity(si);
        }

        return true;
    }

    public void bunusEditTexts(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("Try inputs!");

        // list view
        final LinearLayout bunusLayout = new LinearLayout(this);
        bunusLayout.setOrientation(LinearLayout.HORIZONTAL);

        // edit text 1
        final EditText input1 = new EditText(this);
        input1.setHint("Type text here");
        bunusLayout.addView(input1);

        // edit text 2
        final EditText input2 = new EditText(this);
        input2.setHint("Type text here");
        bunusLayout.addView(input2);

        adb.setView(bunusLayout);

        adb.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputString1 = input1.getText().toString();
                String inputString2 = input2.getText().toString();
                Toast.makeText(MainActivity.this, inputString1 + inputString2, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        AlertDialog ad = adb.create();
        ad.show();

    }
}