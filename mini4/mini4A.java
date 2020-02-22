/********************************************************************
Nimi: 	Miniprojekti 4 A
Tekijä: Maarit Parkkonen
Pvm:	28.6.2018
Kuvaus: Laskee ja tulostaa ympyrän pinta-alan tai pallon tilavuuden
		käyttäjän antamien tietojen perusteella
*********************************************************************/

//luokkakirjasto
import java.io.*;
import java.util.*;    //mm. Scanner
import java.lang.Math; //PI, pow


public class mini4A{
   //pääohjelma
   public static void main(String [ ] args) {
      byte vastaus=0;					//käyttäjän antama vastaus
	  double tulos;						//laskennan tulos
	  double sade;					
	  boolean ok=false;					//onko ohjelma onnistuneesti suoritettu
	  boolean kohdeValittu=false;		//onko kohde (ympyrä/pallo) valittu

	  //syötteen lukuolio
	  Scanner lukija;					//esittely
	  lukija=new Scanner(System.in);	//luonti
	  
	  //aloitusohjeet (ä=\u00E4)
	  System.out.println("\nOhjelma laskee valintasi mukaisesti:");	  
      System.out.println("- ympyr\u00E4n pinta-alan");	  
      System.out.println("- pallon tilavuuden\n");	  
	  
	  do{ 
		try{
			if (kohdeValittu==false){					//jos kohdetta ei ole valittu
			//kohteen valinta
				do{
					System.out.print("Valitse kohde (ympyr\u00E4 = 1, pallo = 2):");
					vastaus=lukija.nextByte();
					lukija.nextLine();
				}while ((vastausOk(vastaus,1,2))!=true); //kysytään, kunnes vastaus ok
				kohdeValittu=true;
			}
			
			//säteen syöttö
			System.out.print("Anna s\u00E4teen pituus cm (esim. 22,5):");	
			sade=lukija.nextDouble();
			lukija.nextLine();		
			
			//laskenta ja tuloksen näyttö
			if (vastaus==1){							//jos valinta on ympyrä
				tulos=(Math.PI)*(Math.pow(sade,2));
				System.out.print("\nYmpyr\u00E4n pinta-ala on "+pyorista(tulos)+" cm2\n");		  
			}
			else{										//jos valinta on pallo
				tulos=(4*(Math.PI)*(Math.pow(sade,3)))/3;
				System.out.print("\nPallon tilavuus on "+pyorista(tulos)+" cm3\n");
			}
			ok=true;
		}
		//virheiden pyydystys
		catch (InputMismatchException e){				//käyttäjän antamien syötteiden tietotyyppivirheet
		  if (kohdeValittu==false)
			System.out.println("Virheellinen valinta. Valitse uudestaan.");
		  else
			System.out.println("Virheellinen pituus, annoitko s\u00E4teen lukuna?"); 
		  lukija= new Scanner(System.in);
		}
		catch (Exception e) {					   		//muut virheet
		  System.out.print("Yll\u00E4tt\u00E4v\u00E4 virhe. Lopetetaan.");
		  System.exit(1);
		}
	  } while (ok!=true); 								//jatketaan, kunnes ohjelma suorittu onnistuneesti
   }
   
   //käyttäjän antamien vastausten oikeellisuuden tarkistus
   //- palauttaa true = vastaus ok, false = vastaus virheellinen
   public static boolean vastausOk(int vastaus, int min, int max){
	   boolean ok=true;
	   //jos vastaus virheellinen
	   if ((vastaus<min)||(vastaus>max)){
			ok=false;
			System.out.println("Virheellinen valinta. Valitse uudestaan.");			
	   }
	   return ok;
   }
   
   //Pyöristää desimaaliluvun kahden desimaalin tarkkuuteen
   //-palauttaa pyöristetyn desimaaliluvun
   public static double pyorista(double luku){
		int kokonaisOsa = (int)((luku * 100)+0.5);	//kerrotaan halutulla desimaalimäärällä ja muunnetaan kokonaisluvuksi
		luku = (double)kokonaisOsa / 100;			//jaetaan kokonaisluku takaisin desimaaliluvuksi
		return luku;
   }
} 