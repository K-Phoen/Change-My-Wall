package wallpaper.provider;

import java.util.ArrayList;
import java.util.Random;

import wallpaper.entity.DrawableWallpaper;
import wallpaper.entity.Wallpaper;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;



public class GalleryProvider implements Provider {

	@Override
	public Wallpaper getWallpaper(Activity activity) {
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
	    
	    return new DrawableWallpaper(BitmapDrawable.createFromPath(imagesPath.get(random.nextInt(count-1))));
	}

	@Override
	public String getName() {
		return "gallery";
	}
}
