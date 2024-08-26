
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


	private List<DadosLocacao> historico = new ArrayList<>();
	
	public void salvar(Cliente cliente, double valorPago, LocalDate data, Filme filme, int diasAlugado) {
		historico.add(new DadosLocacao(cliente, valorPago, data, filme, diasAlugado));
	}
	
	public List<DadosLocacao> getHistorico() {
		return historico;
	}
	
	public List<DadosLocacao> getfilmesLocados7Dias() {
		LocalDate dataAtual = LocalDate.now();
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
		LocalDate dataAtual = LocalDate.now();
		double total = 0.0;

		for (DadosLocacao dadosL : historico) {
			LocalDate dataLocacao = dadosL.data();
			if (dataLocacao.getMonthValue() == dataAtual.getMonthValue() && dataLocacao.getYear() == dataAtual.getYear()) {
				total += dadosL.valorPago();
			}
		}
		return total;
	}

	public List<String> getClienteQueMaisLocou() {
		Map<Long, Integer> contagemLocacaoCliente = new HashMap<>();
		Map<Long, String> nomesClientes = new HashMap<>(); // usei para mapear CPF aos nomes
		List<String> clienteQueMaisLocou = new ArrayList<>();
		int contagemMax = 0;

		for (DadosLocacao dadosL : historico) {
			Long cpfCliente = dadosL.cliente().getCPF();
			String nomeCliente = dadosL.cliente().getNome() + " " + dadosL.cliente().getSobrenome();

			// atualiza o nome do cliente se ainda não estiver no mapa
			// por exemplo, se já existir a chave (cpf) `1` com o valor de `Luan`
			// ele vai apenas ignorar, pois já existe essa chave com um valor.
			nomesClientes.putIfAbsent(cpfCliente, nomeCliente);

			int contagemAtual = contagemLocacaoCliente.getOrDefault(cpfCliente, 0) + 1;
			contagemLocacaoCliente.put(cpfCliente, contagemAtual);

			if (contagemAtual > contagemMax) {
				contagemMax = contagemAtual;
			}
		}

		for (Map.Entry<Long, Integer> entry : contagemLocacaoCliente.entrySet()) {
			if (entry.getValue() == contagemMax) {
				clienteQueMaisLocou.add(nomesClientes.get(entry.getKey()));
			}
		}

		return clienteQueMaisLocou;
	}

}