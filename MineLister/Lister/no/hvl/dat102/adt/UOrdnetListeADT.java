package no.hvl.dat102.adt;

public interface UOrdnetListeADT<T extends Comparable<T>> extends ListeADT<T> {

	
	
	/**
	 * Legger et element foran liste.
	 *
	 * @param element det nye elementet som skal legges til
	 */
	void leggTilForan(T element);
	
	
	
	/**
	 * Legger et element bak liste.
	 *
	 * @param element det nye elementet som skal legges til
	 */
	void leggTilBak(T element);
	
	
	
	/**
	 * Legger et element etter nabo.
	 *
	 * @param element det nye elementet som skal legges til
	 */
	void leggTilEtter(T elem, T nabo);
	
	



}
