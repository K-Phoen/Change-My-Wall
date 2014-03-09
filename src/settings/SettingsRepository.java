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
		Cursor c = databaseHandler.getReadableDatabase().query(DatabaseHandler.SETTINGS_TABLE_NAME, new String[] {"value"}, "key = ?", new String[] {"currentProviderName"}, null, null, null);
		String name;
		
		c.moveToNext();
		if (c.isAfterLast()) {
			return null;
		}
		
		name = c.getString(0);
		c.close();
		
		return name;
	}

	public void setCurrentProviderName(String name) {
		ContentValues values = new ContentValues();
		values.put("value", "currentProviderName");
		values.put("key", name);
		databaseHandler.getWritableDatabase().replace(DatabaseHandler.SETTINGS_TABLE_NAME, null, values);
	}
}
