package ar.edu.unrn.userservice;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class ArchitectureTest {

    private static final String BASE_PACKAGE = "ar.edu.unrn.userservice";

    /*private JavaClasses classes = new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages(BASE_PACKAGE);

    // Regla para verificar la capa de acceso a datos
    @Test
    public void dataAccessLayerRuleTest() {
        layeredArchitecture()
                .consideringAllDependencies()
                .layer("Controller").definedBy("..controller..")
                .layer("data").definedBy("..data..")
                .layer("domain").definedBy("..domain..")
                .layer("model").definedBy("..model..")

                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("domain").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("data").mayOnlyBeAccessedByLayers("domain")
                .whereLayer("model").mayOnlyBeAccessedByLayers("domain", "data");

        // Agregamos una verificación adicional para asegurar que la capa de controlador no acceda
        // directamente a la capa de datos ni al modelo compartido
        ArchRule controllerLayerRule = noClasses().that().resideInAPackage("..controller..")
                .should().dependOnClassesThat().resideInAnyPackage("..data..", "..model..");
        controllerLayerRule.check(classes);
    }*/
}
