package wallpaper.provider;

import wallpaper.repository.ResultCallback;
import android.app.Activity;

public interface Provider {
	void getWallpaper(Activity activity, ResultCallback callback);

	String getName();
	int getIcon();

	//boolean isConfigurable();
	//Class<?> getConfigurationActivity();
}
