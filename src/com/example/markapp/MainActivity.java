package com.example.markapp;

import com.example.markapp.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements OnClickListener {
	
	
	
	
	EditText nickname, password;
	TextView txt_new;
	Button btn_login;
	
	    
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        nickname = (EditText) findViewById(R.id.txt_nickname);
        password = (EditText) findViewById(R.id.txt_paswword);
        
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        
        txt_new = (TextView) findViewById(R.id.txt_register);
        txt_new.setOnClickListener(this);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onClick(View v) {
		if(v == btn_login){
			Toast.makeText(getApplicationContext(), "Verificando datos", Toast.LENGTH_SHORT).show();
			Log.d("Resultado del registro: ", "Entr� al boton login");
		}
		if(v == txt_new){
			Intent i = new Intent(this, UserRegister.class);
			startActivity(i);
			
		}
	}
	
	 
	 
	 
	
}
