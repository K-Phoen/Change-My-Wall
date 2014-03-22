package wallpaper.entity;

import java.io.IOException;

import android.app.WallpaperManager;

public class ResourceWallpaper extends AbstractWallpaper {
	protected int resource;
	
	public ResourceWallpaper(int resource) {
		this.resource = resource;
	}
	
	public void promoteAsWallpaper(WallpaperManager manager) {
		try {
			manager.setResource(resource);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
