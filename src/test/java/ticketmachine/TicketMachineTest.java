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
	public void priceIsCorrectlyInitialized() {
		// Le prix correspond au paramètre passé lors de l'initialisation
		assertEquals(PRICE, machine.getPrice());
	}

	@Test
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals(10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}

	
	@Test
	public void refundClearsBalance() {
		machine.insertMoney(40); // On insère de l'argent
		machine.refund(); // On appuie sur "cancel"
		assertEquals(0, machine.getBalance()); // La balance est remise à 0
	}	

}
