package com.example.demoTP7;

import com.example.demoTP7.entity.Student;
import com.example.demoTP7.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControllerTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Order(1)
    void shouldSaveStudent() {
        System.out.println("=== Test 1: Sauvegarde d'un étudiant ===");
        
        Student student = new Student();
        student.setName("Charlie");
        student.setAddress("Algeria");
        
        Student savedStudent = studentRepository.save(student);
        
        assertThat(savedStudent.getId()).isNotNull();
        assertThat(studentRepository.count()).isEqualTo(1);
        
        System.out.println("Étudiant sauvegardé: " + savedStudent);
    }

    @Test
    @Order(2)
    void shouldFindAllStudents() {
        System.out.println("=== Test 2: Récupération de tous les étudiants ===");
        
        List<Student> students = studentRepository.findAll();
        
        assertThat(students).hasSize(1);
        assertThat(students.get(0).getName()).isEqualTo("Charlie");
        assertThat(students.get(0).getAddress()).isEqualTo("Algeria");
        
        System.out.println("Étudiants trouvés: " + students.size());
        students.forEach(System.out::println);
    }

    @Test
    @Order(3)
    void shouldSaveAndRetrieveStudent() {
        System.out.println("=== Test 3: Sauvegarde et récupération spécifique ===");
        
        Student student = new Student("Mohamed", "Algeria");
        Student savedStudent = studentRepository.save(student);
        
        Student foundStudent = studentRepository.findById(savedStudent.getId()).orElse(null);
        
        assertThat(foundStudent).isNotNull();
        assertThat(foundStudent.getName()).isEqualTo("Mohamed");
        assertThat(foundStudent.getAddress()).isEqualTo("Algeria");
        
        System.out.println("Étudiant sauvegardé: " + savedStudent);
        System.out.println("Étudiant récupéré: " + foundStudent);
    }
    
    @AfterEach
    void cleanup() {
        // Cette méthode s'exécute après chaque test
        System.out.println("Nettoyage après test...");
    }
}