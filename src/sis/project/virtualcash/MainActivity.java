package sis.project.virtualcash;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.Set;

import com.google.gson.Gson;

import sis.project.virtualcash.Bluetooth.SocketClient;
import sis.project.virtualcash.Bluetooth.SocketServ;
import sis.project.virtualcash.adapter.TabsPagerAdapter;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import sis.project.virtualcash.socketManager.Client;
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class MainActivity extends FragmentActivity implements

ActionBar.TabListener {

	public static final String PREFS_NAME = "MyPrefsFile";
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	private static BluetoothAdapter BAdapter  = BluetoothAdapter.getDefaultAdapter();
	private SocketClient Client;
	private SocketServ Serveur;
	private Gson gson;
	// Tab titles
	
	private String[] tabs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		SharedPreferences currentuser = getSharedPreferences(PREFS_NAME, 0);
	    String user = currentuser.getString("name", null);
	    tabs = new String[] { "Accounts", "Transactions", "Settings", user};
	    

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	public static BluetoothAdapter getBAdapter() {
		return BAdapter;
	}

	public void setBAdapter(BluetoothAdapter bAdapter) {
		BAdapter = bAdapter;
	}

	public void deconnexion(View view) {
		Set<BluetoothDevice> devices;
		devices = MainActivity.getBAdapter().getBondedDevices();
		BluetoothDevice device = null;
		for (BluetoothDevice blueDevice : devices) {
				device = blueDevice;
		}
		Client = new SocketClient(device,this);
		Client.start();
		
		

/*SharedPreferences currentuser = getActivity().getSharedPreferences(PREFS_NAME, 0);
SharedPreferences.Editor editor = currentuser.edit();
editor.putString("name",null);
SocketClient Client= new SocketClient(); 
Client.start();
Toast.makeText(getActivity(), , duration)*/

}

	public void deconnexion2(View view) {
		Serveur = new SocketServ(this);
		Serveur.start();
		
		Toast.makeText(this,Serveur.res ,Toast.LENGTH_LONG ).show();
		
		

/*SharedPreferences currentuser = getActivity().getSharedPreferences(PREFS_NAME, 0);
SharedPreferences.Editor editor = currentuser.edit();
editor.putString("name",null);
SocketClient Client= new SocketClient(); 
Client.start();
Toast.makeText(getActivity(), , duration)*/

}

	
}

