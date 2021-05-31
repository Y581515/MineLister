package no.hvl.dat102.tabell;

import no.hvl.dat102.adt.UOrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

public class TabellUOrdnetListe<T extends Comparable<T>> implements UOrdnetListeADT<T> {

	private final static int STDK = 100;
	private final static int IKKE_FUNNET = -1;
	private int bak;
	private T[] liste;

	public TabellUOrdnetListe() {
		this(STDK);
	}

	public TabellUOrdnetListe(int startKapasitet) {
		bak = 0;
		liste = (T[]) (new Comparable[startKapasitet]);
	}

	@Override
	public void leggTilForan(T element) {
		if (bak == liste.length) {
			utvid();
		}
		for (int j = bak; j > 0; j--) { // Flytter de elementene som står
			liste[j] = liste[j - 1]; // bak element en plass bakover
		}
		liste[0] = element; // setter inn element
		bak++;

	}

	@Override
	public void leggTilBak(T element) {
		if (bak == liste.length) {
			utvid();
		}
		liste[bak] = element;
		bak++;
	}

	@Override
	public T fjernFoerste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");
		} else {
			T svar = liste[0];
			bak--;
			liste[0] = liste[bak];
			liste[bak] = null;
			return svar;
		}
	}

	@Override
	public T fjernSiste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");
		} else {
			T svar = liste[bak - 1];
			bak--;
			liste[bak] = null;
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
			while (i < bak && !(el.compareTo(liste[i]) == 0)) {
				i++;
			}
			if (i < bak) {
				resultat = i;
			}
		}
		return resultat;
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
			liste[pos] = liste[bak];
			liste[bak] = null;

		}
		return resultat;
	}

	@Override
	// for UordnetTabelListe
	public void leggTilEtter(T element, T aktuell) { //
		if (antall() == liste.length) {
			utvid();
		}
		int i = 0;
		while (i < bak && !aktuell.equals(liste[i])) {
			i++;
		}
		if (i == bak) {
			System.out.println(" Listen har ikke elementet " + aktuell);
		} else { // Først lage plass og så sette inn
			i++;
			// i peker nå på plassen etter aktuell, altså der elementet skal inn
			for (int j = bak; j > i; j--) { // Flytter de elementene som står
				liste[j] = liste[j - 1]; // bak element en plass bakover
			}
			liste[i] = element; // setter inn element
			bak++;
		}
	}//

	private void utvid() {

		T[] hjelpeTabell = (T[]) (new Comparable[liste.length * 2]);

		for (int i = 0; i < liste.length; i++) {
			hjelpeTabell[i] = liste[i];
		}

		liste = hjelpeTabell;

	}

}
