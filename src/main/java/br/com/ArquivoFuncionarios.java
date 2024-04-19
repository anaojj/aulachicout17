package br.com;

import java.util.List;

public class ArquivoFuncionarios {
    public static void main(String[] args) {
        GerenciadorFuncionarios gerenciador = new GerenciadorFuncionarios("funcionarios.dat");
        
        // Adicionar funcionários
        gerenciador.adicionarFuncionario(new Funcionario(1, "João", 2500.0));
        gerenciador.adicionarFuncionario(new Funcionario(2, "Maria", 3000.0));
        
        // Listar funcionários
        List<Funcionario> funcionarios = gerenciador.listarFuncionarios();
        System.out.println("Funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
        
        // Atualizar funcionário
        Funcionario funcionarioAtualizado = new Funcionario(2, "Maria Silva", 3200.0);
        gerenciador.atualizarFuncionario(funcionarioAtualizado);
        
        // Excluir funcionário
        gerenciador.excluirFuncionario(1);
        
        // Listar funcionários após atualização e exclusão
        funcionarios = gerenciador.listarFuncionarios();
        System.out.println("\nFuncionários após atualização e exclusão:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }
}