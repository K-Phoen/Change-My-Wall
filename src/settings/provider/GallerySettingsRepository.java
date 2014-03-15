package settings.provider;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import database.DatabaseHandler;

public class GallerySettingsRepository {
	private DatabaseHandler databaseHandler;

	public GallerySettingsRepository(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}
	
	public boolean useFullGallery() {
		Cursor c = databaseHandler.getReadableDatabase().query(
				DatabaseHandler.GALLERY_PROVIDER_SETTINGS_TABLE_NAME,
				new String[] { "value" }, "key = ?",
				new String[] { "useFullGallery" }, null, null, null);
		boolean useFullGallery;
		c.moveToNext();
		if (c.isAfterLast()) {
			return true;
		}

		useFullGallery = Boolean.parseBoolean(c.getString(0));
		c.close();

		return useFullGallery;
	}

	public void useFullGallery(boolean totality) {
		ContentValues values = new ContentValues();
		values.put("value", Boolean.toString(totality));
		values.put("key", "useFullGallery");
		databaseHandler.getWritableDatabase().replace(
				DatabaseHandler.GALLERY_PROVIDER_SETTINGS_TABLE_NAME, null,
				values);
	}

	public List<String> selectionImages() {
		List<String> listeSelectionImages = new ArrayList<String>();
		Cursor c = databaseHandler.getReadableDatabase().query(
				DatabaseHandler.GALLERY_PROVIDER_SELECTION_TABLE_NAME,
				new String[] { "image" }, null, null, null, null, null);
		while (c.moveToNext()) {
			listeSelectionImages.add(c.getString(0));
		}
		c.close();
		return listeSelectionImages;
	}

	public void selectionImages(List<String> listeSelectionImages) {
		databaseHandler.getWritableDatabase().delete(DatabaseHandler.GALLERY_PROVIDER_SELECTION_TABLE_NAME, null, null);

		for (String path : listeSelectionImages) {
			ContentValues values = new ContentValues();
			values.put("image", path);
			databaseHandler.getWritableDatabase().insert(
					DatabaseHandler.GALLERY_PROVIDER_SELECTION_TABLE_NAME, null,
					values);
		}
	}
}
