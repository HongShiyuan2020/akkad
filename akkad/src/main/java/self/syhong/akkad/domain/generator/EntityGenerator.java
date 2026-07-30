package self.syhong.akkad.domain.generator;

import java.nio.file.Paths;
import java.util.Collections;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;

public class EntityGenerator {

    private static final IColumnType OFFSET_DATE_TIME = new IColumnType() {
        @Override
        public String getType() {
            return "OffsetDateTime"; // 生成实体类中的字段类型名
        }

        @Override
        public String getPkg() {
            return "java.time.OffsetDateTime"; // 自动在实体类顶部 import 的包路径
        }
    };

    public static void main(String[] args) {
        FastAutoGenerator.create(
            "jdbc:postgresql://127.0.0.1:5432/akkad?currentSchema=public", 
            System.getenv("TEST_DB_USER_NAME"),
            System.getenv("TEST_DB_PASSWORD") 
        )
        .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
            String typeName = metaInfo.getTypeName().toLowerCase();
            if (typeName.contains("json") || typeName.contains("jsonb")) {
                return DbColumnType.STRING;
            } else if (typeName.contains("timestamptz") || typeName.contains("timestamp with time zone")) {
                return OFFSET_DATE_TIME;
            }
            return typeRegistry.getColumnType(metaInfo);
        }))
        .globalConfig(builder -> builder
            .author("syhong")
            .enableSwagger()
            .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
            .commentDate("yyyy-MM-dd")
        )
        .packageConfig(builder -> builder
            .parent("self.syhong.akkad")
            .entity("domain.entity")
            .mapper("domain.mapper")
            .xml("mapper.xml")
            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/mapper/auto"))
        )
        .strategyConfig(builder -> builder.addInclude(
                "akd_user", 
                "akd_role", 
                "akd_permission",
                "akd_user_role",
                "akd_role_permission"
            ) 
            .addTablePrefix("akd_")   

            .entityBuilder()
            .enableLombok(
                new ClassAnnotationAttributes("@Data", "lombok.Data"),
                new ClassAnnotationAttributes("@Builder", "lombok.Builder"),
                new ClassAnnotationAttributes("@NoArgsConstructor", "lombok.NoArgsConstructor"),
                new ClassAnnotationAttributes("@AllArgsConstructor", "lombok.AllArgsConstructor"),
                new ClassAnnotationAttributes("@ToString", "lombok.ToString")
            )
            .enableFileOverride()
            .formatFileName("%sEntity")     
            .enableTableFieldAnnotation()     
            .idType(IdType.AUTO)        
            
            .mapperBuilder()
        )
        .templateEngine(new FreemarkerTemplateEngine())
        .execute();
    }
}
