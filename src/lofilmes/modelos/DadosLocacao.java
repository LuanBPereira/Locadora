package lofilmes.modelos;

import java.time.LocalDate;

public record DadosLocacao(Cliente cliente, double valorPago, LocalDate data,
		String tituloFilme, int diasAlugado) {}