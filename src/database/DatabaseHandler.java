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

	
	public static final String GALLERY_PROVIDER_SETTINGS_ID = "key";
	public static final String GALLERY_PROVIDER_SETTINGS_VALUE = "value";
	public static final String GALLERY_PROVIDER_SETTINGS_TABLE_NAME = "gallery_provider_settings";
	
	protected static final String GALLERY_PROVIDER_SETTINGS_TABLE_DROP = "	DROP TABLE IF EXISTS " + 
																			GALLERY_PROVIDER_SETTINGS_TABLE_NAME + ";";
	protected static final String GALLERY_PROVIDER_SETTINGS_TABLE_CREATE = "CREATE TABLE "
			+ GALLERY_PROVIDER_SETTINGS_TABLE_NAME + " (" + GALLERY_PROVIDER_SETTINGS_ID + " VARCHAR PRIMARY KEY, "
			+ GALLERY_PROVIDER_SETTINGS_VALUE + " TEXT);";
	
	public static final String GALLERY_PROVIDER_SELECTION_IMAGE = "image";
	public static final String GALLERY_PROVIDER_SELECTION_TABLE_NAME = "gallery_provider_selection";
	
	protected static final String GALLERY_PROVIDER_SELECTION_TABLE_DROP = "	DROP TABLE IF EXISTS " + 
																			GALLERY_PROVIDER_SELECTION_TABLE_NAME + ";";
	protected static final String GALLERY_PROVIDER_SELECTION_TABLE_CREATE = "CREATE TABLE "
			+ GALLERY_PROVIDER_SELECTION_TABLE_NAME + " (" + GALLERY_PROVIDER_SELECTION_IMAGE + " TEXT PRIMARY KEY);";
	
	protected static final int VERSION = 3;

	public DatabaseHandler(Context context) {
		super(context.getApplicationContext(), "database.db", null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SETTINGS_TABLE_CREATE);
		db.execSQL(GALLERY_PROVIDER_SETTINGS_TABLE_CREATE);
		db.execSQL(GALLERY_PROVIDER_SELECTION_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL(SETTINGS_TABLE_DROP);
		db.execSQL(GALLERY_PROVIDER_SETTINGS_TABLE_DROP);
		db.execSQL(GALLERY_PROVIDER_SELECTION_TABLE_DROP);
		onCreate(db);
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}