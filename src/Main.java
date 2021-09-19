import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		final String caminhoInicial = "C:\\Users\\Lucas\\Music\\arquivos";
		Gerenciador gerenciador = new Gerenciador(caminhoInicial);

		try {
			ArrayList<Empreendedor> empreendedores = gerenciador.getEmpreendedores();
			empreendedores.forEach(empreendedor -> {
				empreendedor.imprimirArvore(true);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
