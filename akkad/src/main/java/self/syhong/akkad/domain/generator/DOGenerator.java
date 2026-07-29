package self.syhong.akkad.domain.generator;

import java.nio.file.Paths;
import java.util.Collections;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class DOGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:postgresql://127.0.0.1:5432/akkad?currentSchema=public", "akkad", "aiouvgaiuvh234124ads")
        .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
            String typeName = metaInfo.getTypeName().toLowerCase();
            if (typeName.contains("json") || typeName.contains("jsonb")) {
                return DbColumnType.STRING;
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
            .entity("domain.do")
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
            .formatFileName("%sDO")     
            .enableTableFieldAnnotation()     
            .idType(IdType.AUTO)                
        )
        .templateEngine(new FreemarkerTemplateEngine())
        .execute();
    }
}
