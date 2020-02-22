/*******************************************************************
Nimi:	miniprojekti 8 B
Tekijä:	Maarit Parkkonen
Pvm:	11.7.2018
Kuvaus: kysyNimi -funktio 
        - kysyy käyttäjältä tämän nimen ja 
		  palauttaa sen StringBuffer oliona
		- parametrina lukija -olio
		
		kysySyntymaAika -funktio
        - kysyy käyttäjältä tämän syntymäpäivän, -kuukauden ja -vuoden.
		  Palauttaa syntymäajan StringBuffer oliona.
		- parametrina lukija -olio
		
		Pääohjelma yhdistää  tiedot yhdeksi merkkijonoksi ja 
		tulostaa sen käyttäjälle
		
		(ä=\u00E4 ö=\u00F6)
********************************************************************/

//luokkakirjasto
import java.lang.String;
import java.lang.StringBuffer;
import java.util.*; 	//Scanner, InputMismatchException

//
public class  mini8B{

	//pääohjelma
	public static void main(String [ ] args) {
	  
	  
	  Scanner lukija=new Scanner(System.in);		//syötteen lukuolion luonti
	  StringBuffer tiedot=new StringBuffer();		//merkkijonon puskuriolion luonti
	  
	  try{
		tiedot.append("Nimesi on: ").append(kysyNimi(lukija));					  //Nimirivin merkkijonon muodostus (nimitiedon kysymisen kutsuminen)
		tiedot.append("Syntym\u00E4aikasi on: ").append(kysySyntymaAika(lukija)); //Syntymäaikarivin lisäys edelliseen (syntymäaikatiedon kysymisen kutsuminen)
		System.out.println("\n"+tiedot.toString());								  //Tietojen tulostus (skandit tulostuu ? -merkkeinä..)	  

	  }
	  catch (InputMismatchException e){											  //virheiden nappaus
		  System.out.print("Virheellinen sy\u00F6te. Lopetetaan.");
		  System.exit(1);
	  }
	  catch (Exception e) {
		  System.out.print("Yll\u00E4tt\u00E4v\u00E4 virhe. Lopetetaan.");
		  System.exit(1);
	  }
	}
	
	//kysyy käyttäjän nimen ja palauttaa sen StringBuffer -oliona
	public static StringBuffer kysyNimi(Scanner l){
		StringBuffer palautus=new StringBuffer();		//puskuriolio, joka palautetaan
		String vastaus;									//staattinen merkkijono-olio vastauksen lukemiseen
		System.out.print("Kirjoita nimesi: ");		
		vastaus=l.nextLine();							//vastauksen luku (koko rivi)
		palautus.append(vastaus).append("\n");			//liitetään vastaus puskuriolion loppuun ja lopuksi rivinvaihto
		return palautus;
	}
	
    //kysyy käyttäjän syntymäajan tiedot (päivän, kuukauden ja vuoden),
	//muodostaa niistä syntymäajan ja palauttaa sen StringBuffer -oliona
	public static StringBuffer kysySyntymaAika(Scanner l){
		StringBuffer palautus=new StringBuffer();		//puskuriolio, joka palautetaan
		int pp, kk, vvvv;								//päivä, kuukausi, vuosi
		System.out.println("Kirjoita syntym\u00E4aikasi tiedot");
		System.out.print("\tP\u00E4iv\u00E4: ");			
		pp=l.nextInt();									//luetaan syntymäpäivä
		System.out.print("\tKuukausi: ");			
		kk=l.nextInt();									//luetaan syntymäkuukausi
		System.out.print("\tVuosi: ");			
		vvvv=l.nextInt();								//luetaan syntymävuosi
		palautus.append(pp).append(".").append(kk).append(".").append(vvvv);	//muodostetaan liittämällä vastauksista palautus puskuriolioon
		return palautus;
	}
   
} 