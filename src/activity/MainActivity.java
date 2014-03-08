package activity;

import wallpaper.entity.Wallpaper;
import wallpaper.repository.WallpaperRepository;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cmw.R;
import com.polites.android.GestureImageView;

public class MainActivity extends Activity {
	WallpaperRepository wallpaperRepository;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
       
		wallpaperRepository = WallpaperRepository.create();
		wallpaperRepository.selectProvider("gallery");

		setContentView(R.layout.main_view);
    }
	
	public void editSettings(View view) {
		Intent intent = new Intent (this, ProvidersActivity.class);
		startActivity(intent);
	}

	public void changeWallpaper(View view) {
		GestureImageView wallpaperView = (GestureImageView) findViewById(R.id.wallpaperImage);
		Wallpaper wallpaper = wallpaperRepository.changeWallpaper(this);

		wallpaperView.setImageDrawable(wallpaper.toDrawable(this));
	}
}