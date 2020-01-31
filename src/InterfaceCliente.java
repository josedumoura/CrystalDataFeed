import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class InterfaceCliente extends JFrame {

	private static final long serialVersionUID = 3919540301747528940L;

	private JPanel contentPane;
	private JTextField textServidor;
	private JTextField textUser;
	private JTextField textPassword;
	private JTextField textPorta;
	private JTextField textChave;
	private JTextField textComando;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	private Socket client;

	public void Socket() {
		String host = textServidor.getText();
		int port = Integer.parseInt(textPorta.getText());

		try {
			client = new Socket(host, port);
		} catch (Exception err) {
			System.err.println("Erro no Socket, Metodo: Socket");
		}
	}

	public void sendCommand() {
		String comando = textComando.getText();

		try {

			client.getOutputStream().write(comando.getBytes());
			client.getOutputStream().write("\n".getBytes());

		} catch (Exception err) {
			System.err.println("Erro ao enviar");
		}
	}

	public void login() {
		String key = textChave.getText();
		String user = textUser.getText();
		String pass = textPassword.getText();
		try {
			client.getOutputStream().write(key.getBytes());
			client.getOutputStream().write("\n".getBytes());

			client.getOutputStream().write(user.getBytes());
			client.getOutputStream().write("\n".getBytes());

			client.getOutputStream().write(pass.getBytes());
			client.getOutputStream().write("\n".getBytes());

		} catch (Exception err) {
			System.err.println("Erro ao logar!");
		}
	}

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceCliente frame = new InterfaceCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Socket getClient() {
		return client;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	/**
	 * Create the frame.
	 */
	public InterfaceCliente() {
		setTitle("FEED TESTE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 457, 568);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//																				Botão Conectar
		JButton btnNewButton = new JButton("Conectar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Thread t = new Thread(new Cliente(InterfaceCliente.this));
				t.start();

			}
		});
		btnNewButton.setBounds(28, 83, 90, 23);
		contentPane.add(btnNewButton);

//																				Botão Desconectar
		JButton btnNewButton_1 = new JButton("Desconectar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				try {
					client.getOutputStream().write("quit".getBytes());
					client.getOutputStream().write("\n".getBytes());
					textArea.append("Client fechado!"+"\n");
					System.out.println("Client fechado!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(136, 83, 108, 23);
		contentPane.add(btnNewButton_1);

//																				Botão Enviar
		JButton btnNewButton_2 = new JButton("Enviar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendCommand();
			}
		});
		btnNewButton_2.setBounds(254, 152, 73, 23);
		contentPane.add(btnNewButton_2);

//																				Botão Limpar LOG
		JButton btnNewButton_3 = new JButton("Limpar LOG");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
			}
		});
		btnNewButton_3.setBounds(332, 152, 101, 23);
		contentPane.add(btnNewButton_3);

//		CRIAÇÃO DAS JLABELS, JTEXT FIELDS E JTEXT AREA -----------------------------------------------------------
//																				--- JLabels ---
		JLabel lblNewLabel = new JLabel("Dados de Acesso");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 108, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Servidor:");
		lblNewLabel_1.setBounds(10, 33, 54, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Usu\u00E1rio:");
		lblNewLabel_2.setBounds(10, 58, 56, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Senha:");
		lblNewLabel_3.setBounds(177, 58, 46, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Porta:");
		lblNewLabel_4.setBounds(305, 33, 46, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Chave:");
		lblNewLabel_5.setBounds(305, 58, 46, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Requisi\u00E7\u00F5es");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(9, 127, 73, 14);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Comando:");
		lblNewLabel_7.setBounds(10, 156, 63, 14);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Comandos:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setForeground(Color.RED);
		lblNewLabel_8.setBounds(10, 183, 63, 14);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Solicitar cota\u00E7\u00E3o: SQT - Exemplo: SQT PETR4");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_9.setForeground(Color.RED);
		lblNewLabel_9.setBounds(10, 208, 207, 13);
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Parar cota\u00E7\u00E3o: USQ - Exemplo: USQ PETR4");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_10.setForeground(Color.RED);
		lblNewLabel_10.setBounds(233, 208, 198, 13);
		contentPane.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Solicitar book: BQT - Exemplo: BQT PETR4");
		lblNewLabel_11.setForeground(Color.RED);
		lblNewLabel_11.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_11.setBounds(10, 228, 196, 13);
		contentPane.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Solicitar trades: GQT - Exemplo: GQT PETR4");
		lblNewLabel_12.setForeground(Color.RED);
		lblNewLabel_12.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_12.setBounds(10, 250, 201, 13);
		contentPane.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Parar book: UBQ - Exemplo: UBQ PETR4");
		lblNewLabel_13.setForeground(Color.RED);
		lblNewLabel_13.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_13.setBounds(233, 228, 187, 13);
		contentPane.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel("Parar trades: UQT - Exemplo: UQT PETR4");
		lblNewLabel_14.setForeground(Color.RED);
		lblNewLabel_14.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_14.setBounds(233, 250, 192, 13);
		contentPane.add(lblNewLabel_14);
//																				--- JArea com JScroll ---
		textArea = new JTextArea("");
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 274, 451, 266);

		getContentPane().add(scrollPane);
		scrollPane.setViewportView(textArea);
		this.setVisible(true);
		contentPane.add(scrollPane);

//																				--- JTextFields ---
		textServidor = new JTextField();
		lblNewLabel_1.setLabelFor(textServidor);
		textServidor.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textServidor.setBounds(73, 30, 222, 20);
		contentPane.add(textServidor);
		textServidor.setColumns(10);

		textUser = new JTextField();
		lblNewLabel_2.setLabelFor(textUser);
		textUser.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textUser.setBounds(73, 55, 94, 20);
		contentPane.add(textUser);
		textUser.setColumns(10);

		textPassword = new JTextField();
		lblNewLabel_3.setLabelFor(textPassword);
		textPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textPassword.setBounds(222, 55, 73, 20);
		contentPane.add(textPassword);
		textPassword.setColumns(10);

		textPorta = new JTextField();
		textPorta.setText("81");
		textPorta.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textPorta.setBounds(345, 30, 86, 20);
		contentPane.add(textPorta);
		textPorta.setColumns(10);

		textChave = new JTextField();
		lblNewLabel_5.setLabelFor(textChave);
		textChave.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textChave.setBounds(345, 55, 86, 20);
		contentPane.add(textChave);
		textChave.setColumns(10);

		textComando = new JTextField();
		lblNewLabel_7.setLabelFor(textComando);
		textComando.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textComando.setBounds(72, 152, 172, 20);
		contentPane.add(textComando);
	}
}
