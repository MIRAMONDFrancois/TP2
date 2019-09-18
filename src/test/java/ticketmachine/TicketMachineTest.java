package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
	// S3 :  on n’imprime pas le ticket si le montant inséré est insuffisant
	public void notEnoughBalance() {
		machine.insertMoney(PRICE-1);
		assertFalse("La machine a imprimé alors qu'il fallait pas!", machine.printTicket());
		assertFalse("La machine a imprimé alors qu'il fallait pas!", machine.printTicket());    
	}
        
        @Test
	// S4 : on imprime le ticket si le montant inséré est suffisant
	public void enoughBalance() {
		machine.insertMoney(PRICE);
		assertTrue("La machine n'a pas imprimé alors qu'il fallait!", machine.printTicket()); 
		machine.insertMoney(99999);
		assertTrue("La machine n'a pas imprimé alors qu'il fallait!", machine.printTicket());  
		assertTrue("La machine n'a pas imprimé alors qu'il fallait!", machine.printTicket());  
		assertTrue("La machine n'a pas imprimé alors qu'il fallait!", machine.printTicket());              
	}
        
        @Test
	// S5 : quand on imprime un ticket la balance est décrémentée du prix
	public void balanceChangedAfterPrint() {
		machine.insertMoney(PRICE+1);
                int balanceTemp = machine.getBalance();
                machine.printTicket();
                assertEquals("La balance n'a pas changé correctement!", balanceTemp-PRICE, machine.getBalance());
	}
        
        @Test
	// S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	public void totalPrint() {
            machine.insertMoney(PRICE);
            assertEquals(0, machine.getTotal());
            machine.printTicket();
            assertEquals("Le montant collecté n'a pas été mis à jour", PRICE, machine.getTotal());
	}
        
        @Test
	// S7 : refund() rend correctement la monnaie
	public void refundCorrect() {
                machine.insertMoney(10);
                assertEquals(10, machine.getBalance());
                assertEquals("refund() ne rend pas correctement la monnaie", 10, machine.refund());
	}
        
        @Test
	// S8 : refund() remet la balance à zéro
	public void refundResetBalance() {
                machine.refund();
                assertEquals("la balance n'a pas été remise à zéro.", 0, machine.getBalance());
	}
        
        @Test (expected = IllegalArgumentException.class)
	// S9 : on ne peut pas insérer un montant négatif
	public void illegalAmount() {
                machine.insertMoney(-1);
	}
        
        @Test (expected = IllegalArgumentException.class)
	// S10 : on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	public void illegalMachine() {
                machine = new TicketMachine(-1);
	}

}
