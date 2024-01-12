package com.ehei;

import java.util.ArrayList;
import java.util.List;

public class ProduitService {

	private List<Produit> produitsList = new ArrayList<>();
	public boolean UnicitéProduit(Long id,String nom) {
		   for (Produit p : produitsList) {
		        if (p.getId() == id || p.getNom().equals(nom)) {
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

	

public void updateProduit(Produit updatedpro) {
    for (Produit existingProduit : produitsList) {
        if (existingProduit.getId() == updatedpro.getId()) {
            if (DonneesValides(updatedpro)) {
                existingProduit.setNom(updatedpro.getNom());
                existingProduit.setPrix(updatedpro.getPrix());
                existingProduit.setQuantite(updatedpro.getQuantite());
                return;  
            } else {
                throw new IllegalArgumentException("Le prix et la quantité doivent être positifs.");
            }
        }
    }

    
    throw new IllegalArgumentException("Le produit n'existe pas: " + updatedpro.getId());
}

public void deleteProduit(long id) {
    Produit produitSupprimer = null;


    for (Produit produit : produitsList) {
        if (produit.getId() == id) {
            produitSupprimer = produit;
            break;
        }
    }

    if (produitSupprimer != null) {
    	produitsList.remove(produitSupprimer);
    } else {
       
        throw new IllegalArgumentException("Le produit à supprimer n'existe pas.");
    }
}
}
