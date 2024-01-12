package com.ehei;

import java.util.ArrayList;
import java.util.List;

public class ProduitService {

	private List<Produit> produitsList = new ArrayList<>();
	public boolean UnicitéProduit(Long id,String nom) {
		   for (Produit p : produitsList) {
		        if (p.getId().equals(id) || p.getNom().equals(nom)) {
		            return true;
		        }
		    }
		    return false;
	}
	
	public boolean DonneesValides(Produit p) {
	    return p.getPrix() >= 0 && p.getQuantite() >= 0;
	}
	
	
	public void ajouterProduit(Produit p) {
		if (UnicitéProduit(p.getId(),p.getNom())) {
			  throw new IllegalArgumentException("il existe déja un produit qui a le même id ou le nom.");
		}
		if (!DonneesValides(p)) {
			  throw new IllegalArgumentException("Le prix et la quantité doivent être toujours positifs");
		}
		produitsList.add(p);
	}
		
	
	public Produit readProduit(long id) {
	    for (Produit produit : produitsList) {
	        if (produit.getId() == id) {
	            return produit;
	        }
	    }

	    throw new IllegalArgumentException("Le produit avec l'ID spécifié n'a pas été trouvé.");
	}

	
	
}
