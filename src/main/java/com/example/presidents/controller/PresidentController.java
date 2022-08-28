package com.example.presidents.controller;

import com.example.presidents.model.entity.President;
import com.example.presidents.repository.PresidentsRepository;
import com.example.presidents.service.president.PresidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("presidents")
@RequiredArgsConstructor
public class PresidentController {

    private final PresidentService presidentService;

    @GetMapping("all")
    public List<President> getAll() {
        return presidentService.getAllPresidents();
    }

    @PostMapping("save")
    public President save(@RequestBody President president) {
        return presidentService.savePresident(president);
    }


   /* @GetMapping("all")
    public List<President> getAll() {

        return PresidentsRepository.presidentRepository;
    }




    @PutMapping("update/{id}")
    public String update(@PathVariable("id") int index,
                         @RequestBody President president) {
        PresidentsRepository.presidentRepository.set(index, president);
        return "updated!";
    }

    @PutMapping("update")
    public String updateWithBodyOnly(@RequestBody President president) {
        if (PresidentsRepository.presidentRepository.size()  <= president.getId()) {
            president.setId(Integer.valueOf(PresidentsRepository.presidentRepository.size()).longValue());
            PresidentsRepository.presidentRepository.add(president);
        } else {

            PresidentsRepository.presidentRepository.set((president.getId().intValue()), president);
        }
        return "updated!";
    }

    @PatchMapping("partial-update")
    public String updatePartial(@RequestBody President president) {
        President p = PresidentsRepository.presidentRepository.get(Math.toIntExact(president.getId()));
        if (president.getName() != null) {
            p.setName(president.getName());
        }
        if (president.getSurname() != null) {
            p.setSurname(president.getSurname());
        }
        if (president.getTermFrom() != null) {
            p.setTermFrom(president.getTermFrom());
        }
        if (president.getTermTo() != null) {
            p.setTermTo(president.getTermTo());
        }
        if (president.getPoliticalParty() != null) {
            p.setPoliticalParty(president.getPoliticalParty());
        }
        return "Updated";
    }
    @DeleteMapping("delete/{id}")
    public String deleteByIndex(@PathVariable int id) {
        PresidentsRepository.presidentRepository.remove(id);
        return "deleted!";
    }*/

}
