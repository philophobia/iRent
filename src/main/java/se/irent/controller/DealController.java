package se.irent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "交易类控制器", value = "后台系统交易类控制器")
public class DealController {
    @Resource
    private DealServiceImpl dealService = new DealServiceImpl();

    @GetMapping("/v1/backstage/deals")
    @ApiOperation(value = "获取交易信息", notes = "获取全部交易信息的列表；若有请求url有参数id则进行模糊搜索，与交易id、买卖双方id匹配")
    public List<Deal> getDeal(@ApiParam(name = "id", value = "交易或房东或租客的部分或完整id", required = true) @RequestParam(value = "id", required = false) String did){
        if (did == null)
            return dealService.findAll();
        else
            return dealService.findByIdLike(did);
    }
}
