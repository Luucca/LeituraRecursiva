import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Gerenciador gerenciador = new Gerenciador("/home/lucasoliveira/arquivos");

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
