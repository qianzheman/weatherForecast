package hi.man.cityselect;

import hi.man.cityselect.bean.Province;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * @author Mr.Himan
 * @version 1.0<br>
 * 
 */
public class MainActivity extends Activity {

	public static final String INTENT_TAG_SCODE = "scode";
	private CityDaoImpl mCityDaoImpl;
	private List<Province> mAllProvinces;
	private ListView mListView;
	private ProvinceAdapter mProvinceAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initDatas();
		initListView();
	}

	private void initDatas() {
		mCityDaoImpl = new CityDaoImpl(this);
		mAllProvinces = mCityDaoImpl.queryProvince();
	}

	private void initListView() {
		mListView = (ListView) findViewById(R.id.listview);
		mProvinceAdapter = new ProvinceAdapter(mAllProvinces, this);
		mListView.setAdapter(mProvinceAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Province pro = (Province) parent.getAdapter().getItem(position);
				int sCode = pro.getsCode();
				Intent intent = new Intent(MainActivity.this,
						CityActivity.class);
				intent.putExtra(INTENT_TAG_SCODE, sCode);
				startActivity(intent);
			}
		});
	}
}
