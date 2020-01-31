import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Conexao {
	
	private InterfaceCliente tela;
	
	public Conexao(InterfaceCliente tela) {
		this.tela = tela;
	}

	public void Connect() {
		try {

			tela.Socket();

			String line;

			BufferedReader in = new BufferedReader(new InputStreamReader(tela.getClient().getInputStream()));

			tela.login();

			while ((line = in.readLine()) != null) {
				System.out.println(line);
				tela.getTextArea().append(line + "\n");
			}
		} catch (Exception err) {
			try {
				tela.getClient().close();
			} catch (IOException e) {
				System.out.println("Catch, client fechado!");
			}
			System.err.println("Erro na Conexão, Metodo: Connect");
		}
	}
}
