package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.IllegalArgumentException;

public class ContaTest {
    
    /**
     * Número que identifica unicamente uma conta em uma determinada agência,
     * devendo estar no formato 99999-9. Se o número não estiver no formato
     * indicado, o valor não pode ser armazenado e uma exceção deve ser lançada
     * (R01). O número da agência tem um dígito verificador como no CPF, mas
     * isto é outro requisito não definido aqui.
     */
    @Test
    void testR01SetIdValorEsperadoValidoLimiteSuperior(){
        final long esperado = 99999;
        final Conta conta = new Conta();
        conta.setId(esperado);
        final long obtido = conta.getId();
        assertEquals(esperado, obtido);
    }
    @Test
    void testR01SetIdValorEsperadoValidoLimiteInferior(){
        final long esperado = 0;
        final Conta conta = new Conta();
        conta.setId(esperado);
        final long obtido = conta.getId();
        assertEquals(esperado, obtido);
    }
    @Test
    void testR01SetIdValorEsperadoInvalidoLimiteSuperior(){
        final long esperado = 100000;
        final Conta conta = new Conta();
        assertThrows( IllegalArgumentException.class , () -> conta.setId(esperado));
    }
    @Test
    void testR01SetIdValorEsperadoInvalidoLimiteInferior(){
        final long esperado = -1;
        final Conta conta = new Conta();
        assertThrows( IllegalArgumentException.class , () -> conta.setId(esperado));
    }
    /**
     * Contas devem ser instanciadas como "Conta Corrente" e não como
     * "Poupança". (R02) O valor padrão para atributos boolean é false, assim
     * não precisamos escrever código adicional para definir "poupanca" como
     * false. Mas é preciso escrever o teste para verificar tal situação. Com
     * isto, buscamos detectar se uma alteração no código fizer com que este
     * requisito deixe de ser atendido.
     */
    @Test
    void testR02IsPoupancaByDefault(){
        final Conta conta = new Conta();
        assertEquals(conta.isPoupanca(),false);
    }
    /**
     * Limite da conta: valor que o cliente pode utilizar além do {@link #saldo}
     * disponível. Somente contas especiais podem ter limite, ou seja, o limite
     * de contas "não especiais" não pode ser maior que zero (R03).
     */
    @Test
    void testR03SetLimitePositivoContaEspecial(){
        final Conta conta = new Conta();
        conta.setEspecial(true);
        final double esperado = 0.5;
        conta.setLimite(esperado);
        final double obtido = conta.getLimite();
        assertEquals(esperado, obtido);
    }
    @Test
    void testR03SetLimitePositivoContaNaoEspecial(){
        final Conta conta = new Conta();
        conta.setEspecial(false);
        final double esperado = 0.5;
        assertThrows(IllegalStateException.class, () -> conta.setLimite(esperado));
    }
    @Test
    void testR03SetLimiteNuloContaNaoEspecial(){
        final Conta conta = new Conta();
        conta.setEspecial(false);
        final double esperado = 0.0;
        conta.setLimite(esperado);
        final double obtido = conta.getLimite();
        assertEquals(esperado, obtido);
    }
    /**
     * Histórico de movimentações da conta. Deve ser inicializado com uma lista
     * vazia. Sem isto, ao tentar utilizar a lista, dará o erro
     * NullPointerException. Um teste deve verificar se, após instanciar uma
     * conta usando qualquer um dos construtores, a lista de movimentações não é
     * nula, chamando o método {@link #getMovimentacoes()}. (R04)
     */
    @Test
    void testR04GetDefaultNullPointerHistoricoManigestacao(){
        final Conta conta = new Conta();
        assertNotEquals(conta.getMovimentacoes(), null);
        
        final Conta conta2 = new Conta(new Agencia(), true, 0);
        assertNotEquals(conta2.getMovimentacoes(), null);
        
        
        
    }
    
}
