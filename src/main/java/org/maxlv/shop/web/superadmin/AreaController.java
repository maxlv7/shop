package org.maxlv.shop.web.superadmin;


import org.maxlv.shop.entity.Area;
import org.maxlv.shop.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class AreaController {

    private Logger logger = LoggerFactory.getLogger(AreaController.class);
    private AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @RequestMapping(value = "/arealist",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> test() {
        logger.info("=====start=====");
        long startTime = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        List<Area> list = areaService.getAreaList();
        map.put("size", list.size());
        map.put("rows",list);
        logger.error("test error~");
        logger.info("=====end=====");
        logger.info("use time[{}]",System.currentTimeMillis()-startTime);
        return map;
    }

}


