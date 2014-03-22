package wallpaper.entity;

import android.app.WallpaperManager;

public interface Wallpaper {
	String getTitle();
	void setTitle(String title);

	String getAuthor();
	void setAuthor(String author);

	void promoteAsWallpaper(WallpaperManager manager);
}