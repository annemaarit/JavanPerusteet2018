/********************************************************************************
Nimi:	mini10B
Tekijä:	Maarit Parkkonen
Pvm:	25.7.2018
Kuvaus:	Tehtava -luokka
		- attribuutit: kuvaus
		- metodit: konstruktori, kysyTiedot, tulostaTehtava 
		Pääohjelma leipoo kakun
		- määrittelee tehtäville jonorakenteen, lisää testitehtäviä jonoon ja
    	  testaa jonorakenteen toimivuutta

*********************************************************************************/

//luokkakirjasto
import java.io.*;
import java.util.*;

class Tehtava{
	private String kuvaus;
	
	Tehtava(){
		kysyTehtavaKuvaus();
	}
	
	Tehtava (String teksti){
		kuvaus=teksti;
	}
	
	private void kysyTehtavaKuvaus(){
		Scanner lukija=new Scanner(System.in);				//syötteen lukuolion luonti		
		System.out.print("**UUSI TEHTAVA, kirjoita kuvaus: ");
		kuvaus=lukija.nextLine();
	}
	
	public void tulostaTehtava(){
		System.out.println("\t"+kuvaus);					//tehtävän kuvaus		
	}
}

//
public class mini10B{
   //pääohjelma
   public static void main(String [ ] args) {
      Queue<Tehtava> tehtavajono = new LinkedList<Tehtava>();
	  Scanner syotelukija=new Scanner(System.in);				//syötteen lukuolion luonti
	  Tehtava tehtava;
	  Tehtava tehtava1;
	  Tehtava tehtava2;
	  Tehtava[] tehtavat;
	  
	  tehtavat=new Tehtava[6];
	  tehtavat[0]=new Tehtava("Lue kakkuohje kirjasta \"Tuhat kakkua\" s.45 huolellisesti.");
	  tehtavat[1]=new Tehtava("Etsi valmiiksi raaka-aineet ja leivonta valineet.");
	  tehtavat[2]=new Tehtava("Laita uuni paalle ja valitse oikea lampotila.");
	  tehtavat[3]=new Tehtava("Mittaa ja sekoita aineet ohjeen mukaisessa jarjestyksessa.");
	  tehtavat[4]=new Tehtava("Kaada valmis taikina kakkuvuokaan ja laita vuoka uuniin. Noudata paistoaikaa.");
	  tehtava1=new Tehtava("Odota, etta kakku jaahtyy ja sitten herkuttele!");	  
	  tehtava2=new Tehtava("Tiskaa leivontavalineet ja pyyhi poydat.");	  	   
	  teeJono(tehtavajono,tehtavat);
	  tulostaJono(tehtavajono);

	  tehtavajono = new LinkedList<Tehtava>();
	  teeJono(tehtavajono,tehtavat);
	  
	  System.out.println("Jonossa ekana on:");
	  tehtava=tehtavajono.element(); 
	  tehtava.tulostaTehtava();
      System.out.println("Tehdaanpa pois kolme ekaa tehtavaa:");  
	  poistaJonosta(tehtavajono,3); 
	  
	  System.out.println("Nyt jonossa ekana on: "); 
	  tehtava=tehtavajono.element(); 
	  tehtava.tulostaTehtava();
	  
	  System.out.println("Lisataan kaksi uutta tehtavaa:");
	  tehtavajono.add(tehtava1);
	  tehtavajono.add(tehtava2);
	  tehtava1.tulostaTehtava();
	  tehtava2.tulostaTehtava();
	  
	  System.out.println("Ja keksi lopuksi yksi oma tehtava:");
	  tehtava=new Tehtava();
	  tehtavajono.offer(tehtava);
	  
	  System.out.println("Ollaanpa oikein ahkeria ja yritetaan tehda 7 tehtavaa:");	  
	  poistaJonosta(tehtavajono,7);  
	  
	  System.out.println("Ovatko tehtavat varmasti loppu?");
	  if (tehtavajono.isEmpty())
		  System.out.println("Kylla ovat!");
	  else
	  	  System.out.println("Valitan, eivat ole.");
		  
   }
   
	
	public static void tulostaJono(Queue<Tehtava> jono){
	  Tehtava t;
	  System.out.println("TEHTAVAJONO");
	  while (jono.peek()!=null){
		  t=jono.element();
		  t.tulostaTehtava();
		  jono.remove();
	  }		
	}
	
	public static void teeJono(Queue<Tehtava> jono, Tehtava[] taulukko){
	  for (int i=0;i<5;i++)
	  	jono.add(taulukko[i]);	
	}
	
	public static void poistaJonosta(Queue<Tehtava> jono, int kpl){
	  Tehtava t;
	  for (int i=1;i<=kpl;i++){
		 if (jono.peek()!=null){
			t=jono.poll();
			t.tulostaTehtava();
		 }
		 else{
			  System.out.println("Tehty "+(i-1)+" tehtavaa. Tehtavat loppuivat.");
			  i=kpl+1;
		 }
	  }	 		
	}
	
} 