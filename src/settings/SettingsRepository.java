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
		Cursor c = databaseHandler.getWritableDatabase().query(DatabaseHandler.SETTINGS_TABLE_NAME, new String[] {"value"}, "key = ?", new String[] {"currentProviderName"}, null, null, null);
		String name = null;

		while (c.moveToNext()) {
			name = c.getString(0);
			break;
		}

		c.close();

		System.out.println("[lala] read db provider " + (name == null ? "null" : name));

		return name;
	}

	public void setCurrentProviderName(String name) {
		ContentValues values = new ContentValues();
		values.put("value", "currentProviderName");
		values.put("key", name);

		System.out.println("[lala] set db provider " + name);

		databaseHandler.getWritableDatabase().replace(DatabaseHandler.SETTINGS_TABLE_NAME, null, values);
	}
}
