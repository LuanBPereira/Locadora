package lofilmes.servicos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lofilmes.modelos.Cliente;
import lofilmes.modelos.DadosLocacao;

public class HistoricoLocacoes {

	private LocalDate dataAtual = LocalDate.of(2024, 7, 29); /* 29/7/2024 */
	private List<DadosLocacao> listaHistorico = new ArrayList<>();
	
	
	// mudar esse método pra salvar um filme em vez de só o nome do filme!
	public void salvar(Cliente cliente, double valorPago, LocalDate dataAtual, String nomeFilme,
			int diasAlugado) {
		listaHistorico.add(new DadosLocacao(cliente, valorPago, dataAtual, nomeFilme, diasAlugado));
	}
	
	public List<DadosLocacao> getHistorico() {
		return listaHistorico;
	}
	
	public List<DadosLocacao> getfilmesLocados7Dias() {
        List<DadosLocacao> alugadosRecentes = new ArrayList<>();

        for (DadosLocacao dadosL : listaHistorico) {
            LocalDate dataCompra = dadosL.data();
            if (!dataCompra.isBefore(dataAtual.minusDays(7)))
                alugadosRecentes.add(dadosL);
        }

        return alugadosRecentes;
    }

	public List<String> getFilmesMaisLocados() {
        Map<String, Integer> contagemFilmes = new HashMap<>();
        List<String> filmesMaisLocados = new ArrayList<>();
        int contagemMax = 0;

        for (DadosLocacao dadosL : listaHistorico) {
            String titulo = dadosL.tituloFilme();
            int contagemAtual = contagemFilmes.getOrDefault(titulo, 0) + 1; 
            contagemFilmes.put(titulo, contagemAtual);                               

            if (contagemAtual > contagemMax) {
                contagemMax = contagemAtual;
            }
        }

        
        for (String titulo : contagemFilmes.keySet()) {
            if (contagemFilmes.get(titulo) == contagemMax) {
                filmesMaisLocados.add(titulo);
            }
        }

        return filmesMaisLocados;
    }

	public double getValorTotalLocacoesUltimoMes() {
		double total = 0.0;

		for (DadosLocacao dadosL : listaHistorico) {
			LocalDate dataLocacao = dadosL.data();
			if (dataLocacao.getMonthValue() == 7 && dataLocacao.getYear() == dataAtual.getYear()) {
				total += dadosL.valorPago();
			}
		}
		return total;
	}

	public List<String> getClienteQueMaisLocou() {
		Map<String, Integer> contagemClientes = new HashMap<>();
		List<String> clienteQueMaisLocou = new ArrayList<>();
		
		int contagemMax = 0;
		
		for (DadosLocacao dadosL : listaHistorico) {
			String cliente = dadosL.cliente().nome() + " " + dadosL.cliente().sobrenome();
			int contagemAtual = contagemClientes.getOrDefault(cliente, 0) + 1;  
			contagemClientes.put(cliente, contagemAtual);
			
			if (contagemAtual > contagemMax) {
				contagemMax = contagemAtual;
			}
		}
		
		for(Map.Entry<String, Integer> entry : contagemClientes.entrySet()) {
			if(entry.getValue() == contagemMax) {
				clienteQueMaisLocou.add(entry.getKey());
			}
		} 
		
		return clienteQueMaisLocou;

	}
	
 	
}