package cdd.kr.android.hashchecker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FileTransfer extends Activity implements OnClickListener{
	Button btn_Wifi;
	Button btn_Bludtooth;
	Button btn_NFC;
	Button btn_Email;
	Button btn_QR;
	
	TextView tvState;
	
	Uri URI = null;

	String path =null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//ȭ�鿡 ������ ������ ���� �����ϴ� �Լ�
		setContentView(R.layout.activity_filetransfer);

		tvState = (TextView)findViewById(R.id.tvState);		

		//������ ��������
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();

		if(bundle== null){	//�����Ͱ� ������ �� �ִ� ���鰴ü�� null�̸� �����Ͱ� ������ �ǹ�		
			tvState.append("\n\n ���޹��� �����Ͱ� �����ϴ�!");
		}else{
			path = bundle.getString("msg");
			URI = Uri.parse("file://" + path);
			tvState.append("\n\n" + path);
		}
		
		btn_Email = (Button)findViewById(R.id.btn_Email);
		btn_Email.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == btn_Email){
			Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, "");
            email.putExtra(Intent.EXTRA_SUBJECT, "File transfer demo");
            email.putExtra(Intent.EXTRA_TEXT, "File message");
            if (URI != null) {
            	email.putExtra(Intent.EXTRA_STREAM, URI);
			}
            
            
            //prompts email client only
            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "Choose an Email client :"));
		}
		
		
	}
	
	
	
}
