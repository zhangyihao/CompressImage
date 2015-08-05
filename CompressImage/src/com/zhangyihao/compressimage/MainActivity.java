package com.zhangyihao.compressimage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;

import com.zhangyihao.compressimage.util.CompressImageUtil;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		compressImage();
	}
	
	private void compressImage() {
		if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return;
		}
		String filePath = Environment.getExternalStorageDirectory()+"/Pictures/P50620-122135.jpg";
		String destPath = Environment.getExternalStorageDirectory()+"/Pictures/P50620-122135_2.jpg";
		File file = new File(filePath);
		if(file.exists()) {
			Bitmap src = BitmapFactory.decodeFile(filePath);
//			byte[] bytes = CompressImageUtil.compressImage(src, 200);
			byte[] bytes = CompressImageUtil.getimage(filePath);
			InputStream in = new ByteArrayInputStream(bytes);
			try {
				OutputStream out = new FileOutputStream(destPath);
				int length = 0;
				byte[] b = new byte[1024];
				while((length=in.read(b))>0) {
					out.write(b, 0, length);
				}
				out.flush();
				out.close();
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
