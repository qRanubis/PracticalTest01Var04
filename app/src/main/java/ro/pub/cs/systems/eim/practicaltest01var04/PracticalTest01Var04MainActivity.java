package ro.pub.cs.systems.eim.practicaltest01var04;

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var04MainActivity extends AppCompatActivity {

    private static final int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    private CheckBox checkbox1, checkbox2;
    private EditText editText1, editText2;
    private Button buttonDisplayText, buttonActivityTransfer;
    private TextView labelDisplayText;

    private final BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("ro.pub.cs.systems.eim.practicaltest01var04.name")) {
                String name = intent.getStringExtra("name");
                Log.d("ServiceBroadcast", "Received name: " + name);
            } else if (intent.getAction().equals("ro.pub.cs.systems.eim.practicaltest01var04.group")) {
                String group = intent.getStringExtra("group");
                Log.d("ServiceBroadcast", "Received group: " + group);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var04_main);

        // Initialize UI components
        initializeViews();

        // Setup button listeners
        setupDisplayTextButton();
        setupActivityTransferButton();
    }

    private void initializeViews() {
        checkbox1 = findViewById(R.id.checkbox1);
        checkbox2 = findViewById(R.id.checkbox2);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        buttonDisplayText = findViewById(R.id.buttonDisplayText);
        buttonActivityTransfer = findViewById(R.id.buttonActivityTransfer);
        labelDisplayText = findViewById(R.id.labelDisplayText);
    }

    private void setupDisplayTextButton() {
        buttonDisplayText.setOnClickListener(v -> {
            if (!editText1.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty()) {
                Intent serviceIntent = new Intent(this, PracticalTest01Var04Service.class);
                serviceIntent.putExtra("name", editText1.getText().toString());
                serviceIntent.putExtra("group", editText2.getText().toString());
                startService(serviceIntent);
            } else {
                Toast.makeText(this, "Both fields must be filled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupActivityTransferButton() {
        buttonActivityTransfer.setOnClickListener(v -> {
            Intent intent = new Intent(PracticalTest01Var04MainActivity.this, PracticalTest01Var04SecondaryActivity.class);
            intent.putExtra("name", editText1.getText().toString());
            intent.putExtra("group", editText2.getText().toString());
            startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction("ro.pub.cs.systems.eim.practicaltest01var04.name");
        filter.addAction("ro.pub.cs.systems.eim.practicaltest01var04.group");
        registerReceiver(messageReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(messageReceiver);
    }

    @Override
    protected void onDestroy() {
        Intent serviceIntent = new Intent(this, PracticalTest01Var04Service.class);
        stopService(serviceIntent);
        super.onDestroy();
    }
}

