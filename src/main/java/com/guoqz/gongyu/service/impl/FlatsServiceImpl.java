package com.guoqz.gongyu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoqz.gongyu.bean.Flats;
import com.guoqz.gongyu.dao.FlatsDao;
import com.guoqz.gongyu.service.FlatsService;
import com.guoqz.gongyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlatsServiceImpl implements FlatsService {

    @Autowired
    private FlatsDao flatsDao;

    @Override
    public List<Flats> getAllFlats() {
        return flatsDao.selectAllFlats();
    }

    @Override
    public Result getFlatsList(int page, int limit) {
        PageHelper.startPage(page, limit);

//        List<Flats> flatsList = flatsDao.selectAllFlats();
        List<Flats> flatsList = flatsDao.selectAllFlatsIncludePerson();

        PageInfo<Flats> pageInfo = new PageInfo<>(flatsList);

        return new Result(0, "获取公寓列表成功！", pageInfo.getTotal(), flatsList);

    }

    @Override
    public Result isFlatsExists(String name) {
        Flats flats = flatsDao.selectFlatsByName(name);

        if (flats == null) {
            return Result.ok("公寓名称可以使用");
        } else {
            return Result.error(100, "公寓名称已存在");
        }
    }

    @Override
    public Result addFlats(Flats flats) {
        int count = flatsDao.insertFlats(flats);
        if (count > 0) {
            return Result.ok("添加公寓成功");
        } else {
            return Result.error(100, "添加公寓失败");
        }
    }

    @Override
    public Flats getFlatsById(int id) {
        return flatsDao.selectFlatsById(id);
    }

    @Override
    public Result updateFlats(Flats flats) {
        int count = flatsDao.updateFlats(flats);
        if (count > 0) {
            return Result.ok("更新公寓成功");
        } else {
            return Result.error(100, "更新公寓失败");
        }
    }

    @Override
    public Result deleteFlatsById(int id) {
        int count = flatsDao.deleteFlatsById(id);
        if (count > 0) {
            return Result.ok("删除公寓成功");
        } else {
            return Result.error(100, "删除公寓失败");
        }
    }
}
