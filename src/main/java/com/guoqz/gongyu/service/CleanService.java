package com.guoqz.gongyu.service;

import com.guoqz.gongyu.bean.Clean;
import com.guoqz.gongyu.util.Result;

public interface CleanService {

    public Result getCleanList(int page, int limit);

    public Result addClean(Clean clean);

    public Clean getCleanById(int id);

    public Result updateClean(Clean clean);

    public Result deleteCleanById(int id);
}
