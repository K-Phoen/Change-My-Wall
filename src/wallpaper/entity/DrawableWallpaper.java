package wallpaper.entity;

import java.io.IOException;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class DrawableWallpaper extends AbstractWallpaper {
	protected Drawable drawable;
	
	public DrawableWallpaper(Drawable drawable) {
		this.drawable = drawable;
	}
	
	public void promoteAsWallpaper(WallpaperManager manager) {
		try {
			manager.setBitmap(drawableToBitmap(drawable));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected static Bitmap drawableToBitmap (Drawable drawable) {
	    if (drawable instanceof BitmapDrawable) {
	        return ((BitmapDrawable)drawable).getBitmap();
	    }
	
	    Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
	    Canvas canvas = new Canvas(bitmap); 
	    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
	    drawable.draw(canvas);
	
	    return bitmap;
	}
}
