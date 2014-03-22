package settings;

import android.content.ContentValues;
import android.database.Cursor;
import database.DatabaseHandler;

public class SettingsRepository {
	private DatabaseHandler databaseHandler;

	public SettingsRepository(DatabaseHandler databaseHandler) {
		this.databaseHandler = databaseHandler;
	}

	public String getCurrentProviderName() {
		return readSetting("currentProviderName");
	}

	public String getCurrentWallpaperTitle() {
		return readSetting("currentWallpaperTitle");
	}

	public String getCurrentWallpaperAuthor() {
		return readSetting("currentWallpaperAuthor");
	}

	public void setCurrentProviderName(String name) {
		saveSetting("currentProviderName", name);
	}

	public void setCurrentWallpaperTitle(String title) {
		saveSetting("currentWallpaperTitle", title);
	}

	public void setCurrentWallpaperAuthor(String author) {
		saveSetting("currentWallpaperAuthor", author);
	}

	protected void saveSetting(String key, String value) {
		if (value == null) {
			deleteSetting(key);
			return;
		}

		ContentValues values = new ContentValues();
		values.put("key", key);
		values.put("value", value);

		System.out.println("[lala] save " + key + " -> " + value);
		
		databaseHandler.getWritableDatabase().replace(DatabaseHandler.SETTINGS_TABLE_NAME, null, values);
	}

	protected String readSetting(String key) {
		Cursor c = databaseHandler.getReadableDatabase().query(DatabaseHandler.SETTINGS_TABLE_NAME, new String[] {"value"}, "key = ?", new String[] {key}, null, null, null);
		String value = null;

		while (c.moveToNext()) {
			value = c.getString(0);
			break;
		}

		c.close();

		System.out.println("[lala] read " + key + " -> " + value);
		return value;
	}

	protected void deleteSetting(String key) {
		databaseHandler.getReadableDatabase().delete(DatabaseHandler.SETTINGS_TABLE_NAME, "key = ?", new String[] {key});
	}
}
