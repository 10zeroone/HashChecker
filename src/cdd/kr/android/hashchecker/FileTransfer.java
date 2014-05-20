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
	Button btn_Email;
	
	TextView tvState;
	
	Uri URI = null;

	String path =null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//화면에 무엇을 보여줄 지를 결정하는 함수
		setContentView(R.layout.activity_filetransfer);

		tvState = (TextView)findViewById(R.id.tvState);		

		//데이터 가져오기
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();

		if(bundle== null){	//데이터가 보관할 수 있는 번들객체가 null이면 데이터가 없음을 의미		
			tvState.append("\n\n 전달받은 데이터가 없습니다!");
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
            email.putExtra(Intent.EXTRA_SUBJECT, "파일 전달");
            email.putExtra(Intent.EXTRA_TEXT, "파일 첨부합니다.");
            if (URI != null) {
            	email.putExtra(Intent.EXTRA_STREAM, URI);
			}
            
            //prompts email client only
            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "메일을 보낼 프로그램 선택:"));
		}
		
	}
	
}
