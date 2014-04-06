package edu.mtu.citizenscience.thunderbay;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Comment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ThunderBayDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = {MySQLiteHelper.COLUMN_ID,MySQLiteHelper.name,MySQLiteHelper.type,MySQLiteHelper.hull,
			MySQLiteHelper.built,MySQLiteHelper.lost,MySQLiteHelper.builder,MySQLiteHelper.buildPlace,
			MySQLiteHelper.length,MySQLiteHelper.beam,MySQLiteHelper.lossType,MySQLiteHelper.cargo,
			MySQLiteHelper.livesLost, MySQLiteHelper.county,MySQLiteHelper.latitude,
			MySQLiteHelper.longitude,MySQLiteHelper.depth,MySQLiteHelper.notes}; 

	public ThunderBayDataSource(Context context){
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		System.out.println(dbHelper.DATABASE_CREATE);
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public ShipWreck createWreck(String name, String type, String hull, int built,
			int lost, String builder, String buildPlace, double length,
			double beam, String lossType, String cargo, int livesLost,
			String county, float latitude, float longitude, double depth,
			String notes)
	{
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.name, name);
		values.put(MySQLiteHelper.type, type);
		values.put(MySQLiteHelper.hull, hull);
		values.put(MySQLiteHelper.built, built);
		values.put(MySQLiteHelper.lost, lost);
		values.put(MySQLiteHelper.builder, builder);
		values.put(MySQLiteHelper.buildPlace, buildPlace);
		values.put(MySQLiteHelper.length, length);
		values.put(MySQLiteHelper.beam, beam);
		values.put(MySQLiteHelper.lossType, lossType);
		values.put(MySQLiteHelper.cargo, cargo);
		values.put(MySQLiteHelper.livesLost, livesLost);
		values.put(MySQLiteHelper.county, county);
		values.put(MySQLiteHelper.latitude, latitude);
		values.put(MySQLiteHelper.longitude, longitude);
		values.put(MySQLiteHelper.depth, depth);
		values.put(MySQLiteHelper.notes, notes);

		long insertId = database.insert(MySQLiteHelper.TABLE_THUNDERBAY, null,
				values);

		Cursor cursor = database.query(MySQLiteHelper.TABLE_THUNDERBAY,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		ShipWreck newWreck = cursorToWreck(cursor);
		cursor.close();
		return newWreck;	
	}
	
	public List<ShipWreck> getAllComments() {
	    List<ShipWreck> wrecks = new ArrayList<ShipWreck>();

	    Cursor cursor = database.query(MySQLiteHelper.TABLE_THUNDERBAY,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      ShipWreck myShipWreck = cursorToWreck(cursor);
	      wrecks.add(myShipWreck);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return wrecks;
	  }
	
	private ShipWreck cursorToWreck(Cursor cursor) {
	    ShipWreck shipWreck = new ShipWreck();
	    shipWreck.setId(cursor.getLong(0));
	    shipWreck.setName((cursor.getString(1)));
	    return shipWreck;
	  }
	

}