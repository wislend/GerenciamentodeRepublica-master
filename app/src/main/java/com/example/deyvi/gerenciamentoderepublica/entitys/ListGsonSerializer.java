package com.example.deyvi.gerenciamentoderepublica.entitys;

import com.activeandroid.serializer.TypeSerializer;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ListGsonSerializer extends TypeSerializer {


    private final static Gson gson = new Gson();

    @Override
    public Class<?> getDeserializedType() {
        return List.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return String.class;
    }

    @Override
    public String serialize(Object data) {
        if (null == data )
            return null;

        return gson.toJson(data);

    }

    @Override
    public List<String> deserialize(Object data) {
        if (null == data)
            return null;

        List<String> stringList = new ArrayList<>();
        stringList =  gson.fromJson(data.toString(), List.class);
        return stringList;
    }
}
