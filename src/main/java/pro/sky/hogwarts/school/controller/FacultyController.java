package pro.sky.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.hogwarts.school.entity.Faculty;
import pro.sky.hogwarts.school.entity.Student;
import pro.sky.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
@Tag(name="faculties", description="Endpoints to operate upon faculties")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {

        return ResponseEntity.ok(facultyService.addFaculty(faculty));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        return facultyService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/students")
    public Collection<Student> getStudentsByFacultyId(@PathVariable long id) {
        return facultyService.findStudentsByFacultyId(id);

    }

    @GetMapping("/all")
    public Collection<Faculty> getAllFaculties() {
        return facultyService.findAll();
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {

        return facultyService.editFaculty(faculty)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        return facultyService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(params ="color")
    public Collection<Faculty> getFacultiesByColor(@RequestParam String color) {
        return facultyService.findByColor(color);
    }

    @GetMapping(params="color_or_name")
    public Collection<Faculty> getFacultiesByColorOrNameIgnoreCase(
            @RequestParam (name = "color_or_name") String colorOrName) {
        return facultyService.findByColorIgnoreCaseOrNameIgnoreCase(colorOrName);
    }
}
