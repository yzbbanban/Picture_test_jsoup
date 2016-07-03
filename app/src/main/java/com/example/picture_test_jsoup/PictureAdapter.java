package com.example.picture_test_jsoup;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PictureAdapter extends BaseAdapter<Picture> {

	public PictureAdapter(Context context, List<Picture> data) {
		super(context, data);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Picture picture = getData().get(position);
		ViewHolder holder;
		if (convertView == null) {
			convertView = getLayoutInflater().inflate(R.layout.picture_item,
					null);
			holder = new ViewHolder();
			holder.tvHeight = (TextView) convertView
					.findViewById(R.id.tv_height);
			holder.tvPath = (TextView) convertView.findViewById(R.id.tv_path);
			holder.tvWidth = (TextView) convertView.findViewById(R.id.tv_width);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();

		}
		holder.tvPath.setText(picture.getPath());
		holder.tvWidth.setText(picture.getWidth());
		holder.tvHeight.setText(picture.getHeight());

		return convertView;
	}

	class ViewHolder {
		TextView tvPath;
		TextView tvWidth;
		TextView tvHeight;
	}
}
