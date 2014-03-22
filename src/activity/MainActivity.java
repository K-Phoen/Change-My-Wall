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
import android.widget.TextView;
import android.widget.Toast;

import com.cmw.R;
import com.polites.android.GestureImageView;

import database.DatabaseHandler;

public class MainActivity extends Activity {
	WallpaperRepository wallpaperRepository;
	WallpaperManager wallpaperManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		wallpaperManager = WallpaperManager.getInstance(this);
		wallpaperRepository = WallpaperRepository.create(this);

		setContentView(R.layout.main_view);

		final GestureImageView wallpaperView = (GestureImageView) findViewById(R.id.wallpaperImage);
		wallpaperView.setImageDrawable(wallpaperManager.getDrawable());

		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_WALLPAPER_CHANGED);

		BroadcastReceiver receiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
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
		SettingsRepository settings = new SettingsRepository(new DatabaseHandler(this));

		if (settings.getCurrentProviderName() != null) {
			wallpaperRepository.selectProvider(settings.getCurrentProviderName());
		}

		Toast.makeText(this, "Okay, let's do that!", Toast.LENGTH_LONG).show();

		final MainActivity that = this;
		wallpaperRepository.changeWallpaper(this, new ResultCallback() {
			@Override
			public void handleResult(Wallpaper wallpaper) {
				if (wallpaper.getTitle() != null) {
					that.displayWallpaperTitle(wallpaper.getTitle());
				}
				if (wallpaper.getAuthor() != null) {
					that.displayWallpaperAuthor(wallpaper.getAuthor());
				}

				Toast.makeText(that, "Tadaa!", Toast.LENGTH_SHORT).show();
			}
		});
	}

	protected void displayWallpaperTitle(String title) {
		TextView titleView = (TextView) findViewById(R.id.title);
		titleView.setText(title);
	}

	protected void displayWallpaperAuthor(String author) {
		TextView titleView = (TextView) findViewById(R.id.author);
		titleView.setText(author);
	}
}