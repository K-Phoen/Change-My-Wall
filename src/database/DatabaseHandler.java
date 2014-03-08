package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	public static final String SETTINGS_KEY = "key";
	public static final String SETTINGS_VALUE = "value";
	public static final String SETTINGS_TABLE_NAME = "Settings";

	protected static final String SETTINGS_TABLE_DROP = "DROP TABLE IF EXISTS " + SETTINGS_TABLE_NAME + ";";
	protected static final String SETTINGS_TABLE_CREATE = "CREATE TABLE "
			+ SETTINGS_TABLE_NAME + " (" + SETTINGS_KEY + " VARCHAR PRIMARY KEY, "
			+ SETTINGS_VALUE + " TEXT);";
	protected static final int VERSION = 2;

	public DatabaseHandler(Context context) {
		super(context, "database.db", null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SETTINGS_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL(SETTINGS_TABLE_DROP);
		onCreate(db);
	}
}