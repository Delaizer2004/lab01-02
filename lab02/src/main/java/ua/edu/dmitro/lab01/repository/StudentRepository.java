package ua.edu.dmitro.lab01.repository;

import ua.edu.dmitro.lab01.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Student> studentRowMapper = (rs, rowNum) ->
            new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age")
            );

    public List<Student> findAll() {
        return jdbcTemplate.query("SELECT * FROM students", studentRowMapper);
    }

    public Student findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM students WHERE id = ?", studentRowMapper, id);
    }

    public int save(Student student) {
        return jdbcTemplate.update("INSERT INTO students (name, age) VALUES (?, ?)",
                student.getName(), student.getAge());
    }

    public int update(Student student) {
        return jdbcTemplate.update("UPDATE students SET name = ?, age = ? WHERE id = ?",
                student.getName(), student.getAge(), student.getId());
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM students WHERE id = ?", id);
    }
}