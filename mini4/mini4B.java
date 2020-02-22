/********************************************************************
Nimi: 	Miniprojekti 4 B
Tekijä: Maarit Parkkonen
Pvm:	28.6.2018
Kuvaus: Pelkistetty laskin
		- käyttäjä antaa kaksi lukua
		- käyttäjä valitsee luvuille laskutoimituksen
				*summa, erotus, kerto tai jako
		- tulos tulostetaan näytölle
*********************************************************************/

//luokkakirjasto
import java.io.*;
import java.util.*;    //mm. Scanner

public class mini4B{
   //pääohjelma
   public static void main(String [ ] args) {
      byte operaattori=0;				//käyttäjän operaattori valinta
	  double luku1=0,luku2=0;			//käyttäjän antamat luvut (operandit)
	  double tulos;						//laskennan tulos
	  boolean ok=false;					//onko ohjelma onnistuneesti suoritettu
	  boolean luvutAnnettu=false;		//onko luvut annettu
	  byte kysymys=1;					//

	  //syötteen lukuolio
	  Scanner lukija;					//esittely
	  lukija=new Scanner(System.in);	//luonti
	  
	  //aloitusohjeet (ä=\u00E4)
      System.out.println("\nTERVETULOA k\u00E4ytt\u00E4m\u00E4\u00E4n pelkistetty\u00E4 laskinta!");	  
	  System.out.println("Kirjoita kaksi lukua ja valitse niille laskutoimitus:");	  
      System.out.println("- summa, erotus, kerto tai jakolasku");	
      System.out.println("Desimaaliluvussa k\u00E4yt\u00E4 pilkkua\n");	  
	  
	  //tietojen pyytäminen ja tarkistus
	  do{ 
		try{
	     switch (kysymys){ //mahdollistaa virheen pyydystämisen jälkeen paluun oikeaan kohtaan, ei break lausekkeita
		  case 1:  //luku1
			kysymys=1;
			System.out.print("Kirjoita 1.luku: ");
			luku1=lukija.nextDouble();
			lukija.nextLine(); 
		  case 2:  //luku2
			kysymys=2;
			System.out.print("Kirjoita 2.luku: ");
			luku2=lukija.nextDouble();
			lukija.nextLine();
			luvutAnnettu=true;
		  case 3:  //operaatio
			kysymys=3;
			do{
				System.out.print("Valitse laskutoimitus (1=summa, 2=erotus, 3=kerto, 4=jako): ");
				operaattori=lukija.nextByte();
				lukija.nextLine();	
			}while ((vastausOk(operaattori,1,4))!=true); //toistetaan, kunnes valinta ok		
			break;
	     } //switch
		 
		 //laskenta ja tulostus näytölle
		 System.out.println("\n**TULOS*************************************************");
		 switch (operaattori){
		  case 1: //summa
			System.out.println(luku1+" + "+luku2+" = "+(luku1+luku2));		
			break;
		  case 2: //erotus
			System.out.println(luku1+" - "+luku2+" = "+(luku1-luku2));			  
			break;
		  case 3: //kerto
			System.out.println(luku1+" * "+luku2+" = "+(luku1*luku2));			  
			break;
		  case 4: //jako
			if (luku1==0)
				System.out.println(luku1+" / "+luku2+" = nollaa ei voi jakaa");	
			else if (luku2==0)
				System.out.println(luku1+" / "+luku2+" = nollalla ei voi jakaa");				
			else
				System.out.println(luku1+" / "+luku2+" = "+(luku1/luku2));			  
			break;
		 }
		 System.out.println("********************************************************");		 
		 ok=true;
		}
		//virheiden pyydystys
		catch (InputMismatchException e){				//käyttäjän antamien syötteiden tietotyyppivirheet
		  if (luvutAnnettu==false)						//virhe lukujen antamisessa
			System.out.println("Virheellinen luku, annoitko numeroita?"); 		
		  else											//virhe operaation valinnassa
			System.out.println("Virheellinen valinta. Valitse uudestaan.");	
		  lukija= new Scanner(System.in);
		}
		catch (Exception e) {					   		//muut virheet
		  System.out.print("Yll\u00E4tt\u00E4v\u00E4 virhe. Lopetetaan.");
		  System.exit(1);
		}
	  } while (ok!=true); 								//toistetaan, kunnes ohjelma suorittu onnistuneesti
   }
   
   //käyttäjän antamien vastausten oikeellisuuden tarkistus
   //- palauttaa true = vastaus ok, false = vastaus virheellinen
   public static boolean vastausOk(byte vastaus, int min, int max){
	   boolean ok=true;
	   //jos vastaus virheellinen
	   if ((vastaus<min)||(vastaus>max)){
			ok=false;
			System.out.println("Virheellinen valinta. Valitse uudestaan.");			
	   }
	   return ok;
   }

} 