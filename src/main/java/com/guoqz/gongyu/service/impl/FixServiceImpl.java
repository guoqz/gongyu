package com.guoqz.gongyu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoqz.gongyu.bean.Fix;
import com.guoqz.gongyu.dao.FixDao;
import com.guoqz.gongyu.service.FixService;
import com.guoqz.gongyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixServiceImpl implements FixService {

    @Autowired
    private FixDao fixDao;

    @Override
    public Result getFixList(int page, int limit) {

        PageHelper.startPage(page, limit);
        List<Fix> fixList = fixDao.selectAllFix();

        PageInfo<Fix> pageInfo = new PageInfo<>(fixList);

        return new Result(0, "获取维修记录成功", pageInfo.getTotal(), fixList);
    }

    @Override
    public Result addFix(Fix fix) {
        int count = fixDao.insertFix(fix);
        if (count > 0) {
            return Result.ok("维修记录添加成功");
        } else {
            return Result.error(100, "维修记录添加失败");
        }
    }

    @Override
    public Fix getFixById(int id) {
        return fixDao.selectFixById(id);
    }

    @Override
    public Result updateFix(Fix fix) {
        int count = fixDao.updateFix(fix);
        if (count > 0) {
            return Result.ok("维修记录更新成功");
        } else {
            return Result.error(100, "维修记录更新失败");
        }
    }

    @Override
    public Result deleteFixById(int id) {
        int count = fixDao.deleteFixById(id);
        if (count > 0) {
            return Result.ok("维修记录删除成功");
        } else {
            return Result.error(100, "维修记录删除失败");
        }
    }
}
