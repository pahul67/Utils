package com.cpumedia.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * Save image url to file
 * @author William Hill <william.hill@gmail.com>
 *
 */
public class Url2ImageFile {

	public static boolean saveImage(String src, String dest)	{

		boolean ret = false;
		
		InputStream is = null;
		OutputStream os = null;

		try {
			URL url = new URL(src);
			is = url.openStream();
			os = new FileOutputStream(dest);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
			
			return true;
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
			return false;
		}

	}

}
