package com.saucesubfresh.lineage.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * LocalDateTime 默认序列化格式
 * <p>
 * LocalDateTime 现在全局时间格式是yyyy-MM-dd HH:mm:ss
 * 配合@JsonFormat注解使用，在特定的字段属性添加@JsonFormat注解
 *
 * @author lijunping
 */
public class JacksonCustomizerConfiguration {

  @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
  private String pattern;

  /**
   * Date类型序列化时进行格式化
   */
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer dateMapperBuilder() {
    return builder -> {
      final TimeZone timeZone = TimeZone.getTimeZone("UTC");
      final SimpleDateFormat df = new SimpleDateFormat(pattern);
      df.setTimeZone(timeZone);
      builder.failOnEmptyBeans(false)
        .failOnUnknownProperties(false)
        .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .dateFormat(df);
    };
  }

  @Bean
  public LocalDateTimeSerializer localDateTimeSerializer() {
    return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
  }

  @Bean
  public LocalDateTimeDeserializer localDateTimeDeserializer() {
    return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern));
  }

  /**
   * LocalDateTime 自定义序列化格式
   * LocalDateTime 自定义反序列化格式
   */
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer localDateTimeMapperBuilderCustomizer(LocalDateTimeSerializer localDateTimeSerializer,
                                                                                    LocalDateTimeDeserializer localDateTimeDeserializer) {
    return builder -> {
      builder.serializerByType(LocalDateTime.class, localDateTimeSerializer);
      builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer);
    };
  }

  /**
   * 由于前端 JS Long 类型精度损失问题，将 Long 序列化为 String
   */
  @Bean
  public Jackson2ObjectMapperBuilderCustomizer longTypeToStringMapperBuilderCustomizer() {
    return builder -> {
      builder.serializerByType(Long.TYPE, ToStringSerializer.instance);
      builder.serializerByType(Long.class, ToStringSerializer.instance);
    };
  }

}
