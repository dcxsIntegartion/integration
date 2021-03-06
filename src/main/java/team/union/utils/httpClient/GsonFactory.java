package team.union.utils.httpClient;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
/**
 * @ClassName: GsonFactory 
 * @Description:
 * @author zhubin
 * @date Apr 21, 2016 3:05:41 PM 
 */
public class GsonFactory {
	public static Gson getGson() {
		JsonSerializer<Date> serializer = new JsonSerializer<Date>() {
			public JsonElement serialize(Date src, Type typeOfSrc,
					JsonSerializationContext context) {
				return src == null ? null : new JsonPrimitive(src.getTime());
			}
		};

		JsonDeserializer<Date> deserializer = new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type type,
					JsonDeserializationContext ctx) throws JsonParseException {
				if(json.getAsString().contains("-")){
					return DateConverter.parse(json.getAsJsonPrimitive().getAsString(), DateConverter.YYYY_MM_DD);
				}else {
					return new Date(json.getAsJsonPrimitive().getAsLong());
				}
			}
		};
		
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, deserializer)
		.registerTypeAdapter(Date.class, serializer);
		Gson gson = builder.create();
		return gson;
	}

}




