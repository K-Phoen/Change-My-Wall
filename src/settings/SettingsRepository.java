package settings;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SettingsRepository {
	private SQLiteDatabase database;
	public SettingsRepository (SQLiteDatabase database) {
		this.database = database;
	}
	
	public String getCurrentProviderName() {
		String name;
		Cursor c = database.rawQuery("select value  from Settings where key = ?", new String[]{"currentProviderName"});
		
		c.moveToNext();
		if (c.isAfterLast())
			return null;

		name = c.getString(0);
		c.close();
		
		return name;
	}
	
	public void setCurrentProviderName (String name) {
		database.execSQL("INSERT OR REPLACE INTO Settings (key, value) VALUES (?, ?)", new String [] {"currentProviderName", name});
	}
}
