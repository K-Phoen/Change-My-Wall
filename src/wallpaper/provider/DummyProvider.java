package wallpaper.provider;

import wallpaper.entity.ResourceWallpaper;
import wallpaper.entity.Wallpaper;
import wallpaper.repository.ResultCallback;
import android.app.Activity;

import com.cmw.R;

public class DummyProvider implements Provider {
	private String name;
	
	public DummyProvider (String name) {
		this.name = name;
	}

	@Override
	public void getWallpaper(Activity activity, ResultCallback callback) {
		Wallpaper wallpaper = new ResourceWallpaper(R.drawable.dummy_wallpaper);
		wallpaper.setTitle("Dummy wallpaper");
		wallpaper.setAuthor(name);

		callback.handleResult(wallpaper);
	}

	@Override
	public String getName() {
		return name;
	}
	
	public int getIcon() {
		return R.drawable.sd;
	}

	@Override
	public boolean isConfigurable() {
		return false;
	}

	@Override
	public Class<? extends Activity> getConfigurationActivity() {
		return null;
	}
}
