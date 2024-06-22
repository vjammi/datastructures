package io.dev.v2.dev.caching.caffeine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Cacheable("nameById")
    public String getName(int id) {
        return studentDAO.fetchName(id);
    }
}