package wallpaper.provider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wallpaper.entity.DrawableWallpaper;
import wallpaper.entity.Wallpaper;
import wallpaper.repository.ResultCallback;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.cmw.R;

public class AndroidWallpapersProvider implements Provider {

	protected final static String URL = "http://androidwallpape.rs/";
	protected String pageContent = "";

	@Override
	public void getWallpaper(Activity activity, ResultCallback callback) {
		AsyncTask<String, Void, String> task = new DownloadWebpageTask(callback);
		task.execute(URL);
	}

	@Override
	public int getIcon() {
		return R.drawable.sd;
	}
	
	@Override
	public String getName() {
		return "AndroidWallpape.rs";
	}

	private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
		ResultCallback callback;
		Wallpaper wallpaper;
		
		public DownloadWebpageTask(ResultCallback callback) {
			super();

			this.callback = callback;
		}

		@Override
		protected String doInBackground(String... urls) {
			try {
				return downloadUrl(urls[0]);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		protected void onPostExecute(String result) {
			this.callback.handleResult(wallpaper);
		}
		
		protected Wallpaper extractWallpaper(String html) {
			List<String> urls = new ArrayList<String>();
			
		    Pattern pattern = Pattern.compile("<h2><a href=\"([^\"]+)\">", Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(html);

		    while (matcher.find()) {
		      	urls.add(matcher.group(1));
		    }

		    Random random = new Random();

			try {
				URL wallpaperURL = new URL(urls.get(random.nextInt(urls.size()-1)));
				InputStream stream = wallpaperURL.openStream();
				Drawable drawable = new BitmapDrawable(BitmapFactory.decodeStream(stream));
				
				return new DrawableWallpaper(drawable);
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private String downloadUrl(String myurl) throws IOException {
			InputStream is = null;

			try {
				URL url = new URL(myurl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setReadTimeout(15000 /* milliseconds */);
				conn.setConnectTimeout(15000 /* milliseconds */);
				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				// Starts the query
				conn.connect();

				if (conn.getResponseCode() != 200) {
					return "";
				}

				is = conn.getInputStream();

				String html = readIt(is);
				wallpaper = extractWallpaper(html);

				return html;
				
			} finally {
				if (is != null) {
					is.close();
				}
			}
		}

		// Reads an InputStream and converts it to a String.
		public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String read;
			
			while ((read = reader.readLine()) != null) {
				sb.append(read);
			}

			return sb.toString();
		}
	}

	@Override
	public boolean isConfigurable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Class<?> getConfigurationActivity() {
		// TODO Auto-generated method stub
		return null;
	}
}
