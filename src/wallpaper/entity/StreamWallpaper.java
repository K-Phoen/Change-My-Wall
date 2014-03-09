package wallpaper.entity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.WallpaperManager;

public class StreamWallpaper implements Wallpaper {
	protected InputStream stream;
	
	public StreamWallpaper(InputStream stream) {
		if (stream instanceof BufferedInputStream) {
			this.stream = stream;
		} else {
			this.stream = new BufferedInputStream(stream);
		}
	}
	
	public void promoteAsWallpaper(WallpaperManager manager) {
		try {
			manager.setStream(stream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
