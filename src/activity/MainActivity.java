package activity;


import settings.SettingsRepository;
import wallpaper.entity.Wallpaper;
import wallpaper.repository.ResultCallback;
import wallpaper.repository.WallpaperRepository;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cmw.R;
import com.polites.android.GestureImageView;

import database.DatabaseHandler;

public class MainActivity extends Activity {
	WallpaperRepository wallpaperRepository;
	SettingsRepository settings;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		settings = new SettingsRepository(new DatabaseHandler(this));
		wallpaperRepository = WallpaperRepository.create();

		setContentView(R.layout.main_view);
    }

	public void editSettings(View view) {
		Intent intent = new Intent(this, ProvidersActivity.class);
		startActivity(intent);
		//Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
		//startActivityForResult(i, 200);
	}

	/*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            String single_path = data.getStringExtra("single_path");
        	System.out.println("file://" + single_path);
        } else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            String[] all_path = data.getStringArrayExtra("all_path");

            ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>();

            for (String string : all_path) {
                CustomGallery item = new CustomGallery();
                item.sdcardPath = string;

                dataT.add(item);
                System.out.println(string);
            }

        }
    }*/

	public void changeWallpaper(View view) {
		final GestureImageView wallpaperView = (GestureImageView) findViewById(R.id.wallpaperImage);

		if (settings.getCurrentProviderName() != null) {
			wallpaperRepository.selectProvider(settings.getCurrentProviderName());
		}

		final Activity self = this;
		wallpaperRepository.changeWallpaper(this, new ResultCallback() {
			@Override
			public void handleResult(Wallpaper wallpaper) {
				wallpaperView.setImageDrawable(wallpaper.toDrawable(self));				
			}
		});
	}
}