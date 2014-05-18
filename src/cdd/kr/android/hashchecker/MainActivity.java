package cdd.kr.android.hashchecker;

import java.io.File;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener{

	private static final int REQUEST_PATH = 1;
	String curFileName;
	String curPath;
	static String path;
	String hashMD5;
	String hashSHA;
	
	private String SHAHash;
	public static int NO_OPTIONS=0;
	
	//���� ���� ǥ�ø� ����
	Button btnFileSearch1;
	Button btnFileSearch2;
	EditText etFilePath1;
	EditText etFilePath2;

	TextView tvMD5Result1;
	TextView tvSHA1Result1;	
	TextView tvMD5Result2;
	TextView tvSHA1Result2;
	TextView tvCompResult;
	
	//���� ��
	Switch swCompare;
	LinearLayout llComare;
	LinearLayout llCompResult;
	ImageView ivShare;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME);
		getActionBar().setDisplayShowTitleEnabled(true);

		etFilePath1 = (EditText)findViewById(R.id.etFilePath1);
		tvMD5Result1 = (TextView)findViewById(R.id.tvMD5Result1);
		tvSHA1Result1 = (TextView)findViewById(R.id.tvSHA1Result1);
		
		
		etFilePath2 =(EditText)findViewById(R.id.etFilePath2);
		tvMD5Result2 = (TextView)findViewById(R.id.tvMD5Result2);
		tvSHA1Result2 = (TextView)findViewById(R.id.tvSHA1Result2);
		
		llComare = (LinearLayout)findViewById(R.id.llComare);
		llCompResult = (LinearLayout)findViewById(R.id.llCompResult);
		tvCompResult =(TextView)findViewById(R.id.tvCompResult);
		ivShare = (ImageView)findViewById(R.id.ivShare);
		ivShare.setOnClickListener(this);

		//�ʱ�ȭ
		etFilePath1.setText("");
		tvMD5Result1.setText("");
		tvSHA1Result1.setText("");
		etFilePath2.setText("");
		tvMD5Result2.setText("");
		tvSHA1Result2.setText("");
		
		llComare.setVisibility(View.GONE);
		llCompResult.setVisibility(View.GONE);
		
		//�̺�Ʈ ����
		btnFileSearch1 = (Button)findViewById(R.id.btnFileSearch1);
		btnFileSearch1.setOnClickListener(this);		
		btnFileSearch2 = (Button)findViewById(R.id.btnFileSearch2);
		btnFileSearch2.setOnClickListener(this);
		
		//���� �� Ȱ��ȭ (���)
		swCompare = (Switch)findViewById(R.id.swCompare);				
		swCompare.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked!=false){
					llComare.setVisibility(View.VISIBLE);
					llCompResult.setVisibility(View.VISIBLE);
				}else{
					llComare.setVisibility(View.GONE);
					llCompResult.setVisibility(View.GONE);
				}
			}
		});
		
		//�ʱⰪ ���
		Toast.makeText(this, 	
				"1 path: " + etFilePath1.getText() + "\n" +
				"1 MD5 : " + tvMD5Result1.getText() + "\n" +
				"1 SHA1: " + tvSHA1Result1.getText() + "\n" +
				"2 path: " + etFilePath2.getText() +  "\n" +
				"2 MD5 : " + tvMD5Result2.getText() + "\n" +
				"2 SHA1: " + tvSHA1Result2.getText(),
				Toast.LENGTH_LONG).show();
	}
	
	
	
	
	
	//����Ʈ ȣ��
	public void getfile(View v){ 
    	Intent intent1 = new Intent(this, FileChooser.class);
        startActivityForResult(intent1,REQUEST_PATH);
        Toast.makeText(this, "Intent call", Toast.LENGTH_SHORT).show();
    }
	
/*	public void getCompare(View v){ 
    	Intent intent2 = new Intent(this, FileChooser.class);
        startActivityForResult(intent2,REQUEST_PATH);
    }*/

	 //����Ʈ ��� Listening
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // See which child activity is calling us back.
    	Toast.makeText(this, "onActivityResult() called", Toast.LENGTH_SHORT).show();
    	
    	if (requestCode == REQUEST_PATH){
    		if (resultCode == RESULT_OK) { 
    			curFileName = data.getStringExtra("GetFileName"); 
    			curPath = data.getStringExtra("GetPath");
            	
    			path = curPath + "/" + curFileName;
    			
    			Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
    			//etFilePath.setText(curPath + "/" + curFileName);
    			//etFilePath.setText(curFileName);
   			
    			/*computeMD5Hash(etFilePath.toString());
				computeSHAHash(etFilePath.toString());*/
    		}
    	 }
    }
	
    //�̺�Ʈ �ڵ鷯
	@Override
	public void onClick(View v) {
		
		Toast.makeText(this, "onClick() call", Toast.LENGTH_SHORT).show();
		
		
		
			if(v.getId()==R.id.btnFileSearch1){
				
				getfile(v);
				Toast.makeText(this, "1 getfile() called", Toast.LENGTH_SHORT).show();
				
				//���� ��� ǥ��
				etFilePath1.setText(path);
				//etFilePath.setText(curPath + "/" + curFileName);
				Toast.makeText(this, "1 path: " + path, Toast.LENGTH_SHORT).show();
				
				//���� �ؽ� ����
				try{
					File file = new File(path);
					tvMD5Result1.setText(MD5.calculateMD5(file));
				}catch(Exception e){
					e.printStackTrace();
				}			
				
//				computeMD5Hash(etFilePath1.toString());
//				tvMD5Result.setText(hashMD5);
//				computeSHAHash(etFilePath1.toString());
//				tvSHA1Result1.setText(hashSHA);
				
			}else if(v.getId()==R.id.btnFileSearch2){
				
				getfile(v);
				Toast.makeText(this, "2 getfile() called", Toast.LENGTH_SHORT).show();
				
				//���� ��� ǥ��
				etFilePath2.setText(path);
//				etFilePath2.setText(curPath + "/" + curFileName);
				//getCompare(v);

				//���� �ؽ� ����
				try{
					File file = new File(path);
					tvMD5Result2.setText(MD5.calculateMD5(file));
				}catch(Exception e){
					e.printStackTrace();
				}			
				
				
				/*computeSHAHash(etFilePath2.toString());
				tvSHA1Result2.setText(hashSHA);*/
				
				Toast.makeText(this, "File Compare ======", Toast.LENGTH_SHORT).show();
				
				//���� �� ����
				if(tvMD5Result1.toString() == tvMD5Result2.toString()){
				//if( tvMD5Result1.equals(tvMD5Result2) ){
					tvCompResult.setText("���� ����");
				}else{
					tvCompResult.setText("������ ����");
				}
				Toast.makeText(this, 
						"tvMD5 1:" + tvMD5Result1.getText() +"\n" +
						"tvMD5 2:" + tvMD5Result2.getText() , Toast.LENGTH_LONG).show();
			}else if(v.getId()==R.id.ivShare){
					// ȭ�� �̵�
					// ����Ʈ ��ü ����
					Intent intent = null;
										//���� ��Ƽ��Ƽ, �̵��� ��Ƽ��Ƽ
					intent = new Intent(this, FileTransfer.class);
					
					//ȭ�� �̵��ÿ� �̵��� ȭ�鿡�� ȣ���� �����͸� ����
					//					Editable -> String
					intent.putExtra("msg", etFilePath1.getText().toString());

					//����Ʈ�� �Ű������� ���� ��Ƽ��Ƽ ����
					startActivity(intent);
				}
	}
	
	
// String Hash ó�� =================================================================
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