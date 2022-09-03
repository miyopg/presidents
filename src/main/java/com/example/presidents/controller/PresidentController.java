package com.example.presidents.controller;

import com.example.presidents.model.dto.PresidentDto;
import com.example.presidents.service.president.PresidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("presidents")
@RequiredArgsConstructor
public class PresidentController {

    private final PresidentService presidentService;

    @GetMapping("all")
    public List<PresidentDto> getAll() {
        return presidentService.getAllPresidents();
    }

    @PostMapping("save")
    public PresidentDto save(@RequestBody PresidentDto presidentDto) {
        return presidentService.savePresident(presidentDto);
    }

    @PutMapping("update")
    public PresidentDto update(@RequestBody PresidentDto presidentDto) {
        return presidentService.updatePresident(presidentDto);
    }



    @PatchMapping("update")
    public PresidentDto updatePartial(@RequestBody PresidentDto presidentDto) {
        return presidentService.updatePresidentPartial(presidentDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteByIndex(@PathVariable Long id) {
        presidentService.deletePresidentById(id);
    }

    @GetMapping("find/{name}")
    public Set<PresidentDto> findPresidentByName(@PathVariable String name){
        return presidentService.findPresidentByName(name);
    }

    @GetMapping("find-by-party/{party}")
    public Set<PresidentDto> findPresidentsByPoliticalParty(@PathVariable String party){
        return presidentService.findPresidentsByPoliticalParty(party);
    }

   /* @GetMapping("all")
    public List<President> getAll() {

        return PresidentsRepository.presidentRepository;
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
