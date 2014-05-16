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
	
	

	//Activity호출해서 만들어진 데이터를 전달받는 메소드
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);

		if(data!=null){
			FileOutputStream fileOutputStream =null;
			try{
				Uri uri = data.getData();
														
				
				
				//선택된 SD Card에 저장된(\storage\sdcard\) 파일을 읽어들여 내부 어플리케이션 내부 공간(\data\data\kr.android.gallery\files\test.jpg)에 저장하는 것
				fileOutputStream = openFileOutput("test.jpg",  MODE_PRIVATE);
				//포멧, 컬리티					
				

			}catch(Exception e){
				Log.e("FileSearchByGallery", "IO Error", e);					

			}finally{
				if(fileOutputStream!=null){ try{fileOutputStream.close();}catch(IOException e){e.printStackTrace();} };					
			}
		}//end of if
	}// void
	
	
	
	
}
