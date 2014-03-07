package wallpaper.entity;

import java.io.IOException;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.drawable.Drawable;

public class ResourceWallpaper implements Wallpaper {
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

	public Drawable toDrawable(Context context)
	{
		return context.getResources().getDrawable(resource);
	}
}
