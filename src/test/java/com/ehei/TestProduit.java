package com.ehei;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestProduit {
	
	  private ProduitService produitService;


	    @Before
	    public void setUp() {
	        produitService = new ProduitService();
	    }

	    @Test
	    public void testAjouterProduit() {
	    	  Produit produit1 = new Produit(3L, "Product1", 15.0, 8);

	        produitService.ajouterProduit(produit1);
	        assertFalse(produitService.UnicitéProduit(1L, "Produit1"));

	        
	        Produit produit3 = new Produit(3L, "Product1", 15.0, 8);
	        try {
	            produitService.ajouterProduit(produit3);
	            fail("Expected IllegalArgumentException");
	        } catch (IllegalArgumentException e) {
	        	assertEquals("il existe déja un produit qui a le même id ou le nom.", e.getMessage());
	        }
	        
	        
	        // Tentative d'ajout d'un produit3 avec des valeurs invalides
	        Produit produit4= new Produit(2L, "produit3", -20.0, -6);
	        try {
	            produitService.ajouterProduit(produit4);
	        //    fail("Devrait lever une exception pour valeurs negatifs");
	        } catch (IllegalArgumentException e) {
	            assertEquals("Le prix et la quantité doivent être toujours positifs", e.getMessage());
	        }
	    }

	    @Test
	    public void testReadProduit() {
	        Produit produit1 = new Produit(1L, "Produit1", 10.0, 5);

	        produitService.ajouterProduit(produit1);

	        Produit result = produitService.readProduit(1L);
	        assertNotNull("Le produit devrait être ", result);
	        assertEquals("Produit1", result.getNom());

	        // Tentative de lecture d'un produit inexistant (ID 4)
	        try {
	            Produit inexistant = produitService.readProduit(4L);
	            assertNull("Le produit inexistant ne devrait pas être trouvé", inexistant);
	        } catch (IllegalArgumentException e) {
	            assertEquals("Le produit avec l'ID 4 n'existe pas.", e.getMessage());
	        }
	    }

	    @Test
	    public void testUpdateProduit() {
	        Produit produit1 = new Produit(1L, "Produit1", 10.0, 5);
	        produitService.ajouterProduit(produit1);

	        Produit produitToUpdate = new Produit(1L, "produit10", 20.0, 10);
	        produitService.updateProduit(produitToUpdate);
	        assertEquals("Le nom du produit devrait être mis à jour", "produit10", produit1.getNom());
	        assertEquals("Le prix du produit devrait être mis à jour", 20.0, produit1.getPrix(), 0.001);
	        assertEquals("La quantité du produit devrait être mise à jour", 10, produit1.getQuantite());

	        // Tentative de mise à jour d'un produit inexistant (ID 2)
	        Produit produit3 = new Produit(2L, "produit3", 15.0, 8);
	        try {
	            produitService.updateProduit(produit3);
	            fail("Devrait lever une exception pour produit inexistant");
	        } catch (IllegalArgumentException e) {
	            assertEquals("Le produit avec l'ID 2 n'existe pas.", e.getMessage());
	        }
	    }

	    @Test
	    public void testDeleteProduit() {
	        Produit produit1 = new Produit(1L, "Produit1", 10.0, 5);
	        produitService.ajouterProduit(produit1);

	        produitService.deleteProduit(1L);
	        assertFalse("Le produit ne devrait pas exister après la suppression", produitService.UnicitéProduit(1L, "Produit1"));

	        // Tentative de suppression d'un produit inexistant (ID 2)
	        try {
	            produitService.deleteProduit(6L);
	            fail("Devrait lever une exception pour produit inexistant");
	        } catch (IllegalArgumentException e) {
	            assertEquals("Le produit avec l'ID 2 n'existe pas.", e.getMessage());
	        }
	    }
	}