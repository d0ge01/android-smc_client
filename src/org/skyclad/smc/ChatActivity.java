package org.skyclad.smc;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ChatActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setupActionBar();
		setContentView(R.layout.activity_chat);
		
		Intent intent = getIntent();
	    String ip = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	    String message = "";
	    String output = "";
	    
	    /*
	    ip = "Connection to " + ip;
	    TextView textView = (TextView) findViewById(R.id.chat_log);
	    textView.setTextSize(20);
	    textView.setText(ip);
	    */
	    EditText edittext_message = (EditText) findViewById(R.id.message_input);
	    message = (String) edittext_message.getText().toString();
	    
	    try {
			Socket clientSocket = new Socket(ip, 12345);
			DataOutputStream outToServer = (DataOutputStream) clientSocket.getOutputStream();
			outToServer.writeChars(message);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
