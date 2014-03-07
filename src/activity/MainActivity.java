package activity;

import wallpaper.repository.WallpaperRepository;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cmw.R;

public class MainActivity extends Activity {
	WallpaperRepository wallpaperRepository;

	@Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       
       wallpaperRepository = WallpaperRepository.create();

       setContentView(R.layout.main_view);
    }
	
	public void editSettings(View view) {
		Intent intent = new Intent (this, ProvidersActivity.class);
		startActivity(intent);
	}
	
	public void changeWallpaper(View view) {
		wallpaperRepository.selectProvider("dummy");
		wallpaperRepository.changeWallpaper(this);
	}
}