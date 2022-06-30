import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Poetry ozymandias = new Poetry("Emily Bronte", "Faith and Despondency");
        System.out.println(ozymandias.getContent());


    }
}

class Poetry {
    private StringBuffer content;
    Poetry() throws IOException {
        URL url = new URL("https://poetrydb.org/author");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        getPoem(con);
    }

    Poetry(String author) throws IOException {
        URL url = new URL("https://poetrydb.org/author/"+author+"/title.text");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        getPoem(con);
    }

    Poetry(String author, String title) throws IOException {
        URL url = new URL("https://poetrydb.org/title/"+title+"/author,lines.text");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        getPoem(con);
    }

    void getPoem(HttpURLConnection con) throws IOException {
        con.setRequestMethod("GET");
        con.setConnectTimeout(2000);
        con.setReadTimeout(2000);

        int status = con.getResponseCode();

        Reader streamReader;

        if (status > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            streamReader = new InputStreamReader(con.getInputStream());
        }

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuffer content = new StringBuffer();


        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine).append("\n");
        }
        in.close();
        con.disconnect();
        this.content = content;
    }

    public StringBuffer getContent() {
        return content;
    }

    public String getPoemFromContent() {
        return " ";
    }


}
