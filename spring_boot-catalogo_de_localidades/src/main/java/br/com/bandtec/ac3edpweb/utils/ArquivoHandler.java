package br.com.bandtec.ac3edpweb.utils;

import br.com.bandtec.ac3edpweb.models.Cidade;
import br.com.bandtec.ac3edpweb.repositories.CidadeRepository;
import br.com.bandtec.ac3edpweb.repositories.PaisRepository;

import java.io.*;

public class ArquivoHandler {

    public static void gravaRegistro (String nomeArq, String registro) {
        BufferedWriter saida = null;
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
        try {
            saida.append(registro + "\n");
            saida.close();

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }

    public static void leArquivo(String nomeArq, CidadeRepository repositoryCidade, PaisRepository repositoryPais) {
        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        String nomeCidade,  nomePais;
        double pib;
        int tamanho, idPais, idCidade, contRegistro=0, regPais=0;

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            registro = entrada.readLine();

            while (registro != null) {
                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("00")) {
                    System.out.println("Header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 10));
                    System.out.println("Data/hora de geração do arquivo: " + registro.substring(10,29));
                    System.out.println("Versão do layout: " + registro.substring(29,31));

                } else if (tipoRegistro.equals("01")) {
                    System.out.println("\nTrailer");
                    int qtdRegistro = Integer.parseInt(registro.substring(2,12));
                    if (qtdRegistro == contRegistro) {
                        System.out.println("Quantidade de registros gravados compatível com quantidade lida");
                    }
                    else {
                        System.out.println("Quantidade de registros gravados não confere com quantidade lida");
                    }

                } else if (tipoRegistro.equals("02")) {
                    if (contRegistro == 0) {
                        System.out.println();
                        System.out.printf("%3s %-30s %3s %1s %3s\n", "ID","NOME DA CIDADE/ PAIS","PIB","T","PAIS");
                    }

                    do{
                        registro += " ";
                    } while(registro.length()<35);

                    idPais = Integer.parseInt(registro.substring(2, 5));
                    nomePais = registro.substring(5,35);

                    System.out.printf("%3d %-30s\n", idPais, nomePais);
                    contRegistro++;
                    regPais = idPais;

                } else if (tipoRegistro.equals("03")) {
                    idCidade = Integer.parseInt(registro.substring(2,5));
                    nomeCidade = registro.substring(5,35);
                    pib = Double.parseDouble(registro.substring(35,38).replace(',', '.'));
                    tamanho = Integer.parseInt(registro.substring(38,39));
                    idPais = Integer.parseInt(registro.substring(39, 42));

                    System.out.printf("%3d %-30s %2.1f %1d %3d\n", idCidade, nomeCidade, pib, tamanho, idPais);
                    boolean tam = tamanho == 1;
                    if(idPais == regPais || repositoryPais.existsById(idPais)){
                        repositoryCidade.save(new Cidade(idCidade, nomeCidade.trim(), pib, tam, repositoryPais.findById(idPais).get()));
                    }
                    contRegistro++;

                } else {
                    System.out.println("Tipo de registro inválido");
                }

                registro = entrada.readLine();
            }

            entrada.close();
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }

    }
}
