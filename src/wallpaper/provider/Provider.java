package wallpaper.provider;

import wallpaper.entity.Wallpaper;
import android.content.Context;

public interface Provider {
	Wallpaper getWallpaper(Context context);

	String getName();
}
