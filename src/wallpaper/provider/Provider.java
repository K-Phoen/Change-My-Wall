package wallpaper.provider;

import wallpaper.entity.Wallpaper;
import android.app.Activity;

public interface Provider {
	Wallpaper getWallpaper(Activity activity);

	String getName();
}
