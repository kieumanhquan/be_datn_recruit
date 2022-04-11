package com.itsol.recruit.web.rest;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.entity.Rank;
import com.itsol.recruit.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class RankController {

    @Autowired
    private RankRepository rankRepository;

    @GetMapping("/ranks")
    public List<Rank> findAllRanks() {
        return rankRepository.findAll();
    }
}
