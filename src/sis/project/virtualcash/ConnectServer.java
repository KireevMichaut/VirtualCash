package sis.project.virtualcash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import sis.project.virtualcash.socketManager.Client;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ConnectServer extends AsyncTask<Void, Void, Boolean> {
private EditText editText;
private String reponse = "";
String envoi = "";
private boolean recu = false;
public ConnectServer(String json) {
		this.envoi = json;
	}
	@Override
	protected Boolean doInBackground(Void... arg0) {
		Client client = new Client();
	    client.run();
	    
	    try {
	    	String str = envoi;
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(client.getSocket().getOutputStream())),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));
			out.println(str);
			
			setReponse(in.readLine());
			Log.i("Recu",str);
			setRecu(true);
			out.flush();
			out.close();
			in.close();
		client.getSocket().close();
	    } catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return true;
	}
	public boolean isRecu() {
		return recu;
	}
	public void setRecu(boolean recu) {
		this.recu = recu;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

}	