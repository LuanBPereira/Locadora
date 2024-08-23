
package lofilmes.servicos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lofilmes.modelos.Cliente;
import lofilmes.modelos.DadosLocacao;
import lofilmes.modelos.Filme;

public class HistoricoLocacoes {

	private LocalDate dataAtual = LocalDate.now(); 
	private List<DadosLocacao> historico = new ArrayList<>();
	
	public void salvar(Cliente cliente, double valorPago, LocalDate dataAtual, Filme filme, int diasAlugado) {
		historico.add(new DadosLocacao(cliente, valorPago, dataAtual, filme, diasAlugado));
	}
	
	public List<DadosLocacao> getHistorico() {
		return historico;
	}
	
	public List<DadosLocacao> getfilmesLocados7Dias() {
        List<DadosLocacao> alugadosRecentes = new ArrayList<>();

        for (DadosLocacao dadosL : historico) {
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

        for (DadosLocacao dadosL : historico) {
            String titulo = dadosL.filme().getTitulo();
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

		for (DadosLocacao dadosL : historico) {
			LocalDate dataLocacao = dadosL.data();
			if (dataLocacao.getMonthValue() == dataAtual.getMonthValue() && dataLocacao.getYear() == dataAtual.getYear()) {
				total += dadosL.valorPago();
			}
		}
		return total;
	}
// ORIGINAL
//	public List<String> getClienteQueMaisLocou() {
//		Map<String, Integer> contagemClientes = new HashMap<>();
//		List<String> clienteQueMaisLocou = new ArrayList<>();
//		
//		int contagemMax = 0;
//		
//		for (DadosLocacao dadosL : historico) {
//			String cliente = dadosL.cliente().getNome() + " " + dadosL.cliente().getSobrenome();
//			int contagemAtual = contagemClientes.getOrDefault(cliente, 0) + 1;  
//			contagemClientes.put(cliente, contagemAtual);
//			
//			if (contagemAtual > contagemMax) {
//				contagemMax = contagemAtual;
//			}
//		}
//		
//		for(Map.Entry<String, Integer> entry : contagemClientes.entrySet()) {
//			if(entry.getValue() == contagemMax) {
//				clienteQueMaisLocou.add(entry.getKey());
//			}
//		} 
//		
//		return clienteQueMaisLocou;
//
//	}
//	
	// TESTE
	public List<String> getClienteQueMaisLocou() {
		Map<Long, String> contagemClientes = new HashMap<>();
		List<String> clienteQueMaisLocou = new ArrayList<>();
		
		
		for (DadosLocacao dadosL : historico) {
			Long idCliente = dadosL.cliente().getID();
			String nomeCliente
			String contagemCliente = contagemClientes.getOrDefault(idCliente, dadosL.cliente().getNome());  
			contagemClientes.put(dadosL.cliente().getID(), dadosL.cliente().getNome());
			
			
			
		}
		
		for(Map.Entry<String, Integer> entry : contagemClientes.entrySet()) {
			if(entry.getValue() == contagemMax) {
				clienteQueMaisLocou.add(entry.getKey());
			}
		} 
		
		return clienteQueMaisLocou;

	}
	
}