package com.example.markapp;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.markapp.client.JsonHttpRequest;
import com.example.markapp.R;

public class UserRegister extends ActionBarActivity implements OnClickListener {
	
	// Propiedades de Registro:
	EditText fname, lname, nkname, pass;
	Button btn;
	
	// Propiedades de Tareas HTTP:
	private ProgressDialog pDialog;
	JSONObject json_rta;
	String result;
	// JSON Node names
    private static final String MESSAGE = "message";
    
    	
	
	JsonHttpRequest Json_Http_Request = new JsonHttpRequest();	
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	public static final String URL_REGISTER = "http://markapp.esy.es/MarkApp/db_register.php";
	//public static final String URL_REGISTER = "http://10.0.2.2/MarkApp/db_register.php";
		
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_register);
		
		fname = (EditText) findViewById(R.id.txt_name);
		lname = (EditText) findViewById(R.id.txt_last_name);
		nkname = (EditText) findViewById(R.id.txt_nickname);
		pass = (EditText) findViewById(R.id.txt_paswword);
		
		btn = (Button) findViewById(R.id.btn_register);
		btn.setOnClickListener(this);
	}

	
	@Override
	public void onClick(View v) {
		params.add(new BasicNameValuePair("firstname", fname.getText().toString()));
        params.add(new BasicNameValuePair("lastname", lname.getText().toString()));
        params.add(new BasicNameValuePair("nickname", nkname.getText().toString()));
        params.add(new BasicNameValuePair("password", pass.getText().toString()));
        
        
       
		new HTPPAsync().execute();
	}
	
	

public class HTPPAsync extends AsyncTask<String, String, String> {
	 
	@Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(UserRegister.this);
        pDialog.setMessage("Registrando usuario. Espere...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        
    }

	@Override
	protected String doInBackground(String... args) {
		//Se ejecuta la peticion Http y se recupera la tra en formato Json:
		json_rta = Json_Http_Request.JsonHttpRequest(URL_REGISTER, "POST", params);

        //Se muestra la rta json en el LogCat:
        Log.d("All Products: ", json_rta.toString());
        
        return null;
	}
	protected void onPostExecute(String file_url) {
        // dismiss the dialog after getting all products
        pDialog.dismiss();
        runOnUiThread(
        	new Runnable() {
        		public void run() {            	
        			try {
        			result = json_rta.getString(MESSAGE);   
        			}
        			catch (JSONException e) {
        				e.printStackTrace();
        				result = "Error interno!";
        			}
        	        Toast.makeText(UserRegister.this, result, Toast.LENGTH_LONG ).show();
        		}
            }
        );
                
    }

}


	
}
