package no.hvl.dat102.tabell;

import javax.management.AttributeNotFoundException;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

public class TabellOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {

	private final static int STDK = 100;
	private final static int IKKE_FUNNET = -1;
	private int bak;
	private T[] liste;

	public TabellOrdnetListe() {
		this(STDK);
	}

	public TabellOrdnetListe(int startKapasitet) {
		bak = 0;
		liste = (T[]) (new Comparable[startKapasitet]);
	}

	@Override
	public T fjernSiste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");
		} else {
			T svar = liste[bak - 1];
			bak--;
			return svar;
		}
	}

	@Override
	public T fjernFoerste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");
		} else {
			T svar = liste[0];
			bak--;
			for (int i = 0; i < bak; i++) {
				liste[i] = liste[i + 1];
			}

			return svar;
		}
	}

	@Override
	public T foerste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");

		} else {
			T resultat = liste[0];
			return resultat;
		}
	}

	@Override
	public T siste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");

		} else {
			T resultat = liste[bak - 1];
			return resultat;
		}
	}

	@Override
	public boolean erTom() {
		return (bak == 0);
	}

	@Override
	public int antall() {
		return bak;
	}

	@Override
	public void leggTil(T element) {
		if (antall() == liste.length) {
			utvid();
		}
		int i = 0;
		while (i < bak && element.compareTo(liste[i]) > 0) {
			i++;
		}
		for (int j = bak; j > i; j--) { // Flytter de elementene som står
			liste[j] = liste[j - 1]; // bak element en plass bakover
		}
		liste[i] = element; // setter inn element
		bak++;

	}

	@Override
	public boolean inneholder(T element) {
		return (finn(element) != IKKE_FUNNET);
	}

	@Override
	public T fjern(T element) {
		T resultat = null;
		int pos = finn(element);
		if (pos == IKKE_FUNNET) {
			System.out.println("elementet er ikke i tabllen");
		} else {
			resultat = liste[pos];
			bak--;

			for (int i = pos; i < bak; i++) {
				liste[i] = liste[i + 1];
			}
			liste[bak] = null;

		}
		return resultat;

	}

	private int finn(T el) {
		int resultat = IKKE_FUNNET;

//		int indeks = 0;	
//		if (!erTom()) {
//			while (resultat == IKKE_FUNNET && indeks < bak) {
//				if (el.compareTo(liste[indeks]) == 0) {
//					resultat = indeks;
//				} else {
//					indeks++;
//				}
//			}
//		}

		if (!erTom()) {
			int i = 0;
			while (i < bak && (el.compareTo(liste[i]) > 0)) {
				i++;
			}
			if (i < bak && el.compareTo(liste[i]) == 0) {
				resultat = i;

			}
		}

		return resultat;
	}

	public String toString() {
		String resultat = "";

		for (int i = 0; i < bak; i++) {
			resultat = resultat + liste[i].toString() + "\n";
		}
		return resultat;
	}

	private void utvid() {
		T[] hjelpeTabell = (T[]) (new Comparable[liste.length * 2]);

		for (int i = 0; i < liste.length; i++) {
			hjelpeTabell[i] = liste[i];
		}

		liste = hjelpeTabell;
	}

}// class
