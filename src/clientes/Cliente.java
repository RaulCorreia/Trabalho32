package clientes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	private String id;
	private Scanner teclado;
	
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;

	public Cliente(String id) {
		this.id = id;
		this.teclado = new Scanner(System.in);
		
		try {
			
			this.socket = new Socket("127.0.0.1", 12345);
			this.in = new DataInputStream(socket.getInputStream()); 
			this.out = new DataOutputStream(socket.getOutputStream());
			
			menu();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void menu() throws IOException {
		
		boolean exit = this.in.readBoolean();
		this.out.writeUTF(this.id);
		
		while(exit) {
			
			System.out.println("Opções: soma, sub, mult, div, registro, sair");
	    	String option = teclado.nextLine();
	    	
	    	switch(option) {
	    	
		    	case "soma":{
		    		
		    		this.out.writeUTF("soma");
		    		
		    		System.out.println("Primeiro numero: ");
		    		int i = teclado.nextInt();
		    		this.out.writeInt(i);
		    		
		    		System.out.println("Segundo numero: ");
		    		int j = teclado.nextInt();
		    		this.out.writeInt(j);
		    		
		    		String response = this.in.readUTF();
		    		System.out.println("Resultado: " + response);
		    		
		    	}
		    	break;
		    	
				case "sub":{
					
					this.out.writeUTF("sub");
		    		
		    		System.out.println("Primeiro numero: ");
		    		int i = teclado.nextInt();
		    		this.out.writeInt(i);
		    		
		    		System.out.println("Segundo numero: ");
		    		int j = teclado.nextInt();
		    		this.out.writeInt(j);
		    		
		    		String response = this.in.readUTF();
		    		System.out.println("Resultado: " + response);
					    		
		    	}
				break;
				
				case "mult":{
					
					this.out.writeUTF("mult");
		    		
		    		System.out.println("Primeiro numero: ");
		    		int i = teclado.nextInt();
		    		this.out.writeInt(i);
		    		
		    		System.out.println("Segundo numero: ");
		    		int j = teclado.nextInt();
		    		this.out.writeInt(j);
		    		
		    		String response = this.in.readUTF();
		    		System.out.println("Resultado: " + response);
				}
				break;
				
				case "div":{
					
					this.out.writeUTF("div");
		    		
		    		System.out.println("Primeiro numero: ");
		    		int i = teclado.nextInt();
		    		this.out.writeInt(i);
		    		
		    		System.out.println("Segundo numero: ");
		    		int j = teclado.nextInt();
		    		this.out.writeInt(j);
		    		
		    		String response = this.in.readUTF();
		    		System.out.println("Resultado: " + response);
				}
				break;
				
				case "registro":{
					
					this.out.writeUTF("show");
		    		String response = this.in.readUTF();
		    		System.out.println(response);
		    		
				}
				break;
				
				case "sair":{
					this.out.writeUTF("sair");
					System.out.println("Finalizado");
					exit = false;
				}
				break;
	    	}
	    	
	    	if(!option.equalsIgnoreCase("sair"))
	    		teclado.nextLine();
			
		}
		
	}
}
