package com.example.deyvi.gerenciamentoderepublica.Util.validacion;

import android.util.Log;
import com.example.deyvi.gerenciamentoderepublica.entitys.Endereco;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@SuppressWarnings("deprecation")
public class EnderecoUtil {

    public static Endereco discover(String cep) {

        String urlString = "http://viacep.com.br/ws/" + cep + "/json/";
        try {
            URL url = new URL("http://viacep.com.br/ws/" + cep + "/json/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            String json = getStringFromInputStream(urlConnection.getInputStream());
            urlConnection.disconnect();
            return getEnderecoFromJSON(json);

        } catch (Exception e) {
            Log.e("Erro", "Falha ao acessar Web service", e);
        }

        return null;
    }

    private static Endereco getEnderecoFromJSON(String jsonString) {
        Endereco endereco = null;

        try {

            JSONObject jsonObject = new JSONObject(jsonString);
            endereco = new Endereco();
            endereco.setRua(jsonObject.getString("logradouro"));
            endereco.setCep(jsonObject.getString("cep"));
            endereco.setCidade(jsonObject.getString("localidade"));
            endereco.setEstado(jsonObject.getString("uf"));
            endereco.setBairro(jsonObject.getString("bairro"));


        } catch (JSONException e) {
            Log.e("Erro", "Erro no parsing do JSON", e);
        }
        return endereco;
    }

    public static String getStringFromInputStream(InputStream instream) {

        java.util.Scanner s = new java.util.Scanner(instream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
