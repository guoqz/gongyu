package com.guoqz.gongyu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoqz.gongyu.bean.Clean;
import com.guoqz.gongyu.dao.CleanDao;
import com.guoqz.gongyu.service.CleanService;
import com.guoqz.gongyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CleanServiceImpl implements CleanService {

    @Autowired
    private CleanDao cleanDao;

    @Override
    public Result getCleanList(int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Clean> cleanList = cleanDao.selectAllClean();

        PageInfo<Clean> pageInfo = new PageInfo<>(cleanList);

        return new Result(0, "获取清扫记录成功！", pageInfo.getTotal(), cleanList);
    }

    @Override
    public Result addClean(Clean clean) {
        int count = cleanDao.insertClean(clean);
        if (count > 0) {
            return Result.ok("添加清扫记录成功");
        } else {
            return Result.error(100, "添加清扫记录失败");
        }
    }

    @Override
    public Clean getCleanById(int id) {
        return cleanDao.selectCleanById(id);
    }

    @Override
    public Result updateClean(Clean clean) {
        int count = cleanDao.updateClean(clean);
        if (count > 0) {
            return Result.ok("清扫记录更新成功");
        } else {
            return Result.error(100, "清扫记录更新失败");
        }
    }

    @Override
    public Result deleteCleanById(int id) {
        int count = cleanDao.deleteCleanById(id);
        if (count > 0) {
            return Result.ok("删除清扫记录成功");
        } else {
            return Result.error(100, "删除清扫记录失败");
        }
    }
}
