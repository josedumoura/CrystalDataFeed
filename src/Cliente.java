public class Cliente extends Conexao implements Runnable {

	public Cliente(InterfaceCliente tela) {
		super(tela);
	}

	public void run() {
		Connect();
	}
}
