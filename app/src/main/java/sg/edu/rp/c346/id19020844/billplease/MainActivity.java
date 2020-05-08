package sg.edu.rp.c346.id19020844.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editAmt, editNop, editDis;
    ToggleButton svs, gst;
    Button btSplit, btReset;
    TextView tvTotal, tvPays, tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAmt = findViewById(R.id.editAmt);
        editNop = findViewById(R.id.editNop);
        editDis = findViewById(R.id.editDis);
        svs = findViewById(R.id.tbSvs);
        gst = findViewById(R.id.tbGst);
        btSplit = findViewById(R.id.btSplit);
        btReset = findViewById(R.id.btReset);
        tvTotal = findViewById(R.id.tvTotal);
        tvPays = findViewById(R.id.tvPays);
        tvMsg = findViewById(R.id.tvMsg);


        btSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editAmt.getText().toString().trim().length() == 0) {
                    return;
                }
                if (editNop.getText().toString().trim().length() == 0) {
                    return;
                }
                double amount = 0.0;
                if (!svs.isChecked() && !gst.isChecked()) {
                    amount = Double.parseDouble(editAmt.getText().toString());
                } else if (svs.isChecked() && !gst.isChecked()) {
                    amount = Double.parseDouble(editAmt.getText().toString()) * 1.1;
                } else if (!svs.isChecked() && gst.isChecked()) {
                    amount = Double.parseDouble(editAmt.getText().toString()) * 1.07;
                } else {
                    amount = Double.parseDouble(editAmt.getText().toString()) * 1.17;
                }


                if (editDis.getText().toString().trim().length() != 0) {
                    double dis = Double.parseDouble(editDis.getText().toString().trim());
                    amount = amount * (1 - dis / 100);
                }

                tvTotal.setText("Total Bill: $" + String.format("%.2f", amount));
                int numOfPax = Integer.parseInt(editNop.getText().toString());
                tvPays.setText("Each Pays $" + String.format("%.2f", amount / numOfPax));


            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editAmt.setText("");
                editNop.setText("");
                editDis.setText("");
            }
        });

    };
}
