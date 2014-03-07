package wallpaper.provider;

import wallpaper.entity.ResourceWallpaper;
import wallpaper.entity.Wallpaper;
import android.content.Context;

import com.cmw.R;



public class DummyProvider implements Provider {

	@Override
	public Wallpaper getWallpaper(Context context) {
		return new ResourceWallpaper(R.drawable.dummy_wallpaper);
	}

	@Override
	public String getName() {
		return "dummy";
	}
}
