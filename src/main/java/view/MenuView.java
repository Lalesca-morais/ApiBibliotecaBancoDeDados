package view;

import model.LivroModel;
import service.LivroService;
import java.util.InputMismatchException;
import java.util.Scanner;
public class MenuView {
    private Scanner scanner;
    private LivroModel usuarioModel;
    private LivroService livroService;

    public MenuView(){
        scanner = new Scanner(System.in);
        usuarioModel = new LivroModel();
        livroService = new LivroService();
    }

    public void iniciar(){
        int opcao;
        do {
            imprimirMenu();
            opcao = selecionarOpcao();

            switch (opcao){
                case 1:
                    livroService.consultaTodosDados();
                    break;
                case 2:
                    System.out.println("Insira o ID do livro que deseja exibir");
                    int idLivro = scanner.nextInt();
                    livroService.consultaLivroPorId(idLivro);
                    break;
                case 3:
                    System.out.println("Digite o nome do novo livro: ");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o autor do livro: ");
                    String autor = scanner.nextLine();

                    System.out.println("Digite a data de lançamento do livro: ");
                    String dataDeLancamento = scanner.nextLine();

                    System.out.println("Digite o novo código do livro: ");
                    String novoCodigo = scanner.nextLine();

                    livroService.inserirLivro(nome, autor,dataDeLancamento,novoCodigo);
                    break;
                case 4:
                    System.out.println("Digite o nome do novo livro: ");
                    String nomeAlterado = scanner.nextLine();

                    System.out.println("Digite o autor do livro: ");
                    String autorAlterado = scanner.nextLine();

                    System.out.println("Digite a data de lançamento do livro: ");
                    int dataDeLancamentoAlterado = scanner.nextInt();

                    System.out.println("Digite o novo código do livro: ");
                    String codigoAlterado = scanner.nextLine();

                    livroService.alterarLivro(nomeAlterado,
                            autorAlterado,dataDeLancamentoAlterado,codigoAlterado);
                    break;
                case 5:
                    System.out.println("Digite o ID do novo livro que deseja deletar: ");
                    int idParaDeletar = scanner.nextInt();
                    livroService.deletarLivro(idParaDeletar);
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:

                    System.out.println("Opção inválida, tente novamente!");
                    break;
            }
        }while (opcao != 0);
    }

    public void imprimirMenu(){
        System.out.println("Digite uma das seguinte opções");
        System.out.println("1 - Exibir todos os livros");
        System.out.println("2 - Exibir um livro específico");
        System.out.println("3 - Cadastro de um novo livro");
        System.out.println("4 - Alteração de um livro");
        System.out.println("5 - Deleção de um livro");
        System.out.println("0 - Sair.");
    }

    public int selecionarOpcao(){
        try{
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
        return 1;
    }
}
