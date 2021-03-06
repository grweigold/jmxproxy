package com.topsy.jmxproxy.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Host implements JsonSerializable {
    private static final Logger LOG = LoggerFactory.getLogger(Host.class);

    private Map<String, MBean> mbeans;

    public Host() {
        mbeans = new HashMap<String, MBean>();
    }

    public MBean addMBean(String domain, String mbeanName) {
        MBean mbean = new MBean();
        mbeans.put(mbeanName, mbean);

        return mbean;
    }

    public Set<String> getMBeans() {
        return mbeans.keySet();
    }

    public MBean getMBean(String mbean) {
        return mbeans.get(mbean);
    }

    public void serialize(JsonGenerator jgen, SerializerProvider sp) throws IOException, JsonProcessingException {
        buildJson(jgen);
    }

    public void serializeWithType(JsonGenerator jgen, SerializerProvider sp, TypeSerializer ts) throws IOException, JsonProcessingException {
        buildJson(jgen);
    }

    public void buildJson(JsonGenerator jgen) throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        for (Map.Entry<String, MBean>mbeanEntry : mbeans.entrySet()) {
            jgen.writeObjectField(mbeanEntry.getKey(), mbeanEntry.getValue());
        }
        jgen.writeEndObject();
    }
}
