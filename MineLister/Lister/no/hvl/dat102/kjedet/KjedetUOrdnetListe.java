package no.hvl.dat102.kjedet;

import no.hvl.dat102.adt.UOrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

public class KjedetUOrdnetListe<T extends Comparable<T>> implements UOrdnetListeADT<T> {

	private int antall;
	private LinearNode<T> foerste, siste;

	/**
	 * Lager en ny tom liste.
	 */
	public KjedetUOrdnetListe() {
		antall = 0;
		foerste = null;
		siste = null;
	}

	@Override
	public void leggTilForan(T element) {
		LinearNode<T> nyNode = new LinearNode<T>(element);

		if (erTom()) {
			foerste = nyNode;
			siste = foerste;
			antall++;
		} else {
			nyNode.setNeste(foerste);
			foerste = nyNode;
			antall++;
		}

	}

	@Override
	public void leggTilBak(T element) {
		LinearNode<T> nyNode = new LinearNode<T>(element);

		if (erTom()) {
			foerste = nyNode;
			siste = foerste;
			antall++;
		} else {
			siste.setNeste(nyNode);
			siste = nyNode;
			antall++;
		}

	}

	@Override
	public void leggTilEtter(T el, T nabo) {
		/*
		 * som legger til en ny node med elementet nyttElement rett bak noden med
		 * elementet el. Dersom el ikke fins i strukturen, skal den nye noden plasseres
		 * inn helt framme.
		 */
		LinearNode<T> p = foerste;
		boolean funnet = false;
		LinearNode<T> nyNode = new LinearNode<T>(nabo);
		if (p == null) {// Tom struktur
			foerste = nyNode;
			siste = foerste;
		} else {// Ikke-tom struktur
			while (p != null && !funnet) {
				if (p.getElement().equals(el)) {
					nyNode.setNeste(p.getNeste());
					p.setNeste(nyNode);
					funnet = true;
					if (p == siste) {
						siste = nyNode;
					}
				} else {
					p = p.getNeste();
				}
			} // while
			if (!funnet) {// Innsetting foran
				nyNode.setNeste(foerste);
				foerste = nyNode;
			}
			antall++;
		} // metode

	}

	@Override
	public T fjernFoerste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");
		} else {
			T svar = foerste.getElement();
			foerste = foerste.getNeste();
			antall--;
			if (erTom()) {
				siste = null;
			}
			return svar;
		}
	}

	@Override
	public T fjernSiste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");
		} else {
			T svar = siste.getElement();
			antall--;
			if (antall == 0) {
				foerste = null;
				siste = null;
			} else {
				siste = foerste;
				for (int i = 1; i < antall; i++) {
					siste = siste.getNeste();
				}
				siste.setNeste(null);
			}
			return svar;

		}
	}

	@Override
	public T foerste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");

		} else {
			T svar = foerste.getElement();
			return svar;
		}
	}

	@Override
	public T siste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");

		} else {
			T svar = siste.getElement();
			return svar;
		}
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public boolean inneholder(T element) {
		LinearNode<T> denne = foerste;
		while (denne != null && !element.equals(denne.getElement())) {
			denne = denne.getNeste();
		}
		return (denne != null);

	}

	@Override
	public T fjern(T element) { // Hvis flere, så fjernes første forekomst hvis det fins, ellers returneres null
		// Søk etter og fjern gitt element (generell, uordnet liste)
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");
		}
		T svar = null;
		LinearNode<T> forrige = null, denne = foerste;
		while (denne != null && !element.equals(denne.getElement())) {
			forrige = denne;
			denne = denne.getNeste();
		}
		if (denne != null) {
			antall--;
			svar = denne.getElement();
			if (forrige == null) { // Første element
				foerste = foerste.getNeste();
				if (foerste == null) {
					siste = null;
				}
			} else {// Midt i listen eller bak
				forrige.setNeste(denne.getNeste());
				if (denne == siste) {
					siste = forrige;
				}
			}
		}
		return svar;
	}

	public void snuKjedetStruktur() {
		LinearNode<T> r, n, s; // s peker på første noden i strukturen som
		// skal snues(reverseres).
		s = foerste;
		LinearNode<T> s_2 = foerste;
		r = null; // initier r, den reverserte strukturen,
		// til den tomme listen
		while (s != null) {
			n = s; // La n peke på s sin første node
			s = s.getNeste(); // La nå s peke på resten av strukturen
			n.setNeste(r); // Kjed n til resten av r
			r = n; // og la r peke på dens nye første node
		}
		// Til slutt, la start peke på den reverserte
		// kjedete strukturen.
		foerste = r;
		siste = s_2;
	}// metode

	public int finnAntallLike(T el) {
		LinearNode<T> p = foerste;
		int antallLike = 0;
		while (p != null) {
			if (p.getElement().equals(el)) {//

				antallLike++;
			}
			p = p.getNeste();
		} // while
		return antallLike;
	}// metode

//	//samme som legg etter
//	public void leggTilBak(T el, T nyttElement) {
//		/*
//		 * som legger til en ny node med elementet nyttElement rett bak noden med
//		 * elementet el. Dersom el ikke fins i strukturen, skal den nye noden plasseres
//		 * inn helt framme.
//		 */
//		LinearNode<T> p = foerste;
//		boolean funnet = false;
//		LinearNode<T> nyNode = new LinearNode<T>(nyttElement);
//		if (p == null) {// Tom struktur
//			foerste = nyNode;
//			siste = foerste;
//		} else {// Ikke-tom struktur
//			while (p != null && !funnet) {
//				if (p.getElement().equals(el)) {
//					nyNode.setNeste(p.getNeste());
//					p.setNeste(nyNode);
//					funnet = true;
//					if (p == siste) {
//						siste = nyNode;
//					}
//				} else {
//					p = p.getNeste();
//				}
//			} // while
//			if (!funnet) {// Innsetting foran
//				nyNode.setNeste(foerste);
//				foerste = nyNode;
//			}
//			antall++;
//		} // metode
//	}

	public void show() {
		System.out.print("{");
		LinearNode<T> node = foerste;
		while (node != null) {
			System.out.print(node.getElement() + " ");
			node = node.getNeste();
		}
		System.out.println("}");
	}

}
