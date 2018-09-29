package app.upc.com.appsafecity.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import app.upc.com.appsafecity.R;

public class RedesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redes);

        ImageView img_facebook = findViewById(R.id.i_facebook);
        ImageView img_twitter = findViewById(R.id.i_twitter);
        ImageView img_instagram = findViewById(R.id.i_instagram);

        img_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"));
                startActivity(browserIntent);
            }
        });

        img_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.instagram.com"));
                startActivity(browserIntent);
            }
        });

        img_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.twitter.com"));
                startActivity(browserIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.i_inicio:
                Intent intent = new Intent(this, GeneralActivity.class);
                startActivity(intent);
        }
        switch (item.getItemId()){
            case R.id.i_lugares:
                Intent intent = new Intent(this, MapaBasicoActivity.class);
                startActivity(intent);
        }
        switch (item.getItemId()){
            case R.id.i_redes:
                Intent intent = new Intent(this, RedesActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
