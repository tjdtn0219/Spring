package dev.be.moduleapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 가장 쉬운 방식
 * Component Scan이 해당 클래스를 ROOT 기점으로 삼아 그 아래 Bean들 등록
 * 따라서 해당 클래스를 dev.be바로 아래에 두어야,
 * dev.be아래에 모든 Bean 등록 가능 (다른 모듈, module-common포함)
 */

/**
 * 실제 현업에서 하는 방식
 * basePackage 설정
 */
@SpringBootApplication(
    scanBasePackages = { "dev.be.moduleapi", "dev.be.modulecommon" }
)
@EntityScan("dev.be.modulecommon.domain")   // @Entity는 @Component를 상속하지 않으므로, 별도 스캔을 위해 선언
@EnableJpaRepositories(basePackages = "dev.be.modulecommon.repository") //Repository도 마찬가지
public class ModuleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleApiApplication.class, args);
    }

}
