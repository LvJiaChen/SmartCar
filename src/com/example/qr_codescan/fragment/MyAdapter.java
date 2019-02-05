package com.example.qr_codescan.fragment;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qr_codescan.R;
import com.yolanda.nohttp.able.StartAble;

public class MyAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater mInflater;
	private List<Map<String, Object>> data;

	public MyAdapter() {
		// TODO Auto-generated constructor stub
	}

	public MyAdapter(Context context, List<Map<String, Object>> data) {
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		this.data = data;
	}

	public CharSequence[] getAutofillOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.favorable_title, null);
			holder = new ViewHolder();
			holder.img = (ImageView) convertView.findViewById(R.id.fImage);
			holder.fInfo = (TextView) convertView.findViewById(R.id.fInfo);
			holder.fPrice = (TextView) convertView.findViewById(R.id.fPrice);
			holder.price = (TextView) convertView.findViewById(R.id.price);
			holder.price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 涓垝绾�
			holder.viewBtn = (Button) convertView.findViewById(R.id.daohang);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		holder.fInfo.setText((String) data.get(position).get("fInfo"));
		holder.fPrice.setText(data.get(position).get("fPrice") + "");
		holder.price.setText(data.get(position).get("price") + "");
		holder.img.setImageBitmap((Bitmap) data.get(position).get("img"));
		holder.viewBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("瀵艰埅鍧愭爣",Integer.parseInt(String.valueOf(data.get(position).get(
						"X")))+"");
				Intent intent=new Intent(context,MyFragment1.class);
				intent.putExtra("X", String.valueOf(data.get(position).get("X")));
				intent.putExtra("Y", String.valueOf(data.get(position).get("Y")));
				context.startActivity(intent);
			}
		});
		return convertView;
	}

	public final class ViewHolder {
		public ImageView img;
		public TextView fInfo;
		public TextView fPrice;
		public TextView price;
		public Button viewBtn;
	}
}
