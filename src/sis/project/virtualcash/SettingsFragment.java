package sis.project.virtualcash;

import java.util.Set;

import sis.project.virtualcash.Bluetooth.SocketClient;
import sis.project.virtualcash.Bluetooth.SocketServ;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class SettingsFragment extends Fragment {
	public static final String PREFS_NAME = "MyPrefsFile";
	View rootView;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		 rootView = inflater.inflate(R.layout.fragment_settings, container, false);
		
		
	/*SocketServ Serveur= new SocketServ(this.getActivity()); 
		Serveur.start();*/
		return rootView;
	}
	
		
}
