import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Reader reader = new Reader();
		ArrayList<Empreendedor> empreendedores = reader.getEmpreendedores("/home/lucasoliveira/arquivos/");

		empreendedores.forEach(empreendedor -> {
			try {
				reader.populateEmpreendedor(empreendedor, "/home/lucasoliveira/arquivos/");
				recursivePrinter(empreendedor.getProcessos(), "");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private static void recursivePrinter(ArrayList<Processo> processos, String prefix) {
		processos.forEach(processo -> {
			System.out.println(prefix + "[ " + processo.getId() + " ]");
			processo.getArquivos().forEach(arquivo -> {
				System.out.println(prefix + "\t- " + arquivo.getNome());
			});
		});
	}
}
