package br.com.alura.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import static br.com.alura.AdopetConsoleApplication.dispararGet;
import static br.com.alura.AdopetConsoleApplication.dispararPost;

public class AbrigoService {

    public void ListarAbrigos() throws Exception, InterruptedException{

        HttpClient client = HttpClient.newHttpClient();
        // URL da API
        String uri = "http://localhost:8080/abrigos";
        HttpResponse<String> response = dispararGet(client, uri);
        String responseBody = response.body();
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray();
        System.out.println("Abrigos cadastrados:");

        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject();
            long id = jsonObject.get("id").getAsLong();
            String nome = jsonObject.get("nome").getAsString();
            System.out.println(id +" - " +nome);
        }

    }

    public void CadastrarAbrigo() throws IOException, InterruptedException {

        System.out.println("Digite o nome do abrigo:");
        String nome = new Scanner(System.in).nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = new Scanner(System.in).nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = new Scanner(System.in).nextLine();

        JsonObject json = new JsonObject();
        json.addProperty("nome", nome);
        json.addProperty("telefone", telefone);
        json.addProperty("email", email);

        HttpClient client = HttpClient.newHttpClient();
        String uri = "http://localhost:8080/abrigos";

        HttpResponse<String> response = dispararPost(client, uri, json);

        int statusCode = response.statusCode();
        String responseBody = response.body();
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }

    }

    private static HttpResponse<String> dispararGet(HttpClient client, String uri) throws  IOException, InterruptedException{

        //Conexão com a API executando o mét.odo GET restornando um HTTPRESPONSE<STRING>
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());

    }

    private static HttpResponse<String> dispararPost(HttpClient client, String uri, JsonObject json) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .method("POST", HttpRequest.BodyPublishers.ofString(json.toString()))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}