package wallpaper.provider;

import wallpaper.entity.ResourceWallpaper;
import wallpaper.entity.Wallpaper;
import android.app.Activity;

import com.cmw.R;



public class DummyProvider implements Provider {
	private String name;
	
	public DummyProvider (String name) {
		this.name = name;
	}
	@Override
	public Wallpaper getWallpaper(Activity activity) {
		return new ResourceWallpaper(R.drawable.dummy_wallpaper);
	}

	@Override
	public String getName() {
		return name;
	}
	
	public int getIcon() {
		return R.drawable.sd;
	}
	@Override
	public boolean isConfigurable() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Class<?> getConfigurationActivity() {
		// TODO Auto-generated method stub
		return null;
	}
}
