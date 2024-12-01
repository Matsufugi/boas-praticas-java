package br.com.alura;

import br.com.alura.services.AbrigoService;
import br.com.alura.services.PetService;

import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {

        AbrigoService service = new AbrigoService();
        PetService petService = new PetService();

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
                System.out.println("1 -> Listar abrigos cadastrados");
                System.out.println("2 -> Cadastrar novo abrigo");
                System.out.println("3 -> Listar pets do abrigo");
                System.out.println("4 -> Importar pets do abrigo");
                System.out.println("5 -> Sair");

                String textoDigitado = new Scanner(System.in).nextLine();
                opcaoEscolhida = Integer.parseInt(textoDigitado);

                if (opcaoEscolhida == 1) {
                    service.ListarAbrigos();
                } else if (opcaoEscolhida == 2) {
                    service.CadastrarAbrigo();
                } else if (opcaoEscolhida == 3) {
                    petService.ListarPets();
                } else if (opcaoEscolhida == 4) {
                    petService.ImportarPets();
                } else if (opcaoEscolhida == 5) {
                    break;
                } else {
                    System.out.println("NÚMERO INVÁLIDO!");
                    opcaoEscolhida = 0;
                }
            }

            System.out.println("Finalizando o programa...");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
