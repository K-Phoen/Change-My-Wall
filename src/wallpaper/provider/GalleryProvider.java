package wallpaper.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import settings.provider.GallerySettingsRepository;
import wallpaper.entity.DrawableWallpaper;
import wallpaper.repository.ResultCallback;
import activity.prodiver.GalleryProviderActivity;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;

import com.cmw.R;



public class GalleryProvider implements Provider {
	GallerySettingsRepository repo;
	public GalleryProvider (GallerySettingsRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public void getWallpaper(Activity activity, ResultCallback callback) {
		if (repo.useFullGallery())
			getWallpaperFromFullGallery (activity, callback);
		else
			getWallpaperFromSelection (activity, callback, repo.selectionImages());
	}

	protected void getWallpaperFromSelection (Activity activity, ResultCallback callback, List <String> selection) {
	    Random random = new Random();
	    String path = selection.get(random.nextInt(selection.size()));

	    callback.handleResult(new DrawableWallpaper(BitmapDrawable.createFromPath(path)));
	}
	
	protected void getWallpaperFromFullGallery (Activity activity, ResultCallback callback) {
		String[] projection = new String[] {
				MediaStore.Images.Media.DATA
	    };

	    Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	    Cursor cur = activity.getContentResolver().query(images, projection, "", null, "");

	    ArrayList<String> imagesPath = new ArrayList<String>();
	    if (cur.moveToFirst()) {
	        int dataColumn = cur.getColumnIndex(MediaStore.Images.Media.DATA);
	        do {
	            imagesPath.add(cur.getString(dataColumn));
	        } while (cur.moveToNext());
	    }

	    Random random = new Random();
	    int count = imagesPath.size();
	    
	    callback.handleResult(new DrawableWallpaper(BitmapDrawable.createFromPath(imagesPath.get(random.nextInt(count)))));
	}
	@Override
	public String getName() {
		return "gallery";
	}

	@Override
	public int getIcon() {
		return R.drawable.logoandroidwall;
	}

	@Override
	public boolean isConfigurable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Class<?> getConfigurationActivity() {
		// TODO Auto-generated method stub
		return GalleryProviderActivity.class;
	}
}
