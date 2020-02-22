/*****************************************************
Nimi:	Miniprojekti 3 B
Tekijä: Maarit Parkkonen
Pvm:	27.6.2018
Kuvaus: Ohjelma laskee 
		- vuoden kokonaistulon 
		- keskimääräisen kuukausikohtaisen tulon 
		- suurimman kuukausikohtaisen tulon 
		käyttäjän antamien tietojen perusteella.
******************************************************/

//luokkakirjastot
import java.io.*;
import java.util.*;

public class mini3B{
	public static final int KK=12;	//kuukausien lukumäärä	
	
   //pääohjelma
   public static void main(String [ ] args) {
    
	//tulotaulukko
	float [] tulot;					//esittely
	tulot=new float [KK];			//luonti
	
	float tulo;						//yhden kuukauden tulo
	int i=0;						//indeksoi kuukausia
	boolean ok=false;				//onko ohjelma onnistuneesti suoritettu
	  
	//syötteen lukuolio
	Scanner lukija;					//esittely
	lukija=new Scanner(System.in);	//luonti
	
	//aloitusohjeet
	System.out.println("Ohjelma laskee:");
	System.out.println("- vuoden kokonaistulon");
	System.out.println("- keskimääräisen kuukausikohtaisen tulon");
	System.out.println("- suurimman kuukausikohtaisen tulon");
	System.out.println("antamiesi tietojen perusteella.");	
	System.out.println("");	
	
	//kysytään käyttäjältä tulotiedot
	System.out.println("Syötä kuukausikohtaiset tulot (esim. 2460,50):");
	System.out.println("");	
	do{ 
	  try{
		while(i<KK){				//kuukausien läpikäynti
			do{						//vastauksen kontrollointi
				System.out.print("Anna "+(i+1)+". kuukauden tulo:");
				tulo=lukija.nextFloat();
				lukija.nextLine();	
				if (tulo<0)			//jos virheellinen, negatiivinen luku
					System.out.println("Tulo ei voi olla negatiivinen");
				else				//luku ok
					tulot[i]=tulo;	//tallennus taulukkoon
			}while (tulo<0);		//tulon oltava positiivinen
			i++;					//siirrytään seuraavaan kuukauteen
		}
		
		//tulosten laskenta ja tulostus näytölle
		System.out.println("*************************************************************");
		System.out.println("Kokonaistulot ovat "+pyorista(kokonaisTulo(tulot)));					//kutsutaan laskentametodia, jonka tulos..
		System.out.println("Keskiarvoinen kuukausikohtainen tulo on "+pyorista(keskiarvo(tulot)));	//..pyöristetään pyöristys -metodilla
		System.out.println("Suurin kuukausikohtainen tulo on "+pyorista(suurinTulo(tulot)));
		System.out.println("*************************************************************");
		ok=true;
	  }
	  //virheiden pyydystys
	  catch (InputMismatchException e){			//käyttäjän antamien syötteiden tietotyyppivirheet
		  System.out.println("Virheellinen tulo, annoitko tulon lukuna?");
		  lukija= new Scanner(System.in);
	  }
	  catch (Exception e) {					   //muut virheet
		  System.out.print("Yllättävä virhe. Lopetetaan.");
		  System.exit(1);
	  }
	}while(ok!=true); 						   //jatketaan, kunnes ohjelma suorittu onnistuneesti
   }

   //Laskee taulukon lukujen keskiarvon
   //-palauttaa keskiarvon desimaalilukuna
   public static float keskiarvo(float[] luvut){
	   float summa=0;
	   int kpl=luvut.length;	 //taulukon pituus
	   for (int i=0;i<kpl;i++)   //taulukon läpikäynti
		   summa=summa+luvut[i]; //luvun summaus
	   if (summa>0)				 //nollaa ei jaeta
		summa=summa/kpl;
	   return summa;
   }
   
   //Etsii taulukon lukujen maksimiarvon
   //-palauttaa suurimman luvun desimaalilukuna
   public static float suurinTulo(float[] luvut){
	   float max=luvut[0];					
	   for (int i=1;i<luvut.length;i++)		//taulukon läpikäynti
		   if (max<luvut[i])				//jos taulukon luku on muuttujaa suurempi
			   max=luvut[i];				//vaihdetaan luku muuttujaan
	   return max;
   }
   
   //Laskee taulukon lukujen summan
   //-palauttaa summan desimaalilukuna
   public static float kokonaisTulo(float[] luvut){
	   float summa=0;
	   for (int i=0;i<luvut.length;i++)		//taulukon läpikäynti
		   summa=summa+luvut[i];			//luvun summaus
	   return summa;	
   }
   
   //Pyöristää desimaaliluvun kahden desimaalin tarkkuuteen
   //-palauttaa pyöristetyn desimaaliluvun
   public static float pyorista(float luku){
		int kokonaisOsa = (int)((luku * 100)+0.5);	//kerrotaan halutulla desimaalimäärällä ja muunnetaan kokonaisluvuksi
		luku = (float)kokonaisOsa / 100;			//jaetaan kokonaisluku takaisin desimaaliluvuksi
		return luku;
   }
   
} 