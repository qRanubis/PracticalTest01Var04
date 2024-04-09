package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var04SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_secondary);

        // Initialize views
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextGroup = findViewById(R.id.editTextGroup);
        Button buttonOk = findViewById(R.id.buttonOk);
        Button buttonCancel = findViewById(R.id.buttonCancel);

        // Get values from the intent extras sent from the main activity
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String group = intent.getStringExtra("group");
            // Set values to EditText fields
            editTextName.setText(name);
            editTextGroup.setText(group);
        }

        // Set click listener for Ok button
        buttonOk.setOnClickListener(v -> {
            // Return result to the main activity with OK result code
            Intent resultIntent = new Intent();
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Set click listener for Cancel button
        buttonCancel.setOnClickListener(v -> {
            // Return result to the main activity with CANCEL result code
            Intent resultIntent = new Intent();
            setResult(RESULT_CANCELED, resultIntent);
            finish();
        });
    }
}
