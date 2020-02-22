/*******************************************************
Nimi: 	Miniprojekti 2 A
Tekijä:	Maarit Parkkonen
Pvm:	20.6.2018
Kuvaus:	- Kysytään käyttäjän ikä, pituus (m) ja paino (kg)
		  sekä onko ajokorttia. 
		- Tulostetaan tiedot.
		- Virheellisten syötteiden huomiointi.
		  (kokeilin, kun oli bonuksena neuvottu)
********************************************************/

//luokkakirjasto
import java.util.*;

//ohjelmaluokka
public class  mini2a{
	
   //pääohjelma
   public static void main(String [ ] args) {
	   
      //muuttujien esittelyt ja alustus
	  int ika=0;
	  float pituus=0, paino=0;
	  byte vastaus=0;			//ajokorttikysymyksen apuvastaus
	  boolean ajokortti=false;
	  byte kysymys=1;			//kysymyksen numero
	  boolean ok=false;			//ohjelman lopetus
	  
	  //syötteen lukuolion esittely ja luonti
	  Scanner lukija;
	  lukija=new Scanner(System.in);
	  
	  //englantilainen desimaalityyli (piste), HUOM! ei toiminut syötteiden luvussa??
	  //Locale.setDefault(Locale.ENGLISH);
	  
	  do{ //ohjelman lopetuksen kontrollointi
	   try{ 
	     switch (kysymys){ //mahdollistaa virheen pyydystämisen jälkeen paluun oikeaan kohtaan, ei break lausekkeita
		  case 1:
			kysymys=1;
			System.out.print("Anna ikäsi kokonaislukuna: ");
			ika=lukija.nextInt();
			lukija.nextLine(); //rivin tyhjennys
		  case 2: 
			kysymys=2;
			System.out.print("Anna pituutesi metreinä (esim: 1,72): ");
			pituus=lukija.nextFloat();
			lukija.nextLine();
		  case 3:
			kysymys=3;
			System.out.print("Anna painosi kiloina (esim. 62,1): ");
			paino=lukija.nextFloat();
			lukija.nextLine();
		  case 4:
			kysymys=4;
			do{ //annetun vastauksen kontrollointi
				System.out.print("Omistatko ajokortin (1=kyllä, 0=ei): ");				
				vastaus=lukija.nextByte();
				lukija.nextLine();			
				if (vastaus==1)
					ajokortti=true;
				else if (vastaus==0)
					ajokortti=false;
				else
					System.out.println("Virheellinen tieto.");
			}while ((vastaus!=1)&&(vastaus!=0)); //toistetaan, kunnes vastaus on 0 tai 1			
			break;
	     }
		 
	  //tietojen tulostus
	  System.out.println("******************************************");
	  System.out.println("Ikäsi on:\t\t"+ika+" vuotta");
	  System.out.println("Pituutesi on:\t\t"+pituus+" m");
	  System.out.println("Painosi on:\t\t"+paino+" kg");
	  System.out.print("Omistat ajokortin:\t");
	  if (ajokortti){
		System.out.println("kyllä");}
	  else{
		System.out.println("ei");
	  }
	  System.out.println("******************************************");
	  
	  //ohjelma suoritettu onnistuneesti
	  ok=true;
	  
	  //käyttäjän antamien epäsopivien syötteiden aiheuttamien virheiden pyydystys
	  }catch (InputMismatchException e){
		  System.out.print("Virheellinen tieto.");
		  lukija= new Scanner(System.in);  						//KYSYMYS: miksi tämä pitää luoda uudestaan??
	  }
	  //muiden virheiden pyydystys
	  catch (Exception e) {
		  System.out.print("Yllättävä virhe. Lopetetaan.");
		  System.exit(1);
	  }
	  
	 //jatketaan, kunnes ohjelma suoritettu onnistuneesti
	 } while (ok!=true); 
   } 
} 