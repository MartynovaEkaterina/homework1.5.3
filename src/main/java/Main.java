import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "data.json";
        String json = readString(fileName);
        List<Employee> list = jsonToList(json);
        printList(list);
    }

    public static String readString(String fileName) {
        String json = new String();
        JSONParser parser = new JSONParser();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Object obj = parser.parse(reader);
            JSONArray jsonObject = (JSONArray) obj;
            json = jsonObject.toJSONString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return json;
    }

    public static List<Employee> jsonToList(String json) {
        List<Employee> staff = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(json);
            JSONArray jsonArray = (JSONArray) obj;
            for (Object jb : jsonArray) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Employee employee = gson.fromJson(jb.toString(), Employee.class);
                staff.add(employee);
            }

        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }
        return staff;
    }

    public static void printList(List<Employee> list) {
        for (Employee s : list) {
            System.out.println(s.toString());
        }
    }
}
