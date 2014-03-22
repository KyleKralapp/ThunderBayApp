package edu.mtu.citizenscience.thunderbay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.mtu.citizenscience.thunderbay.R;
import edu.mtu.citizenscience.thunderbay.CustomListViewAdapter;
import edu.mtu.citizenscience.thunderbay.RowItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
 
public class ImageTextListViewActivity extends Activity implements OnItemClickListener {
	
    public ArrayList<String> titles;
    public ArrayList<String> descriptions;
    public ArrayList<Integer> images;
 
    ListView listView;
    List<RowItem> rowItems;
 
    
    
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        
        // TODO:
       
        
 
        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.size(); i++) {
        	System.out.println("creating new RowItem");
            RowItem item = new RowItem(images.get(i), titles.get(i), descriptions.get(i));
            rowItems.add(item);
        }
 
        listView = (ListView) findViewById(R.id.list);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }
 
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        Toast toast = Toast.makeText(getApplicationContext(),
            "Item " + (position + 1) + ": " + rowItems.get(position),
            Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
    
    private boolean loadData(){
		InputStream csvFile;
		try {
			csvFile = getAssets().open("WreckList.csv");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile));
		try {
			String line;
			int i = 0;
			titles = new ArrayList<String>();
			descriptions = new ArrayList<String>();
			images = new ArrayList<Integer>();
			while ((line = reader.readLine()) != null) {
				String[] RowData = line.split(";");
				// TODO:
				System.out.println(line);
				System.out.println(RowData[0]);
				System.out.println(RowData[1]);
				if (i > 0) {
					titles.add(RowData[0].toString());
					descriptions.add(RowData[1].toString());
					images.add(R.drawable.thuder_bay_logo);
				}
				System.out.println("added");
				i++;
			}
		}
		catch (IOException ex) {
			// handle exception
			return false;
		}
		finally {
			try {
				csvFile.close();
			}
			catch (IOException e) {
				// handle exception
				return false;
			}
		}
		return true;

	}
}
