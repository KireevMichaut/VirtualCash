package sis.project.virtualcash.Bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import sis.project.virtualcash.MainActivity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.provider.Settings.Secure;
import android.util.Log;

public class SocketClient extends Thread {
		   private static UUID MY_UUID ;
    	   private BluetoothSocket mmSocket = null;
		   private BluetoothDevice mmDevice = null;
		   private ConnectedThread connectedThread;
		   
		   public SocketClient(BluetoothDevice device,Context context) {
		       BluetoothSocket tmp = null;
		       mmDevice = device;
		       MY_UUID = UUID.fromString("6b5016b4-9b54-40bd-a7cf-87552fc75336");
		       try {
		           tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
		       } catch (IOException e) {
		    	   Log.i("Bluetooth","Socket non created!");
		    
		       }
		       
		       mmSocket = tmp;
		   }

		   public void run() {
		       MainActivity.getBAdapter().cancelDiscovery();
		       try {
		           mmSocket.connect();
		              Log.i("Bluetooth","Connection is open !");
			    	   
		       } catch (IOException connectException) {
		      
		    	   try {
		               mmSocket.close();
		           } catch (IOException closeException) { }
		    	   Log.i("Bluetooth",MY_UUID.toString());

		           return;
		       }
		      
		       try {
					manageConnectedSocket(mmSocket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       
		   }
		   
		   public void manageConnectedSocket(BluetoothSocket mmSocket) throws IOException {
			 
			connectedThread = new ConnectedThread(mmSocket); 
			connectedThread.start();   
			
		   }
		   
		   public void cancel() {
		       try {
		           mmSocket.close();
		       } catch (IOException e) { }
		   }

		}

