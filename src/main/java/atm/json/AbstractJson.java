package atm.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public abstract class AbstractJson {


    public static void writeJson(String link, Object data) throws IOException {
        JsonFactory jfactory = new JsonFactory();
        JsonGenerator jsonGenerator = jfactory.createGenerator(new File(link), JsonEncoding.UTF8);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(jsonGenerator, data);
        }
        catch (JsonMappingException e){
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
