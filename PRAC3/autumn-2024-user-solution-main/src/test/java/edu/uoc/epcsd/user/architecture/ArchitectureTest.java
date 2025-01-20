package edu.uoc.epcsd.user.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.base.DescribedPredicate.describe;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.belongTo;
import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;
import static org.glassfish.hk2.classmodel.reflect.impl.TypeProxy.adapter;
import static org.springframework.boot.validation.beanvalidation.MethodValidationExcludeFilter.byAnnotation;

import java.lang.annotation.Annotation;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.properties.CanBeAnnotated;

@AnalyzeClasses(packages = {
        "edu.uoc.epcsd.user",
})
public class ArchitectureTest {
    @ArchTest
    static final ArchRule onion_architecture_defined_by_annotations = onionArchitecture()
            .domainModels("edu.uoc.epcsd.user.domain")
            .domainServices(byAnnotation(Service.class))
            .adapter("persistence", byAnnotation(Repository.class))
            .adapter("rest", byAnnotation(RestController.class));

    @ArchTest
    static final ArchRule domain_services_name_ends_in_ServiceImpl = classes().that()
            .resideInAPackage("edu.uoc.epcsd.user.domain.service")
            .and().areAnnotatedWith(Service.class).should().haveSimpleNameEndingWith("ServiceImpl");


    private static DescribedPredicate<JavaClass> byAnnotation(Class<? extends Annotation> annotationType) {
        DescribedPredicate<CanBeAnnotated> annotatedWith = annotatedWith(annotationType);
        return belongTo(annotatedWith).as(annotatedWith.getDescription());
    }

    private static DescribedPredicate<JavaClass> byAnnotation(DescribedPredicate<? super JavaAnnotation<?>> annotationType) {
        DescribedPredicate<CanBeAnnotated> annotatedWith = annotatedWith(annotationType);
        return belongTo(annotatedWith).as(annotatedWith.getDescription());
    }
}
