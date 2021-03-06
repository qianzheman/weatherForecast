package hi.man.cityselect;

import hi.man.cityselect.bean.City;
import hi.man.cityselect.bean.Province;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CityActivity extends Activity {

	private List<City> mAllCitys;
	private ListView mListView;
	private CityAdapter mCityAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initDatas();
		initListView();
	}

	private void initDatas() {
		int sCode = getIntent().getIntExtra(MainActivity.INTENT_TAG_SCODE, 0);
		mAllCitys = new CityDaoImpl(this).queryCity(sCode);
	}

	private void initListView() {
		mListView = (ListView) findViewById(R.id.listview);
		mCityAdapter = new CityAdapter(mAllCitys, this);
		mListView.setAdapter(mCityAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				City pro = (City) parent.getAdapter().getItem(position);
				int sCode = pro.getsCode();
				Intent intent = new Intent(CityActivity.this,
						DistrictActivity.class);
				intent.putExtra(MainActivity.INTENT_TAG_SCODE, sCode);
				startActivity(intent);
			}
		});
	}
}
