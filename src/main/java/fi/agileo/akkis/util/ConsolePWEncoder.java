package fi.agileo.akkis.util;

import java.util.Scanner;

import java.util.Base64;

public class ConsolePWEncoder {
	
	public static void main(String[] args) {
		Scanner lukija = new Scanner(System.in);
		System.out.print("Anna salasana: ");
		String password = lukija.nextLine();
		
		byte[] passwordBytes = password.getBytes();
		String kryptattuna = Base64.getEncoder().encodeToString(passwordBytes);		
		System.out.println("Salasanasi on kryptattuna: " + kryptattuna);
		lukija.close();
	}
}	


