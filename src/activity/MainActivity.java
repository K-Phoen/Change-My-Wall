package activity;


import settings.SettingsRepository;
import wallpaper.entity.Wallpaper;
import wallpaper.repository.ResultCallback;
import wallpaper.repository.WallpaperRepository;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cmw.R;
import com.polites.android.GestureImageView;

import database.DatabaseHandler;

public class MainActivity extends Activity {
	WallpaperRepository wallpaperRepository;
	WallpaperManager wallpaperManager;
	SettingsRepository settings;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		settings = new SettingsRepository(new DatabaseHandler(this));
		wallpaperManager = WallpaperManager.getInstance(this);
		wallpaperRepository = WallpaperRepository.create();

		setContentView(R.layout.main_view);

		GestureImageView wallpaperView = (GestureImageView) findViewById(R.id.wallpaperImage);
		wallpaperView.setImageDrawable(wallpaperManager.peekDrawable());
    }

	public void editSettings(View view) {
		Intent intent = new Intent(this, ProvidersActivity.class);
		startActivity(intent);
	}
	
	public void changeWallpaper(View view) {
		final GestureImageView wallpaperView = (GestureImageView) findViewById(R.id.wallpaperImage);

		if (settings.getCurrentProviderName() != null) {
			wallpaperRepository.selectProvider(settings.getCurrentProviderName());
		}

		wallpaperRepository.changeWallpaper(this, new ResultCallback() {
			@Override
			public void handleResult(Wallpaper wallpaper) {
				wallpaperView.setImageDrawable(wallpaperManager.peekDrawable());				
			}
		});
	}
}