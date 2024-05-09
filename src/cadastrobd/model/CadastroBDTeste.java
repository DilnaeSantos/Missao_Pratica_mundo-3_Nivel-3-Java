/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Dilnae
 */

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CadastroBDTeste {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean sair = false;
            
            while (!sair) {
                System.out.println("Selecione uma opção:");
                System.out.println("1. Incluir");
                System.out.println("2. Alterar");
                System.out.println("3. Excluir");
                System.out.println("4. Exibir pelo ID");
                System.out.println("5. Exibir todos");
                System.out.println("0. Sair");
                
                try {
                    int opcao = scanner.nextInt();
                    switch (opcao) {
                        case 1 -> incluir(scanner);
                        case 2 -> alterar(scanner);
                        case 3 -> excluir(scanner);
                        case 4 -> obter(scanner);
                        case 5 -> obterTodos(scanner);
                        case 0 -> sair = true;
                        default -> System.out.println("Opção inválida. Por favor, selecione novamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número.");
                    scanner.nextLine();
                }
            }
        }
        System.out.println("Programa finalizado.");
    }

    private static void incluir(Scanner scanner) {
        System.out.println("Selecione o tipo:");
        System.out.println("1. Pessoa");
        System.out.println("2. Pessoa Física");
        System.out.println("3. Pessoa Jurídica");

        int tipo = scanner.nextInt();
        scanner.nextLine();
        switch (tipo) {
            case 1 -> incluirPessoa(scanner);
            case 2 -> incluirPessoaFisica(scanner);
            case 3 -> incluirPessoaJuridica(scanner);
            default -> System.out.println("Tipo inválido.");
        }
    }
    
    private static void incluirPessoa(Scanner scanner) {
        System.out.println("Digite o ID_Pessoa:");
        String ID_Pessoa = scanner.nextLine();
        System.out.println("Digite o Nome:");
        String Nome = scanner.nextLine();
        System.out.println("Digite o Telefone:");
        String Telefone = scanner.nextLine();
        System.out.println("Digite o Logradouro:");
        String Logradouro = scanner.nextLine();
        System.out.println("Digite o Cidade:");
        String Cidade = scanner.nextLine();
        System.out.println("Digite o Estado:");
        String Estado = scanner.nextLine();
        System.out.println("Digite o Email:");
        String Email = scanner.nextLine();

        PessoaFisica pessoaFisica = new PessoaFisica(0, ID_Pessoa, Nome, Telefone, Logradouro, Cidade, Estado, Email);
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        pessoaFisicaDAO.incluir(pessoaFisica);
        System.out.println("Pessoa incluída com sucesso.");
    }

    private static void incluirPessoaFisica(Scanner scanner) {
        System.out.println("Digite o ID_Pessoa:");
        String ID_Pessoa = scanner.nextLine();
        System.out.println("Digite o CPF:");
        String CPF = scanner.nextLine();

        PessoaFisica pessoaFisica = new PessoaFisica(0, ID_Pessoa, CPF);
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        pessoaFisicaDAO.incluir(pessoaFisica);
        System.out.println("Pessoa física incluída com sucesso.");
    }

    private static void incluirPessoaJuridica(Scanner scanner) {
        System.out.println("Digite o ID_Pessoa:");
        String ID_Pessoa = scanner.nextLine();
        System.out.println("Digite o CNPJ:");
        String CNPJ = scanner.nextLine();
     
        PessoaJuridica pessoaJuridica = new PessoaJuridica(0, ID_Pessoa, CNPJ);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        pessoaJuridicaDAO.incluir(pessoaJuridica);
        System.out.println("Pessoa jurídica incluída com sucesso.");
    }

private static void alterar(Scanner scanner) {
    System.out.println("Selecione o tipo de entidade para alterar:");
    System.out.println("1. Pessoa Física");
    System.out.println("2. Pessoa Jurídica");
    System.out.println("3. Pessoa");

    int tipo = scanner.nextInt();
    scanner.nextLine();

    switch (tipo) {
        case 1 -> alterarPessoaFisica(scanner);
        case 2 -> alterarPessoaJuridica(scanner);
        case 3 -> alterarPessoa(scanner);
        default -> System.out.println("Opção inválida.");
    }
}

private static void alterarPessoaFisica(Scanner scanner) {
    System.out.println("Digite o ID da pessoa física que deseja alterar:");
    int id = scanner.nextInt();

    PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
    PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);

    if (pessoaFisica != null) {
        System.out.println("Pessoa física encontrada. Dados atuais:");
        System.out.println(pessoaFisica);
        
        System.out.println("Digite o novo CPF:");
        String novoCPF = scanner.nextLine();
        pessoaFisica.setCPF(novoCPF);

        pessoaFisicaDAO.alterar(pessoaFisica);
        System.out.println("Dados da pessoa física alterados com sucesso.");
    } else {
        System.out.println("Pessoa física não encontrada com o ID fornecido.");
    }
}

private static void alterarPessoaJuridica(Scanner scanner) {
    System.out.println("Digite o ID da pessoa jurídica que deseja alterar:");
    int id = scanner.nextInt();
    scanner.nextLine();

    PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
    PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);

    if (pessoaJuridica != null) {
        System.out.println("Pessoa jurídica encontrada. Dados atuais:");
        System.out.println(pessoaJuridica);

        System.out.println("Digite o novo CNPJ:");
        String novoCNPJ = scanner.nextLine();
        pessoaJuridica.setCnpj(novoCNPJ);

        pessoaJuridicaDAO.alterar(pessoaJuridica);
        System.out.println("Dados da pessoa jurídica alterados com sucesso.");
    } else {
        System.out.println("Pessoa jurídica não encontrada com o ID fornecido.");
    }
}


private static void alterarPessoa(Scanner scanner) {
    System.out.println("Digite o ID da pessoa que deseja alterar:");
    int id = scanner.nextInt();
    scanner.nextLine(); 

    PessoaDAO pessoaDAO = new PessoaDAO();
    Pessoa pessoa = pessoaDAO.getPessoa(id);

    if (pessoa != null) {
        System.out.println("Pessoa encontrada. Dados atuais:");
        System.out.println(pessoa);

        System.out.println("Digite o novo nome:");
        String novoNome = scanner.nextLine();
        pessoa.setNome(novoNome);

        System.out.println("Digite o novo telefone:");
        String novoTelefone = scanner.nextLine();
        pessoa.setTelefone(novoTelefone);

        System.out.println("Digite o novo logradouro:");
        String novoLogradouro = scanner.nextLine();
        pessoa.setLogradouro(novoLogradouro);

        System.out.println("Digite a nova cidade:");
        String novaCidade = scanner.nextLine();
        pessoa.setCidade(novaCidade);

        System.out.println("Digite o novo estado:");
        String novoEstado = scanner.nextLine();
        pessoa.setEstado(novoEstado);

        System.out.println("Digite o novo email:");
        String novoEmail = scanner.nextLine();
        pessoa.setEmail(novoEmail);

        pessoaDAO.alterar(pessoa);
        System.out.println("Dados da pessoa alterados com sucesso.");
    } else {
        System.out.println("Pessoa não encontrada com o ID fornecido.");
    }
}


private static void excluir(Scanner scanner) {
    System.out.println("Selecione o tipo de entidade para excluir:");
    System.out.println("1. Pessoa Física");
    System.out.println("2. Pessoa Jurídica");
    System.out.println("3. Pessoa");

    int tipo = scanner.nextInt();
    scanner.nextLine(); 

    switch (tipo) {
        case 1 -> excluirPessoaFisica(scanner);
        case 2 -> excluirPessoaJuridica(scanner);
        case 3 -> excluirPessoa(scanner);
        default -> System.out.println("Opção inválida.");
    }
}

 private static void excluirPessoaFisica(Scanner scanner) {
        System.out.println("Digite o ID da pessoa física que deseja excluir:");
        int id = scanner.nextInt();
        scanner.nextLine();

        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        pessoaFisicaDAO.excluir(id);

        System.out.println("Pessoa física excluída com sucesso.");
    }


private static void excluirPessoaJuridica(Scanner scanner) {
        System.out.println("Digite o ID da pessoa jurídica que deseja excluir:");
        int id = scanner.nextInt();
        scanner.nextLine();

        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        pessoaJuridicaDAO.excluir(id);

        System.out.println("Pessoa jurídica excluída com sucesso.");
    }

    private static void excluirPessoa(Scanner scanner) {
        System.out.println("Digite o ID da pessoa que deseja excluir:");
        int id = scanner.nextInt();
        scanner.nextLine();

        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.excluir(id);

        System.out.println("Pessoa excluída com sucesso.");
    }

private static void obter(Scanner scanner) {
    System.out.println("Selecione o tipo de entidade para obter:");
    System.out.println("1. Pessoa Física");
    System.out.println("2. Pessoa Jurídica");
    System.out.println("3. Pessoa");

    int tipo = scanner.nextInt();
    scanner.nextLine();

    switch (tipo) {
        case 1 -> obterPessoaFisica(scanner);
        case 2 -> obterPessoaJuridica(scanner);
        case 3 -> obterPessoa(scanner);
        default -> System.out.println("Opção inválida.");
    }
}

private static void obterPessoaFisica(Scanner scanner) {
    System.out.println("Digite o ID da pessoa física que deseja obter:");
    int id = scanner.nextInt();
    scanner.nextLine();

    PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
    PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);

    if (pessoaFisica != null) {
        System.out.println("Dados da pessoa física:");
        System.out.println("ID: " + pessoaFisica.getId());
        System.out.println("Nome: " + pessoaFisica.getNome());
        System.out.println("CPF: " + pessoaFisica.getCpf());
        System.out.println("Logradouro: " + pessoaFisica.getLogradouro());
        System.out.println("Cidade: " + pessoaFisica.getCidade());
        System.out.println("Estado: " + pessoaFisica.getEstado());
        System.out.println("Telefone: " + pessoaFisica.getTelefone());
        System.out.println("Email: " + pessoaFisica.getEmail());
    } else {
        System.out.println("Pessoa física não encontrada com o ID fornecido.");
    }
}

private static void obterPessoaJuridica(Scanner scanner) {
    System.out.println("Digite o ID da pessoa jurídica que deseja obter:");
    int id = scanner.nextInt();
    scanner.nextLine(); 
    
    PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
    PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);

    if (pessoaJuridica != null) {
        System.out.println("Dados da pessoa jurídica:");
        System.out.println("ID: " + pessoaJuridica.getId());
        System.out.println("Nome: " + pessoaJuridica.getNome());
        System.out.println("CNPJ: " + pessoaJuridica.getCnpj());
        System.out.println("Logradouro: " + pessoaJuridica.getLogradouro());
        System.out.println("Cidade: " + pessoaJuridica.getCidade());
        System.out.println("Estado: " + pessoaJuridica.getEstado());
        System.out.println("Telefone: " + pessoaJuridica.getTelefone());
        System.out.println("Email: " + pessoaJuridica.getEmail());
    } else {
        System.out.println("Pessoa jurídica não encontrada com o ID fornecido.");
    }
}

private static void obterPessoa(Scanner scanner) {
    System.out.println("Digite o ID da pessoa que deseja obter:");
    int id = scanner.nextInt();
    scanner.nextLine();

    PessoaDAO pessoaDAO = new PessoaDAO();
    Pessoa pessoa = pessoaDAO.getPessoa(id);

    if (pessoa != null) {
        System.out.println("Dados da pessoa:");
        System.out.println("ID: " + pessoa.getId());
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Logradouro: " + pessoa.getLogradouro());
        System.out.println("Cidade: " + pessoa.getCidade());
        System.out.println("Estado: " + pessoa.getEstado());
        System.out.println("Telefone: " + pessoa.getTelefone());
        System.out.println("Email: " + pessoa.getEmail());
        switch (pessoa) {
            case PessoaFisica pessoaFisica -> System.out.println("CPF: " + pessoaFisica.getCpf());
            case PessoaJuridica pessoaJuridica -> System.out.println("CNPJ: " + pessoaJuridica.getCnpj());
            default -> {
            }
        }
    } else {
        System.out.println("Pessoa não encontrada com o ID fornecido.");
    }
}


private static void obterTodos(Scanner scanner) {
    System.out.println("Selecione o tipo de entidade para obter todos:");
    System.out.println("1. Pessoas Físicas");
    System.out.println("2. Pessoas Jurídicas");
    System.out.println("3. Pessoas");

    int tipo = scanner.nextInt();
    scanner.nextLine();

    switch (tipo) {
        case 1 -> obterTodasPessoasFisicas();
        case 2 -> obterTodasPessoasJuridicas();
        case 3 -> obterTodasPessoas();
        default -> System.out.println("Opção inválida.");
    }
}

private static void obterTodasPessoasFisicas() {
    PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
    List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();

    if (!pessoasFisicas.isEmpty()) {
        System.out.println("Listagem de todas as pessoas físicas:");
        for (PessoaFisica pessoa : pessoasFisicas) {
            System.out.println("ID: " + pessoa.getId());
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("CPF: " + pessoa.getCpf());
            System.out.println("Logradouro: " + pessoa.getLogradouro());
            System.out.println("Cidade: " + pessoa.getCidade());
            System.out.println("Estado: " + pessoa.getEstado());
            System.out.println("Telefone: " + pessoa.getTelefone());
            System.out.println("Email: " + pessoa.getEmail());
            System.out.println("--------------------");
        }
    } else {
        System.out.println("Não há pessoas físicas cadastradas.");
    }
}

private static void obterTodasPessoasJuridicas() {
    PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
    List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();

    if (!pessoasJuridicas.isEmpty()) {
        System.out.println("Listagem de todas as pessoas jurídicas:");
        for (PessoaJuridica pessoa : pessoasJuridicas) {
            System.out.println("ID: " + pessoa.getId());
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("CNPJ: " + pessoa.getCnpj());
            System.out.println("Logradouro: " + pessoa.getLogradouro());
            System.out.println("Cidade: " + pessoa.getCidade());
            System.out.println("Estado: " + pessoa.getEstado());
            System.out.println("Telefone: " + pessoa.getTelefone());
            System.out.println("Email: " + pessoa.getEmail());
            System.out.println("--------------------");
        }
    } else {
        System.out.println("Não há pessoas jurídicas cadastradas.");
    }
}

private static void obterTodasPessoas() {
    PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
    PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

    List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
    List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();

    if (!pessoasFisicas.isEmpty() || !pessoasJuridicas.isEmpty()) {
        System.out.println("Listagem de todas as pessoas:");

        for (PessoaFisica pessoa : pessoasFisicas) {
            System.out.println("ID: " + pessoa.getId());
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("CPF: " + pessoa.getCpf());
            System.out.println("Logradouro: " + pessoa.getLogradouro());
            System.out.println("Cidade: " + pessoa.getCidade());
            System.out.println("Estado: " + pessoa.getEstado());
            System.out.println("Telefone: " + pessoa.getTelefone());
            System.out.println("Email: " + pessoa.getEmail());
            System.out.println("--------------------");
        }

        for (PessoaJuridica pessoa : pessoasJuridicas) {
            System.out.println("ID: " + pessoa.getId());
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("CNPJ: " + pessoa.getCnpj());
            System.out.println("Logradouro: " + pessoa.getLogradouro());
            System.out.println("Cidade: " + pessoa.getCidade());
            System.out.println("Estado: " + pessoa.getEstado());
            System.out.println("Telefone: " + pessoa.getTelefone());
            System.out.println("Email: " + pessoa.getEmail());
            System.out.println("--------------------");
        }
    } else {
        System.out.println("Não há pessoas cadastradas.");
    }
}

}