/*******************************************************************************
Nimi:	Miniprojekti 5 B
Tekijä: Maarit Parkkonen
Pvm:	29.6.2018
Kuvaus: laskePintaAla -metodi:
		- laskee pinta-alan ja palauttaa tuloksena aareja tai hehtaareja
		
		Pääohjelma testaa metodia
********************************************************************************/

//luokkakirjasto
import java.io.*;
import java.lang.String;
//
public class mini5B{
   //pääohjelma (ä=\u00E4 ö=\u00F6)
   public static void main(String [ ] args) {	  
	  try{
		  //testataan metodia
		  System.out.println("\nPinta-ala aareina******************** ");
		  System.out.println(laskePintaAla(56,77,true));			//default: 43.12
		  System.out.println(laskePintaAla(1258,89,true));			//default: 1119,62
		  System.out.println(laskePintaAla(0,9.6,true));			//default: 0.0
		  System.out.println(laskePintaAla(0.06,0.12,true));		//default: 0,000072 (7.2E-5)
		  System.out.println(laskePintaAla(856,1678,true));			//default: 14363,68
		  System.out.println(laskePintaAla(3780,5899,true));		//default: 222982,2

		  System.out.println("Pinta-ala hehtaareina*****************");
		  System.out.println(laskePintaAla(56,77,false));			//default: 0,4312
		  System.out.println(laskePintaAla(1258,89,false));			//default: 11,2
		  System.out.println(laskePintaAla(0,9.6,false));			//default: 0.0
		  System.out.println(laskePintaAla(0.06,0.12,false));		//default: 0,00000072 (7.2E-7)
		  System.out.println(laskePintaAla(856,1678,false));		//default: 14,36
		  System.out.println(laskePintaAla(3780,5899,false));		//default: 2229,82

	  }
	  catch (Exception e) {	//virheiden pyydystys		  
		  System.out.print("Yllättävä virhe. Lopetetaan.");
		  System.exit(1);
	  }

   }
   
   //laskee pinta-alan ja palauttaa tuloksena aareja tai hehtaareja
   //-parametrit leveys ja pituus metrejä, aari: true=aareja false=hehtaareja
   public static float laskePintaAla(double leveys,double pituus, boolean aari){
		float tulos;				//palautusarvo
		double ala=leveys*pituus;	//laskennan tulos m2
		if (aari==true)				//muutetaan aareiksi
			tulos=(float)ala/100;	//aari=10mx10m
		else						//muutetaan hehtaareiksi
			tulos=(float)ala/10000; //hehtaari=100a
		if (tulos>1)				//yli ykköstä suuremmat tulokset
			return pyorista(tulos); //pyöristetään kahteen desimaaliin
		else
			return tulos;
   }

   //Pyöristää desimaaliluvun kahden desimaalin tarkkuuteen
   //-palauttaa pyöristetyn desimaaliluvun
   public static float pyorista(float luku){
		int kokonaisOsa = (int)((luku * 100)+0.5);	//kerrotaan halutulla desimaalimäärällä ja muunnetaan kokonaisluvuksi
		luku = (float)kokonaisOsa / 100;			//jaetaan kokonaisluku takaisin desimaaliluvuksi
		return luku;
   }
   
} 