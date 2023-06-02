package srl.ios;

import org.junit.jupiter.api.Test;

import srl.ios.models.Cliente;

public class CrudClientiTest {

    @Test
    public void insertCliente() {
        String[] cliente = {"Giacomo"};
        Cliente.insert(cliente);
    }

    @Test
    public void deleteCliente() {
        Cliente giacomo = new Cliente("Giacomo");
        giacomo.delete();
    }

    @Test
    public void updateCliente() {
        String[] cliente = {"Giacomo"};
        Cliente.insert(cliente);
        String[] updateCliente = {"Lucrezio"};
        Cliente.update(2, updateCliente);
    }

    @Test
    public void readCliente() {
        System.out.println(Cliente.getStringified(2));
    }

    @Test
    public void findCliente() {
        System.out.println(Cliente.findRecord("Giacomo"));
    }

    @Test
    public void tooManyFields() {
        Cliente.insert(null);
    }

    @Test
    public void insertDeleteCliente() {
        String[] cliente = {"Giangiacomo"};
        Cliente.insert(cliente);
        System.out.println(Cliente.find("Giangiacomo"));
        Cliente giangiacomo = new Cliente(Cliente.find("Giangiacomo"));
        giangiacomo.delete();
    }
}
