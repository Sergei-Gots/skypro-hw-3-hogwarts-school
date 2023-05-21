package pro.sky.hogwarts.school.service;

import org.springframework.stereotype.Service;
import pro.sky.hogwarts.school.model.Faculty;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();

    private long lastFacultyId;

    public Faculty getFaculty(Long id) {
        return faculties.get(id);
    }

    public void addFaculty(Faculty faculty) {
        faculty.setId(lastFacultyId++);
        faculties.put(faculty.getId(), faculty);
    }

    public void editFaculty(Faculty faculty) {
        if(!faculties.containsKey(faculty.getId())) {
            faculty.setId(lastFacultyId++);
        }
        faculties.put(faculty.getId(), faculty);
    }

    public Faculty removeFaculty(Long id) {
        return faculties.remove(id);
    }

    public Collection<Faculty> getFacultiesWithColorEqualTo(String color) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toUnmodifiableList());
    }

    public Collection<Faculty> getAllFaculties() {
        return Collections.unmodifiableCollection(faculties.values());
    }
}