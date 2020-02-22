/*************************************************************************
Nimi:	mini10C
Tekijä:	Maarit Parkkonen
Pvm:	2.8.2018
Kuvaus: Joukko -luokka
		- attribuutit: nimi, joukko (Set<Integer>)
		- metodit: konstruktori, alkiot, muodostaAlkiot, tulostaAlkiot,
		  tulosta, unioni, leikkaus, erotus

		Pääohjelma määrittelee kaksi viiden kokonaislukualkion joukkoa,
		joille ohjelmassa toteutetaan unioni, leikkaus ja erotukset.
		Ohjelma tulostaa tulosjoukon jokaisen operaation jälkeen.
**************************************************************************/

//luokkakirjasto
import java.io.*;
import java.util.*;

class Joukko{
	String nimi;				//joukon nimi
	Set<Integer> joukko;		//joukkorakenne alkioille
	
	//konstruktori
	Joukko(String name){
		joukko = new HashSet<Integer>();
		nimi=name;
	}
	
	//palauttaa alkiot joukkona
	public Set<Integer> alkiot(){
		return joukko;
	}
	
	//luo joukon alkiot min-max väliltä askel mitan välein
	public void muodostaAlkiot(int min, int max, int askel){
		for (int i=min; i<max;i+=askel)
			joukko.add(i);
	}
	
	//joukon tietojen tulostus
	public void tulostaAlkiot(){
		System.out.print("Joukon "+nimi+" alkiot: ");	//otsikko
		tulosta(joukko);								//alkiot
	}	
	
	//parametrina saadun joukon alkioiden tulostus
	private void tulosta(Set<Integer> j){
		Iterator<Integer> i=j.iterator();
		while(i.hasNext()){
			int alkio=i.next();
			System.out.print(alkio+" ");
		}
		System.out.println();	
	}

	//muodostaa ja tulostaa unionin tulosjoukon parametrina saadun joukon alkioiden kanssa	
	public void unioni(Set<Integer> j){
		Set<Integer> apujoukko=new HashSet<Integer>(); //luodaan uusi apujoukko
		apujoukko.addAll(joukko);					   //kopioidaan apujoukkoon joukon alkiot
		apujoukko.addAll(j);						   //apujoukkoon lisätään kaikki joukon j alkiot (ei samoja)
		tulosta(apujoukko);
	}

	//muodostaa ja tulostaa leikkauksen tulosjoukon parametrina saadun joukon alkioiden kanssa
	public void leikkaus(Set<Integer> j){
		Set<Integer> apujoukko=new HashSet<Integer>(); //luodaan uusi apujoukko
		apujoukko.addAll(joukko);					   //kopioidaan apujoukkoon joukon alkiot 
		apujoukko.retainAll(j);						   //apujoukosta poistetaan kaikki ne alkiot, joita ei ole joukossa j
		tulosta(apujoukko);
	}
	
	//muodostaa ja tulostaa erotuksen tulosjoukon parametrina saadun joukon alkioiden kanssa
	public void erotus(Set<Integer> j){
		Set<Integer> apujoukko=new HashSet<Integer>(); //luodaan uusi apujoukko
		apujoukko.addAll(joukko);					   //kopioidaan apujoukkoon joukon alkiot
		apujoukko.removeAll(j);						   //apujoukosta poistetaan kaikki joukon j alkiot
		tulosta(apujoukko);
	}
}

//
public class mini10C{
   //pääohjelma
   public static void main(String [ ] args) {
	  //määritellään joukko-oliot
	  Joukko joukkoA= new Joukko("A");
	  Joukko joukkoB= new Joukko("B");	  
	  
	  //muodostetaan joukkoihin alkiot
	  joukkoA.muodostaAlkiot(3,8,1);
	  joukkoB.muodostaAlkiot(3,12,2);
	  
	  //tulostetaan joukkojen alkiot
	  joukkoA.tulostaAlkiot();
	  joukkoB.tulostaAlkiot();	  
	  System.out.println();  
	  
	  //unioni
	  System.out.print("Joukkojen A ja B unioni: ");
	  joukkoA.unioni(joukkoB.alkiot());
	  System.out.println();
		
	  //leikkaus
	  System.out.print("Joukkojen A ja B leikkaus: ");
	  joukkoA.leikkaus(joukkoB.alkiot());
	  System.out.println();
	  
	  //erotus A-B
	  System.out.print("Joukkojen A ja B erotus: ");
	  joukkoA.erotus(joukkoB.alkiot());
	  System.out.println();
	  
	  //erotus B-A
	  System.out.print("Joukkojen B ja A erotus: ");
	  joukkoB.erotus(joukkoA.alkiot());
	  System.out.println();
   }
} 