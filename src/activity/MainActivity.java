package activity;

import settings.SettingsRepository;
import wallpaper.entity.Wallpaper;
import wallpaper.repository.ResultCallback;
import wallpaper.repository.WallpaperRepository;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
		wallpaperRepository = WallpaperRepository.create(this);

		setContentView(R.layout.main_view);

		final GestureImageView wallpaperView = (GestureImageView) findViewById(R.id.wallpaperImage);
		wallpaperView.setImageDrawable(wallpaperManager.peekDrawable());

		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_WALLPAPER_CHANGED);

		BroadcastReceiver receiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				System.out.println("lolilol");
				wallpaperView.setImageDrawable(wallpaperManager.getDrawable());
			}
		};
		registerReceiver(receiver, filter);
	}

	public void editSettings(View view) {
		Intent intent = new Intent(this, ProvidersActivity.class);
		startActivity(intent);
	}

	public void changeWallpaper(View view) {
		/*if (settings.getCurrentProviderName() != null) {
			wallpaperRepository.selectProvider(settings
					.getCurrentProviderName());
		}*/
		wallpaperRepository.selectProvider("gallery");

		wallpaperRepository.changeWallpaper(this, new ResultCallback() {
			@Override
			public void handleResult(Wallpaper wallpaper) {
			}
		});
	}
}