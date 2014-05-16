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
		
		//현재 Activity의 ActionBar를 얻고(getActionBar() )
		//상단 왼쪽에 있는 아이콘을 클릭했을 때 어플리케이션 홈으로 이동
		
		//홈으로 갈 수 있도록 링크 할 때, 타이틀이 같이 보여짐
		//API버전 14부터 사용가능		
//		getActionBar().setHomeButtonEnabled(true);
		
		//API버전 11부터 사용가능
		//홈으로 가거나 상위 메뉴로 이동할 경우, 타이틀이 기본적으로 보여지지 않음
										//ActionBar.DISPLAY_HOME_AS_UP: <만 있고 링크가 있다.
																	//ActionBar.DISPLAY_SHOW_HOME: 아이콘이 보여짐
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME);
		//타이틀이 보여지도록 처리
		getActionBar().setDisplayShowTitleEnabled(true);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// MenuInflater: XML파일의 메뉴정보를 읽어들여 메뉴 등록
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.itSearch:
			Toast.makeText(this, "검색으로", Toast.LENGTH_SHORT).show();
			return true;			
		case R.id.itChat:
			Toast.makeText(this, "채팅으로", Toast.LENGTH_SHORT).show();
			return true;			
		case R.id.itEmail:
			Toast.makeText(this, "메일함으로", Toast.LENGTH_SHORT).show();
			return true;			
		case R.id.itsettings:
			Toast.makeText(this, "셋팅으로", Toast.LENGTH_SHORT).show();
			return true;		
		case android.R.id.home:
			Toast.makeText(this, "홈으로", Toast.LENGTH_SHORT).show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}	
}
