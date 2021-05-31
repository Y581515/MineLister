package no.hvl.dat102.kjedet;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

/**
 * 
 * @param <T> elementypen
 */
public class KjedetOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {
	private int antall;
	private LinearNode<T> foerste, siste;

	/**
	 * Lager en ny tom liste.
	 */
	public KjedetOrdnetListe() {
		antall = 0;
		foerste = null;
		siste = null;
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
	public void leggTil(T element) {
		LinearNode<T> nyNode = new LinearNode<T>(element);
		boolean funnet = false;
		if (erTom()) {
			foerste = nyNode;
			siste = foerste;
			antall++;
		} else {
			if ((element.compareTo(foerste.getElement())) < 0) {
				nyNode.setNeste(foerste);
				foerste = nyNode;
				antall++;

			} else {
				LinearNode<T> forgjenger = foerste;
				LinearNode<T> aktuell = forgjenger.getNeste();

				for (int i = 2; i <= antall && (!funnet); i++) {
					if ((element.compareTo(aktuell.getElement())) < 0) {
						funnet = true;
					} else {
						forgjenger = forgjenger.getNeste();
						aktuell = aktuell.getNeste();
					}
				}
				if (funnet) {
					nyNode.setNeste(aktuell);
					forgjenger.setNeste(nyNode);
					antall++;
				} else {
					siste.setNeste(nyNode);
					siste = siste.getNeste();
					antall++;
				}

			}
		}
	}

	@Override
	public T fjern(T element) {
		T svar = null;
		LinearNode<T> forrige = null, denne = foerste;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			forrige = denne;
			denne = denne.getNeste();
		}
		if (denne != null && element.equals(denne.getElement())) { // funnet
			antall--;
			svar = denne.getElement();
			if (forrige == null) { // Første element
				foerste = foerste.getNeste();
				if (foerste == null) { // Tom liste
					siste = null;
				}
			} else { // Inni listen eller bak
				forrige.setNeste(denne.getNeste());
				if (denne == siste) { // bak
					siste = forrige;
				}
			}
		} // ikke-funn
		return svar;
	}

	@Override
	public boolean inneholder(T element) {
		LinearNode<T> denne = foerste;
		boolean resultat = false;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			denne = denne.getNeste();
		}
		if (denne != null && element.equals(denne.getElement())) {

			resultat = true;

		} // ikke-funn
		return resultat;
	}

	public void show() {
		System.out.print("{");
		LinearNode<T> node = foerste;
		while (node != null) {
			System.out.print(node.getElement() + " ");
			node = node.getNeste();
		}
		System.out.println("}");
	}
}// class
