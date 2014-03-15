package wallpaper.provider;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import wallpaper.entity.StreamWallpaper;
import wallpaper.entity.Wallpaper;
import wallpaper.repository.ResultCallback;
import android.app.Activity;
import android.os.AsyncTask;

import com.cmw.R;

public class AndroidWallpapersProvider implements Provider {

	protected final static String URL = "http://androidwallpape.rs/";

	@Override
	public void getWallpaper(Activity activity, ResultCallback callback) {
		AsyncTask<String, Void, Wallpaper> task = new DownloadWebpageTask(callback);
		task.execute(URL);
	}

	@Override
	public int getIcon() {
		return R.drawable.logoandroidwall;
	}
	
	@Override
	public String getName() {
		return "AndroidWallpape.rs";
	}

	@Override
	public boolean isConfigurable() {
		return false;
	}

	@Override
	public Class<?> getConfigurationActivity() {
		return null;
	}

	/**
	 * Used to download a wallpaper without blocking the UI
	 */
	protected class DownloadWebpageTask extends AsyncTask<String, Void, Wallpaper> {
		ResultCallback callback;
		
		public DownloadWebpageTask(ResultCallback callback) {
			super();

			this.callback = callback;
		}

		@Override
		protected Wallpaper doInBackground(String... urls) {
			try {
				String html=  downloadUrl(urls[0]);
				return extractWallpaper(html);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		protected void onPostExecute(Wallpaper wallpaper) {
			callback.handleResult(wallpaper);
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
				URL wallpaperURL = new URL(urls.get(random.nextInt(urls.size())));
				return new StreamWallpaper(wallpaperURL.openStream());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private String downloadUrl(String url) throws IOException {
		    HttpClient client = new DefaultHttpClient();

		    return client.execute(new HttpGet(url), new BasicResponseHandler());
		}
	}
}
