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
		String name = null;

		while (c.moveToNext()) {
			name = c.getString(0);
			break;
		}

		c.close();

		return name;
	}

	public void setCurrentProviderName(String name) {
		ContentValues values = new ContentValues();
		values.put("key", "currentProviderName");
		values.put("value", name);

		databaseHandler.getWritableDatabase().replace(DatabaseHandler.SETTINGS_TABLE_NAME, null, values);
	}
}
