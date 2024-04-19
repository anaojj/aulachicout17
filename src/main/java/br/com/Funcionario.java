package br.com;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nome;
    private double salario;
    
    public Funcionario(int id, String nome, double salario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getSalario() {
        return salario;
    }
    
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Salário: " + salario;
    }
}

class GerenciadorFuncionarios {
    private String nomeArquivo;
    
    public GerenciadorFuncionarios(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
    
    public void adicionarFuncionario(Funcionario funcionario) {
        List<Funcionario> funcionarios = listarFuncionarios();
        funcionarios.add(funcionario);
        salvarFuncionarios(funcionarios);
    }
    
    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            funcionarios = (List<Funcionario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Arquivo não existe ou está vazio
        }
        return funcionarios;
    }
    
    public void salvarFuncionarios(List<Funcionario> funcionarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(funcionarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void atualizarFuncionario(Funcionario funcionarioAtualizado) {
        List<Funcionario> funcionarios = listarFuncionarios();
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getId() == funcionarioAtualizado.getId()) {
                funcionarios.set(i, funcionarioAtualizado);
                salvarFuncionarios(funcionarios);
                return;
            }
        }
        System.out.println("Funcionário não encontrado.");
    }
    
    public void excluirFuncionario(int id) {
        List<Funcionario> funcionarios = listarFuncionarios();
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getId() == id) {
                funcionarios.remove(i);
                salvarFuncionarios(funcionarios);
                return;
            }
        }
        System.out.println("Funcionário não encontrado.");
    }
}