package cdd.kr.android.hashchecker;

import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FileTransfer extends Activity implements OnClickListener{
	Button btn_Wifi;
	Button btn_Bludtooth;
	Button btn_NFC;
	Button btn_SDCard;
	Button btn_QR;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		btn_SDCard = (Button)findViewById(R.id.btn_SDCard);
		btn_SDCard.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		
	}
	
	

	//Activityȣ���ؼ� ������� �����͸� ���޹޴� �޼ҵ�
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);

		if(data!=null){
			FileOutputStream fileOutputStream =null;
			try{
				Uri uri = data.getData();
														
				
				
				//���õ� SD Card�� �����(\storage\sdcard\) ������ �о�鿩 ���� ���ø����̼� ���� ����(\data\data\kr.android.gallery\files\test.jpg)�� �����ϴ� ��
				fileOutputStream = openFileOutput("test.jpg",  MODE_PRIVATE);
				//����, �ø�Ƽ					
				

			}catch(Exception e){
				Log.e("FileSearchByGallery", "IO Error", e);					

			}finally{
				if(fileOutputStream!=null){ try{fileOutputStream.close();}catch(IOException e){e.printStackTrace();} };					
			}
		}//end of if
	}// void
	
	
	
	
}
