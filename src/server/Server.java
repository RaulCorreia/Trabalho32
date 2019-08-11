package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;


public class Server extends Thread {
	
	private MulticastSocket receivMultiCast;
	private DatagramSocket sendMultiCast;
    private InetAddress group;
	
	public Server() throws UnknownHostException, IOException {
		
	 	this.sendMultiCast = new DatagramSocket();
        this.group = InetAddress.getByName("230.0.0.0");
        this.receivMultiCast = new MulticastSocket(4446);
        this.receivMultiCast.joinGroup(this.group);
        
	}
	
	public void run() {
    	
		try {
    		
    		while (true) {
        	
    			byte[] buf = new byte[256];
        		DatagramPacket packet = new DatagramPacket(buf, buf.length);
				this.receivMultiCast.receive(packet);
			
                String received = new String(packet.getData(), 0, packet.getLength());
                String split[] = received.split("!");
                
                
                if(!split[0].equalsIgnoreCase("server")) {
                	
                	switch(split[2]) {
	                	case "soma":{
	                		String calcReceived = split[3];
	                		String calc[] = calcReceived.split("@");
	                		int i = Integer.parseInt(calc[0]);
	                		int j = Integer.parseInt(calc[1]);
	                		
	                		int result = i + j;
	                		String resultStr = split[1]+"!"+split[2]+"!"+String.valueOf(result);
	                		sendCast(resultStr);
	                	}
	                	break;
	                	
	                	case "sub":{
	                		String calcReceived = split[3];
	                		String calc[] = calcReceived.split("@");
	                		int i = Integer.parseInt(calc[0]);
	                		int j = Integer.parseInt(calc[1]);
	                		
	                		int result = i - j;
	                		String resultStr = split[1]+"!"+split[2]+"!"+String.valueOf(result);
	                		sendCast(resultStr);
	                	}
	                	break;
	                	
	                	case "div":{
	                		String calcReceived = split[3];
	                		String calc[] = calcReceived.split("@");
	                		int i = Integer.parseInt(calc[0]);
	                		int j = Integer.parseInt(calc[1]);
	                		
	                		String resultStr = split[1]+"!"+split[2]+"!";
	                		
	                		if(j == 0) {
	                			resultStr += "Divisão por 0 não é possivel";
	                		} else {
	                			int result = i / j;
	                			resultStr += String.valueOf(result);
	                		}
	                		
	                		sendCast(resultStr);
	                	}
	                	break;
	                	
	                	case "mult":{
	                		String calcReceived = split[3];
	                		String calc[] = calcReceived.split("@");
	                		int i = Integer.parseInt(calc[0]);
	                		int j = Integer.parseInt(calc[1]);
	                		
	                		int result = i * j;
	                		String resultStr = split[1]+"!"+split[2]+"!"+String.valueOf(result);
	                		sendCast(resultStr);
	                	}
	                	break;
	                
                	}
                	
                } 
                
    		}
    		
    	 } catch (IOException e) {
			e.printStackTrace();
    	 }
    	
	}
	
	
	private void sendCast(String mensagem) {
    	
    	try {
    		
    		mensagem = "server!"+mensagem;
    		byte[] buf = mensagem.getBytes();
	    	DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
	    	sendMultiCast.send(packet);
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	
}
