package sis.project.virtualcash.Bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import sis.project.virtualcash.MainActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.provider.Settings.Secure;
import android.util.Log;

public class SocketServ extends Thread {
		private final BluetoothServerSocket mmServerSocket;
		private UUID MY_UUID;
		private InputStream in ;
		private OutputStream out ;
		private ObjectOutputStream outObj;
		private ObjectInputStream inObj;
		public String res = "";
		   
		public SocketServ(Context context) {
		       BluetoothServerSocket tmp = null;
		       MY_UUID = UUID.fromString("6b5016b4-9b54-40bd-a7cf-87552fc75336");
		       try {
		           tmp = MainActivity.getBAdapter().listenUsingRfcommWithServiceRecord(getName(),MY_UUID);
		       } catch (IOException e) { }
		       mmServerSocket = tmp;
		   }

		   public void run() {
		       BluetoothSocket socket = null;
		       while (true) {
		           try {
		               socket = mmServerSocket.accept();
		               mmServerSocket.close();
		           } catch (IOException e) {
		               break;
		           }

		           if (socket != null) {
		               manageConnectedSocket(socket);
		               try {
						mmServerSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		               break;
		           }
		       }
		   }

		   public void manageConnectedSocket(BluetoothSocket mmSocket) {
			   String bla;
			   try {
					in = mmSocket.getInputStream();
					out = mmSocket.getOutputStream();
					outObj = new ObjectOutputStream(out);
					inObj = new ObjectInputStream(in);
					bla = inObj.readUTF();
					Log.i("bla",bla);
			   } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		   }
		   
		   public void cancel() {
		       try {
		           mmServerSocket.close();
		       } catch (IOException e) { }
		   }
		}

