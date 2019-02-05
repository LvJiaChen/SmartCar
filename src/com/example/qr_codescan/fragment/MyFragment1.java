package com.example.qr_codescan.fragment;

/**
 * 导航
 */
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.qr_codescan.R;
import com.example.qr_codescan.SQLite.InsertMapSearch;
import com.example.qr_codescan.SQLite.MyOpenHelper;
import com.example.qr_codescan.do_server.MapServlet;
import com.example.qr_codescan.do_server.ShelfServlet;
import com.example.qr_codescan.public_.Public_Attribute;
import com.example.qr_codescan.suanfa.WeiZhi;
import com.example.qr_codescan.view.MyMapView;

public class MyFragment1 extends Activity {
	private MyMapView Map_view;
	private SearchView searchView;
	private Intent intent;
	private ListView listView;
	private Handler myHandler;
	private WeiZhi weiZhi;
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view1);
		init();

		Bitmap bitmap = readBitMap(MyFragment1.this, R.drawable.map);
		BitmapDrawable bd = new BitmapDrawable(bitmap);
		Map_view.setBackgroundDrawable(bd);

		myHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					Log.i("handlemessage", "1");
					Map_view.my(msg.arg1, msg.arg2);
				}

			}
		};
		Public_Attribute.myHandler = myHandler;
		Log.i("MyFragment1", "onCreate");
		// 位置显示线程
		weiZhi = new WeiZhi(this);
		weiZhi.start();

		new InsertMapSearch(this).delete();

		new MapServlet().post(this);

		searchView.setSubmitButtonEnabled(true);
		searchView.setIconifiedByDefault(true);
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				if (searchView != null) {
					// 得到输入管理对象
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					if (imm != null) {
						// 这将让键盘在所有的情况下都被隐藏，但是一般我们在点击搜索按钮后，输入法都会乖乖的自动隐藏的。
						imm.hideSoftInputFromWindow(
								searchView.getWindowToken(), 0); // 输入法如果是显示状态，那么就隐藏输入法
					}
					searchView.clearFocus(); // 不获取焦点
				}
				MyOpenHelper help = new MyOpenHelper(MyFragment1.this);
				SQLiteDatabase db = help.getReadableDatabase();
				String querySql = "select * from commodity_map where trade_name=\""
						+ query + "\"";
				Cursor cursor = db.rawQuery(querySql, null);
				cursor.moveToNext();
				int x = cursor.getInt(cursor.getColumnIndex("X"));
				int y = cursor.getInt(cursor.getColumnIndex("Y"));
				Log.i("搜索", "" + x + y);
				changeff(x * 40, y * 40);
				return true;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				Cursor cursor = (Cursor) (TextUtils.isEmpty(newText) ? null
						: queryData(newText));
				setAdapter(cursor);
				return true;
			}

			private void setAdapter(Cursor cursor) {
				if (listView.getAdapter() == null) {
					SimpleCursorAdapter sAdapter = new SimpleCursorAdapter(
							MyFragment1.this, R.layout.item_layout, cursor,
							new String[] { "trade_name" },
							new int[] { R.id.text1 });
					listView.setAdapter(sAdapter);
				} else {
					((SimpleCursorAdapter) listView.getAdapter())
							.changeCursor(cursor);
				}
			}

			private Cursor queryData(String newText) {
				MyOpenHelper help = new MyOpenHelper(MyFragment1.this);
				SQLiteDatabase db = help.getReadableDatabase();
				String querySql = "select * from commodity_map where trade_name like '%"
						+ newText + "%'";
				Cursor cursor = db.rawQuery(querySql, null);
				return cursor;
			}
		});

	}

	private void init() {
		Map_view = (MyMapView) findViewById(R.id.myMapView1);
		listView = (ListView) findViewById(R.id.listView);
		searchView = (SearchView) findViewById(R.id.searchview);
	}

	public Bitmap readBitMap(Context context, int resId) {

		BitmapFactory.Options opt = new BitmapFactory.Options();

		opt.inPreferredConfig = Bitmap.Config.RGB_565;

		opt.inPurgeable = true;

		opt.inInputShareable = true;

		// 获取资源图片

		InputStream is = context.getResources().openRawResource(resId);

		return BitmapFactory.decodeStream(is, null, opt);

	}

	public void changeff(int x, int y) {
		Log.i("changeff", "MyFragment1");
		Map_view.ff(x, y);
	}

	@Override
	protected void onStart() {
		Log.i("Fragment1", "onStart");
		// TODO Auto-generated method stub
		super.onStart();
		int X, Y;
		intent = getIntent();
		String x = intent.getStringExtra("X");
		String y = intent.getStringExtra("Y");
		if (x != null && y != null) {
			X = Integer.parseInt(x);
			Y = Integer.parseInt(y);
		} else {
			X = -1;
			Y = -1;
		}
		Log.i("MyFragment1", "onCreate" + x);
		changeff(X * 40, Y * 40);
	}

	private void showListDialog(String shelfName,final String[] items) {
		AlertDialog.Builder listDialog = new AlertDialog.Builder(
				MyFragment1.this);

		listDialog.setTitle(shelfName);
		listDialog.setIcon(R.drawable.huojia);
		listDialog.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// which 下标从0开始
				// ...To-do
				Toast.makeText(MyFragment1.this, "你点击了" + items[which],
						Toast.LENGTH_SHORT).show();
			}
		});
		listDialog.create();
		listDialog.show();
	}

	public void button1(View view) {

		new ShelfServlet().do_post(MyFragment1.this, "1");
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x951) {
					List<String> shelfList = (List<String>) msg.obj;
					final String[] items = new String[shelfList.size()];
					for (int i = 0; i < shelfList.size(); i++) {
						items[i] = shelfList.get(i);
					}
					showListDialog("货架1",items);
				}

			}

		};
		Public_Attribute.shelfHandler = handler;
	}

	public void button2(View view) {
		new ShelfServlet().do_post(MyFragment1.this, "2");
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x951) {
					List<String> shelfList = (List<String>) msg.obj;
					final String[] items = new String[shelfList.size()];
					for (int i = 0; i < shelfList.size(); i++) {
						items[i] = shelfList.get(i);
					}
					showListDialog("货架2",items);
				}

			}

		};
		Public_Attribute.shelfHandler = handler;
	}

	public void button3(View view) {
		new ShelfServlet().do_post(MyFragment1.this, "3");
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x951) {
					List<String> shelfList = (List<String>) msg.obj;
					final String[] items = new String[shelfList.size()];
					for (int i = 0; i < shelfList.size(); i++) {
						items[i] = shelfList.get(i);
					}
					showListDialog("货架3",items);
				}

			}

		};
		Public_Attribute.shelfHandler = handler;
	}

	public void button4(View view) {
		new ShelfServlet().do_post(MyFragment1.this, "4");
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x951) {
					List<String> shelfList = (List<String>) msg.obj;
					final String[] items = new String[shelfList.size()];
					for (int i = 0; i < shelfList.size(); i++) {
						items[i] = shelfList.get(i);
					}
					showListDialog("货架4",items);
				}

			}

		};
		Public_Attribute.shelfHandler = handler;
	}

	public void button5(View view) {
		new ShelfServlet().do_post(MyFragment1.this, "5");
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x951) {
					List<String> shelfList = (List<String>) msg.obj;
					final String[] items = new String[shelfList.size()];
					for (int i = 0; i < shelfList.size(); i++) {
						items[i] = shelfList.get(i);
					}
					showListDialog("货架5",items);
				}

			}

		};
		Public_Attribute.shelfHandler = handler;
	}

	public void button6(View view) {
		new ShelfServlet().do_post(MyFragment1.this, "6");
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x951) {
					List<String> shelfList = (List<String>) msg.obj;
					final String[] items = new String[shelfList.size()];
					for (int i = 0; i < shelfList.size(); i++) {
						items[i] = shelfList.get(i);
					}
					showListDialog("货架6",items);
				}

			}

		};
		Public_Attribute.shelfHandler = handler;
	}
}
