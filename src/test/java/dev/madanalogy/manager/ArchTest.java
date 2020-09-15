package dev.madanalogy.manager;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("dev.madanalogy.manager");

        noClasses()
            .that()
                .resideInAnyPackage("dev.madanalogy.manager.service..")
            .or()
                .resideInAnyPackage("dev.madanalogy.manager.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..dev.madanalogy.manager.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
