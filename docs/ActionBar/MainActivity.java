package kr.android.actionbar2;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//���� Activity�� ActionBar�� ���(getActionBar() )
		//��� ���ʿ� �ִ� �������� Ŭ������ �� ���ø����̼� Ȩ���� �̵�
		
		//Ȩ���� �� �� �ֵ��� ��ũ �� ��, Ÿ��Ʋ�� ���� ������
		//API���� 14���� ��밡��		
//		getActionBar().setHomeButtonEnabled(true);
		
		//API���� 11���� ��밡��
		//Ȩ���� ���ų� ���� �޴��� �̵��� ���, Ÿ��Ʋ�� �⺻������ �������� ����
										//ActionBar.DISPLAY_HOME_AS_UP: <�� �ְ� ��ũ�� �ִ�.
																	//ActionBar.DISPLAY_SHOW_HOME: �������� ������
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME);
		//Ÿ��Ʋ�� ���������� ó��
		getActionBar().setDisplayShowTitleEnabled(true);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// MenuInflater: XML������ �޴������� �о�鿩 �޴� ���
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.itSearch:
			Toast.makeText(this, "�˻�����", Toast.LENGTH_SHORT).show();
			return true;			
		case R.id.itChat:
			Toast.makeText(this, "ä������", Toast.LENGTH_SHORT).show();
			return true;			
		case R.id.itEmail:
			Toast.makeText(this, "����������", Toast.LENGTH_SHORT).show();
			return true;			
		case R.id.itsettings:
			Toast.makeText(this, "��������", Toast.LENGTH_SHORT).show();
			return true;		
		case android.R.id.home:
			Toast.makeText(this, "Ȩ����", Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}	
}
