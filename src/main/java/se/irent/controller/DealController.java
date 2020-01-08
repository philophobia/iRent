package se.irent.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.irent.entity.Deal;
import se.irent.service.DealServiceImpl;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
public class DealController {
    @Resource
    private DealServiceImpl dealService = new DealServiceImpl();

    @GetMapping("/v1/backstage/deals")
    public List<Deal> getDeal(@RequestParam(value = "id", required = false) String did){
        if (did == null)
            return dealService.findAll();
        else
            return dealService.findByIdLike(did);
    }
}
