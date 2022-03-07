package pdp.springboot.dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ThymeObjectParser {
    @Autowired
    Gson gson;
    public String company(Object object){
        Map<String, String> company=gson.fromJson(object.toString(),
                new TypeToken<Map<String, String>>(){}.getType());
        return company.get("name");
    }
    public String department(Object object){
        Map<String, Object> department=gson.fromJson(object.toString(), new TypeToken<Map<String, Object>>(){}.getType());
        return department.get("name")+", "+company(department.get("company"));
    }


    @SneakyThrows
    public String parse(Object object, String field)  {
        if(field.equals("department")){
            return department(object);
        }else if(field.equals("company")){
            return company(object);
        }
        return object.toString();
    }
}
