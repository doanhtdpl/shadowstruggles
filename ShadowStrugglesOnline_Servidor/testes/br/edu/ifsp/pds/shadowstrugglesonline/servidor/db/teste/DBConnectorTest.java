package br.edu.ifsp.pds.shadowstrugglesonline.servidor.db.teste;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Random;
import java.math.BigInteger;

import org.junit.Test;

import br.edu.ifsp.pds.shadowstrugglesonline.servidor.db.DBConnector;
import br.edu.ifsp.pds.shadowstrugglesonline.servidor.util.Login;

public class DBConnectorTest {

	@Test
	public void testDBConnector() {
		try {
			DBConnector connector = new DBConnector();
			assertNotNull(connector);
		} catch (ClassNotFoundException e) {
			fail(e.getMessage());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testNovoUsuario(){
		DBConnector connector = null;
		try {
		connector = new DBConnector();
		} catch (ClassNotFoundException e) {
			fail(e.getMessage());
		} catch (SQLException e) {
			fail(e.getMessage());
		}	
			
			Random random = new Random();

			Login loginTeste = new Login(String.valueOf(Long.toHexString(random.nextLong())), 
					String.valueOf(Long.toHexString(random.nextLong())));
			
			try {
				connector.novoUsuario(loginTeste);
			} catch (SQLException e) {
				fail(e.getMessage());
			}
			
			boolean valida = false;
			try {
				valida = connector.verificaUsuario(loginTeste);
			} catch (SQLException e) {
				fail(e.getMessage());
			}
			
			try {
				connector.close();
			} catch (SQLException e) {
				fail(e.getMessage());
			}
			
			assertTrue(valida);
	}
	
	@Test
	public void testGetEscolaridade(){
		DBConnector connector = null;
		
		
		try {
			connector = new DBConnector();
		} catch (ClassNotFoundException e) {
			fail(e.getMessage());
		} catch (SQLException e) {
			fail(e.getMessage());
		}
			
			Random random = new Random();

			Login loginTeste = new Login(String.valueOf(Long.toHexString(random.nextLong())), 
					String.valueOf(Long.toHexString(random.nextLong())));
			
			try {
				connector.novoUsuario(loginTeste);
			} catch (SQLException e) {
				fail(e.getMessage());
			}
			
			int escolaridadeTeste = random.nextInt();
			
			try {
				connector.AtualizarEscolaridade(loginTeste.login, escolaridadeTeste);
			} catch (SQLException e) {
				fail(e.getMessage());
			}
			
			int escolaridadeRecebida = 0;
			try {
				escolaridadeRecebida = connector.getEscolaridade(loginTeste.login);
			} catch (SQLException e) {
				fail(e.getMessage());
			}
			
			assertEquals(escolaridadeTeste, escolaridadeRecebida);
	}
	
	
}
