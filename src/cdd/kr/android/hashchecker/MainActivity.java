package cdd.kr.android.hashchecker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private static final int REQUEST_PATH = 1;
	String curFileName;
	String curPath;
	String hashMD5;
	String hashSHA;
	String path;
	
	Button btnFileSearch1;
	Button btnFileSearch2;
	EditText etFilePath;
	EditText etFilePath2;
	Switch swCompare;
	LinearLayout llComare;
	

	TextView tvCRC3Result;
	TextView tvMD5Result;
	TextView tvSHA1;
	TextView tvResult;

	
	private String SHAHash;
	public static int NO_OPTIONS=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME);
		getActionBar().setDisplayShowTitleEnabled(true);

		etFilePath = (EditText)findViewById(R.id.etFilePath);
		tvMD5Result = (TextView)findViewById(R.id.tvMD5Result);
		tvSHA1 = (TextView)findViewById(R.id.tvSHA1Result);
		
		
		etFilePath2 =(EditText)findViewById(R.id.etFilePath2);
		llComare = (LinearLayout)findViewById(R.id.llComare);
		tvResult =(TextView)findViewById(R.id.tvResult);

		//초기화
		etFilePath.setText("");
		etFilePath2.setText("");
		tvMD5Result.setText("");
		tvSHA1.setText("");
		
		//이벤트 연결
		btnFileSearch1 = (Button)findViewById(R.id.btnFileSearch1);
		btnFileSearch1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				getfile(v);
				
				//etFilePath.setText(curPath + "/" + curFileName);
				etFilePath.setText(path);
				
				File file = new File(path);
				tvMD5Result.setText(MD5.calculateMD5(file));
			}
		});
		computeMD5Hash(etFilePath.toString());
//		tvMD5Result.setText(hashMD5);
		computeSHAHash(etFilePath.toString());
		tvSHA1.setText(hashSHA);
		
		swCompare = (Switch)findViewById(R.id.swCompare);				
		swCompare.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked!=false){
					llComare.setVisibility(View.VISIBLE);
				}else{
					llComare.setVisibility(View.GONE);
				}
				
			}
		});
		btnFileSearch2 = (Button)findViewById(R.id.btnFileSearch2);
		btnFileSearch2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				getCompare(v);
				
				etFilePath2.setText(curPath + "/" + curFileName);
			}
		});
				
		computeSHAHash(etFilePath2.toString());
		tvResult.setText(hashSHA);
		
		
		//파일 비교 수행
		
	}
	
	public void getfile(View v){ 
    	Intent intent1 = new Intent(this, FileChooser.class);
        startActivityForResult(intent1,REQUEST_PATH);
    }
	
	public void getCompare(View v){ 
    	Intent intent2 = new Intent(this, FileChooser.class);
        startActivityForResult(intent2,REQUEST_PATH);
    }

	 // 결과 Listen
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // See which child activity is calling us back.
    	if (requestCode == REQUEST_PATH){
    		if (resultCode == RESULT_OK) { 
    			curFileName = data.getStringExtra("GetFileName"); 
    			curPath = data.getStringExtra("GetPath");
            	
    			path = curPath + "/" + curFileName;
    			//etFilePath.setText(curPath + "/" + curFileName);
    			//etFilePath.setText(curFileName);
    			
    			/*computeMD5Hash(etFilePath.toString());
				computeSHAHash(etFilePath.toString());*/
    		}
    	 }
    }

		
	private static String convertToHex(byte[] data) throws java.io.IOException 
	{
		StringBuffer sb = new StringBuffer();
		String hex=null;

		hex=Base64.encodeToString(data, 0, data.length, NO_OPTIONS);

		sb.append(hex);

		return sb.toString();
	}


	public void computeSHAHash(String s)
	{
		MessageDigest mdSha1 = null;
		try
		{
			mdSha1 = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e1) {
			Log.e("myapp", "Error initializing SHA1 message digest");
		}
		try {
			mdSha1.update(s.getBytes("ASCII"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] data = mdSha1.digest();
		try {
			SHAHash=convertToHex(data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		hashSHA = SHAHash.toString();
		//tvSHA1.setText(SHAHash);
	}


	public void computeMD5Hash(String s)
	{

		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			StringBuffer MD5Hash = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++)
			{
				String h = Integer.toHexString(0xFF & messageDigest[i]);
				while (h.length() < 2)
					h = "0" + h;
				MD5Hash.append(h);
			}

			hashMD5 = MD5Hash.toString();
			//tvMD5Result.setText(MD5Hash);

		}catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
	}
}
