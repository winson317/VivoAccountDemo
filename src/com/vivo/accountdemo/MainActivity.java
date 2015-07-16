package com.vivo.accountdemo;

import org.json.JSONException;
import org.json.JSONObject;

import com.vivo.account.base.activity.LoginActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = "MainActivity";
	public final static String KEY_LOGIN_RESULT = "LoginResult";
	public final static String KEY_SWITCH_ACCOUNT = "switchAccount";
	public final static String KEY_NAME = "name";
	public final static String KEY_OPENID = "openid";
	public final static String KEY_AUTHTOKEN = "authtoken";
	public final static String KEY_SHOW_TEMPLOGIN = "showTempLogin";
	private static final int REQUEST_CODE_LOGIN = 0;
	private Button loginBtn;
	private Button switchBtn;
	private TextView nameVal;
	private TextView openidVal;
	private TextView authtokenVal;
	private Context mContext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		loginBtn = (Button)findViewById(R.id.loginBtn);
		switchBtn = (Button)findViewById(R.id.switchBtn);
		nameVal = (TextView)findViewById(R.id.nameVal);
		openidVal = (TextView)findViewById(R.id.openidVal);
		authtokenVal = (TextView)findViewById(R.id.authtokenVal);
		loginBtn.setOnClickListener(this);
		switchBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		//登录帐户
		case R.id.loginBtn:
			Intent loginIntent = new Intent(this, LoginActivity.class);
//			loginIntent.putExtra(KEY_SHOW_TEMPLOGIN, false);
			startActivityForResult(loginIntent, REQUEST_CODE_LOGIN);
			break;
	    //切换帐户
		case R.id.switchBtn:
			Intent swithIntent = new Intent(this, LoginActivity.class);
			swithIntent.putExtra(KEY_SWITCH_ACCOUNT, true);
//			swithIntent.putExtra(KEY_SHOW_TEMPLOGIN, false);
			startActivityForResult(swithIntent, REQUEST_CODE_LOGIN);
			break;
		default:
			Log.d(TAG, "unsupport type");
			break;
		}
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d(TAG, "MainActivity, onActivityResult,requestCode="+requestCode+", resultCode="+resultCode);
		if(requestCode == REQUEST_CODE_LOGIN){
			if(resultCode == Activity.RESULT_OK){
				String loginResult = data.getStringExtra(KEY_LOGIN_RESULT);
				JSONObject loginResultObj;
				try {
					loginResultObj = new JSONObject(loginResult);
					String name = loginResultObj.getString(KEY_NAME);
					String openid = loginResultObj.getString(KEY_OPENID);
					String authtoken = loginResultObj.getString(KEY_AUTHTOKEN);
					nameVal.setText(name);
					openidVal.setText(openid);
					authtokenVal.setText(authtoken);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
//				Toast.makeText(mContext, loginResult, Toast.LENGTH_SHORT).show();
				Log.d(TAG, "loginResult="+loginResult);
			}
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}
}
