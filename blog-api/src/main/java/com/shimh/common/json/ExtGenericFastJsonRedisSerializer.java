package com.shimh.common.json;

import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;

/**
 * Fastjson集成 spring-data-redis 扩展
 *
 * @author shimh
 * <p>
 * 2018年1月23日
 */
@Deprecated
public class ExtGenericFastJsonRedisSerializer extends GenericFastJsonRedisSerializer {

    private static int NO_SkipTransientFieldFEATURE;

    {
        int features = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.SkipTransientField, false);
        NO_SkipTransientFieldFEATURE = SerializerFeature.config(features, SerializerFeature.WriteClassName, true);
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {

        if (object == null) {
            return new byte[0];
        }
        try {
            return JSON.toJSONBytes(object, NO_SkipTransientFieldFEATURE);
        } catch (Exception ex) {
            throw new SerializationException("Could not serialize: " + ex.getMessage(), ex);
        }

    }

}
