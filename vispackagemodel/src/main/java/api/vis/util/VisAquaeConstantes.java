package api.vis.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public interface VisAquaeConstantes {

	public class Bandeira {

		public final static byte VERDE = 0;

		public final static byte AMARELA = 1;

		public final static byte VERMELHA_PATAMAR_1 = 2;

		public final static byte VERMELHA_PATAMAR_2 = 3;

	}

	public class TarifaCoelba2023 {

		// para consumo até 150 kWh/mês
		public final static BigDecimal CONSUMO_FAIXA_1 = new BigDecimal("0.83235");

		// para consumo entre 151 kWh/mês e 500 kWh/mês
		public final static BigDecimal CONSUMO_FAIXA_2 = new BigDecimal("0.93517");

		// para consumo acima de 500 kWh/mês
		public final static BigDecimal CONSUMO_FAIXA_3 = new BigDecimal("1.00504");
	}

	public class Status {

		public static final Long STATUS_ABERTA = 1L;

		public static final Long STATUS_ENCERRADO = 2L;

		public static final String ABERTA = "ABERTA";

		public static final String ENCERRADO = "ENCERRADO";
	}

	public class Entity {

		public static final String USUARIO = "Usuario";

		public static final String ELETRODOMESTICO = "Eletrodomestico";
		
		public static final String ELETRO_USER = "EletroUser";

	}

	public class Deleted {

		public static final String DELETED = "Deleted";

	}

	public class Key {

		//4fa4f390d5764816a0422456fe1c14bb
		public static final String API_SETOR_ELETRICO = "d14dfb83475d47b3b92332f121f7fc9d";

	}
	
	public class Console {
                                                       
		public static final String FORMATO_CONSUMO_SEMANA = "%.2f kWh/Semana. *Baseado no tempo em horas: (%d) e no número de dias: (%d) por semana, de funcionamento informado.";
		
		public static final String FORMATO_CONSUMO_MENSAL = "%.2f kWh/Mês. *Baseado no tempo em horas: (%d) e na duração do mês corrente: (%d) dias, de funcionamento informado.";
		
		public static final String FORMATO_CONSUMO_DIARIO = "%.2f kWh/Dia. *Baseado no tempo em horas: (%d) de funcionamento informado.";
		
		//public static final String CUSTO_MEDIO_MENSAL = "Considerando a potência do Eletrodomestico {0} {1} {2} {3}V de {4,number,#.##} W e o uso contínuo por {5,number} dias, com a tarifa de energia de R${6,number,#.#####}/kWh, o custo médio mensal será de: R${7,number,#.##}";
        
		public static final String CUSTO_MEDIO_MENSAL = "Considerando a potência do Eletrodomestico %s %s %s %sV de %.2f W e o uso contínuo por %d dias, com a tarifa de energia de R$%.5f/kWh, o custo médio mensal será de: %s";
	}

}
