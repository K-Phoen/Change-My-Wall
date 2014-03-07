package wallpaper.entity;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.drawable.Drawable;

public interface Wallpaper {
	void promoteAsWallpaper(WallpaperManager manager);
	Drawable toDrawable(Context context);
}
