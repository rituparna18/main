package com.validapp.main.controller;

import java.util.List;

// import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.validapp.main.entity.Tests_valEntity;
import com.validapp.main.repository.TestsvalRepository;


@RestController
@RequestMapping("/report")
public class testValController {
    // private final testValService testValService;

    @Autowired
    private TestsvalRepository TestsvalRepository;

    @PostMapping("/checking")
    // @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> searchEntities(@RequestParam("test_id") Integer id,
            @RequestParam("result") Float result, @RequestParam("unit") String unit,
            @RequestParam("gender") String gender, @RequestParam("age") Integer age) {
        List<Tests_valEntity> ls;
        ls = TestsvalRepository.findByTestidAndGenderAndUnit(id, gender, unit);
        if (ls.size() == 0) {
            return ResponseEntity.ok().body("Invalid Report");
        }
        for (int i = 0; i < ls.size(); i++) {
            if ((ls.get(i).getAgefrom() <= age) && (ls.get(i).getAgeto() >= age)) {
                if (result > ls.get(i).getMaxresults()) {
                    return ResponseEntity.ok().body("High" + "(" + ls.get(i).getName() + ")");
                }
                if (result < ls.get(i).getMinresults()) {
                    return ResponseEntity.ok().body("Low" + "(" + ls.get(i).getName() + ")");
                }
                return ResponseEntity.ok().body("Normal" + "(" + ls.get(i).getName() + ")");
            }
        }

        return ResponseEntity.ok().body("Age Out Of Range");
        // return null;
    }
}
