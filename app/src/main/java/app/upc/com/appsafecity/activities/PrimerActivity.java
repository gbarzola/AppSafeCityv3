package app.upc.com.appsafecity.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.upc.com.appsafecity.R;

public class PrimerActivity extends AppCompatActivity {

    Button continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);

        continuar = (Button) findViewById(R.id.button);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent continuar = new Intent(PrimerActivity.this, GeneralActivity.class);
                startActivity(continuar);
            }
        });
    }


}
