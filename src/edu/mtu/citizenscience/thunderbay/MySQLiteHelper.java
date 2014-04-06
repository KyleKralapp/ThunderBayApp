package edu.mtu.citizenscience.thunderbay;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper{


	public static final String DATABASE_NAME = "thunderbay.db";
	public static final String TABLE_THUNDERBAY = "thunderbay";
	public static final String COLUMN_ID = "_id";
	public static final String name = "name";
	public static final String type = "type";
	public static final String hull = "hull";
	public static final String built = "built";
	public static final String lost = "lost";
	public static final String builder = "builder";
	public static final String buildPlace = "buildPlace";
	public static final String length = "length";
	public static final String beam = "beam";
	public static final String lossType = "lossType";
	public static final String cargo = "cargo";
	public static final String livesLost = "livesLost";
	public static final String county = "county";
	public static final String latitude = "latitude";
	public static final String longitude = "longitude";
	public static final String depth = "depth";
	public static final String notes = "notes";

	
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table" + TABLE_THUNDERBAY + "(" + COLUMN_ID
		      + " integer primary key autoincrement, "+ name + "TEXT NOT NULL,"
			+ type + "TEXT," + hull + "TEXT," + built + "INTEGER," + lost + "INTEGER," + builder + "TEXT," + buildPlace + "TEXT," +
			length + "DOUBLE," + beam + "DOUBLE," + lossType + "TEXT," + cargo + "TEXT," + livesLost + "INTEGER," + county + "TEXT," + latitude 
			+ "FLOAT," + longitude + "FLOAT," + depth + "DOUBLE," + notes + "TEXT" + ");";
	

			public MySQLiteHelper(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_THUNDERBAY);
		    onCreate(db);

	}
}
