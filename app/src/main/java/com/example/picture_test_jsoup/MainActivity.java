package com.example.picture_test_jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tvTest;
	private String webPath;
	private String path;
	private String width;
	private String height;
	private ListView lvPicture;
	private List<Picture> pictures;
	private PictureAdapter adapter;
	private Thread thread;
	private Picture picture;
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case HANDLER_LINKS_SUCCESS:
				setAdapter();
				Log.i(TAG, "");	
				
				break;

			default:
				break;
			}

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		
		thread=new MyThread();
		thread.start();
		
	}

	protected void setAdapter() {
		adapter = new PictureAdapter(this, pictures);
		lvPicture.setAdapter(adapter);
	}

	private void initView() {
		tvTest=(TextView) findViewById(R.id.tv_test);
		lvPicture = (ListView) findViewById(R.id.lv_picture_item);
		webPath = "http://m.xxxiao.com/25033";
		pictures = new ArrayList<Picture>();
	}

	private class MyThread extends Thread {
		@Override
		public void run() {

			try {
				
				Document doc = Jsoup.connect(webPath).get();

				Elements e1 = doc.getElementsByClass("rgg-imagegrid");
				Elements a = e1.first().getElementsByTag("a");
				//Log.i(TAG, "element:"+e1);
				//Log.i(TAG, "a:"+a);
				int index=0;
				for (Element element : a) {
					//Log.i(TAG, "遍历a的内容:"+a);
					path = a.get(index).getElementsByTag("img").attr("src");
					width = a.get(index).getElementsByTag("img").attr("width");
					height = a.get(index).getElementsByTag("img").attr("height");
					index++;
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							tvTest.setText("路径："+path+"\n宽度"+width+"\n高度"+height);
						}
					});
					
					picture = new Picture(path, width, height);
					pictures.add(picture);
					Log.i(TAG, "路径:  "+path);
					Log.i(TAG, "\n宽度:  "+width);
					Log.i(TAG, "\n高度:  "+height);
					handler.sendEmptyMessage(HANDLER_LINKS_SUCCESS);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private static final int HANDLER_LINKS_SUCCESS = 1;
	private static final String TAG = "supergirl";
	
}
